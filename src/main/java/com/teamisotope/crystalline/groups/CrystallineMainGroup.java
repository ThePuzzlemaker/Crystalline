package com.teamisotope.crystalline.groups;

import com.teamisotope.crystalline.blocks.CrystallineBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class CrystallineMainGroup extends ItemGroup {

    public CrystallineMainGroup() {
        super("crystallinemain");
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(CrystallineBlocks.arcanumminerale);
    }


}
