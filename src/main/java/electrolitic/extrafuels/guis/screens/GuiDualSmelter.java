package electrolitic.extrafuels.guis.screens;

import electrolitic.extrafuels.guis.containers.ContainerDualSmelter;
import electrolitic.extrafuels.init.tile.TileEntityDualSmelter;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;

/**
 * Created by Colin on 7/31/2016.
 */
public class GuiDualSmelter extends GuiContainer {

    private TileEntityDualSmelter tile;

    public GuiDualSmelter(TileEntityDualSmelter tileEntity, InventoryPlayer playerInventory)
    {
        super(new ContainerDualSmelter(tileEntity, playerInventory));
        this.tile = tileEntity;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

    }
}
