package com.teamisotope.crystalline.client.insanity;

import com.teamisotope.crystalline.api.crystal.CrystalMetadata;
import com.teamisotope.crystalline.api.crystal.ICrystal;
import com.teamisotope.crystalline.api.insanity.capability.ClientInsanityHelper;
import com.teamisotope.crystalline.common.item.CItems;
import com.teamisotope.crystalline.common.item.dyn.ItemCrystal;
import com.teamisotope.crystalline.common.util.CConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class OverlayInsanity extends Gui {

    private static final ResourceLocation INSANITY_TEXTURE = new ResourceLocation("crystalline", "textures/gui/insanityoverlay.png");

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onRenderOverlay(RenderGameOverlayEvent e) {
        if (e.getType().equals(RenderGameOverlayEvent.ElementType.ALL)) {
            Minecraft mc = Minecraft.getMinecraft();
            if (mc.player.getHeldItemMainhand().getItem() == CItems.crystal || mc.player.getHeldItemOffhand().getItem() == CItems.crystal) {
                ItemStack s = mc.player.getHeldItemMainhand();
                ItemStack so = mc.player.getHeldItemOffhand();
                if (s.getItem() == CItems.crystal) {
                    CrystalMetadata meta = ItemCrystal.get(s);
                    if (meta != null) {
                        ICrystal c = meta.getCrystal();
                        if (!c.getRegistryName().toString().equals("crystalline:crystal.knowledge")) {
                            return;
                        }
                    }
                } else if (so.getItem() == CItems.crystal) {
                    CrystalMetadata meta = ItemCrystal.get(so);
                    if (meta != null) {
                        ICrystal c = meta.getCrystal();
                        if (!c.getRegistryName().toString().equals("crystalline:crystal.knowledge")) {
                            return;
                        }
                    } else {
                        return;
                    }
                } else {
                     return;
                }

            } else {
                return;
            }
            int insanity = ClientInsanityHelper.getInstance().getInsanity();
            int renderSize = 16;
            int iconDimensions = 95;
            int defU = 0;
            int posX = e.getResolution().getScaledWidth() / 2 - renderSize*5;
            int posY = 10;

            GlStateManager.enableAlpha();
            GlStateManager.enableBlend();
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
            mc.getTextureManager().bindTexture(INSANITY_TEXTURE);
            if (insanity == 101) {
                insanity = 100;
                defU = iconDimensions * 2;
            }
            for (int i = 0; i < (int)(insanity / 10); i++) {
                GuiScreen.drawScaledCustomSizeModalRect(posX, posY, defU, 0, iconDimensions, iconDimensions, renderSize, renderSize, 384, 384);

                posX += renderSize + 1;
            }

            if (insanity % 10 > 0) {
                GuiScreen.drawScaledCustomSizeModalRect(posX, posY, iconDimensions, 0, iconDimensions, iconDimensions, renderSize, renderSize, 384, 384);
            }

            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
            GlStateManager.disableBlend();
            GlStateManager.disableAlpha();
        }
    }

}
