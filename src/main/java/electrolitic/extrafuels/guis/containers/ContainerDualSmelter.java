package electrolitic.extrafuels.guis.containers;


import electrolitic.extrafuels.guis.slots.OutputSlot;
import electrolitic.extrafuels.init.tile.TileEntityDualSmelter;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraftforge.items.SlotItemHandler;

/**
 * Created by Colin on 7/31/2016.
 */
public class ContainerDualSmelter extends Container {

    private int[] oldFields;
    private TileEntityDualSmelter tile;
    public ContainerDualSmelter(TileEntityDualSmelter tileEntity, InventoryPlayer playerInventory)
    {
        tile = tileEntity;

        addSlotToContainer(new SlotItemHandler(tile.itemStackHandler, 0, 39, 20));
        addSlotToContainer(new SlotItemHandler(tile.itemStackHandler, 1, 56, 20));
        addSlotToContainer(new SlotItemHandler(tile.itemStackHandler, 2, 47, 60));
        addSlotToContainer(new OutputSlot(tile.itemStackHandler, 3, 109, 20));
        addSlotToContainer(new OutputSlot(tile.itemStackHandler, 4, 126, 20));

        addPlayerSlotsToContainer(playerInventory);
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
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
    public void detectAndSendChanges() {
        super.detectAndSendChanges();


    }
}
