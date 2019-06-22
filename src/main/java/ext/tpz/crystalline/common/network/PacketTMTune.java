package ext.tpz.crystalline.common.network;

import ext.tpz.crystalline.common.block.tuning.TETuningMachine;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketTMTune implements IMessage {

    private BlockPos blockPos;

    @Override
    public void fromBytes(ByteBuf buf) {
        blockPos = BlockPos.fromLong(buf.readLong());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(blockPos.toLong());
    }

    public PacketTMTune(BlockPos pos) {
        this.blockPos = pos;
    }

    public PacketTMTune() {

    }

    public static class Handler implements IMessageHandler<PacketTMTune, IMessage> {
        @Override
        public IMessage onMessage(PacketTMTune message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
            return null;
        }

        private void handle(PacketTMTune message, MessageContext ctx) {
            EntityPlayerMP playerEntity = ctx.getServerHandler().player;
            World world = playerEntity.getEntityWorld();
            if (world.isBlockLoaded(message.blockPos)) {
                if (world.isBlockModifiable(playerEntity, message.blockPos)) {
                    if (world.getTileEntity(message.blockPos) instanceof TETuningMachine) {
                        TETuningMachine te = (TETuningMachine) world.getTileEntity(message.blockPos);
                        te.tune(te.getItemStackHandler().getStackInSlot(0));
                    }
                }
            }
        }
    }

}
