package ext.tpz.crystalline.util;

import ext.tpz.crystalline.item.CrystallineItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class TabCrystalline extends CreativeTabs {

    public TabCrystalline() {
        super("crystalline");
    }

    public ItemStack getTabIconItem() {
        ItemStack knowledge = new ItemStack(CrystallineItems.crystal);
        CrystallineItems.crystal.setType(knowledge, EnumCrystalTypes.KNOWLEDGE);
        return knowledge;
    }

}
