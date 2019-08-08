package com.teamisotope.crystalline.client.insanity;

import com.teamisotope.crystalline.api.insanity.capability.ClientInsanityHelper;
import net.minecraft.client.Minecraft;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientInsanityHandler {

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onPlayerJoin(WorldEvent.Load e) {
        ClientInsanityHelper.init(Minecraft.getMinecraft().player);
    }

}
