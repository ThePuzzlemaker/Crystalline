package ext.tpz.crystalline.block.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;

public class TESRRestorationApparatus extends TileEntitySpecialRenderer<TERestorationApparatus> {

    @Override
    public void render(TERestorationApparatus te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        GlStateManager.pushAttrib();
        GlStateManager.pushMatrix();

        // Translate to the location of our tile entity
        GlStateManager.translate(x, y, z);
        GlStateManager.disableRescaleNormal();
        // Render our item
        renderCrystal(te);
        renderEssence(te);

        GlStateManager.popMatrix();
        GlStateManager.popAttrib();
    }

    private void renderCrystal(TERestorationApparatus te) {
        ItemStack stack = te.getCrystalStack();
        if (!stack.isEmpty()) {
            if (te.rotC >= 360.0f) {
                te.rotC = 0.0f;
            } else {
                GlStateManager.enableLighting();
                RenderHelper.enableStandardItemLighting();
                GlStateManager.pushMatrix();
                // Translate to the center of the blocks and .9 points higher
                GlStateManager.translate(.5, .25, .5);
                GlStateManager.scale(.4f, .4f, .4f);
                GlStateManager.rotate(te.rotC, 0.0f, 1.0f, 0.0f);

                Minecraft.getMinecraft().getRenderItem().renderItem(stack, ItemCameraTransforms.TransformType.NONE);

                GlStateManager.popMatrix();
                te.rotC += 0.5f;
            }
        }
    }

    private void renderEssence(TERestorationApparatus te) {
        ItemStack essenceStack = te.getEssenceStack();
        if (!essenceStack.isEmpty()) {
            if (te.rotE >= 360.0f) {
                te.rotE = 0.0f;
            } else {
                GlStateManager.enableLighting();
                RenderHelper.enableStandardItemLighting();
                GlStateManager.pushMatrix();
                // Translate to the center of the blocks and .9 points higher
                GlStateManager.translate(.5, .9, .5);
                GlStateManager.scale(.4f, .4f, .4f);
                GlStateManager.rotate(te.rotE, 0.0f, 1.0f, 0.0f);
                GlStateManager.rotate(180, 1.0f, 0.0f, 0.0f);

                Minecraft.getMinecraft().getRenderItem().renderItem(essenceStack, ItemCameraTransforms.TransformType.NONE);

                GlStateManager.popMatrix();
                te.rotE += 0.5f;
            }
        }
    }


}
