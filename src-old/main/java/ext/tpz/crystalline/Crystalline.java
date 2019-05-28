@Mpackage ext.tpz.crystalline;

import ext.tpz.crystalline.proxy.CommonProxy;
import ext.tpz.crystalline.util.CommandCrystalline;
import ext.tpz.crystalline.util.Reference;
import ext.tpz.crystalline.util.TabCrystalline;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.server.permission.DefaultPermissionLevel;
import net.minecraftforge.server.permission.PermissionAPI;
import org.apache.logging.log4j.Logger;

@Mod(modid = Reference.MODID, name = Reference.MODNAME, version = Reference.MODVER, dependencies = Reference.MODDEPS, useMetadata = true)
public class Crystalline {

    @SidedProxy(clientSide = Reference.PROXYCLIENT, serverSide = Reference.PROXYSERVER)
    public static CommonProxy proxy;

    @Mod.Instance
    public static Crystalline instance;

    public static Logger logger;

    public static CreativeTabs tab = new TabCrystalline();

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

    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent e) {
        PermissionAPI.registerNode("crystalline.crystalline", DefaultPermissionLevel.OP, "A debug command for setting insanity or listing all crystal types.");
        e.registerServerCommand(new CommandCrystalline());
    }

}
