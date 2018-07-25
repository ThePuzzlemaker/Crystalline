package ext.tpz.crystalline.proxy;

import ext.tpz.crystalline.block.CrystallineBlocks;
import ext.tpz.crystalline.compat.thaum.ThaumcraftCompat;
import ext.tpz.crystalline.item.CrystallineItems;
import ext.tpz.crystalline.item.ItemCrystal;
import ext.tpz.crystalline.packet.client.InputHandler;
import ext.tpz.crystalline.packet.client.KeyBindings;
import ext.tpz.crystalline.util.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);

        OBJLoader.INSTANCE.addDomain(Reference.MODID);

        if (Loader.isModLoaded("thaumcraft")) {
            ThaumcraftCompat.clientPre(e);
        }
    }

    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);

        MinecraftForge.EVENT_BUS.register(new InputHandler());
        KeyBindings.init();

        if (Loader.isModLoaded("thaumcraft")) {
            ThaumcraftCompat.clientInit(e);
        }
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);

        if (Loader.isModLoaded("thaumcraft")) {
            ThaumcraftCompat.clientPost(e);
        }
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent e) {
        CrystallineItems.crystal.initModel();
        ModelLoader.setCustomModelResourceLocation(CrystallineItems.reagent_basic, 0, new ModelResourceLocation(CrystallineItems.reagent_basic.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(CrystallineItems.reagent_advanced, 0, new ModelResourceLocation(CrystallineItems.reagent_advanced.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(CrystallineItems.reagent_extreme, 0, new ModelResourceLocation(CrystallineItems.reagent_extreme.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(CrystallineItems.reagent_rift, 0, new ModelResourceLocation(CrystallineItems.reagent_rift.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(CrystallineItems.reagent_universe, 0, new ModelResourceLocation(CrystallineItems.reagent_universe.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(CrystallineItems.pure_rift_essence, 0, new ModelResourceLocation(CrystallineItems.pure_rift_essence.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(CrystallineItems.pure_universe_essence, 0, new ModelResourceLocation(CrystallineItems.pure_universe_essence.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(CrystallineItems.cleansing_reagent, 0, new ModelResourceLocation(CrystallineItems.cleansing_reagent.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(CrystallineItems.cleansing_potion, 0, new ModelResourceLocation(CrystallineItems.cleansing_potion.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(new ItemBlock(CrystallineBlocks.distillationBasin), 0, new ModelResourceLocation(CrystallineBlocks.distillationBasin.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(CrystallineBlocks.distillationBasin), 0, new ModelResourceLocation(CrystallineBlocks.distillationBasin.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(new ItemBlock(CrystallineBlocks.restorationApparatus), 0, new ModelResourceLocation(CrystallineBlocks.restorationApparatus.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(CrystallineBlocks.restorationApparatus), 0, new ModelResourceLocation(CrystallineBlocks.restorationApparatus.getRegistryName(), "inventory"));
        CrystallineBlocks.distillationBasin.initTESR();
        CrystallineBlocks.restorationApparatus.initTESR();
        CrystallineItems.essence_bottle.initModel();
    }

}