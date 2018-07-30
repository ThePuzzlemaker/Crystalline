package ext.tpz.crystalline.item;

import com.google.common.collect.Lists;
import ext.tpz.crystalline.Crystalline;
import ext.tpz.crystalline.item.dynamic.*;
import ext.tpz.crystalline.item.util.ItemCleansingPotion;
import ext.tpz.crystalline.util.Reference;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CrystallineItems {

    @GameRegistry.ObjectHolder(Reference.MODID + ":crystal")
    public static ItemCrystal crystal = new ItemCrystal();

    @GameRegistry.ObjectHolder(Reference.MODID + ":cleansing_reagent")
    public static CrystallineItem cleansing_reagent = new CrystallineItem("cleansing_reagent", Reference.MODID + ".cleansing_reagent", 64, Crystalline.tab, Lists.newArrayList(TextFormatting.RESET + I18n.format("item.crystalline.cleansing_reagent.lore")));

    @GameRegistry.ObjectHolder(Reference.MODID + ":rebinding_reagent")
    public static ItemRebindingReagent rebinding_reagent = new ItemRebindingReagent();

    @GameRegistry.ObjectHolder(Reference.MODID + ":cleansing_potion")
    public static ItemCleansingPotion cleansing_potion = new ItemCleansingPotion();

    @GameRegistry.ObjectHolder(Reference.MODID + ":essence_bottle")
    public static ItemEssenceBottle essence_bottle = new ItemEssenceBottle();

    @GameRegistry.ObjectHolder(Reference.MODID + ":essence_powder")
    public static ItemEssencePowder essence_powder = new ItemEssencePowder();

    @GameRegistry.ObjectHolder(Reference.MODID + ":reagent")
    public static ItemReagent reagent = new ItemReagent();

}
