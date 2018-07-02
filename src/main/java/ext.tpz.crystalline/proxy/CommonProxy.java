package ext.tpz.crystalline.proxy;


import ext.tpz.crystalline.compat.thaum.ThaumcraftCompat;
import ext.tpz.crystalline.item.ItemCrystal;
import ext.tpz.crystalline.packet.common.PacketHandler;
import ext.tpz.crystalline.util.EventHandlers;
import ext.tpz.crystalline.util.config.Config;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

@Mod.EventBusSubscriber
public class CommonProxy {

    public static Configuration config;

    public void preInit(FMLPreInitializationEvent e) {
        File directory = e.getModConfigurationDirectory();
        config = new Configuration(new File(directory.getPath(), "crystalline.cfg"));
        Config.readConfig();

        PacketHandler.registerMessages("crystalline");

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

    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> e) {
        e.getRegistry().register(new ItemCrystal());
    }

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> e) {

    }



}
