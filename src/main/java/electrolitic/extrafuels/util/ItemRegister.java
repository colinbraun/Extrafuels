package electrolitic.extrafuels.util;

import electrolitic.extrafuels.handlers.Reference;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class ItemRegister extends Item{

    public ItemRegister(String name)
    {
        setRegistryName(new ResourceLocation("extrafuels", name));
        setUnlocalizedName(Reference.MODID + "." + name);
    }


}
