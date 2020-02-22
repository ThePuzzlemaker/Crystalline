package com.teamisotope.crystalline.items;

import com.teamisotope.crystalline.Crystalline;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CrystallineItems {

    @ObjectHolder("crystalline:arcanummineralecrystal")
    public static Item arcanummineralecrystal;

    @SubscribeEvent
    public static void onItemsRegistry(final RegistryEvent.Register<Item> itemRegistryEvent) {
        IForgeRegistry<Item> registry = itemRegistryEvent.getRegistry();
        Item.Properties mainGroup = new Item.Properties().group(Crystalline.setup.crystallineMainGroup);
        registry.register(new Item(mainGroup).setRegistryName("arcanummineralecrystal"));
    }

    /*@SubscribeEvent
    this is just testing code for now
    kill me now
    public static void onModelBakeEvent(ModelBakeEvent event) {
        try {
            IUnbakedModel model = ModelLoaderRegistry.getModelOrMissing(new ResourceLocation("crystalline:block/crystal.obj"));
            if (model instanceof OBJModel) {
                OBJModel objModel = (OBJModel)model;
                OBJModel.MaterialLibrary newMtlLib = CrystallineOBJHelper.replaceMaterials(objModel, new ResourceLocation("minecraft:models/block/crystal_yellow.mtl"));
                IResourceManager irm = CrystallineOBJHelper.getIRMFromOBJLoader();
                IUnbakedModel newModel = new CrystallineOBJHelper.CrystallineOBJParser(irm.getResource(new ResourceLocation("crystalline:models/block/crystal.obj")), irm).parse(newMtlLib);
                IBakedModel bakedModel = newModel.bake(event.getModelLoader(), ModelLoader.defaultTextureGetter(), new BasicState(newModel.getDefaultState(), false), DefaultVertexFormats.ITEM);
                event.getModelRegistry().put(new ModelResourceLocation("stick", "inventory"), bakedModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

}
