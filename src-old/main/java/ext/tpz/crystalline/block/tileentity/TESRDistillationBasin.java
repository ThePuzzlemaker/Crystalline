package ext.tpz.crystalline.block.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;

public class TESRDistillationBasin extends TileEntitySpecialRenderer<TEDistillationBasin> {

    @Override
    public void render(TEDistillationBasin te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        GlStateManager.pushAttrib();
        GlStateManager.pushMatrix();

        // Translate to the location of our tile entity
        GlStateManager.translate(x, y, z);
        GlStateManager.disableRescaleNormal();

        // Render our item
        renderItem(te);

        GlStateManager.popMatrix();
        GlStateManager.popAttrib();
    }

    private void renderItem(TEDistillationBasin te) {
        ItemStack stack = te.getStack();
        if (!stack.isEmpty()) {
            if (te.rot >= 360.0f) {
                te.rot = 0.0f;
            } else {
                RenderHelper.enableStandardItemLighting();
                GlStateManager.enableLighting();
                GlStateManager.pushMatrix();
                // Translate to the center of the blocks and .9 points higher
                GlStateManager.translate(.5, 1.20, .5);
                GlStateManager.scale(.4f, .4f, .4f);
                GlStateManager.rotate(te.rot, 0.0f, 1.0f, 0.0f);

                Minecraft.getMinecraft().getRenderItem().renderItem(stack, ItemCameraTransforms.TransformType.NONE);

                GlStateManager.popMatrix();
                te.rot += 0.5f;
            }
        }
    }


}
