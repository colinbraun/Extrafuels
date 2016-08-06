package electrolitic.extrafuels.guis.screens;

import electrolitic.extrafuels.guis.containers.ContainerDualSmelter;
import electrolitic.extrafuels.handlers.Reference;
import electrolitic.extrafuels.init.tile.TileEntityDualSmelter;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Colin on 7/31/2016.
 */
public class GuiDualSmelter extends GuiContainer {

    private TileEntityDualSmelter tile;
    private ResourceLocation dualSmelterTexture = new ResourceLocation(Reference.MODID, "textures/gui/dualSmelter.png");

    public GuiDualSmelter(TileEntityDualSmelter tileEntity, InventoryPlayer playerInventory)
    {
        super(new ContainerDualSmelter(tileEntity, playerInventory));
        this.tile = tileEntity;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);

        mc.renderEngine.bindTexture(dualSmelterTexture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }
}
