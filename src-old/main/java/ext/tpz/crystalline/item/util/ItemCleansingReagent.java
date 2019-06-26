package ext.tpz.crystalline.item.util;

import com.google.common.collect.Lists;
import ext.tpz.crystalline.Crystalline;
import ext.tpz.crystalline.item.CrystallineItem;
import ext.tpz.crystalline.common.util.Reference;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemCleansingReagent extends CrystallineItem {

    public ItemCleansingReagent() {
        super("cleansing_reagent", Reference.MODID + ".cleansing_reagent", 64, Crystalline.tab);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFormatting.RESET + I18n.format("item.crystalline.cleansing_reagent.lore"));
    }
}
