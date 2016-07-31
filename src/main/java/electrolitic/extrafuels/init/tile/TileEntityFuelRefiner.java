package electrolitic.extrafuels.init.tile;

import electrolitic.extrafuels.handlers.itemhandlers.FuelRefinerItemStackHandler;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Colin on 7/17/2016.
 */
public class TileEntityFuelRefiner extends TileEntity implements ITickable {


    private int progress;
    private int processTime;

    private ItemStack[] contents = new ItemStack[2];
    private static final HashMap<Item, Item> validItemsMap = new HashMap<Item, Item>();

    public final FuelRefinerItemStackHandler fuelRefinerItemStackHandler = new FuelRefinerItemStackHandler(contents);
    public final ItemStackHandler itemStackHandler = new ItemStackHandler(contents);
    public TileEntityFuelRefiner()
    {
        processTime = 120;
        progress = 0;
    }

    @Override
    public void update() {
        if(worldObj.isRemote)
            return;
        if(itemStackHandler.getStackInSlot(0) == null || itemStackHandler.getStackInSlot(0).stackSize <= 0) {
            progress = 0;
            return;
        }
        if(!isValidInput(itemStackHandler.getStackInSlot(0).getItem()))
            return;
        if(itemStackHandler.getStackInSlot(1) == null){

        }
        else
        {
            if((itemStackHandler.getStackInSlot(1).stackSize >= 64 ||
                    validItemsMap.get(itemStackHandler.getStackInSlot(0).getItem()) != itemStackHandler.getStackInSlot(1).getItem()))
                return;
        }

        //Now we know it's safe to start processing
        progress++;
        if(progress == processTime)
        {
            craftItem();
            progress = 0;
        }
    }


    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        NBTTagList nbttaglist = compound.getTagList("items", 10);
        contents = new ItemStack[contents.length];

        for(int i = 0; i < nbttaglist.tagCount(); i++)
        {
            NBTTagCompound tempCompound = nbttaglist.getCompoundTagAt(i);
            int tempSlot = (tempCompound.getByte("slot"));
            itemStackHandler.setStackInSlot(tempSlot, ItemStack.loadItemStackFromNBT(tempCompound));
        }
        progress = compound.getInteger("progress");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound = super.writeToNBT(compound);
        compound.setInteger("progress", progress);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < itemStackHandler.getSlots(); i++)
        {
            if (this.itemStackHandler.getStackInSlot(i) != null)
            {
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                nbttagcompound.setByte("slot", (byte)i);
                itemStackHandler.getStackInSlot(i).writeToNBT(nbttagcompound);
                nbttaglist.appendTag(nbttagcompound);
            }
        }

        compound.setTag("items", nbttaglist);

        return compound;
    }

    public int getProgress()
    {
        return progress;
    }

    public int getProcessTime()
    {
        return processTime;
    }

    public static boolean isValidInput(Item item)
    {
        Iterator iterator = validItemsMap.entrySet().iterator();
        while(iterator.hasNext())
        {
            Map.Entry pair = (Map.Entry) iterator.next();
            if(pair.getValue() == validItemsMap.get(item))
                return true;
        }
        return false;
    }

    private void craftItem()
    {
        if(itemStackHandler.getStackInSlot(1) == null) {
            ItemStack temp = new ItemStack(validItemsMap.get(itemStackHandler.getStackInSlot(0).getItem()));
            temp.stackSize = 1;
            itemStackHandler.setStackInSlot(1, temp);
        }
        else
            itemStackHandler.getStackInSlot(1).stackSize++;
        itemStackHandler.getStackInSlot(0).stackSize--;
        if(itemStackHandler.getStackInSlot(0).stackSize <= 0)
            itemStackHandler.setStackInSlot(0, null);
    }

    public static void addValidItemToMap(Item itemIn, Item itemOut)
    {
        validItemsMap.put(itemIn, itemOut);
    }

    public boolean containsItemInSlot(int slot)
    {
        return itemStackHandler.getStackInSlot(slot) != null;
    }

    public int[] getFields()
    {
        return new int[] {progress, processTime};
    }

    public void setFields(int id, int value)
    {
        if(id == 0)
            progress = value;
        if(id == 1 )
            processTime = value;
    }

    public boolean isRunning()
    {
        return progress > 0;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {

        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return true;
        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(fuelRefinerItemStackHandler);
        return super.getCapability(capability, facing);
    }


}
