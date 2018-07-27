package ext.tpz.crystalline.proxy;


import ext.tpz.crystalline.Crystalline;
import ext.tpz.crystalline.block.BlockDistillationBasin;
import ext.tpz.crystalline.block.BlockRestorationApparatus;
import ext.tpz.crystalline.block.CrystallineBlocks;
import ext.tpz.crystalline.block.tileentity.TEDistillationBasin;
import ext.tpz.crystalline.block.tileentity.TERestorationApparatus;
import ext.tpz.crystalline.compat.thaum.ThaumcraftCompat;
import ext.tpz.crystalline.entity.EntityObliterateBlock;
import ext.tpz.crystalline.item.CrystallineItems;
import ext.tpz.crystalline.packet.common.PacketHandler;
import ext.tpz.crystalline.recipe.RecipeBinding;
import ext.tpz.crystalline.recipe.RecipeCleansing;
import ext.tpz.crystalline.util.EventHandlers;
import ext.tpz.crystalline.util.Reference;
import ext.tpz.crystalline.util.config.Config;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.io.File;

@Mod.EventBusSubscriber
public class CommonProxy {

    public static Configuration config;

    public void preInit(FMLPreInitializationEvent e) {
        File directory = e.getModConfigurationDirectory();
        config = new Configuration(new File(directory.getPath(), "crystalline.cfg"));
        Config.readConfig();

        PacketHandler.registerMessages("crystalline");
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID + ":obliterate_block"), EntityObliterateBlock.class, "crystalline.entity.obliterate", 5691, Crystalline.instance, 64, 10, true);

        MinecraftForge.EVENT_BUS.register(new EventHandlers());
        if (Loader.isModLoaded("thaumcraft")) {
            ThaumcraftCompat.preInit(e);
        }
    }

    public void init(FMLInitializationEvent e) {
        if (Loader.isModLoaded("thaumcraft")) {
            ThaumcraftCompat.init(e);
        }
    }

    public void postInit(FMLPostInitializationEvent e) {
        if (config.hasChanged()) {
            config.save();
        }

        if (Loader.isModLoaded("thaumcraft")) {
            ThaumcraftCompat.postInit(e);
        }
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> e) {
        e.getRegistry().register(new BlockDistillationBasin());
        e.getRegistry().register(new BlockRestorationApparatus());
        GameRegistry.registerTileEntity(TEDistillationBasin.class, new ResourceLocation(Reference.MODID + ":distillation_basin"));
        GameRegistry.registerTileEntity(TERestorationApparatus.class, new ResourceLocation(Reference.MODID + ":restoration_apparatus"));
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> e) {
        e.getRegistry().register(CrystallineItems.crystal);
        e.getRegistry().register(CrystallineItems.reagent_basic);
        e.getRegistry().register(CrystallineItems.reagent_advanced);
        e.getRegistry().register(CrystallineItems.reagent_extreme);
        e.getRegistry().register(CrystallineItems.reagent_rift);
        e.getRegistry().register(CrystallineItems.reagent_universe);
        e.getRegistry().register(CrystallineItems.pure_rift_essence);
        e.getRegistry().register(CrystallineItems.pure_universe_essence);
        e.getRegistry().register(CrystallineItems.cleansing_reagent);
        e.getRegistry().register(CrystallineItems.cleansing_potion);
        e.getRegistry().register(new ItemBlock(CrystallineBlocks.distillationBasin).setRegistryName(CrystallineBlocks.distillationBasin.getRegistryName()));
        e.getRegistry().register(new ItemBlock(CrystallineBlocks.restorationApparatus).setRegistryName(CrystallineBlocks.restorationApparatus.getRegistryName()));
        e.getRegistry().register(CrystallineItems.essence_bottle);
    }

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> e) {
        e.getRegistry().register(new RecipeCleansing());
        e.getRegistry().register(new RecipeBinding());
    }



}
