package electrolitic.extrafuels.guis.containers;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

/**
 * Created by Colin on 8/4/2016.
 */
public abstract class ContainerBase extends Container {


    //Only works for standard GUIs (Like a vanilla furnace layout). Possibly plans to overload this to allow for specific positions of the slots
    public void addPlayerSlotsToContainer(InventoryPlayer playerInventory)
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

}
