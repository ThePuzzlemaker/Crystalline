package com.teamisotope.crystalline.common.util;

import com.teamisotope.crystalline.api.crystal.CrystalMetadata;
import com.teamisotope.crystalline.api.crystal.CrystalRegistry;
import com.teamisotope.crystalline.api.crystal.ICrystal;
import com.teamisotope.crystalline.common.item.CItems;
import com.teamisotope.crystalline.common.item.dyn.ItemCrystal;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import java.util.Collection;

public class TabCrystalline extends CreativeTabs {

    public TabCrystalline() {
        super("crystalline");
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(CItems.tuningComponents);
    }

    @Override
    public void displayAllRelevantItems(NonNullList<ItemStack> items) {
        super.displayAllRelevantItems(items);

    }


}
