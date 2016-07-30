package electrolitic.extrafuels.init.blocks;

import electrolitic.extrafuels.util.BlockRegister;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

/**
 * Created by Colin on 7/30/2016.
 */
public class DualSmelter extends BlockRegister{

    public DualSmelter()
    {
        super("dualSmelter", Material.ROCK);
        this.setCreativeTab(CreativeTabs.MISC);
        this.setHardness(5.0f);
        this.setHarvestLevel("pickaxe", 1);
    }


}
