package electrolitic.extrafuels.init.tile;

import net.minecraft.item.ItemStack;
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


}
