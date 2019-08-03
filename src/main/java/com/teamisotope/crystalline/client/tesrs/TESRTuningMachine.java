package com.teamisotope.crystalline.client.tesrs;

import com.teamisotope.crystalline.common.block.tuning.TETuningMachine;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;

public class TESRTuningMachine extends TileEntitySpecialRenderer<TETuningMachine> {

    @Override
    public void render(TETuningMachine te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        GlStateManager.pushAttrib();
        GlStateManager.pushMatrix();

        GlStateManager.translate(x, y, z);
        GlStateManager.disableRescaleNormal();

        renderItem(te);

        GlStateManager.popMatrix();
        GlStateManager.popAttrib();
    }

    private void renderItem(TETuningMachine te) {
        ItemStack stack = te.getItemStackHandler().getStackInSlot(0);
        if (!stack.isEmpty()) {
            GlStateManager.enableLighting();
            RenderHelper.enableStandardItemLighting();
            GlStateManager.pushMatrix();
            GlStateManager.translate(.5, .35, .5);
            GlStateManager.scale(.4f, .4f, .4f);
            Minecraft.getMinecraft().getRenderItem().renderItem(stack, ItemCameraTransforms.TransformType.NONE);
            GlStateManager.popMatrix();
        }
    }

}
