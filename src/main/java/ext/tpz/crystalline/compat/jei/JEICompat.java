package ext.tpz.crystalline.compat.jei;

import ext.tpz.crystalline.api.crystal.ICrystal;
import ext.tpz.crystalline.api.essence.liquid.IEssenceLiquid;
import ext.tpz.crystalline.api.essence.powder.IEssencePowder;
import ext.tpz.crystalline.api.reagent.IReagent;
import ext.tpz.crystalline.block.CrystallineBlocks;
import ext.tpz.crystalline.crystals.BaseModCrystals;
import ext.tpz.crystalline.essences.liquid.BaseModEssenceLiquids;
import ext.tpz.crystalline.essences.powder.BaseModEssencePowders;
import ext.tpz.crystalline.item.CrystallineItems;
import ext.tpz.crystalline.reagents.BaseModReagents;
import ext.tpz.crystalline.recipe.BaseModRecipes;
import mezz.jei.api.*;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;

import java.util.Collection;
import java.util.HashMap;

import static ext.tpz.crystalline.item.CrystallineItems.*;

@JEIPlugin
public class JEICompat implements IModPlugin {

    @Override
    public void registerItemSubtypes(ISubtypeRegistry r) {
        ISubtypeRegistry.ISubtypeInterpreter reagentSubtypeInterpreter = itemStack -> {
            IReagent reagentType = reagent.getType(itemStack);
            return reagentType == null || reagentType == BaseModReagents.reagent_null ? ISubtypeRegistry.ISubtypeInterpreter.NONE : reagentType.getRegistryName().toString();
        };
        ISubtypeRegistry.ISubtypeInterpreter crystalSubtypeInterpreter = itemStack -> {
            ICrystal crystalType = crystal.getType(itemStack);
            return crystalType == null || crystalType == BaseModCrystals.null_crystal ? ISubtypeRegistry.ISubtypeInterpreter.NONE : crystalType.getRegistryName().toString();
        };
        ISubtypeRegistry.ISubtypeInterpreter essencePowderSubtypeInterpreter = itemStack -> {
            IEssencePowder essencePowderType = essence_powder.getType(itemStack);
            return essencePowderType == null || essencePowderType == BaseModEssencePowders.essence_powder_null ? ISubtypeRegistry.ISubtypeInterpreter.NONE : essence_powder.getRegistryName().toString();
        };
        ISubtypeRegistry.ISubtypeInterpreter essenceBottleSubtypeInterpreter = itemStack -> {
            IEssenceLiquid essenceBottleType = essence_bottle.getType(itemStack);
            return essenceBottleType == null || essenceBottleType == BaseModEssenceLiquids.essence_liquid_null ? ISubtypeRegistry.ISubtypeInterpreter.NONE : essence_bottle.getRegistryName().toString();
        };
        r.registerSubtypeInterpreter(reagent, reagentSubtypeInterpreter);
        r.registerSubtypeInterpreter(crystal, crystalSubtypeInterpreter);
        r.registerSubtypeInterpreter(essence_powder, essencePowderSubtypeInterpreter);
        //r.registerSubtypeInterpreter(essence_bottle, essenceBottleSubtypeInterpreter);
    }

    @Override
    public void register(IModRegistry r) {
        // See mezz/JustEnoughItems/#6
        /*IIngredientBlacklist blacklist = r.getJeiHelpers().getIngredientBlacklist();
        ItemStack invalidReagent = new ItemStack(reagent);
        CrystallineItems.reagent.setType(invalidReagent, BaseModReagents.reagent_null);
        ItemStack invalidEssencePowder = new ItemStack(essence_powder);
        CrystallineItems.essence_powder.setType(invalidReagent, BaseModEssencePowders.essence_powder_null);
        blacklist.addIngredientToBlacklist(invalidReagent);
        blacklist.addIngredientToBlacklist(invalidEssencePowder);*/
        HashMap<Integer, DistillationRecipeWrapper> map = new HashMap<>();
        map.put(0, new DistillationRecipeWrapper(BaseModRecipes.recipe_rift));
        Collection<DistillationRecipeWrapper> recipes = map.values();
        r.addRecipes(recipes, "crystalline:category_distillation");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration r) {
        r.addRecipeCategories(new RecipeCategoryDistillation(r.getJeiHelpers().getGuiHelper(), new ItemStack(CrystallineBlocks.distillationBasin)));
    }
}
