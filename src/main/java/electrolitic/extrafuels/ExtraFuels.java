package electrolitic.extrafuels;

import handlers.RecipeHandler;
import handlers.Reference;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by Colin on 7/16/2016.
 */
@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class ExtraFuels {


    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)

    {
        RecipeHandler.registerRecipes();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)

    {

    }
}
