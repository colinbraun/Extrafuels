package electrolitic.extrafuels.guis.containers;

import electrolitic.extrafuels.init.tile.TileEntityFuelRefiner;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraftforge.items.SlotItemHandler;

import javax.naming.NoInitialContextException;

/**
 * Created by Colin on 7/16/2016.
 */
public class GuiContainerFuelRefiner extends Container{

    private TileEntityFuelRefiner tile;

    public GuiContainerFuelRefiner(TileEntityFuelRefiner tileEntity, InventoryPlayer playerInventory)
    {
        tile = tileEntity;
        addSlotToContainer(new SlotItemHandler(tileEntity.itemStackHandler, 0, 0, 0));
        addSlotToContainer(new SlotItemHandler(tileEntity.itemStackHandler, 1, 0, 0));


        addPlayerSlotsToContainer(playerInventory);
    }


    private void addPlayerSlotsToContainer(InventoryPlayer playerInventory)
    {

        for(int y=0; y < 3; y++)
        {
            for(int x = 0; x < 9; x++)
            {
                addSlotToContainer(new Slot(playerInventory,
                        x + y * 9 + 9,
                        8 + x * 18, 84 + y * 18));
            }
        }

        for(int x = 0; x < 9; x++)
        {
            addSlotToContainer(new Slot(playerInventory, x, x * 18 + 8, 142));
        }
    }


    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }


}
