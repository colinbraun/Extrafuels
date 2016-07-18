package electrolitic.extrafuels.guis.containers;

import electrolitic.extrafuels.init.tile.TileEntityCoalRefiner;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraftforge.items.SlotItemHandler;

/**
 * Created by Colin on 7/16/2016.
 */
public class GuiContainerEnhancedFurnace extends Container{


    public GuiContainerEnhancedFurnace(TileEntityCoalRefiner tileEntity, InventoryPlayer playerInventory)
    {
        addSlotToContainer(new SlotItemHandler(tileEntity.itemStackHandler, 0, 0, 0));
        addSlotToContainer(new SlotItemHandler(tileEntity.itemStackHandler, 1, 0, 0));


        addPlayerSlotsToContainer(playerInventory);
    }


    private void addPlayerSlotsToContainer(InventoryPlayer playerInventory)
    {
        for(int x = 0; x < 9; x++)
        {
            addSlotToContainer(getSlotFromInventory(playerInventory, ));
        }

        for(int y=0; y < 3; y++)
        {
            for(int x = 0; x < 9; x++)
            {

            }
        }
    }


    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return false;
    }
}
