package electrolitic.extrafuels.init.blocks;

import electrolitic.extrafuels.util.BlockRegister;
import net.minecraft.block.material.Material;

/**
 * Created by Colin on 7/17/2016.
 */
public class BlockCoalRefiner extends BlockRegister {

    public BlockCoalRefiner(){
        super("blockCoalRefiner", Material.ROCK);
        this.setHardness(100.0f);
        this.setHarvestLevel("pickaxe", 1);
    }
}
