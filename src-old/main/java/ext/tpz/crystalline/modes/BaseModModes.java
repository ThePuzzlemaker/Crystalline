package ext.tpz.crystalline.modes;

import ext.tpz.crystalline.api.crystal.ICrystal;
import ext.tpz.crystalline.api.mode.ICrystalMode;
import ext.tpz.crystalline.crystals.CrystalKnowledge;
import ext.tpz.crystalline.modes.administration.ModeAdministration;
import ext.tpz.crystalline.modes.aquagust.ModeWaterThrow;
import ext.tpz.crystalline.modes.atmosburst.ModeAtmosburst;
import ext.tpz.crystalline.modes.cleansing.ModeCleansingReagent;
import ext.tpz.crystalline.modes.dirtshield.ModeDirtshield;
import ext.tpz.crystalline.modes.hellfire.ModeFireThrow;
import ext.tpz.crystalline.modes.knowledge.ModeViewInsanity;
import ext.tpz.crystalline.modes.life.ModeRegeneration;
import ext.tpz.crystalline.modes.obliterate.ModeObliterateBlock;
import ext.tpz.crystalline.modes.obliterate.ModeObliterateEntity;
import ext.tpz.crystalline.modes.universe.ModeUltraCleanse;
import ext.tpz.crystalline.util.Reference;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BaseModModes {

    // Knowledge Crystal
    @GameRegistry.ObjectHolder(Reference.MODID + ":mode.view_insanity")
    public static ICrystalMode view_insanity = new ModeViewInsanity();

    // All Crystals
    @GameRegistry.ObjectHolder(Reference.MODID + ":mode.null")
    public static ICrystalMode mode_null = new ModeNull();

    // Rift/Universe Crystal
    @GameRegistry.ObjectHolder(Reference.MODID + ":mode.obliterate_block")
    public static ICrystalMode obliterate_block = new ModeObliterateBlock();

    @GameRegistry.ObjectHolder(Reference.MODID + ":mode.obliterate_entity")
    public static ICrystalMode obliterate_entity = new ModeObliterateEntity();

    // Universe Crystal
    @GameRegistry.ObjectHolder(Reference.MODID + ":mode.ultra_cleanse")
    public static ICrystalMode ultra_cleanse = new ModeUltraCleanse();

    // Life Crystal
    @GameRegistry.ObjectHolder(Reference.MODID + ":mode.regeneration")
    public static ICrystalMode regeneration = new ModeRegeneration();

    // Cleansing Crystal
    @GameRegistry.ObjectHolder(Reference.MODID + ":mode.cleansing_reagent")
    public static ICrystalMode cleansing_reagent = new ModeCleansingReagent();

    // Administration Crystal
    @GameRegistry.ObjectHolder(Reference.MODID + ":mode.administration")
    public static ICrystalMode administration = new ModeAdministration();

    // Hellfire Crystal
    @GameRegistry.ObjectHolder(Reference.MODID + ":mode.fire_throw")
    public static ICrystalMode fire_throw = new ModeFireThrow();

    // Aquagust Crystal
    @GameRegistry.ObjectHolder(Reference.MODID + ":mode.water_throw")
    public static ICrystalMode water_throw = new ModeWaterThrow();

    // Dirtshield Crystal
    @GameRegistry.ObjectHolder(Reference.MODID + ":mode.dirt_shield")
    public static ICrystalMode dirt_shield = new ModeDirtshield();

    // Atmosburst
    @GameRegistry.ObjectHolder(Reference.MODID + ":mode.atmos_burst")
    public static ICrystalMode atmos_burst = new ModeAtmosburst();

    public static void register(RegistryEvent.Register<ICrystalMode> e) {
        e.getRegistry().register(view_insanity);
        e.getRegistry().register(mode_null);
        e.getRegistry().register(obliterate_block);
        e.getRegistry().register(obliterate_entity);
        e.getRegistry().register(ultra_cleanse);
        e.getRegistry().register(regeneration);
        e.getRegistry().register(cleansing_reagent);
        e.getRegistry().register(administration);
        e.getRegistry().register(fire_throw);
        e.getRegistry().register(water_throw);
        e.getRegistry().register(dirt_shield);
        e.getRegistry().register(atmos_burst);
    }


}
