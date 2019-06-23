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

public class PacketTMFreqChange implements IMessage {

    private BlockPos blockPos;
    private int change;

    @Override
    public void fromBytes(ByteBuf buf) {
        blockPos = BlockPos.fromLong(buf.readLong());
        change = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(blockPos.toLong());
        buf.writeInt(change);
    }

    public PacketTMFreqChange(BlockPos pos, int change) {
        this.blockPos = pos;
        this.change = change;
    }

    public PacketTMFreqChange() {

    }

    public static class Handler implements IMessageHandler<PacketTMFreqChange, IMessage> {
        @Override
        public IMessage onMessage(PacketTMFreqChange message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
            return null;
        }

        private void handle(PacketTMFreqChange message, MessageContext ctx) {
            EntityPlayerMP playerEntity = ctx.getServerHandler().player;
            World world = playerEntity.getEntityWorld();
            if (world.isBlockLoaded(message.blockPos)) {
                if (world.isBlockModifiable(playerEntity, message.blockPos)) {
                    if (world.getTileEntity(message.blockPos) instanceof TETuningMachine) {
                        TETuningMachine te = (TETuningMachine) world.getTileEntity(message.blockPos);
                        if (!((te.getCurrentFrequency() + message.change) > 9999999) && !((te.getCurrentFrequency() + message.change) < 0)) {
                            te.setCurrentFrequency(te.getCurrentFrequency() + message.change);
                        }
                    }
                }
            }
        }
    }

}
