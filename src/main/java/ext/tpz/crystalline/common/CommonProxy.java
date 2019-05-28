package ext.tpz.crystalline.common;

import ext.tpz.crystalline.api.insanity.InsanityHandler;
import ext.tpz.crystalline.common.capabilities.CCapabilities;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod.EventBusSubscriber
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent e) {

        CCapabilities.register();


        MinecraftForge.EVENT_BUS.register(new InsanityHandler());
    }

    public void init(FMLInitializationEvent e) {

    }

    public void postInit(FMLPostInitializationEvent e) {

    }

}
