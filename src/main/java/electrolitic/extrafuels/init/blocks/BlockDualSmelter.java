package electrolitic.extrafuels.init.blocks;

import electrolitic.extrafuels.ExtraFuels;
import electrolitic.extrafuels.init.tile.TileEntityDualSmelter;
import electrolitic.extrafuels.util.BlockRegister;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
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
 * Created by Colin on 7/30/2016.
 */
public class BlockDualSmelter extends BlockRegister{

    public BlockDualSmelter()
    {
        super("dualSmelter", Material.ROCK);
        this.setCreativeTab(CreativeTabs.MISC);
        this.setHardness(5.0f);
        this.setHarvestLevel("pickaxe", 1);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        if(!worldIn.isRemote)
        {
            if(playerIn.isSneaking() || !(worldIn.getTileEntity(pos) instanceof TileEntityDualSmelter))
                return false;
            playerIn.openGui(ExtraFuels.instance, 1, worldIn, pos.getX(), pos.getY(), pos.getZ());
            return true;
        }
        return false;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityDualSmelter();
    }
}
