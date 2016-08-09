package electrolitic.extrafuels.guis.containers;


import electrolitic.extrafuels.guis.slots.OutputSlot;
import electrolitic.extrafuels.init.tile.TileEntityDualSmelter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.SlotItemHandler;

/**
 * Created by Colin on 7/31/2016.
 */
public class ContainerDualSmelter extends ContainerBase {

    private int[] oldFields;
    private TileEntityDualSmelter tile;
    public ContainerDualSmelter(TileEntityDualSmelter tileEntity, InventoryPlayer playerInventory)
    {
        tile = tileEntity;
        oldFields = tileEntity.getFields();
        addSlotToContainer(new SlotItemHandler(tile.itemStackHandler, 0, 47, 60));
        addSlotToContainer(new SlotItemHandler(tile.itemStackHandler, 1, 39, 20));
        addSlotToContainer(new SlotItemHandler(tile.itemStackHandler, 2, 56, 20));
        addSlotToContainer(new OutputSlot(tile.itemStackHandler, 3, 109, 20));
        addSlotToContainer(new OutputSlot(tile.itemStackHandler, 4, 126, 20));

        addPlayerSlotsToContainer(playerInventory);
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }



    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        int[] fields = tile.getFields();
        boolean update = false;
        for(int i = 0; i < fields.length; ++i)
        {
            if(oldFields[i] != fields[i])
            {
                update = true;
                oldFields[i] = fields[i];
            }
        }

        if(update)
        {
            for(int i = 0; i < listeners.size(); ++i)
            {
                IContainerListener listener = listeners.get(i);
                for(int k = 0; k < fields.length; ++k)
                {
                    listener.sendProgressBarUpdate(this, k, fields[k]);
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void updateProgressBar(int id, int data) {
        this.tile.setFields(id, data);
    }
}
