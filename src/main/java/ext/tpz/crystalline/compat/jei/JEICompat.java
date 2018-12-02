package ext.tpz.crystalline.compat.jei;

import com.google.common.collect.Lists;
import ext.tpz.crystalline.api.crystal.ICrystal;
import ext.tpz.crystalline.api.essence.liquid.IEssenceLiquid;
import ext.tpz.crystalline.api.essence.powder.IEssencePowder;
import ext.tpz.crystalline.api.reagent.IReagent;
import ext.tpz.crystalline.api.recipe.DistillationRegistry;
import ext.tpz.crystalline.api.recipe.IDistillationRecipe;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import static ext.tpz.crystalline.item.CrystallineItems.*;

@JEIPlugin
public class JEICompat implements IModPlugin {

    @Override
    public void registerItemSubtypes(ISubtypeRegistry r) {
        ISubtypeRegistry.ISubtypeInterpreter reagentSubtypeInterpreter = itemStack -> {
            IReagent reagentType = reagent.getType(itemStack);
            return reagentType == null ? ISubtypeRegistry.ISubtypeInterpreter.NONE : reagentType.getRegistryName().toString();
        };
        ISubtypeRegistry.ISubtypeInterpreter crystalSubtypeInterpreter = itemStack -> {
            ICrystal crystalType = crystal.getType(itemStack);
            return crystalType == null ? ISubtypeRegistry.ISubtypeInterpreter.NONE : crystalType.getRegistryName().toString();
        };
        ISubtypeRegistry.ISubtypeInterpreter essencePowderSubtypeInterpreter = itemStack -> {
            IEssencePowder essencePowderType = essence_powder.getType(itemStack);
            return essencePowderType == null ? ISubtypeRegistry.ISubtypeInterpreter.NONE : essencePowderType.getRegistryName().toString();
        };
        ISubtypeRegistry.ISubtypeInterpreter essenceBottleSubtypeInterpreter = itemStack -> {
            IEssenceLiquid essenceBottleType = essence_bottle.getType(itemStack);
            return essenceBottleType == null ? ISubtypeRegistry.ISubtypeInterpreter.NONE : essenceBottleType.getRegistryName().toString();
        };
        r.registerSubtypeInterpreter(reagent, reagentSubtypeInterpreter);
        r.registerSubtypeInterpreter(crystal, crystalSubtypeInterpreter);
        r.registerSubtypeInterpreter(essence_powder, essencePowderSubtypeInterpreter);
        r.registerSubtypeInterpreter(essence_bottle, essenceBottleSubtypeInterpreter);
    }

    @Override
    public void register(IModRegistry r) {
        // See mezz/JustEnoughItems/#1413
        /*IIngredientBlacklist blacklist = r.getJeiHelpers().getIngredientBlacklist();
        ItemStack invalidReagent = new ItemStack(reagent);
        CrystallineItems.reagent.setType(invalidReagent, BaseModReagents.reagent_null);
        ItemStack invalidEssencePowder = new ItemStack(essence_powder);
        CrystallineItems.essence_powder.setType(invalidReagent, BaseModEssencePowders.essence_powder_null);
        blacklist.addIngredientToBlacklist(invalidReagent);
        blacklist.addIngredientToBlacklist(invalidEssencePowder);*/
        ArrayList<DistillationRecipeWrapper> distillationRecipes = new ArrayList<>();
        for (IDistillationRecipe recipe : DistillationRegistry.getRegistry()) {
            distillationRecipes.add(new DistillationRecipeWrapper(recipe));
        }
        r.addRecipes(distillationRecipes, "crystalline:category_distillation");
        ItemStack stackRiftEssencePowder = new ItemStack(CrystallineItems.essence_powder, 8);
        essence_powder.setType(stackRiftEssencePowder, BaseModEssencePowders.essence_powder_rift);
        r.addRecipes(Lists.newArrayList(new InsanityEvent(70, 100, stackRiftEssencePowder, r.getJeiHelpers().getGuiHelper())), "crystalline:category_insanity_event");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration r) {
        r.addRecipeCategories(new RecipeCategoryDistillation(r.getJeiHelpers().getGuiHelper(), new ItemStack(CrystallineBlocks.distillationBasin)));
        r.addRecipeCategories(new RecipeCategoryInsanityEvent(r.getJeiHelpers().getGuiHelper()));
    }
}
