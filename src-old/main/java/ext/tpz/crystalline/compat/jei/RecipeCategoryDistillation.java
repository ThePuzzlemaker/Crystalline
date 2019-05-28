package ext.tpz.crystalline.compat.jei;

import ext.tpz.crystalline.api.recipe.IDistillationRecipe;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

import static ext.tpz.crystalline.item.CrystallineItems.essence_bottle;

public class RecipeCategoryDistillation implements IRecipeCategory<DistillationRecipeWrapper> {

    private ItemStack icon;
    private IGuiHelper guiHelper;

    public RecipeCategoryDistillation(IGuiHelper guiHelper, ItemStack icon) {
        this.icon = icon;
        this.guiHelper = guiHelper;
    }

    @Override
    public IDrawable getIcon() {
        return new ItemStackDrawable(icon);
    }

    @Override
    public String getUid() {
        return "crystalline:category_distillation";
    }

    @Override
    public String getTitle() {
        return I18n.format("crystalline.jei.distillation.title");
    }

    @Override
    public String getModName() {
        return "Crystalline";
    }

    @Override
    public IDrawable getBackground() {
        return guiHelper.createDrawable(new ResourceLocation("crystalline", "textures/gui/jei/distillation.png"), 0, 0, 162, 61);
    }

    @Override
    public void setRecipe(IRecipeLayout layout, DistillationRecipeWrapper recipe, IIngredients ingredients) {
        IGuiItemStackGroup itemStacks = layout.getItemStacks();
        itemStacks.init(0, true, 20, 22);
        itemStacks.init(1, false, 123, 22);
        ItemStack s = recipe.getRecipe().getInput().copy();
        s.setCount(1);
        itemStacks.set(0, s);
        ItemStack essenceBottle = new ItemStack(essence_bottle);
        essence_bottle.setType(essenceBottle, recipe.getRecipe().getOutput());
        itemStacks.set(1, essenceBottle);
    }
}
