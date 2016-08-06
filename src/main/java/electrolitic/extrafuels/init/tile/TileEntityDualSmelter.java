package electrolitic.extrafuels.init.tile;

import com.sun.media.jfxmedia.logging.Logger;
import electrolitic.extrafuels.init.items.ItemRefinedCoal;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ITickable;
import net.minecraftforge.items.ItemStackHandler;

/**
 * Created by Colin on 7/30/2016.
 */
public class TileEntityDualSmelter extends TileEntity implements ITickable{

    //The amount of time the item has been cooking for
    private int progress;
    //The amount of time it takes to cook the item in the furncae
    private int totalCookTime;
    //The amount of burn time left in the furnace
    private int burnTime;
    //The amount of burn time the currently burning item had right as it started
    private int itemTotalBurnTime;
    //Index 0: fuel. Index 1 & 2: items to cook. Index 3 & 4: items processed.
    private ItemStack[] contents = new ItemStack[5];
    public final ItemStackHandler itemStackHandler = new ItemStackHandler(contents);
    //An alias of the recipes for easier use
    private static FurnaceRecipes furnaceRecipes = FurnaceRecipes.instance();

    public TileEntityDualSmelter()
    {
        progress = 0;
        totalCookTime = 200;
    }


    @Override
    public void update() {
        if(worldObj.isRemote)
            return;


        boolean didBurn = false;
        if(isBurning())
        {
            --burnTime;
            didBurn = true;
        }
        else
        {
            if(!TileEntityFurnace.isItemFuel(itemStackHandler.getStackInSlot(0)))
                return;
        }


        boolean isNull1 = itemStackHandler.getStackInSlot(1) == null;//Makes sure inputs aren't null, as otherwise why do anything?
        boolean isNull2 = itemStackHandler.getStackInSlot(2) == null;//Have these to know which is or isn't null later
        if(isNull1 && isNull2) {
            progress = 0;
            return;
        }


        //NOTE: YOU CANNOT PASS furnaceRecipes.getSmeltingResult() A NULL ITEMSTACK
        boolean makesResult1 = false;
        boolean makesResult2 = false;
        if(!isNull1)//Determines if the item makes a result as long as it's not null
            if(furnaceRecipes.getSmeltingResult(itemStackHandler.getStackInSlot(1)) != null)
                makesResult1 = true;
        if(!isNull2)//Determines if the item makes a result as long as it's not null
            if(furnaceRecipes.getSmeltingResult(itemStackHandler.getStackInSlot(2)) != null)
                makesResult2 = true;
        if(!makesResult1 && !makesResult2) {
            progress = 0;
            return;
        }

        boolean canCraft1 = false;
        boolean canCraft2 = false;
        if(makesResult1)//Determines if the item can be crafted as long as it makes a result
        {
            if(itemStackHandler.getStackInSlot(3) == null)
                canCraft1 = true;                                                                                                                                   //Will not produce NullPointerException b/c was checked in makesResult1
            else if(furnaceRecipes.getSmeltingResult(itemStackHandler.getStackInSlot(1)).getItem() == itemStackHandler.getStackInSlot(3).getItem() && itemStackHandler.getStackInSlot(3).stackSize + furnaceRecipes.getSmeltingResult(itemStackHandler.getStackInSlot(1)).stackSize <= itemStackHandler.getStackInSlot(3).getMaxStackSize())
                canCraft1 = true;
        }
        if(makesResult2)//Determines if the item can be crafted as long as it makes a result
        {
            if(itemStackHandler.getStackInSlot(4) == null)
                canCraft2 = true;                                                                                                                                   //Will not produce NullPointerException b/c was checked in makesResult2
            else if(furnaceRecipes.getSmeltingResult(itemStackHandler.getStackInSlot(2)).getItem() == itemStackHandler.getStackInSlot(4).getItem() && itemStackHandler.getStackInSlot(4).stackSize + furnaceRecipes.getSmeltingResult(itemStackHandler.getStackInSlot(2)).stackSize <= itemStackHandler.getStackInSlot(4).getMaxStackSize())
                canCraft2 = true;
        }
        if(!canCraft1  && !canCraft2) {
            progress = 0;
            return;
        }

        if(!didBurn)//Checks to see if the burnTime was NOT reduced AND there is a valid fuel source in slot 0 (the fuel slot)
        {
            itemTotalBurnTime = TileEntityFurnace.getItemBurnTime(itemStackHandler.getStackInSlot(0));
            burnTime = itemTotalBurnTime;
            if(--itemStackHandler.getStackInSlot(0).stackSize <= 0)
                contents[0] = null;
            burnTime--;
        }

        ++progress;
        if(progress >= totalCookTime)
        {
            if(canCraft1)
                craft(1);
            if(canCraft2)
                craft(2);
        }

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
    }

    public int[] getFields()
    {
        return new int[] {progress, totalCookTime, burnTime, itemTotalBurnTime};
    }

    public void setFields(int id, int value)
    {
        switch(id)
        {
            case 0: progress = value;
                break;
            case 1: totalCookTime = value;
                break;
            case 2: burnTime = value;
                break;
            case 3: itemTotalBurnTime = value;
                break;
            default: Logger.logMsg(3, "Tried to set a field that does not exist...");
        }
    }

    public boolean isBurning()
    {
        return burnTime > 0;
    }

    public void craft(int index)//Should only accept one of the 2 input indexes
    {
        ItemStack temp = itemStackHandler.getStackInSlot(index);
        itemStackHandler.insertItem(index+2, furnaceRecipes.getSmeltingResult(temp), false);
        temp.stackSize--;
        if(temp.stackSize <= 0)
            temp = null;
        progress = 0;
    }
}
