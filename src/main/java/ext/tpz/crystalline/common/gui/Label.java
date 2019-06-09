package ext.tpz.crystalline.common.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class Label {


    private int x;
    private int y;
    private int w;
    private int h;
    private int color;
    private String text;
    private boolean shadow;

    public Label(int x, int y, String text, int color, boolean shadow) {
        this.x = x;
        this.y = y;
        this.w = LabelHelper.fontRenderer.getStringWidth(text);
        this.h = LabelHelper.fontRenderer.FONT_HEIGHT;
        this.text = text;
        this.color = color;
        this.shadow = shadow;
    }

    public void draw(int mx, int my) {
        LabelHelper.fontRenderer.drawString(text, x, y, color, shadow);
    }

    public boolean clicked(int mx, int my) {
        return false;
    }

    public static class LabelHelper {
        public static FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
    }

    public static class LabelCentered extends Label {
        public LabelCentered(int x, int y, String text, int color, boolean shadow) {
            super(x - (LabelHelper.fontRenderer.getStringWidth(text) / 2), y, text, color, shadow);
        }
    }

}