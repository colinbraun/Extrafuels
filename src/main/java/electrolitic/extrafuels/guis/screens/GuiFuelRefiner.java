package electrolitic.extrafuels.guis.screens;

import electrolitic.extrafuels.guis.containers.GuiContainerFuelRefiner;
import electrolitic.extrafuels.init.tile.TileEntityFuelRefiner;
import electrolitic.extrafuels.handlers.Reference;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Colin on 7/16/2016.
 */
public class GuiFuelRefiner extends GuiContainer {

    private TileEntityFuelRefiner tile;
    private ResourceLocation guiTextureLocation = new ResourceLocation(Reference.MODID, "textures/gui/fuelRefiner.png");

    public GuiFuelRefiner(TileEntityFuelRefiner tileEntity, InventoryPlayer playerInventory)
    {
        super(new GuiContainerFuelRefiner(tileEntity, playerInventory));
        this.tile = tileEntity;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);

        mc.renderEngine.bindTexture(guiTextureLocation);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
        if(tile.isRunning())
            drawTexturedModalRect(guiLeft + 80, guiTop + 38, 176, 18, (int)(23 * (double)tile.getProgress()/tile.getProcessTime()), 6); //Draws the progress bar
        if(tile.isRunning())
            System.out.println("The tile is running");
    }


}
