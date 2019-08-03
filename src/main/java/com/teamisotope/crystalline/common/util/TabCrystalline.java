package com.teamisotope.crystalline.common.util;

import com.teamisotope.crystalline.common.item.CItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class TabCrystalline extends CreativeTabs {

    public TabCrystalline() {
        super("crystalline");
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(CItems.crystal);
    }

    @Override
    public void displayAllRelevantItems(NonNullList<ItemStack> items) {
        super.displayAllRelevantItems(items);
    }


}
