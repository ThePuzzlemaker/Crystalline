package ext.tpz.crystalline.item;

import com.google.common.collect.Lists;
import ext.tpz.crystalline.Crystalline;
import ext.tpz.crystalline.util.Reference;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CrystallineItems {

    @GameRegistry.ObjectHolder(Reference.MODID + ":crystal")
    public static ItemCrystal crystal = new ItemCrystal();

    @GameRegistry.ObjectHolder(Reference.MODID + ":reagent_basic")
    public static CrystallineItem reagent_basic = new CrystallineItem("reagent_basic", Reference.MODID + ".reagent_basic", 64, Crystalline.tab, Lists.asList(TextFormatting.RESET + "Reagent value: Basic", new String[]{}));

    @GameRegistry.ObjectHolder(Reference.MODID + ":reagent_advanced")
    public static CrystallineItem reagent_advanced = new CrystallineItem("reagent_advanced", Reference.MODID + ".reagent_advanced", 64, Crystalline.tab, Lists.asList(TextFormatting.RESET + "Reagent value: Advanced>>Basic", new String[]{}));

    @GameRegistry.ObjectHolder(Reference.MODID + ":reagent_extreme")
    public static CrystallineItem reagent_extreme = new CrystallineItem("reagent_extreme", Reference.MODID + ".reagent_extreme", 64, Crystalline.tab, Lists.asList(TextFormatting.RESET + "Reagent value: Extreme>>Advanced>>Basic", new String[]{}));

    @GameRegistry.ObjectHolder(Reference.MODID + ":reagent_rift")
    public static CrystallineItem reagent_rift = new CrystallineItem("reagent_rift", Reference.MODID + ".reagent_rift", 64, Crystalline.tab, Lists.asList(TextFormatting.RESET + "Reagent value: Rift>>Extreme>>Advanced>>Basic", new String[]{TextFormatting.DARK_RED + "UNSTABLE: WILL CAUSE PERMANENT INSANITY"}));

    @GameRegistry.ObjectHolder(Reference.MODID + ":reagent_universe")
    public static CrystallineItem reagent_universe = new CrystallineItem("reagent_universe", Reference.MODID + ".reagent_universe", 64, Crystalline.tab, Lists.asList(TextFormatting.RESET + "Reagent value: Universe>>Rift>>Extreme>>Advanced>>Basic", new String[]{}));

    @GameRegistry.ObjectHolder(Reference.MODID + ":pure_rift_essence")
    public static CrystallineItem pure_rift_essence = new CrystallineItem("pure_rift_essence", Reference.MODID + ".pure_rift_essence", 64, Crystalline.tab, Lists.asList(TextFormatting.RESET + "It's a weird purple powder. It might be a pure essence of some sort.", new String[]{}));

    @GameRegistry.ObjectHolder(Reference.MODID + ":pure_universe_essence")
    public static CrystallineItem pure_universe_essence = new CrystallineItem("pure_universe_essence", Reference.MODID + ".pure_universe_essence", 64, Crystalline.tab, Lists.asList(TextFormatting.RESET + "It's a galaxy-colored powder. It might be a pure essence of some sort.", new String[]{}));

    @GameRegistry.ObjectHolder(Reference.MODID + ":cleansing_reagent")
    public static CrystallineItem cleansing_reagent = new CrystallineItem("cleansing_reagent", Reference.MODID + ".cleansing_reagent", 64, Crystalline.tab, Lists.asList(TextFormatting.RESET + "It's a white powder that seems to clean anything it touches.", new String[]{}));

    @GameRegistry.ObjectHolder(Reference.MODID + ":cleansing_potion")
    public static ItemCleansingPotion cleansing_potion = new ItemCleansingPotion();

    @GameRegistry.ObjectHolder(Reference.MODID + ":essence_bottle")
    public static ItemEssenceBottle essence_bottle = new ItemEssenceBottle();

}
