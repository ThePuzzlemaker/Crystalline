package ext.tpz.crystalline.compat.jei;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import static ext.tpz.crystalline.item.CrystallineItems.essence_bottle;

public class RecipeCategoryInsanityEvent implements IRecipeCategory<InsanityEvent> {

    private IGuiHelper guiHelper;

    public RecipeCategoryInsanityEvent(IGuiHelper guiHelper) {
        this.guiHelper = guiHelper;
    }

    @Override
    public IDrawable getIcon() {
        return guiHelper.drawableBuilder(new ResourceLocation("crystalline", "textures/gui/jei/icons/insanity_event.png"), 0, 0, 16, 16).setTextureSize(16, 16).build();
    }

    @Override
    public String getUid() {
        return "crystalline:category_insanity_event";
    }

    @Override
    public String getTitle() {
        return I18n.format("crystalline.jei.insanity_event.title");
    }

    @Override
    public String getModName() {
        return "Crystalline";
    }

    @Override
    public IDrawable getBackground() {
        return guiHelper.createDrawable(new ResourceLocation("crystalline", "textures/gui/jei/insanity_event.png"), 0, 0, 162, 61);
    }

    @Override
    public void setRecipe(IRecipeLayout layout, InsanityEvent event, IIngredients ingredients) {
        IGuiItemStackGroup itemStacks = layout.getItemStacks();
        itemStacks.init(0, false, 55, 32);
        ItemStack i = event.getGivenItem().copy();
        i.setCount(1);
        itemStacks.set(0, i);
    }
}
