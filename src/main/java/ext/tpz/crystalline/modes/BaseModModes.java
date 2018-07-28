package ext.tpz.crystalline.modes;

import ext.tpz.crystalline.api.mode.ICrystalMode;
import ext.tpz.crystalline.crystals.CrystalKnowledge;
import ext.tpz.crystalline.modes.knowledge.ModeViewInsanity;
import ext.tpz.crystalline.modes.life.ModeRegeneration;
import ext.tpz.crystalline.modes.universe.ModeObliterateBlock;
import ext.tpz.crystalline.modes.universe.ModeObliterateEntity;
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


    public static void register(RegistryEvent.Register<ICrystalMode> e) {
        e.getRegistry().register(view_insanity);
        e.getRegistry().register(mode_null);
        e.getRegistry().register(obliterate_block);
        e.getRegistry().register(obliterate_entity);
        e.getRegistry().register(ultra_cleanse);
        e.getRegistry().register(regeneration);
    }


}
