package com.teamisotope.crystalline.common.network;

import com.teamisotope.crystalline.common.block.tuning.TETuningMachine;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketTMTest implements IMessage {

    private BlockPos blockPos;

    @Override
    public void fromBytes(ByteBuf buf) {
        blockPos = BlockPos.fromLong(buf.readLong());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(blockPos.toLong());
    }

    public PacketTMTest(BlockPos pos) {
        this.blockPos = pos;
    }

    public PacketTMTest() {

    }

    public static class Handler implements IMessageHandler<PacketTMTest, IMessage> {
        @Override
        public IMessage onMessage(PacketTMTest message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
            return null;
        }

        private void handle(PacketTMTest message, MessageContext ctx) {
            EntityPlayerMP playerEntity = ctx.getServerHandler().player;
            World world = playerEntity.getEntityWorld();
            if (world.isBlockLoaded(message.blockPos)) {
                if (world.isBlockModifiable(playerEntity, message.blockPos)) {
                    if (world.getTileEntity(message.blockPos) instanceof TETuningMachine) {
                        TETuningMachine te = (TETuningMachine) world.getTileEntity(message.blockPos);
                        te.test(te.getItemStackHandler().getStackInSlot(0));
                    }
                }
            }
        }
    }

}
