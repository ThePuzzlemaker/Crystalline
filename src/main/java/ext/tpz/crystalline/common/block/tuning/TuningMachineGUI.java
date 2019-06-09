package ext.tpz.crystalline.common.block.tuning;

import ext.tpz.crystalline.api.CStatic;
import ext.tpz.crystalline.common.Crystalline;
import ext.tpz.crystalline.common.block.tuning.TETuningMachine;
import ext.tpz.crystalline.common.block.tuning.TuningMachineContainer;
import ext.tpz.crystalline.common.gui.Label;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.io.IOException;

public class TuningMachineGUI extends GuiContainer {

    public static final int WIDTH = 180;
    public static final int HEIGHT = 152;

    private int tuneX, tuneY, tuneW, tuneH = 0;
    private int testX, testY, testW, testH = 0;

    private int difference = 0;
    private int indicY = 0;
    private int finalIndicY = 0;

    private static final ResourceLocation background = new ResourceLocation(CStatic.MODID, "textures/gui/tuningmachine.png");
    private static final ResourceLocation indicator = new ResourceLocation(CStatic.MODID, "textures/gui/tuningmachine-indicator.png");

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
            if (mouseX > tuneX && mouseX < tuneX + tuneW && mouseY > tuneY && mouseY < tuneY + tuneH) {
                // TODO: Functionality
                te.test(te.getItemStackHandler().getStackInSlot(0));
            } else if (mouseX > testX && mouseX < testX + testW && mouseY > testY && mouseY < testY + testH) {
                // TODO: Functionality
                difference = te.test(te.getItemStackHandler().getStackInSlot(0));
            } else {
                super.mouseClicked(mouseX, mouseY, mouseButton);
            }
        } else {
            super.mouseClicked(mouseX, mouseY, mouseButton);
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(background);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        tuneX = guiLeft + 50;
        tuneY = guiTop + 31;
        tuneW = 38;
        tuneH = 14;

        testX = guiLeft + 92;
        testY = guiTop + 31;
        testW = 38;
        testH = 14;

        if (indicY < guiTop + 5 || indicY > guiTop + 565)
            indicY = guiTop + 35;

        if (indicY < finalIndicY) {
            indicY++;
        } else if (indicY > finalIndicY) {
            indicY--;
        }

        mc.getTextureManager().bindTexture(indicator);
        drawTexturedModalRect(guiLeft + 144, indicY, 0, 0, 7, 1);

        finalIndicY = guiTop + 35 + MathHelper.clamp(difference, -30, 30);

        new Label.LabelCentered(tuneX + tuneW / 2, tuneY + tuneH / 2 - 4, "TUNE", 0x000000, false).draw(mouseX, mouseY);
        new Label.LabelCentered(testX + testW / 2, testY + testH / 2 - 4, "TEST", 0x000000, false).draw(mouseX, mouseY);
    }


    @Override
    public void onGuiClosed() {
        te.markClosed();
    }

}
