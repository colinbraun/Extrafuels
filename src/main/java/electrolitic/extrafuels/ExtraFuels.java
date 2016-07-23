package electrolitic.extrafuels;

import electrolitic.extrafuels.proxy.IProxy;
import electrolitic.extrafuels.handlers.GuiHandler;
import electrolitic.extrafuels.handlers.RecipeHandler;
import electrolitic.extrafuels.handlers.Reference;
import electrolitic.extrafuels.handlers.RegistryHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

/**
 * Created by Colin on 7/16/2016.
 */
@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class ExtraFuels {

    @Mod.Instance
    public static ExtraFuels instance;

    @SidedProxy(clientSide=Reference.CLIENT_PROXY, serverSide=Reference.COMMON_PROXY, modId=Reference.MODID)
    public static IProxy proxy;

    private GuiHandler guiHandler = new GuiHandler();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        RegistryHandler.register();
        proxy.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)

    {
        RecipeHandler.registerRecipes();
        NetworkRegistry.INSTANCE.registerGuiHandler(this, guiHandler);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)

    {

    }
}
