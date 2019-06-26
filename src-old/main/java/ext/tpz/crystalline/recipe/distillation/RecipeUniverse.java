package ext.tpz.crystalline.recipe.distillation;

import ext.tpz.crystalline.api.essence.liquid.IEssenceLiquid;
import ext.tpz.crystalline.api.recipe.IDistillationRecipe;
import ext.tpz.crystalline.essences.liquid.BaseModEssenceLiquids;
import ext.tpz.crystalline.essences.powder.BaseModEssencePowders;
import ext.tpz.crystalline.item.CrystallineItems;
import ext.tpz.crystalline.common.util.Reference;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RecipeUniverse implements IDistillationRecipe {

    ItemStack input = new ItemStack(CrystallineItems.essence_powder, 8);

    @Override
    public ItemStack getInput() {
        CrystallineItems.essence_powder.setType(input, BaseModEssencePowders.essence_powder_universe);
        return input;
    }

    @Override
    public IEssenceLiquid getOutput() {
        return BaseModEssenceLiquids.essence_liquid_universe;
    }

    @Override
    public Class<IDistillationRecipe> getRegistryType() {
        return IDistillationRecipe.class;
    }

    @Nullable
    @Override
    public ResourceLocation getRegistryName() {
        return new ResourceLocation(Reference.MODID + ":recipe.universe");
    }

    @Override
    public IDistillationRecipe setRegistryName(ResourceLocation name) {
        return this;
    }
}
