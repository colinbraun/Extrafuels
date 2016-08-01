package electrolitic.extrafuels.handlers.itemhandlers;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

/**
 * Created by Colin on 7/31/2016.
 */
/*The purpose of this class is to offer TileEntities with a Container an ItemStackHandler that works with the Capability<IItemHandler>

* This usually requires that you have two ItemStackHandlers (This one and some other one, probably a regular ItemStackHandler)
  both manipulating the same ItemStack[] The other one would be used for your Contianer (assuming you have one)*/
public class CapabilityItemStackHandler extends ItemStackHandler{
    //Holds the indexes of the outputs of the TileEntity
    private int[] outputSlotIndexes;

    public CapabilityItemStackHandler(ItemStack[] contents, int[] inputSlots, int... outputSlotIndexes)
    {
        super(contents);
        this.outputSlotIndexes = outputSlotIndexes;
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
        return super.insertItem(slot, stack, simulate);
    }

    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        validateSlotIndex(slot);
        for(int index : outputSlotIndexes)
        {
            if(slot == index)
                return super.extractItem(slot, amount, simulate);
        }
        return null;
    }

}
