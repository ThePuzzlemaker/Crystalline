package ext.tpz.crystalline.crystals;

import ext.tpz.crystalline.item.ItemCrystal;
import ext.tpz.crystalline.util.Reference;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BaseModCrystals {

    @GameRegistry.ObjectHolder(Reference.MODID + ":crystal.knowledge")
    public static CrystalKnowledge knowledge_crystal = new CrystalKnowledge();

}
