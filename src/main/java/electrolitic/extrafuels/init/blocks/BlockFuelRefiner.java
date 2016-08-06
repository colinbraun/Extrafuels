package electrolitic.extrafuels.init.blocks;

import electrolitic.extrafuels.ExtraFuels;
import electrolitic.extrafuels.init.tile.TileEntityFuelRefiner;
import electrolitic.extrafuels.util.BlockRegister;
import electrolitic.extrafuels.util.Instances;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.*;

/**
 * Created by Colin on 7/17/2016.
 */
public class BlockFuelRefiner extends BlockRegister {
    public static final PropertyEnum<EnumFacing> FACING = PropertyEnum.create("facing", EnumFacing.class, EnumFacing.NORTH, EnumFacing.SOUTH, EnumFacing.EAST, EnumFacing.WEST);
    public BlockFuelRefiner(){
        super("fuelRefiner", Material.ROCK);
        this.setHardness(10.0f);
        this.setHarvestLevel("pickaxe", 1);
        this.setCreativeTab(CreativeTabs.MISC);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
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


        if((!(tileEntity instanceof TileEntityFuelRefiner) || playerIn.isSneaking()))
            return false;

        playerIn.openGui(ExtraFuels.instance, 0, worldIn, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TileEntityFuelRefiner tileEntity = (TileEntityFuelRefiner) worldIn.getTileEntity(pos);
        if (!worldIn.isRemote) {
            if (!worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 7, false).isCreative()) {
                spawnAsEntity(worldIn, pos, new ItemStack(Instances.BLOCK_FUEL_REFINER));
            }
            if(tileEntity.containsItemInSlot(0))
                spawnAsEntity(worldIn, pos, tileEntity.itemStackHandler.getStackInSlot(0));
            if(tileEntity.containsItemInSlot(1))
                spawnAsEntity(worldIn, pos, tileEntity.itemStackHandler.getStackInSlot(1));
        }
        super.breakBlock(worldIn, pos, state);
    }

    @Override //This occurs just before it's set in the world
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getIndex();
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing enumFacing = EnumFacing.getFront(meta);

        if(enumFacing.getAxis() == EnumFacing.Axis.Y)
        {
            enumFacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(FACING, enumFacing);
    }

    public static void setState(boolean active, World worldIn, BlockPos pos)
    {

    }
}
