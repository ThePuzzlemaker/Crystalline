package com.teamisotope.crystalline.common.groups;

import com.teamisotope.crystalline.common.setup.Registration;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class CrystallineMainGroup extends ItemGroup {

    public CrystallineMainGroup() {
        super("crystallinemain");
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(Registration.arcanumminerale.get());
    }

    @Override
    public void fill(NonNullList<ItemStack> items) {
        super.fill(items);
        //ItemStack guideBook = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("patchouli", "guide_book")));
        //CompoundNBT nbtTag = new CompoundNBT();
        //nbtTag.putString("patchouli:book", "crystalline:crystallineknowledge");
        //guideBook.setTag(nbtTag);
        //items.add(guideBook);
    }
}
