package ext.tpz.crystalline.recipe.distillation;

import ext.tpz.crystalline.api.essence.liquid.IEssenceLiquid;
import ext.tpz.crystalline.api.recipe.IDistillationRecipe;
import ext.tpz.crystalline.essences.liquid.BaseModEssenceLiquids;
import ext.tpz.crystalline.common.util.Reference;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RecipeHellfire implements IDistillationRecipe {

    @Override
    public ItemStack getInput() {
        return new ItemStack(Items.NETHER_WART, 8);
    }

    @Override
    public IEssenceLiquid getOutput() {
        return BaseModEssenceLiquids.essence_liquid_hellfire;
    }

    @Override
    public Class<IDistillationRecipe> getRegistryType() {
        return IDistillationRecipe.class;
    }

    @Nullable
    @Override
    public ResourceLocation getRegistryName() {
        return new ResourceLocation(Reference.MODID + ":recipe.hellfire");
    }

    @Override
    public IDistillationRecipe setRegistryName(ResourceLocation name) {
        return this;
    }
}
