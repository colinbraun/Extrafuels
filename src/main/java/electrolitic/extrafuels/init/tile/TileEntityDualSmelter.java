package electrolitic.extrafuels.init.tile;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.ItemStackHandler;

/**
 * Created by Colin on 7/30/2016.
 */
public class TileEntityDualSmelter extends TileEntity {

    private ItemStack[] contents = new ItemStack[6];
    public final ItemStackHandler itemStackHandler = new ItemStackHandler();
    public TileEntityDualSmelter()
    {

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
    }


}
