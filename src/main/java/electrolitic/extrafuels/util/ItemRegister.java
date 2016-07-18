package electrolitic.extrafuels.util;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Colin on 7/17/2016.
 */
public class ItemRegister extends Item{

    public ItemRegister(String name)
    {
        setRegistryName(new ResourceLocation("extrafuels", name));
        setUnlocalizedName(getRegistryName().toString());
    }


}
