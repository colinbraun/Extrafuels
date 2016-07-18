package handlers;

import electrolitic.extrafuels.init.tile.TileEntityCoalRefiner;
import electrolitic.extrafuels.util.BlockRegister;
import electrolitic.extrafuels.util.Instances;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Colin on 7/16/2016.
 */
public class RegistryHandler {

    public static void register(){
        GameRegistry.register(GameRegistry.register(Instances.BLOCK_COAL_REFINER).createItemBlock());
        GameRegistry.registerTileEntity(TileEntityCoalRefiner.class, "tileCoalRefiner");
    }
}
