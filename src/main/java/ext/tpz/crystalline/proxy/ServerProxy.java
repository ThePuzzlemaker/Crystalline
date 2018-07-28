package ext.tpz.crystalline.proxy;

import ext.tpz.crystalline.compat.thaum.ThaumcraftCompat;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ServerProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent e) {
        if (Loader.isModLoaded("thaumcraft")) {
            ThaumcraftCompat.serverPre(e);
        }
    }

    @Override
    public void init(FMLInitializationEvent e) {
        if (Loader.isModLoaded("thaumcraft")) {
            ThaumcraftCompat.serverInit(e);
        }
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {
        if (Loader.isModLoaded("thaumcraft")) {
            ThaumcraftCompat.serverPost(e);
        }
    }

}
