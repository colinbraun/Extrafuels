package electrolitic.extrafuels.handlers;

import electrolitic.extrafuels.init.tile.TileEntityDualSmelter;
import electrolitic.extrafuels.init.tile.TileEntityFuelRefiner;
import electrolitic.extrafuels.util.Instances;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Colin on 7/16/2016.
 */
public class RegistryHandler {

    public static void register(){
        GameRegistry.register(GameRegistry.register(Instances.BLOCK_FUEL_REFINER).createItemBlock());
        GameRegistry.registerTileEntity(TileEntityFuelRefiner.class, Reference.MODID + ":tileFuelRefiner");
        GameRegistry.register(Instances.ITEM_REFINED_COAL);
        GameRegistry.register(GameRegistry.register(Instances.BLOCK_DUAL_SMELTER).createItemBlock());
        GameRegistry.registerTileEntity(TileEntityDualSmelter.class, Reference.MODID + ":tileDualSmelter");
    }
}
