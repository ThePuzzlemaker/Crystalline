package ext.tpz.crystalline.compat.jei;

import mezz.jei.api.gui.IDrawable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;

// Code taken from JEI Bees by bdew. (converted to Java)
public class ItemStackDrawable implements IDrawable {

    private ItemStack item;

    public ItemStackDrawable(ItemStack item) {
        this.item = item;
    }

    @Override
    public int getHeight() {
        return 16;
    }

    @Override
    public int getWidth() {
        return 16;
    }

    @Override
    public void draw(Minecraft minecraft) {
        draw(minecraft, 0, 0);
    }

    @Override
    public void draw(Minecraft minecraft, int xOffset, int yOffset) {
        GlStateManager.enableDepth();
        RenderHelper.enableGUIStandardItemLighting();
        minecraft.getRenderItem().renderItemAndEffectIntoGUI(null, item, xOffset, yOffset);
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableDepth();
    }


}
