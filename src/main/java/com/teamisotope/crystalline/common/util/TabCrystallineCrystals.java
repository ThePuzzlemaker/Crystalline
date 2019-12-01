package com.teamisotope.crystalline.common.util;

import com.teamisotope.crystalline.api.crystal.CrystalMetadata;
import com.teamisotope.crystalline.api.crystal.CrystalRegistry;
import com.teamisotope.crystalline.api.crystal.ICrystal;
import com.teamisotope.crystalline.common.base.crystal.CrystalInit;
import com.teamisotope.crystalline.common.item.CItems;
import com.teamisotope.crystalline.common.item.dyn.ItemCrystal;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;

import java.util.Collection;

public class TabCrystallineCrystals extends CreativeTabs {

    public TabCrystallineCrystals() {
        super("crystallineCrystals");
    }

    @Override
    public ItemStack getTabIconItem() {
        ItemStack stack = new ItemStack(CItems.crystal);
        ItemCrystal.set(stack, new CrystalMetadata(CrystalInit.knowledgeCrystal));
        return stack;
    }

    @Override
    public void displayAllRelevantItems(NonNullList<ItemStack> items) {
        Collection<ICrystal> crystalCollection = CrystalRegistry.getRegistry().getValuesCollection();
        crystalCollection
                .forEach(iCrystal -> {
                    ItemStack stack = new ItemStack(CItems.crystal);
                    CrystalMetadata meta = new CrystalMetadata(iCrystal);
                    NBTTagCompound serialized = meta.serialize();
                    ItemCrystal.set(stack, meta);
                    items.add(stack);
                });
    }


}
