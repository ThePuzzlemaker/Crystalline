package ext.tpz.crystalline.item.dynamic;

import com.google.common.collect.Lists;
import ext.tpz.crystalline.Crystalline;
import ext.tpz.crystalline.item.CrystallineItem;
import ext.tpz.crystalline.common.util.Reference;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemRebindingReagent extends CrystallineItem {

    public ItemRebindingReagent() {
        super("rebinding_reagent", Reference.MODID + ".rebinding_reagent", 64, Crystalline.tab);
    }

    public void setBound(ItemStack stack, String playerName) {
        if (stack.hasTagCompound()) {
            stack.getTagCompound().setString("bound", playerName);
        } else {
            NBTTagCompound tmp = new NBTTagCompound();
            tmp.setString("bound", playerName);
            stack.setTagCompound(tmp);
        }
    }

    public String getBound(ItemStack stack) {
        if (stack.hasTagCompound()) {
            if (stack.getTagCompound().hasKey("bound")) {
                return stack.getTagCompound().getString("bound");
            } else {
                setBound(stack, "SickPlayerNameThatDoesNotExist");
                return "SickPlayerNameThatDoesNotExist";
            }
        } else {
            setBound(stack, "SickPlayerNameThatDoesNotExist");
            return "SickPlayerNameThatDoesNotExist";
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        if (getBound(stack).equals("SickPlayerNameThatDoesNotExist")) {
            setBound(stack, playerIn.getName());
        }
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return !getBound(stack).equals("SickPlayerNameThatDoesNotExist");
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.addAll(Lists.newArrayList(TextFormatting.RESET + I18n.format("item.crystalline.rebinding_reagent.lore1"), TextFormatting.RESET + I18n.format("item.crystalline.rebinding_reagent.lore2")));
    }
}
