package ext.tpz.crystalline.compat.jei;

import ext.tpz.crystalline.api.recipe.IDistillationRecipe;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import static ext.tpz.crystalline.item.CrystallineItems.essence_bottle;

public class InsanityEvent implements IRecipeWrapper {

    private int insanityAmountMin;
    private int insanityAmountMax;
    private ItemStack givenItem;
    private IGuiHelper guiHelper;

    public InsanityEvent(int insanityAmountMin, int insanityAmountMax, ItemStack givenItem, IGuiHelper guiHelper) {
        this.insanityAmountMin = insanityAmountMin;
        this.insanityAmountMax = insanityAmountMax;
        this.givenItem = givenItem;
        this.guiHelper = guiHelper;
    }

    public int getInsanityAmountMin() {
        return insanityAmountMin;
    }

    public int getInsanityAmountMax() {
        return insanityAmountMax;
    }

    public ItemStack getGivenItem() {
        return givenItem;
    }

    @Override
    public void getIngredients(IIngredients i) {
        i.setOutput(VanillaTypes.ITEM, givenItem);
    }

    @Override
    public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        new ProgressBar(54, 11, 102, 5, 100, 3, insanityAmountMin, 0xFFFFFF, new ResourceLocation("crystalline", "textures/gui/jei/progress_bar.png"), false, guiHelper).draw(mouseX, mouseY);
        new Label(55, 51, "x"+givenItem.getCount(), 0xFFFFFF, false).draw(mouseX, mouseY);
        new Label(7, 10, "Insanity: ", 0xFFFFFF, false).draw(mouseX, mouseY);
        new Label(7, 37, "Items:", 0xFFFFFF, false).draw(mouseX, mouseY);
    }

}
