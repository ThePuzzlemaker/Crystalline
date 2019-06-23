package ext.tpz.crystalline.common.block.tuning;

import ext.tpz.crystalline.api.CStatic;
import ext.tpz.crystalline.common.network.PacketHandler;
import ext.tpz.crystalline.common.network.PacketTMFreqChange;
import ext.tpz.crystalline.common.network.PacketTMTest;
import ext.tpz.crystalline.common.network.PacketTMTune;
import ext.tpz.crystalline.common.util.BalancingEquations;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;

@SideOnly(Side.CLIENT)
public class TuningMachineGUI extends GuiContainer {

    public static final int WIDTH = 180;
    public static final int HEIGHT = 152;

    private int tuneX, tuneY, tuneW, tuneH = 0;
    private int testX, testY, testW, testH = 0;
    private int freqX, freqY, freqW = 0;
    private int freqOffset, freqBtnOffset = 0;
    private int freqBtnX, freqIncY, freqDecY = 0;
    private int freqBtnClickX = 0;
    private int texExtra = 0;

    private int texIncBtnY, texIncBtnHvrY = 0;
    private int texDecBtnY, texDecBtnHvrY = 0;
    private int freqBtnW, freqBtnH = 0;

    private int difference = 0;
    private int indicY = 0;
    private int finalIndicY = 0;

    private static final ResourceLocation background = new ResourceLocation(CStatic.MODID, "textures/gui/tuningmachine.png");

    private TETuningMachine te;

    public TuningMachineGUI(TETuningMachine tileEntity, TuningMachineContainer container) {
        super(container);

        this.te = tileEntity;

        xSize = WIDTH;
        ySize = HEIGHT;

    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        if (mouseButton == 0) {
            if (isInRange(tuneX, tuneY, tuneW, tuneH, mouseX, mouseY)) {
                PacketHandler.INSTANCE.sendToServer(new PacketTMTune(te.getPos()));
            } else if (isInRange(testX, testY, testW, testH, mouseX, mouseY)) {
                PacketHandler.INSTANCE.sendToServer(new PacketTMTest(te.getPos()));
            } else {
                for (int i = 0; i < 7; i++) {
                    if (isInRange(freqBtnClickX - (freqBtnOffset * i), freqIncY, freqBtnW, freqBtnH, mouseX, mouseY)) {
                        if (!(Math.rint(te.getCurrentFrequency() + (int)Math.rint(Math.pow(10, i))) > 9999999)) {
                            PacketHandler.INSTANCE.sendToServer(new PacketTMFreqChange(te.getPos(), (int)Math.rint((Math.pow(10, i)))));
                        }
                    }
                }
                for (int i = 0; i < 7; i++) {
                    if (isInRange(freqBtnClickX - (freqBtnOffset * i), freqDecY, freqBtnW, freqBtnH, mouseX, mouseY)) {
                        if (!(Math.rint(te.getCurrentFrequency() - (int)Math.rint(Math.pow(10, i))) < 0)) {
                            PacketHandler.INSTANCE.sendToServer(new PacketTMFreqChange(te.getPos(), (int)-Math.rint((Math.pow(10, i)))));
                        }
                    }
                }
                super.mouseClicked(mouseX, mouseY, mouseButton);
            }
        } else {
            super.mouseClicked(mouseX, mouseY, mouseButton);
        }
    }

    private boolean isInRange(int x, int y, int w, int h, int mx, int my) {
        return (mx > x && mx < x + w && my > y && my < y + h);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        // Set draw-scope constants and render main background
        renderTexturedModalRect(background, guiLeft, guiTop, 0, 0, xSize, ySize);
        setDrawConstants(guiLeft, guiTop);

        // Draw labels
        if (isInRange(tuneX, tuneY, tuneW, tuneH, mouseX, mouseY)) {
            drawTextCenteredShadow(tuneX + tuneW / 2, tuneY + tuneH / 2 - 4, "TUNE", 0xddddff);
        } else {
            drawTextCenteredShadow(tuneX + tuneW / 2, tuneY + tuneH / 2 - 4, "TUNE", 0xffffff);
        }

        if (isInRange(testX, testY, testW, testH, mouseX, mouseY)) {
            drawTextCenteredShadow(testX + testW / 2, testY + testH / 2 - 4, "TEST", 0xddddff);
        } else {
            drawTextCenteredShadow(testX + testW / 2, testY + testH / 2 - 4, "TEST", 0xffffff);
        }

        // Iterate indicator's position and draw the indicator
        iterateIndicatorPos(guiLeft, guiTop);
        renderTexturedModalRect(background, guiLeft + 144, indicY, texExtra, 0, 7, 1);

        // Draw frequency numbers and buttons
        drawFrequency(te.getCurrentFrequency());
        drawButtons(mouseX, mouseY);

    }

    private void drawButtons(int mx, int my) {
        for (int i = 0; i < 7; i++) {
            if (isInRange(freqBtnX + (freqBtnOffset * i), freqIncY, freqBtnW, freqBtnH, mx, my)) {
                renderTexturedModalRect(background, freqBtnX + (freqOffset * i), freqIncY, texExtra, texIncBtnHvrY, freqBtnW, freqBtnH);
            } else {
                renderTexturedModalRect(background, freqBtnX + (freqOffset * i), freqIncY, texExtra, texIncBtnY, freqBtnW, freqBtnH);
            }
        }
        for (int i = 0; i < 7; i++) {
            if (isInRange(freqBtnX + (freqBtnOffset * i), freqDecY, freqBtnW, freqBtnH, mx, my)) {
                renderTexturedModalRect(background, freqBtnX + (freqOffset * i), freqDecY, texExtra, texDecBtnHvrY, freqBtnW, freqBtnH);
            } else {
                renderTexturedModalRect(background, freqBtnX + (freqOffset * i), freqDecY, texExtra, texDecBtnY, freqBtnW, freqBtnH);
            }
        }
    }



    private void setDrawConstants(int guiLeft, int guiTop) {
        tuneX = guiLeft + 49;
        tuneY = guiTop + 32;
        tuneW = 38;
        tuneH = 14;

        testX = guiLeft + 92;
        testY = guiTop + 32;
        testW = 38;
        testH = 14;

        freqX = guiLeft + 63;
        freqY = guiTop + 13;
        freqW = 5;

        freqOffset = freqW + 3;
        freqBtnOffset = freqBtnW + 1;

        freqBtnX = guiLeft + 62;
        freqIncY = guiTop + 6;
        freqDecY = guiTop + 23;
        freqBtnClickX = guiLeft + 110;

        freqBtnW = 7;
        freqBtnH = 4;

        texExtra = 180;

        texIncBtnY = 1;
        texDecBtnY = 5;

        texIncBtnHvrY = 9;
        texDecBtnHvrY = 13;

        finalIndicY = guiTop + 35 + MathHelper.clamp(te.getCurrentDifference(), -30, 30);
    }

    private void drawFrequency(int freq) {
        char[] freqChs = padInteger(freq, 7).toCharArray();
        for (int i = 0; i < freqChs.length; i++) {
            drawText(freqX + (freqOffset * i), freqY, Character.toString(freqChs[i]), 0xffffff);
        }
    }

    private void drawText(int x, int y, String text, int color) {
        mc.fontRenderer.drawString(text, x, y, color);
    }

    private void drawTextShadow(int x, int y, String text, int color) {
        mc.fontRenderer.drawString(text, x, y, color, true);
    }

    private void drawTextCentered(int x, int y, String text) {
        drawText(x - (mc.fontRenderer.getStringWidth(text) / 2), y, text, 0xffffff);
    }

    private void drawTextCenteredShadow(int x, int y, String text, int color) {
        drawTextShadow(x - (mc.fontRenderer.getStringWidth(text) / 2), y, text, color);
    }

    private void iterateIndicatorPos(int guiLeft, int guiTop) {
        if (indicY < guiTop + 5 || indicY > guiTop + 565)
            indicY = guiTop + 35;

        if (indicY < finalIndicY) {
            indicY++;
        } else if (indicY > finalIndicY) {
            indicY--;
        }
    }

    private void renderTexturedModalRect(ResourceLocation tex, int x, int y, int texx, int texy, int sizex, int sizey) {
        mc.getTextureManager().bindTexture(tex);
        drawTexturedModalRect(x, y, texx, texy, sizex, sizey);
    }

    private String padInteger(int in, int padLength) {
        return String.format("%0" + padLength + "d", in);
    }



}
