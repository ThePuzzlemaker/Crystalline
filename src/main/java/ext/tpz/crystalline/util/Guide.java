package ext.tpz.crystalline.util;

import amerifrance.guideapi.api.*;
import amerifrance.guideapi.api.impl.Book;
import amerifrance.guideapi.api.impl.BookBinder;
import amerifrance.guideapi.api.impl.abstraction.EntryAbstract;
import amerifrance.guideapi.category.CategoryItemStack;
import amerifrance.guideapi.entry.EntryItemStack;
import amerifrance.guideapi.page.PageJsonRecipe;
import amerifrance.guideapi.page.PageText;
import ext.tpz.crystalline.crystals.BaseModCrystals;
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

@GuideBook
public class Guide implements IGuideBook {

    public static Book guide;

    @Nonnull
    @Override
    public Book buildBook() {
        BookBinder binder = new BookBinder(new ResourceLocation(Reference.MODID, "guide"));
        binder.setGuideTitle(I18n.format("crystalline.guide.title"));
        binder.setItemName(I18n.format("crystalline.guide.title"));
        binder.setHeader(I18n.format("crystalline.guide.wdli"));
        binder.setAuthor(TextFormatting.ITALIC + I18n.format("crystalline.guide.lore"));
        binder.setColor(Color.BLACK);
        binder.setCreativeTab(CreativeTabs.MISC);

        Map<ResourceLocation, EntryAbstract> entriesCrystals = new LinkedHashMap<ResourceLocation, EntryAbstract>();

        List<IPage> about = new ArrayList<IPage>();

        ItemStack crystalKnowledge = new ItemStack(CrystallineItems.crystal);
        CrystallineItems.crystal.setType(crystalKnowledge, BaseModCrystals.knowledge_crystal);

        ItemStack crystalLife = new ItemStack(CrystallineItems.crystal);
        CrystallineItems.crystal.setType(crystalLife, BaseModCrystals.life_crystal);

        ItemStack crystalCleansing = new ItemStack(CrystallineItems.crystal);
        CrystallineItems.crystal.setType(crystalCleansing, BaseModCrystals.cleansing_crystal);

        about.add(new PageText("There are many types of crystals that you can find and create. This section will inform you of them."));
        entriesCrystals.put(new ResourceLocation(Reference.MODID, "about"), new EntryItemStack(about, "About", new ItemStack(Items.BOOK)));

        List<IPage> knowledge = new ArrayList<IPage>();
        knowledge.add(new PageText("The knowledge crystal can help you monitor your sanity, so you do not become too insane. You can right click without sneaking to see the quick overview, or right click while sneaking to see information about the stage of insanity you are in."));
        knowledge.add(new PageJsonRecipe(new ResourceLocation(Reference.MODID, "knowledge_crystal")));
        entriesCrystals.put(new ResourceLocation(Reference.MODID, "knowledge"), new EntryItemStack(knowledge, "Knowledge Crystal", crystalKnowledge));

        List<IPage> life = new ArrayList<IPage>();
        life.add(new PageText("Life crystals are dropped on a 25% chance of a player dying. They can be used for regeneration. You can only use a crystal that is bound to you. See Rebinding for more information."));
        entriesCrystals.put(new ResourceLocation(Reference.MODID, "life"), new EntryItemStack(life, "Life Crystal", crystalLife));

        List<IPage> cleansing = new ArrayList<IPage>();
        cleansing.add(new PageText("Cleansing crystals can be made with any crystal, and quartz all around it. You can right-click them to make Cleansing Reagents, at the cost of Potential, and a Basic Reagent."));
        cleansing.add(new PageJsonRecipe(new ResourceLocation(Reference.MODID, "cleansing_crystal")));
        entriesCrystals.put(new ResourceLocation(Reference.MODID, "cleansing"), new EntryItemStack(cleansing, "Cleansing Crystal", crystalCleansing));


        binder.addCategory(new CategoryItemStack(entriesCrystals, "Crystals", crystalKnowledge));

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
