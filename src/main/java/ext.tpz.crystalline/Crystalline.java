package ext.tpz.crystalline;

import ext.tpz.crystalline.proxy.CommonProxy;
import ext.tpz.crystalline.util.Reference;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = Reference.MODID, name = Reference.MODNAME, version = Reference.MODVER, dependencies = Reference.MODDEPS, useMetadata = true)
public class Crystalline {

    @SidedProxy(clientSide = Reference.PROXYCLIENT, serverSide = Reference.PROXYSERVER)
    public static CommonProxy proxy;

    @Mod.Instance
    public static Crystalline instance;

    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        logger = e.getModLog();
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
