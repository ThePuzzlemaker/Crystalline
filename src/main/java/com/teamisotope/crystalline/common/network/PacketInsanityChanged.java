package com.teamisotope.crystalline.common.network;

import com.teamisotope.crystalline.api.insanity.capability.ClientInsanityHelper;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketInsanityChanged implements IMessage {

    private int insanity;

    @Override
    public void fromBytes(ByteBuf buf) {
        insanity = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(insanity);
    }

    public PacketInsanityChanged(int insanity) {
        this.insanity = insanity;
    }

    public PacketInsanityChanged() {

    }

    public static class Handler implements IMessageHandler<PacketInsanityChanged, IMessage> {
        @Override
        public IMessage onMessage(PacketInsanityChanged message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
            return null;
        }

        private void handle(PacketInsanityChanged message, MessageContext ctx) {
            ClientInsanityHelper.getInstance().setInsanity(message.insanity);
        }
    }

}
