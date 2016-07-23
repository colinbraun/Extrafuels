package electrolitic.extrafuels.init.tile;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraftforge.items.ItemStackHandler;

/**
 * Created by Colin on 7/17/2016.
 */
public class TileEntityFuelRefiner extends TileEntity implements ITickable{


    private int progress;

    private ItemStack[] contents = new ItemStack[2];

    public final ItemStackHandler itemStackHandler = new ItemStackHandler(contents);

    public TileEntityFuelRefiner()
    {

    }

    @Override
    public void update() {

    }


    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        NBTTagList nbttaglist = compound.getTagList("items", 10);
        contents = new ItemStack[contents.length];

        for(int i = 0; i < nbttaglist.tagCount(); i++)
        {
            NBTTagCompound tempCompound = nbttaglist.getCompoundTagAt(i);
            int tempSlot = (int)(tempCompound.getByte("slot"));
            contents[tempSlot] = ItemStack.loadItemStackFromNBT(tempCompound);
        }
        progress = compound.getByte("progress");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound = super.writeToNBT(compound);
        compound.setByte("progress", (byte)progress);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < contents.length; i++)
        {
            if (this.contents[i] != null)
            {
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                nbttagcompound.setByte("slot", (byte)i);
                contents[i].writeToNBT(nbttagcompound);
                nbttaglist.appendTag(nbttagcompound);
            }
        }

        compound.setTag("items", nbttaglist);

        return compound;
    }

}
