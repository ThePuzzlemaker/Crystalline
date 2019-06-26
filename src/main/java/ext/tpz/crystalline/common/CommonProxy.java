package ext.tpz.crystalline.common;

import ext.tpz.crystalline.api.CStatic;
import ext.tpz.crystalline.common.capabilities.CCapabilities;
import ext.tpz.crystalline.common.gui.GuiProxy;
import ext.tpz.crystalline.common.network.PacketHandler;
import ext.tpz.crystalline.common.util.OreGen;
import ext.tpz.crystalline.common.util.Recipes;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent e) {
        CCapabilities.register();
        OBJLoader.INSTANCE.addDomain(CStatic.MODID);
    }

    public void init(FMLInitializationEvent e) {
        NetworkRegistry.INSTANCE.registerGuiHandler(Crystalline.instance, new GuiProxy());
        PacketHandler.registerMessages(CStatic.MODID);
        Recipes.init();
        GameRegistry.registerWorldGenerator(new OreGen(), 0);
    }

    public void postInit(FMLPostInitializationEvent e) {

    }

}
