package electrolitic.extrafuels.init.tile;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.ItemStackHandler;

/**
 * Created by Colin on 7/30/2016.
 */
public class TileEntityDualSmelter extends TileEntity {

    //The amount of time the item has been cooking for
    private int progress;
    //The amount of time it takes to cook the item in the furncae
    private int totalCookTime;
    //The amount of burn time left in the furnace
    private int burnTime;
    //The amount of burn time the currently burning item had right as it started
    private int itemTotalBurnTime;

    private ItemStack[] contents = new ItemStack[6];
    public final ItemStackHandler itemStackHandler = new ItemStackHandler();

    public TileEntityDualSmelter()
    {
        progress = 0;
        totalCookTime =
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

    }
}
