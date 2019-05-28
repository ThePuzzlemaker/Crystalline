package ext.tpz.crystalline.packet.common;

import ext.tpz.crystalline.Crystalline;
import ext.tpz.crystalline.insanity.InsanityWorldSavedData;
import ext.tpz.crystalline.item.CrystallineItems;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSendKey implements IMessage {

    private int nilInt;

    @Override
    public void fromBytes(ByteBuf buf) {
        nilInt = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(nilInt);
    }

    public PacketSendKey() {
        nilInt = 0;
    }

    public static class Handler implements IMessageHandler<PacketSendKey, IMessage> {
        @Override
        public IMessage onMessage(PacketSendKey message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
            return null;
        }

        private void handle(PacketSendKey message, MessageContext ctx) {
            EntityPlayerMP playerEntity = ctx.getServerHandler().player;
            World world = playerEntity.getEntityWorld();
            if (playerEntity.getHeldItem(EnumHand.MAIN_HAND).getItem() == CrystallineItems.crystal) {
                PacketHandler.INSTANCE.sendTo(new PacketCrystalNewMode(CrystallineItems.crystal.cycleMode(playerEntity.getHeldItem(EnumHand.MAIN_HAND)).getRegistryName().toString()), playerEntity);
            }
        }
    }


}
