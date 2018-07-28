package ext.tpz.crystalline.modes;

import ext.tpz.crystalline.crystals.CrystalKnowledge;
import ext.tpz.crystalline.modes.knowledge.ModeViewInsanity;
import ext.tpz.crystalline.util.Reference;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BaseModModes {

    // Knowledge Crystal
    @GameRegistry.ObjectHolder(Reference.MODID + ":mode.view_insanity")
    public static ModeViewInsanity view_insanity = new ModeViewInsanity();


}
