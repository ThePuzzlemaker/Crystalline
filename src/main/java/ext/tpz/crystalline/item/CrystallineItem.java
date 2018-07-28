package ext.tpz.crystalline.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;

public class CrystallineItem extends Item {

    private List<String> tooltip;

    public CrystallineItem(String regName, String locName, int maxStackSize, CreativeTabs tab, List<String> tooltip) {
        this.setRegistryName(regName).setUnlocalizedName(locName).setMaxStackSize(maxStackSize).setCreativeTab(tab);
        this.tooltip = tooltip;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        Iterator<String> i = this.tooltip.iterator();
        while (i.hasNext()) {
            String s = i.next();
            tooltip.add(s);
        }
    }
}
