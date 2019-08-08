package com.teamisotope.crystalline.api.insanity.capability;

import com.teamisotope.crystalline.common.network.PacketHandler;
import com.teamisotope.crystalline.common.network.PacketInsanityChanged;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class InsanityHandler {

    @SubscribeEvent
    public static void attachCapabilities(AttachCapabilitiesEvent<Entity> e) {
        if (e.getObject() instanceof EntityPlayer) {
            if (InsanityUtils.getRawInsanity((EntityPlayer) e.getObject()) == null)
                e.addCapability(InsanityCapability.CAPABILITY_INSANITY_RL, new InsanityCapability());
        }
    }

    @SubscribeEvent
    public static void clonePlayer(PlayerEvent.Clone e) {
        InsanityUtils.setInsanity(e.getEntityPlayer(), InsanityUtils.getInsanity(e.getOriginal()));
    }

    @SubscribeEvent
    public static void playerJoin(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent e) {
        // I have no idea if this is necessary but it can't hurt to check :p
        if (!e.player.getEntityWorld().isRemote) {
            EntityPlayer p = e.player;
            PacketHandler.INSTANCE.sendTo(new PacketInsanityChanged(InsanityUtils.getInsanity(p)), (EntityPlayerMP)p);
        }
    }

}
