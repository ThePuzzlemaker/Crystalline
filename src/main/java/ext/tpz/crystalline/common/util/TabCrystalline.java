package ext.tpz.crystalline.common.util;

import ext.tpz.crystalline.common.item.CItems;
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
    public void displayAllRelevantItems(NonNullList<ItemStack> p_78018_1_) {
        super.displayAllRelevantItems(p_78018_1_);

    }


}
