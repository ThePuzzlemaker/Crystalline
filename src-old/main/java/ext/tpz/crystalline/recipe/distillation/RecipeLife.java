package ext.tpz.crystalline.recipe.distillation;

import ext.tpz.crystalline.api.essence.liquid.IEssenceLiquid;
import ext.tpz.crystalline.api.recipe.IDistillationRecipe;
import ext.tpz.crystalline.essences.liquid.BaseModEssenceLiquids;
import ext.tpz.crystalline.common.util.Reference;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RecipeLife implements IDistillationRecipe {

    @Override
    public ItemStack getInput() {
        return new ItemStack(Items.GOLDEN_APPLE, 4);
    }

    @Override
    public IEssenceLiquid getOutput() {
        return BaseModEssenceLiquids.essence_liquid_life;
    }

    @Override
    public Class<IDistillationRecipe> getRegistryType() {
        return IDistillationRecipe.class;
    }

    @Nullable
    @Override
    public ResourceLocation getRegistryName() {
        return new ResourceLocation(Reference.MODID + ":recipe.life");
    }

    @Override
    public IDistillationRecipe setRegistryName(ResourceLocation name) {
        return this;
    }
}
