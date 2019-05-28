package ext.tpz.crystalline.packet.common;

import ext.tpz.crystalline.api.mode.CrystalModeRegistry;
import ext.tpz.crystalline.api.mode.ICrystalMode;
import ext.tpz.crystalline.insanity.InsanityWorldSavedData;
import ext.tpz.crystalline.item.CrystallineItems;
import ext.tpz.crystalline.modes.BaseModModes;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.nio.charset.Charset;

public class PacketCrystalNewMode implements IMessage {

    private String newMode;

    @Override
    public void fromBytes(ByteBuf buf) {
        newMode = buf.readCharSequence(buf.capacity(), Charset.forName("UTF-8")).toString();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeCharSequence(newMode, Charset.forName("UTF-8"));
    }

    public PacketCrystalNewMode() { newMode = ""; }

    public PacketCrystalNewMode(String newMode) {
        this.newMode = newMode;
    }

    public static class Handler implements IMessageHandler<PacketCrystalNewMode, IMessage> {
        @Override
        public IMessage onMessage(PacketCrystalNewMode message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
            return null;
        }

        private void handle(PacketCrystalNewMode message, MessageContext ctx) {
            String regName = message.newMode;
            ICrystalMode mode = CrystalModeRegistry.getRegistry().getValue(new ResourceLocation(regName));
            if (mode == null) {
                mode = BaseModModes.mode_null;
            }
            EntityPlayer player = Minecraft.getMinecraft().player;
            if (player != null)
                player.sendStatusMessage(new TextComponentString(TextFormatting.GREEN + I18n.format("crystalline.crystal.mode.changed", I18n.format(mode.getUnlocalizedName()))), true);
        }
    }


}
