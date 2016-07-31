package electrolitic.extrafuels.handlers.itemhandlers;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

/**
 * Created by Colin on 7/31/2016.
 */
public class FuelRefinerItemStackHandler extends ItemStackHandler{

    public FuelRefinerItemStackHandler(ItemStack[] itemStacks)
    {
        super(itemStacks);
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
        if(slot == 0) {
            return super.insertItem(slot, stack, simulate);
        }
        if(slot == 1)
            return null;
        return null;
    }

    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        if(slot == 1) {
            return super.extractItem(slot, amount, simulate);
        }
        return null;
    }
}
