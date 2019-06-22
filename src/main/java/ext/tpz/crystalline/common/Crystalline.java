package ext.tpz.crystalline.common;

import ext.tpz.crystalline.api.CStatic;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = CStatic.MODID, name = CStatic.MODNAME, version = CStatic.MODVER, dependencies = CStatic.MODDEPS, useMetadata = true)
public class Crystalline {

    @SidedProxy(clientSide = CStatic.PROXY_CLIENT, serverSide = CStatic.PROXY_SERVER)
    public static CommonProxy proxy;

    @Mod.Instance
    public static Crystalline instance;

    public static final Logger logger = LogManager.getLogger(CStatic.MODID);

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        proxy.preInit(e);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }

}
