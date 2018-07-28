package ext.tpz.crystalline.modes;

import ext.tpz.crystalline.api.mode.ICrystalMode;
import ext.tpz.crystalline.crystals.CrystalKnowledge;
import ext.tpz.crystalline.modes.knowledge.ModeViewInsanity;
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

    public static void register(RegistryEvent.Register<ICrystalMode> e) {
        e.getRegistry().register(view_insanity);
        e.getRegistry().register(mode_null);
    }


}
