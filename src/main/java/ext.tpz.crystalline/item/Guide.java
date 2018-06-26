package ext.tpz.crystalline.item;

import amerifrance.guideapi.api.*;
import amerifrance.guideapi.api.impl.Book;
import amerifrance.guideapi.api.impl.BookBinder;
import amerifrance.guideapi.api.impl.abstraction.CategoryAbstract;
import amerifrance.guideapi.api.impl.abstraction.EntryAbstract;
import amerifrance.guideapi.category.CategoryItemStack;
import amerifrance.guideapi.entry.EntryItemStack;
import amerifrance.guideapi.page.PageIRecipe;
import amerifrance.guideapi.page.PageJsonRecipe;
import amerifrance.guideapi.page.PageText;
import amerifrance.guideapi.page.PageTextImage;
import ext.tpz.crystalline.util.Reference;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
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
        binder.setAuthor(TextFormatting.ITALIC + I18n.format("crystalline.guide.lore"));
        binder.setColor(Color.BLACK);
        binder.setCreativeTab(CreativeTabs.MISC);

        Map<ResourceLocation, EntryAbstract> entriesCrystals = new LinkedHashMap<ResourceLocation, EntryAbstract>();

        List<IPage> about = new ArrayList<IPage>();

        about.add(new PageText("There are many types of crystals that you can find and create. This section will inform you of them."));
        entriesCrystals.put(new ResourceLocation(Reference.MODID, "about"), new EntryItemStack(about, "About", new ItemStack(Items.BOOK)));

        List<IPage> knowledge = new ArrayList<IPage>();
        knowledge.add(new PageText("The knowledge crystal can help you monitor your sanity, so you do not become too insane."));
        knowledge.add(new PageJsonRecipe(new ResourceLocation(Reference.MODID, "knowledge_crystal")));
        entriesCrystals.put(new ResourceLocation(Reference.MODID, "knowledge"), new EntryItemStack(knowledge, "Knowledge Crystal", new ItemStack(CrystallineItems.crystal)));

        binder.addCategory(new CategoryItemStack(entriesCrystals, "Crystals", new ItemStack(CrystallineItems.crystal)));

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
    public void handlePost(ItemStack bookStack) {

    }

}
