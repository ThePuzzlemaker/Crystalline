package ext.tpz.crystalline.compat.jei;

import ext.tpz.crystalline.api.recipe.IDistillationRecipe;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

import static ext.tpz.crystalline.item.CrystallineItems.essence_bottle;

public class DistillationRecipeWrapper implements IRecipeWrapper {

    private IDistillationRecipe recipe;

    public DistillationRecipeWrapper(IDistillationRecipe recipe) {
        this.recipe = recipe;
    }

    public IDistillationRecipe getRecipe() {
        return recipe;
    }

    @Override
    public void getIngredients(IIngredients i) {
        i.setInput(VanillaTypes.ITEM, recipe.getInput());
        ItemStack essenceBottle = new ItemStack(essence_bottle);
        essence_bottle.setType(essenceBottle, recipe.getOutput());
        i.setOutput(VanillaTypes.ITEM, essenceBottle);
    }

    @Override
    public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        new Label(20, 41, "x" + recipe.getInput().getCount(), 0xFFFFFF, false).draw(mouseX, mouseY);
        new Label(42, 33, "+1200 ticks", 0xFFFFFF, false).draw(mouseX, mouseY);
    }

}
