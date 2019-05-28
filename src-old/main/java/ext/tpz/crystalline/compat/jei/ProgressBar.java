package ext.tpz.crystalline.compat.jei;

import mezz.jei.api.IGuiHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;

public class ProgressBar {

    private int x;
    private int y;
    private int w;
    private int h;
    private int textX;
    private int textY;
    private int labelColor;
    private ResourceLocation barTexture;
    private boolean labelShadow;
    private int value;
    private IGuiHelper guiHelper;
    private int barHeight;
    private int barWidth;

    public ProgressBar(int x, int y, int w, int h, int barWidth, int barHeight, int value, int labelColor, ResourceLocation barTexture, boolean labelShadow, IGuiHelper guiHelper) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.barHeight = barHeight;
        this.barWidth = barWidth;
        this.textY = (y + h) + 2;
        this.value = value;
        this.labelColor = labelColor;
        this.barTexture = barTexture;
        this.labelShadow = labelShadow;
        this.guiHelper = guiHelper;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void draw(int mx, int my) {
        this.textX = (x + w) - Minecraft.getMinecraft().fontRenderer.getStringWidth(value + "+%");
        new Label(textX, textY, value + "+%", labelColor, labelShadow).draw(mx, my);
        guiHelper.createDrawable(barTexture, 0, 0, (value), barHeight).draw(Minecraft.getMinecraft(), x + (w - barWidth), y + (h - barHeight));
    }

}
