package ext.tpz.crystalline.common.block.tuning;

import ext.tpz.crystalline.api.CStatic;
import ext.tpz.crystalline.common.gui.Label;
import ext.tpz.crystalline.common.network.PacketHandler;
import ext.tpz.crystalline.common.network.PacketTMTest;
import ext.tpz.crystalline.common.network.PacketTMTune;
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
        drawTextCentered(tuneX + tuneW / 2, tuneY + tuneH / 2 - 4, "TUNE");
        drawTextCentered(testX + testW / 2, testY + testH / 2 - 4, "TEST");

        // Iterate indicator's position and draw the indicator
        iterateIndicatorPos(guiLeft, guiTop);
        drawHorizontalLine(guiLeft + 144, guiLeft + 144 + 7, indicY, 0x000000);


    }

    private void setDrawConstants(int guiLeft, int guiTop) {
        tuneX = guiLeft + 50;
        tuneY = guiTop + 31;
        tuneW = 38;
        tuneH = 14;

        testX = guiLeft + 92;
        testY = guiTop + 31;
        testW = 38;
        testH = 14;

        finalIndicY = guiTop + 35 + MathHelper.clamp(te.getCurrentDifference(), -30, 30);
    }

    private void drawText(int x, int y, String text) {
        mc.fontRenderer.drawString(text, x, y, 0x000000);
    }

    private void drawTextCentered(int x, int y, String text) {
        drawText(x - (mc.fontRenderer.getStringWidth(text) / 2), y, text);
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



}
