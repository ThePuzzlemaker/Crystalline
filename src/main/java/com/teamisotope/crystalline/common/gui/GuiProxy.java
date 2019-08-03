package com.teamisotope.crystalline.common.gui;

import com.teamisotope.crystalline.common.block.tuning.TuningMachineContainer;
import com.teamisotope.crystalline.common.block.tuning.TuningMachineGUI;
import com.teamisotope.crystalline.common.block.tuning.TETuningMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
    @SideOnly(Side.CLIENT)
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
