package electrolitic.extrafuels.proxy;

import electrolitic.extrafuels.util.Instances;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

/**
 * Created by Colin on 7/18/2016.
 */
public class ClientProxy implements IProxy{

    @Override
    public void preInit() {
        loadModels(Instances.BLOCK_FUEL_REFINER);
    }

    @Override
    public void init() {

    }

    private static void loadModels(Item item)
    {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

    private static void loadModels(Block block)
    {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
    }
}
