package ext.tpz.crystalline.packet.common;

import ext.tpz.crystalline.api.mode.CrystalModeRegistry;
import ext.tpz.crystalline.api.mode.ICrystalMode;
import ext.tpz.crystalline.modes.BaseModModes;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.nio.charset.Charset;

public class PacketSendClientMessage implements IMessage {

    private String msg;

    @Override
    public void fromBytes(ByteBuf buf) {
        msg = buf.readCharSequence(buf.capacity(), Charset.forName("UTF-8")).toString();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeCharSequence(msg, Charset.forName("UTF-8"));
    }

    public PacketSendClientMessage() { msg = ""; }

    public PacketSendClientMessage(String newMode) {
        this.msg = newMode;
    }

    public static class Handler implements IMessageHandler<PacketSendClientMessage, IMessage> {
        @Override
        public IMessage onMessage(PacketSendClientMessage message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
            return null;
        }

        private void handle(PacketSendClientMessage message, MessageContext ctx) {
            EntityPlayerMP playerEntity = ctx.getServerHandler().player;
            playerEntity.sendStatusMessage(new TextComponentString(message.msg), true);
        }
    }


}
