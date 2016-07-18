package electrolitic.extrafuels.util;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Colin on 7/17/2016.
 */
public class BlockRegister extends Block {

    public BlockRegister(String name, Material material)
    {
        super(material);
        setRegistryName(new ResourceLocation("extrafuels", name));
        setUnlocalizedName(getRegistryName().toString());
    }

    public ItemBlock createItemBlock(){
        return  (ItemBlock)(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }
}
