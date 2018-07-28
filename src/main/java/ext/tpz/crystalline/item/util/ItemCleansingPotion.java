package ext.tpz.crystalline.item.util;

import ext.tpz.crystalline.Crystalline;
import ext.tpz.crystalline.insanity.InsanityWorldSavedData;
import ext.tpz.crystalline.util.Reference;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemSimpleFoiled;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class ItemCleansingPotion extends ItemSimpleFoiled {

    public ItemCleansingPotion() {
        this.setRegistryName("cleansing_potion").setUnlocalizedName(Reference.MODID + ".cleansing_potion").setMaxStackSize(1).setCreativeTab(Crystalline.tab).setMaxStackSize(0);
    }

    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add("It seems to be a potion to help cure insanity.");
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        if (entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entityLiving;
            UUID uuid = player.getUniqueID();
            InsanityWorldSavedData data = InsanityWorldSavedData.get(worldIn);
            int insanity = data.getPlayer(uuid);
            Random rand = new Random();
            if (insanity != 0) {
                if (insanity < 101) {
                    if (!rand.nextBoolean()) {
                        data.setPlayer(uuid, 0);
                        player.sendStatusMessage(new TextComponentString(TextFormatting.GREEN + "You have been cured of all temporary insanity!"), true);
                    } else {
                        if (!(insanity + 10 > 100)) {
                            data.setPlayer(uuid, insanity + 10);
                            player.sendStatusMessage(new TextComponentString(TextFormatting.RED + "Your insanity has gone up by 10%!"), true);
                        } else {
                            data.setPlayer(uuid, 100);
                            player.sendStatusMessage(new TextComponentString(TextFormatting.RED + "Your insanity has gone up by 10%!"), true);
                        }
                    }
                } else {
                    int value = rand.nextInt(100);
                    if (value == 0) {
                        player.sendStatusMessage(new TextComponentString(TextFormatting.GREEN + "Your permanent insanity has been healed!"), true);
                        data.setPlayer(uuid, 0);
                    } else {
                        player.sendStatusMessage(new TextComponentString(TextFormatting.RED + "" + TextFormatting.ITALIC + "It isn't very effective..."), true);
                    }
                }
                return new ItemStack(Items.GLASS_BOTTLE);
            }
            InsanityWorldSavedData.set(data, worldIn);
            return stack;
        }
        return null;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.DRINK;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 32;
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        playerIn.setActiveHand(handIn);
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }
}
