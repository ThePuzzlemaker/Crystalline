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

    public CrystallineItem(String regName, String locName, int maxStackSize, CreativeTabs tab) {
        this.setRegistryName(regName).setUnlocalizedName(locName).setMaxStackSize(maxStackSize).setCreativeTab(tab);
    }

}
