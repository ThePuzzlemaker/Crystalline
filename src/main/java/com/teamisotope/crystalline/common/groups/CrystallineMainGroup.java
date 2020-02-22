package com.teamisotope.crystalline.common.groups;

import com.teamisotope.crystalline.common.blocks.CrystallineBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class CrystallineMainGroup extends ItemGroup {

    public CrystallineMainGroup() {
        super("crystallinemain");
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(CrystallineBlocks.arcanumminerale);
    }

    @Override
    public void fill(NonNullList<ItemStack> items) {
        super.fill(items);
        ItemStack guideBook = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("patchouli", "guide_book")));
        CompoundNBT nbtTag = new CompoundNBT();
        nbtTag.putString("patchouli:book", "crystalline:crystallineknowledge");
        guideBook.setTag(nbtTag);
        items.add(guideBook);
    }
}
