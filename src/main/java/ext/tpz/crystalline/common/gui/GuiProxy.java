package ext.tpz.crystalline.common.gui;

import ext.tpz.crystalline.common.block.tuning.TETuningMachine;
import ext.tpz.crystalline.common.block.tuning.TuningMachineContainer;
import ext.tpz.crystalline.common.block.tuning.TuningMachineGUI;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiProxy implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof TETuningMachine) {
            return new TuningMachineContainer(player.inventory, (TETuningMachine) te);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof TETuningMachine) {
            TETuningMachine containerTileEntity = (TETuningMachine) te;
            return new TuningMachineGUI(containerTileEntity, new TuningMachineContainer(player.inventory, containerTileEntity));
        }
        return null;
    }

}
