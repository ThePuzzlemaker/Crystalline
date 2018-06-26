package ext.tpz.crystalline.proxy;

import ext.tpz.crystalline.compat.thaum.ThaumcraftCompat;
import ext.tpz.crystalline.item.CrystallineItems;
import ext.tpz.crystalline.item.ItemCrystal;
import ext.tpz.crystalline.packet.client.InputHandler;
import ext.tpz.crystalline.packet.client.KeyBindings;
import ext.tpz.crystalline.util.Reference;
import net.minecraftforge.client.event.ModelRegistryEvent;
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
    }

}