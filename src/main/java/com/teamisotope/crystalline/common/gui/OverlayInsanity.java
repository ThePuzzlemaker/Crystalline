package com.teamisotope.crystalline.common.gui;

import com.teamisotope.crystalline.common.util.CConfig;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class OverlayInsanity extends GuiScreen {


    @SubscribeEvent
    public static void onRenderOverlay(RenderGameOverlayEvent e) {
        if (e.getType().equals(RenderGameOverlayEvent.ElementType.HEALTH)) {
            int x = CConfig.insanity.insanityOverlayX;
            int y = CConfig.insanity.insanityOverlayY;

        }
    }

}
