package ext.tpz.crystalline.util;

import ext.tpz.crystalline.item.CrystallineItems;

public class Reference {

    public static final String MODID = "crystalline";
    public static final String MODVER = "1.0.0";
    public static final String MODNAME = "Crystalline";
    public static final String MODDEPS = "required-after:forge@[11.16.0.1865,);before:guideapi@[1.12-2.1.5-60,)";

    public static final String PROXYSERVER = "ext.tpz.crystalline.proxy.ServerProxy";
    public static final String PROXYCLIENT = "ext.tpz.crystalline.proxy.ClientProxy";

    public static final String CRYSTAL_MODEL_BASE = CrystallineItems.crystal.getRegistryName().getResourceDomain() + ":/crystals/" + CrystallineItems.crystal.getRegistryName().getResourceDomain();

}
