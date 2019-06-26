package ext.tpz.crystalline.proxy;

import ext.tpz.crystalline.block.CrystallineBlocks;
import ext.tpz.crystalline.compat.thaum.ThaumcraftCompat;
import ext.tpz.crystalline.entity.EntityFireThrow;
import ext.tpz.crystalline.entity.EntityObliterateBlock;
import ext.tpz.crystalline.entity.EntityObliterateEntity;
import ext.tpz.crystalline.entity.EntityWaterThrow;
import ext.tpz.crystalline.entity.render.RenderEntityFireThrow;
import ext.tpz.crystalline.entity.render.RenderEntityObliterate;
import ext.tpz.crystalline.entity.render.RenderEntityObliterateEntity;
import ext.tpz.crystalline.entity.render.RenderEntityWaterThrow;
import ext.tpz.crystalline.item.CrystallineItems;
import ext.tpz.crystalline.packet.client.InputHandler;
import ext.tpz.crystalline.packet.client.KeyBindings;
import ext.tpz.crystalline.common.util.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
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

        KeyBindings.init();
        MinecraftForge.EVENT_BUS.register(new InputHandler());

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
        ModelLoader.setCustomModelResourceLocation(CrystallineItems.cleansing_reagent, 0, new ModelResourceLocation(CrystallineItems.cleansing_reagent.getRegistryName().getResourceDomain() + ":reagents/" + CrystallineItems.cleansing_reagent.getRegistryName().getResourcePath(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(CrystallineItems.cleansing_potion, 0, new ModelResourceLocation(CrystallineItems.cleansing_potion.getRegistryName().getResourceDomain() + ":util/" + CrystallineItems.cleansing_potion.getRegistryName().getResourcePath(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(CrystallineItems.rebinding_reagent, 0, new ModelResourceLocation(CrystallineItems.rebinding_reagent.getRegistryName().getResourceDomain() + ":util/" + CrystallineItems.rebinding_reagent.getRegistryName().getResourcePath(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(new ItemBlock(CrystallineBlocks.distillationBasin), 0, new ModelResourceLocation(CrystallineBlocks.distillationBasin.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(CrystallineBlocks.distillationBasin), 0, new ModelResourceLocation(CrystallineBlocks.distillationBasin.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(new ItemBlock(CrystallineBlocks.restorationApparatus), 0, new ModelResourceLocation(CrystallineBlocks.restorationApparatus.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(CrystallineBlocks.restorationApparatus), 0, new ModelResourceLocation(CrystallineBlocks.restorationApparatus.getRegistryName(), "inventory"));
        CrystallineBlocks.distillationBasin.initTESR();
        CrystallineBlocks.restorationApparatus.initTESR();
        CrystallineItems.essence_bottle.initModel();
        CrystallineItems.reagent.initModel();
        CrystallineItems.essence_powder.initModel();
        RenderingRegistry.registerEntityRenderingHandler(EntityObliterateBlock.class, RenderEntityObliterate.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityObliterateEntity.class, RenderEntityObliterateEntity.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityFireThrow.class, RenderEntityFireThrow.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityWaterThrow.class, RenderEntityWaterThrow.FACTORY);
    }

}