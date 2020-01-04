package com.teamisotope.crystalline.common.item;

import com.teamisotope.crystalline.common.Crystalline;
import com.teamisotope.crystalline.common.item.dyn.ItemCrystal;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class CItems {

    @GameRegistry.ObjectHolder("crystalline:crystal")
    public static ItemCrystal crystal;

    @GameRegistry.ObjectHolder("crystalline:arcanummineralecrystal")
    public static Item arcanumMineraleCrystal;

    @GameRegistry.ObjectHolder("crystalline:arcaneknowledge")
    public static Item arcaneKnowledge;

    @GameRegistry.ObjectHolder("crystalline:crystalbase")
    public static Item crystalBase;

    @GameRegistry.ObjectHolder("crystalline:mortarandpestle")
    public static Item mortarAndPestle;

    @GameRegistry.ObjectHolder("crystalline:tuningcomponents")
    public static Item tuningComponents;

    @GameRegistry.ObjectHolder("crystalline:seismicresonator")
    public static Item seismicResonator;

    @GameRegistry.ObjectHolder("crystalline:glasstube")
    public static Item glassTube;

    @GameRegistry.ObjectHolder("crystalline:mysterioussolvent")
    public static Item mysteriousSolvent;

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> e) {
        e.getRegistry().register(new ItemCrystal());
        e.getRegistry().register(new Item().setRegistryName("crystalline:arcanummineralecrystal").setUnlocalizedName("crystalline.arcanummineralecrystal").setCreativeTab(Crystalline.tab));
        e.getRegistry().register(new Item().setRegistryName("crystalline:crystalbase").setUnlocalizedName("crystalline.crystalbase").setCreativeTab(Crystalline.tab));
        Item map = new Item().setRegistryName("crystalline:mortarandpestle").setUnlocalizedName("crystalline.mortarandpestle").setCreativeTab(Crystalline.tab).setMaxStackSize(1);
        e.getRegistry().register(map.setContainerItem(map));
        e.getRegistry().register(new Item().setRegistryName("crystalline:tuningcomponents").setUnlocalizedName("crystalline.tuningcomponents").setCreativeTab(Crystalline.tab));
        e.getRegistry().register(new Item().setRegistryName("crystalline:seismicresonator").setUnlocalizedName("crystalline.seismicresonator").setCreativeTab(Crystalline.tab));
        e.getRegistry().register(new Item().setRegistryName("crystalline:arcaneknowledge").setUnlocalizedName("crystalline.arcaneknowledge").setCreativeTab(Crystalline.tab));
        e.getRegistry().register(new Item().setRegistryName("crystalline:glasstube").setUnlocalizedName("crystalline.glasstube").setCreativeTab(Crystalline.tab));
        e.getRegistry().register(new Item().setRegistryName("crystalline:mysterioussolvent").setUnlocalizedName("crystalline.mysterioussolvent").setCreativeTab(Crystalline.tab));
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerModels(ModelRegistryEvent e) {
        crystal.initModel();
        ModelLoader.setCustomModelResourceLocation(arcanumMineraleCrystal, 0, new ModelResourceLocation(arcanumMineraleCrystal.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(crystalBase, 0, new ModelResourceLocation(crystalBase.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(mortarAndPestle, 0, new ModelResourceLocation(mortarAndPestle.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(tuningComponents, 0, new ModelResourceLocation(tuningComponents.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(seismicResonator, 0, new ModelResourceLocation(seismicResonator.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(arcaneKnowledge, 0, new ModelResourceLocation(arcaneKnowledge.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(glassTube, 0, new ModelResourceLocation(glassTube.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(mysteriousSolvent, 0, new ModelResourceLocation(mysteriousSolvent.getRegistryName(), "inventory"));
    }

}
