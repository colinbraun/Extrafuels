package electrolitic.extrafuels.init.tile.specialrenderer;

import electrolitic.extrafuels.init.tile.TileEntityDualSmelter;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by Colin on 8/4/2016.
 */
public class DualSmelterSpecialRenderer extends TileEntitySpecialRenderer{

    public DualSmelterSpecialRenderer()
    {

    }

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage) {
        super.renderTileEntityAt(te, x, y, z, partialTicks, destroyStage);
    }
}
