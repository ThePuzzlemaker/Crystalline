package ext.tpz.crystalline.crystals;

import ext.tpz.crystalline.api.crystal.ICrystal;
import ext.tpz.crystalline.reagents.BaseModReagents;
import ext.tpz.crystalline.common.util.Reference;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BaseModCrystals {

    @GameRegistry.ObjectHolder(Reference.MODID + ":crystal.knowledge")
    public static ICrystal knowledge_crystal = new CrystalKnowledge();

    @GameRegistry.ObjectHolder(Reference.MODID + ":crystal.life")
    public static ICrystal life_crystal = new CrystalLife();

    @GameRegistry.ObjectHolder(Reference.MODID + ":crystal.administration")
    public static ICrystal administration_crystal = new CrystalAdministration();

    @GameRegistry.ObjectHolder(Reference.MODID + ":crystal.rift")
    public static ICrystal rift_crystal = new CrystalRift();

    @GameRegistry.ObjectHolder(Reference.MODID + ":crystal.universe")
    public static ICrystal universe_crystal = new CrystalUniverse();

    @GameRegistry.ObjectHolder(Reference.MODID + ":crystal.null")
    public static ICrystal null_crystal = new CrystalNull();

    @GameRegistry.ObjectHolder(Reference.MODID + ":crystal.cleansing")
    public static ICrystal cleansing_crystal = new CrystalCleansing();

    @GameRegistry.ObjectHolder(Reference.MODID + ":crystal.hellfire")
    public static ICrystal hellfire_crystal = new CrystalHellfire();

    @GameRegistry.ObjectHolder(Reference.MODID + ":crystal.aquagust")
    public static ICrystal aquagust_crystal = new CrystalAquagust();

    @GameRegistry.ObjectHolder(Reference.MODID + ":crystal.dirtshield")
    public static ICrystal dirtshield_crystal = new CrystalDirtshield();

    @GameRegistry.ObjectHolder(Reference.MODID + ":crystal.atmosburst")
    public static ICrystal atmosburst_crystal = new CrystalAtmosburst();

    public static void register(RegistryEvent.Register<ICrystal> e) {
        e.getRegistry().register(BaseModCrystals.knowledge_crystal);
        e.getRegistry().register(BaseModCrystals.null_crystal);
        e.getRegistry().register(BaseModCrystals.administration_crystal);
        e.getRegistry().register(BaseModCrystals.rift_crystal);
        e.getRegistry().register(BaseModCrystals.universe_crystal);
        e.getRegistry().register(BaseModCrystals.cleansing_crystal);
        e.getRegistry().register(BaseModCrystals.life_crystal);
        e.getRegistry().register(BaseModCrystals.hellfire_crystal);
        e.getRegistry().register(BaseModCrystals.aquagust_crystal);
        e.getRegistry().register(BaseModCrystals.dirtshield_crystal);
        e.getRegistry().register(BaseModCrystals.atmosburst_crystal);
    }

}
