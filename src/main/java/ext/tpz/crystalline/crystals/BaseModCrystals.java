package ext.tpz.crystalline.crystals;

import ext.tpz.crystalline.api.crystal.ICrystal;
import ext.tpz.crystalline.reagents.BaseModReagents;
import ext.tpz.crystalline.util.Reference;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BaseModCrystals {

    @GameRegistry.ObjectHolder(Reference.MODID + ":crystal.knowledge")
    public static ICrystal knowledge_crystal = new CrystalKnowledge();

    @GameRegistry.ObjectHolder(Reference.MODID + ":crystal.null")
    public static ICrystal null_crystal = new CrystalNull();

    public static void register(RegistryEvent.Register<ICrystal> e) {
        e.getRegistry().register(BaseModCrystals.knowledge_crystal);
        e.getRegistry().register(BaseModCrystals.null_crystal);
    }

}
