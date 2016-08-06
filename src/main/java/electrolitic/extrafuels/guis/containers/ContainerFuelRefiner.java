package electrolitic.extrafuels.guis.containers;

import electrolitic.extrafuels.init.tile.TileEntityFuelRefiner;
import electrolitic.extrafuels.guis.slots.OutputSlot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nullable;

/**
 * Created by Colin on 7/16/2016.
 */
public class ContainerFuelRefiner extends ContainerBase{

    private TileEntityFuelRefiner tile;
    private int[] oldFields;
    public ContainerFuelRefiner(TileEntityFuelRefiner tileEntity, InventoryPlayer playerInventory)
    {
        tile = tileEntity;
        oldFields = tileEntity.getFields();
        addSlotToContainer(new SlotItemHandler(tileEntity.itemStackHandler, 0, 56, 34));
        addSlotToContainer(new OutputSlot(tileEntity.itemStackHandler, 1, 111, 30));

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

        for(int i = 0; i < oldFields.length; ++i)
        {
            if(oldFields[i] != fields[i])
            {
                update = true;
                oldFields[i] = fields[i];
            }
        }
        if(update) {
            for (int i = 0; i < this.listeners.size(); ++i) {
                IContainerListener iContainerListener = listeners.get(i);
                iContainerListener.sendProgressBarUpdate(this, 0, fields[0]);
                iContainerListener.sendProgressBarUpdate(this, 1, fields[1]);
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data) {
        tile.setFields(id, data);
    }

    @Nullable
    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        boolean flag = false;
        Slot slot = this.inventorySlots.get(index);
        if(slot.getStack()==null)
            return null; //THIS ENDS THE PROCESS, AS CLICKING ON A NULL ITEMSTACK SHOULD DO NOTHING!
        ItemStack itemStack = slot.getStack().copy(); //The ItemStack in the slot whose index is passed (Usually clicked on)


        if(index == 1 && slot.getStack() != null)
        {
            if(mergeItemStack(slot.getStack(), 2, inventorySlots.size(), false))
                return slot.getStack();
            return null;
        }
        if(inventorySlots.get(0).getStack() == null || inventorySlots.get(0).getStack().stackSize <= 0)/*Checks to see if there is an open slot in the input of the
        fuel refiner. If so, moves the clicked on ItemStack into it */
        {
                this.inventorySlots.get(0).putStack(itemStack);
                slot.putStack(null);
        }
        else if(index == 0)//This occurs when the player has shift clicked on the input of the fuel refiner itself
        {
            if(mergeItemStack(slot.getStack(), 2, inventorySlots.size(), false))
                return slot.getStack();
        }
        else if(this.inventorySlots.get(0).getStack().getItem() == itemStack.getItem())/*Checks to see if the clicked ItemStack and the one in the input of the
        fuel refiner are the same. If so, it merges them*/
        {
            if(mergeItemStack(inventorySlots.get(index).getStack(), 0, 1, false))
            {
                return inventorySlots.get(index).getStack();
            }
        }
        return null;
    }

}