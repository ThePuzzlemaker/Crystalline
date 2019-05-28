package ext.tpz.crystalline.recipe;

import ext.tpz.crystalline.api.recipe.IDistillationRecipe;
import ext.tpz.crystalline.recipe.distillation.*;
import ext.tpz.crystalline.recipe.distillation.RecipeCleansing;
import ext.tpz.crystalline.util.Reference;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BaseModRecipes {

    @GameRegistry.ObjectHolder(Reference.MODID + ":recipe.administration")
    public static IDistillationRecipe recipe_administration = new RecipeAdministration();

    @GameRegistry.ObjectHolder(Reference.MODID + ":recipe.cleansing")
    public static IDistillationRecipe recipe_cleansing = new RecipeCleansing();

    @GameRegistry.ObjectHolder(Reference.MODID + ":recipe.knowledge")
    public static IDistillationRecipe recipe_knowledge = new RecipeKnowledge();

    @GameRegistry.ObjectHolder(Reference.MODID + ":recipe.rift")
    public static IDistillationRecipe recipe_rift = new RecipeRift();

    @GameRegistry.ObjectHolder(Reference.MODID + ":recipe.life")
    public static IDistillationRecipe recipe_life = new RecipeLife();

    @GameRegistry.ObjectHolder(Reference.MODID + ":recipe.universe")
    public static IDistillationRecipe recipe_universe = new RecipeUniverse();

    @GameRegistry.ObjectHolder(Reference.MODID + ":recipe.hellfire")
    public static IDistillationRecipe recipe_hellfire = new RecipeHellfire();

    @GameRegistry.ObjectHolder(Reference.MODID + ":recipe.aquagust")
    public static IDistillationRecipe recipe_aquagust = new RecipeAquagust();

    @GameRegistry.ObjectHolder(Reference.MODID + ":recipe.dirtshield")
    public static IDistillationRecipe recipe_dirtshield = new RecipeDirtshield();

    @GameRegistry.ObjectHolder(Reference.MODID + ":recipe.atmosburst")
    public static IDistillationRecipe recipe_atmosburst = new RecipeAtmosburst();

    public static void registerDistillation(RegistryEvent.Register<IDistillationRecipe> e) {
        e.getRegistry().register(recipe_administration);
        e.getRegistry().register(recipe_cleansing);
        e.getRegistry().register(recipe_knowledge);
        e.getRegistry().register(recipe_rift);
        e.getRegistry().register(recipe_life);
        e.getRegistry().register(recipe_universe);
        e.getRegistry().register(recipe_hellfire);
        e.getRegistry().register(recipe_aquagust);
        e.getRegistry().register(recipe_dirtshield);
        e.getRegistry().register(recipe_atmosburst);
    }

}
