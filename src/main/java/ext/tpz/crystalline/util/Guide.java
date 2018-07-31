package ext.tpz.crystalline.util;

import amerifrance.guideapi.api.*;
import amerifrance.guideapi.api.impl.Book;
import amerifrance.guideapi.api.impl.BookBinder;
import amerifrance.guideapi.api.impl.abstraction.EntryAbstract;
import amerifrance.guideapi.api.util.PageHelper;
import amerifrance.guideapi.category.CategoryItemStack;
import amerifrance.guideapi.entry.EntryItemStack;
import amerifrance.guideapi.page.PageJsonRecipe;
import amerifrance.guideapi.page.PageText;
import ext.tpz.crystalline.block.CrystallineBlocks;
import ext.tpz.crystalline.crystals.BaseModCrystals;
import ext.tpz.crystalline.essences.liquid.BaseModEssenceLiquids;
import ext.tpz.crystalline.essences.powder.BaseModEssencePowders;
import ext.tpz.crystalline.item.CrystallineItems;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static ext.tpz.crystalline.item.CrystallineItems.crystal;
import static ext.tpz.crystalline.item.CrystallineItems.essence_bottle;
import static ext.tpz.crystalline.item.CrystallineItems.essence_powder;
import static ext.tpz.crystalline.util.Reference.MODID;

@GuideBook
public class Guide implements IGuideBook {

    public static Book guide;

    @Nonnull
    @Override
    public Book buildBook() {
        BookBinder binder = new BookBinder(new ResourceLocation(MODID, "guide"));
        binder.setGuideTitle(I18n.format("crystalline.guide.title"));
        binder.setItemName(I18n.format("crystalline.guide.title"));
        binder.setHeader(I18n.format("crystalline.guide.wdli"));
        binder.setAuthor(TextFormatting.ITALIC + I18n.format("crystalline.guide.lore"));
        binder.setColor(Color.BLACK);
        binder.setCreativeTab(CreativeTabs.MISC);

        Map<ResourceLocation, EntryAbstract> entriesCrystals = new LinkedHashMap<>();

        ItemStack crystalStack = new ItemStack(crystal);
        crystal.setType(crystalStack, BaseModCrystals.knowledge_crystal);

        List<IPage> about = new ArrayList<>();
        about.addAll(PageHelper.pagesForLongText(I18n.format("crystalline.guide.crystals.about"), 300));
        entriesCrystals.put(new ResourceLocation(MODID, "about"), new EntryItemStack(about, I18n.format("crystalline.guide.crystals.about.title"), new ItemStack(Items.BOOK)));

        List<IPage> knowledge = new ArrayList<>();
        knowledge.addAll(PageHelper.pagesForLongText(I18n.format("crystalline.guide.crystals.knowledge"), 300));
        knowledge.add(new PageJsonRecipe(new ResourceLocation(MODID, "crystal/knowledge_crystal")));
        entriesCrystals.put(new ResourceLocation(MODID, "knowledge_crystal"), new EntryItemStack(knowledge, I18n.format("crystalline.guide.crystals.knowledge.title"), crystalStack.copy()));

        List<IPage> cleansing = new ArrayList<>();
        crystal.setType(crystalStack, BaseModCrystals.cleansing_crystal);
        cleansing.addAll(PageHelper.pagesForLongText(I18n.format("crystalline.guide.crystals.cleansing"), 300));
        cleansing.add(new PageJsonRecipe(new ResourceLocation(MODID, "crystal/cleansing_crystal")));
        entriesCrystals.put(new ResourceLocation(MODID, "cleansing_crystal"), new EntryItemStack(cleansing, I18n.format("crystalline.guide.crystals.cleansing.title"), crystalStack.copy()));

        List<IPage> life = new ArrayList<>();
        crystal.setType(crystalStack, BaseModCrystals.life_crystal);
        life.addAll(PageHelper.pagesForLongText(I18n.format("crystalline.guide.crystals.life"), 300));
        entriesCrystals.put(new ResourceLocation(MODID, "life_crystal"), new EntryItemStack(life, I18n.format("crystalline.guide.crystals.life.title"), crystalStack.copy()));

        List<IPage> administration = new ArrayList<>();
        crystal.setType(crystalStack, BaseModCrystals.administration_crystal);
        administration.addAll(PageHelper.pagesForLongText(I18n.format("crystalline.guide.crystals.administration"), 300));
        entriesCrystals.put(new ResourceLocation(MODID, "administration_crystal"), new EntryItemStack(administration, I18n.format("crystalline.guide.crystals.administration.title"), crystalStack.copy()));

        List<IPage> rift = new ArrayList<>();
        crystal.setType(crystalStack, BaseModCrystals.rift_crystal);
        rift.addAll(PageHelper.pagesForLongText(I18n.format("crystalline.guide.crystals.rift"), 300));
        entriesCrystals.put(new ResourceLocation(MODID, "rift_crystal"), new EntryItemStack(rift, I18n.format("crystalline.guide.crystals.rift.title"), crystalStack.copy()));

        List<IPage> universe = new ArrayList<>();
        crystal.setType(crystalStack, BaseModCrystals.universe_crystal);
        universe.addAll(PageHelper.pagesForLongText(I18n.format("crystalline.guide.crystals.universe"), 300));
        entriesCrystals.put(new ResourceLocation(MODID, "universe_crystal"), new EntryItemStack(universe, I18n.format("crystalline.guide.crystals.universe.title"), crystalStack.copy()));



        crystal.setType(crystalStack, BaseModCrystals.knowledge_crystal);
        binder.addCategory(new CategoryItemStack(entriesCrystals, I18n.format("crystalline.guide.crystals.title"), crystalStack.copy()));

        Map<ResourceLocation, EntryAbstract> entriesUtilities = new LinkedHashMap<>();

        List<IPage> cleansing_potion = new ArrayList<>();
        cleansing_potion.addAll(PageHelper.pagesForLongText(I18n.format("crystalline.guide.utilities.cleansing_potion"), 238));
        cleansing_potion.add(new PageJsonRecipe(new ResourceLocation(MODID, "util/cleansing_potion")));
        entriesUtilities.put(new ResourceLocation(MODID, "cleansing_potion"), new EntryItemStack(cleansing_potion, I18n.format("crystalline.guide.utilities.cleansing_potion.title"), new ItemStack(CrystallineItems.cleansing_potion)));

        List<IPage> rebinding = new ArrayList<>();
        rebinding.addAll(PageHelper.pagesForLongText(I18n.format("crystalline.guide.utilities.rebinding"), 300));
        rebinding.addAll(PageHelper.pagesForLongText(I18n.format("crystalline.guide.utilities.rebinding.life_crystal_binding"), 300));
        rebinding.addAll(PageHelper.pagesForLongText(I18n.format("crystalline.guide.utilities.rebinding.rebinding_reagent"), 300));
        rebinding.add(new PageJsonRecipe(new ResourceLocation(MODID, "reagent/reagent_rebinding")));
        entriesUtilities.put(new ResourceLocation(MODID, "rebinding"), new EntryItemStack(rebinding, I18n.format("crystalline.guide.utilities.rebinding.title"), new ItemStack(CrystallineItems.rebinding_reagent)));

        List<IPage> restoration = new ArrayList<>();
        restoration.addAll(PageHelper.pagesForLongText(I18n.format("crystalline.guide.utilities.restoration"), 300));
        restoration.add(new PageJsonRecipe(new ResourceLocation(MODID, "block/restoration_apparatus")));
        entriesUtilities.put(new ResourceLocation(MODID, "restoration"), new EntryItemStack(restoration, I18n.format("crystalline.guide.utilities.restoration.title"), new ItemStack(CrystallineBlocks.restorationApparatus)));

        List<IPage> distillation = new ArrayList<>();
        distillation.addAll(PageHelper.pagesForLongText(I18n.format("crystalline.guide.utilities.distillation"), 300));
        distillation.add(new PageJsonRecipe(new ResourceLocation(MODID, "block/distillation_basin")));
        entriesUtilities.put(new ResourceLocation(MODID, "distillation"), new EntryItemStack(distillation, I18n.format("crystalline.guide.utilities.distillation.title"), new ItemStack(CrystallineBlocks.distillationBasin)));

        binder.addCategory(new CategoryItemStack(entriesUtilities, I18n.format("crystalline.guide.utilities.title"), new ItemStack(CrystallineItems.rebinding_reagent)));

        ItemStack essenceBottleStack = new ItemStack(essence_bottle);
        essence_bottle.setType(essenceBottleStack, BaseModEssenceLiquids.essence_liquid_knowledge);

        Map<ResourceLocation, EntryAbstract> entriesDistillation = new LinkedHashMap<>();

        List<IPage> knowledge_distill = new ArrayList<>();
        knowledge_distill.addAll(PageHelper.pagesForLongText(I18n.format("crystalline.guide.distillation.knowledge"), 300));
        entriesDistillation.put(new ResourceLocation(MODID, "knowledge_distill"), new EntryItemStack(knowledge_distill, I18n.format("crystalline.guide.distillation.knowledge.title"), essenceBottleStack.copy()));

        List<IPage> cleansing_distill = new ArrayList<>();
        essence_bottle.setType(essenceBottleStack, BaseModEssenceLiquids.essence_liquid_cleansing);
        cleansing_distill.addAll(PageHelper.pagesForLongText(I18n.format("crystalline.guide.distillation.cleansing"), 300));
        entriesDistillation.put(new ResourceLocation(MODID, "cleansing_distill"), new EntryItemStack(cleansing_distill, I18n.format("crystalline.guide.distillation.cleansing.title"), essenceBottleStack.copy()));

        List<IPage> life_distill = new ArrayList<>();
        essence_bottle.setType(essenceBottleStack, BaseModEssenceLiquids.essence_liquid_life);
        life_distill.addAll(PageHelper.pagesForLongText(I18n.format("crystalline.guide.distillation.life"), 300));
        entriesDistillation.put(new ResourceLocation(MODID, "life_distill"), new EntryItemStack(life_distill, I18n.format("crystalline.guide.distillation.life.title"), essenceBottleStack.copy()));

        List<IPage> administration_distill = new ArrayList<>();
        essence_bottle.setType(essenceBottleStack, BaseModEssenceLiquids.essence_liquid_administration);
        administration_distill.addAll(PageHelper.pagesForLongText(I18n.format("crystalline.guide.distillation.administration"), 300));
        entriesDistillation.put(new ResourceLocation(MODID, "administration_distill"), new EntryItemStack(administration_distill, I18n.format("crystalline.guide.distillation.administration.title"), essenceBottleStack.copy()));

        List<IPage> rift_distill = new ArrayList<>();
        essence_bottle.setType(essenceBottleStack, BaseModEssenceLiquids.essence_liquid_rift);
        rift_distill.addAll(PageHelper.pagesForLongText(I18n.format("crystalline.guide.distillation.rift"), 300));
        entriesDistillation.put(new ResourceLocation(MODID, "rift_distill"), new EntryItemStack(rift_distill, I18n.format("crystalline.guide.distillation.rift.title"), essenceBottleStack.copy()));

        List<IPage> universe_distill = new ArrayList<>();
        essence_bottle.setType(essenceBottleStack, BaseModEssenceLiquids.essence_liquid_universe);
        universe_distill.addAll(PageHelper.pagesForLongText(I18n.format("crystalline.guide.distillation.universe"), 300));
        entriesDistillation.put(new ResourceLocation(MODID, "universe_distill"), new EntryItemStack(universe_distill, I18n.format("crystalline.guide.distillation.universe.title"), essenceBottleStack.copy()));

        binder.addCategory(new CategoryItemStack(entriesDistillation, I18n.format("crystalline.guide.distillation.title"), essenceBottleStack.copy()));

        Map<ResourceLocation, EntryAbstract> entriesPowders = new LinkedHashMap<>();

        ItemStack essencePowderStack = new ItemStack(essence_powder);

        List<IPage> rift_powder = new ArrayList<>();
        essence_powder.setType(essencePowderStack, BaseModEssencePowders.essence_powder_rift);
        rift_powder.addAll(PageHelper.pagesForLongText(I18n.format("crystalline.guide.powders.rift"), 300));
        entriesPowders.put(new ResourceLocation(MODID, "rift_powder"), new EntryItemStack(rift_powder, I18n.format("crystalline.guide.powders.rift.title"), essencePowderStack.copy()));

        List<IPage> universe_powder = new ArrayList<>();
        essence_powder.setType(essencePowderStack, BaseModEssencePowders.essence_powder_universe);
        universe_powder.addAll(PageHelper.pagesForLongText(I18n.format("crystalline.guide.powders.universe"), 300));
        entriesPowders.put(new ResourceLocation(MODID, "universe_powder"), new EntryItemStack(universe_powder, I18n.format("crystalline.guide.powders.universe.title"), essencePowderStack.copy()));

        binder.addCategory(new CategoryItemStack(entriesPowders, I18n.format("crystalline.guide.powders.title"), essencePowderStack.copy()));

        binder.setSpawnWithBook();


        guide = binder.build();
        return guide;

    }

    @SideOnly(Side.CLIENT)
    @Override
    public void handleModel(ItemStack bookStack) {
        GuideAPI.setModel(guide);
    }

    @Override
    public void handlePost(@Nonnull ItemStack bookStack) {
        GameRegistry.addShapelessRecipe(new ResourceLocation("guideapi:crystalline_guide"), new ResourceLocation(""), bookStack, Ingredient.fromItem(Items.BOOK), Ingredient.fromItem(CrystallineItems.reagent));
    }
}
