package ext.tpz.crystalline.common.network;

import ext.tpz.crystalline.common.block.tuning.TETuningMachine;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketTETuning implements IMessage {

    private BlockPos blockPos;
    private boolean real;

    @Override
    public void fromBytes(ByteBuf buf) {
        blockPos = BlockPos.fromLong(buf.readLong());
        real = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(blockPos.toLong());
        buf.writeBoolean(real);
    }

    public PacketTETuning(BlockPos pos, boolean real) {
        this.blockPos = pos;
        this.real = real;
    }

    public PacketTETuning() {

    }

    public static class Handler implements IMessageHandler<PacketTETuning, IMessage> {
        @Override
        public IMessage onMessage(PacketTETuning message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
            return null;
        }

        private void handle(PacketTETuning message, MessageContext ctx) {
            EntityPlayerMP playerEntity = ctx.getServerHandler().player;
            World world = playerEntity.getEntityWorld();
            if (world.isBlockLoaded(message.blockPos)) {
                if (world.isBlockModifiable(playerEntity, message.blockPos)) {
                    if (world.getTileEntity(message.blockPos) instanceof TETuningMachine) {
                        TETuningMachine te = (TETuningMachine) world.getTileEntity(message.blockPos);
                        if (message.real) {
                            te.tune(te.getItemStackHandler().getStackInSlot(0));
                        } else {
                            te.test(te.getItemStackHandler().getStackInSlot(0));
                        }
                    }
                }
            }
        }
    }

}
