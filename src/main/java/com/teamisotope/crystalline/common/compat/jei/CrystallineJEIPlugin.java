package com.teamisotope.crystalline.common.compat.jei;

import com.teamisotope.crystalline.common.item.dyn.ItemCrystal;
import mezz.jei.api.*;
import mezz.jei.api.ingredients.IModIngredientRegistration;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;

@JEIPlugin
public class CrystallineJEIPlugin implements IModPlugin {

    @Override
    public void register(IModRegistry registry) {
        ItemCrystal.register(registry);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {

    }

    @Override
    public void registerIngredients(IModIngredientRegistration registry) {

    }

    @Override
    public void registerItemSubtypes(ISubtypeRegistry subtypeRegistry) {
        ItemCrystal.registerItemSubtypes(subtypeRegistry);
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {

    }
}
