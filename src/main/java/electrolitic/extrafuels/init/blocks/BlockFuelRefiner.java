package electrolitic.extrafuels.init.blocks;

import electrolitic.extrafuels.ExtraFuels;
import electrolitic.extrafuels.init.tile.TileEntityFuelRefiner;
import electrolitic.extrafuels.util.BlockRegister;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * Created by Colin on 7/17/2016.
 */
public class BlockFuelRefiner extends BlockRegister {

    public BlockFuelRefiner(){
        super("fuelRefiner", Material.ROCK);
        this.setHardness(100.0f);
        this.setHarvestLevel("pickaxe", 1);
        this.setCreativeTab(CreativeTabs.MISC);
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityFuelRefiner();
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);


        if(!(tileEntity instanceof TileEntityFuelRefiner || playerIn.isSneaking()))
            return false;

        playerIn.openGui(ExtraFuels.instance, 1, worldIn, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }
}
