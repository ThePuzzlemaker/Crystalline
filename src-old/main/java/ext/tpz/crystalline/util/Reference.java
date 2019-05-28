package ext.tpz.crystalline.util;

import ext.tpz.crystalline.item.CrystallineItems;

public class Reference {

    public static final String MODID = "crystalline";
    public static final String MODVER = "1.12.2-git";
    public static final String MODNAME = "Crystalline";
    public static final String MODDEPS = "required-after:forge@[14.23.5.2838,);required-after:patchouli@[1.0-19,)";

    public static final String PROXYSERVER = "ext.tpz.crystalline.proxy.ServerProxy";
    public static final String PROXYCLIENT = "ext.tpz.crystalline.proxy.ClientProxy";

    public static final String CRYSTAL_MODEL_BASE = CrystallineItems.crystal.getRegistryName().getResourceDomain() + ":crystals/" + CrystallineItems.crystal.getRegistryName().getResourcePath();
    public static final String REAGENT_MODEL_BASE = CrystallineItems.reagent.getRegistryName().getResourceDomain() + ":reagents/" + CrystallineItems.reagent.getRegistryName().getResourcePath();
    public static final String POWDER_MODEL_BASE = CrystallineItems.essence_powder.getRegistryName().getResourceDomain() + ":essences/powders/" + CrystallineItems.essence_powder.getRegistryName().getResourcePath();
    public static final String BOTTLE_MODEL_BASE = CrystallineItems.essence_bottle.getRegistryName().getResourceDomain() + ":essences/bottles/" + CrystallineItems.essence_bottle.getRegistryName().getResourcePath();

}
