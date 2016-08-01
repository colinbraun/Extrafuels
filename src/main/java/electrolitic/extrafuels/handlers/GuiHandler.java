package electrolitic.extrafuels.handlers;

import electrolitic.extrafuels.guis.containers.ContainerFuelRefiner;
import electrolitic.extrafuels.guis.screens.GuiFuelRefiner;
import electrolitic.extrafuels.init.tile.TileEntityFuelRefiner;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/**
 * Created by Colin on 7/16/2016.
 */
public class GuiHandler implements IGuiHandler{

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return new ContainerFuelRefiner((TileEntityFuelRefiner)(world.getTileEntity(new BlockPos(x, y, z))), player.inventory);
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return new GuiFuelRefiner((TileEntityFuelRefiner)(world.getTileEntity(new BlockPos(x, y, z))), player.inventory);
    }
}
