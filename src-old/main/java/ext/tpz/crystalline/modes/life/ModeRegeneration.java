package ext.tpz.crystalline.modes.life;

import com.google.common.collect.Lists;
import ext.tpz.crystalline.api.crystal.ICrystal;
import ext.tpz.crystalline.api.mode.ICrystalMode;
import ext.tpz.crystalline.crystals.BaseModCrystals;
import ext.tpz.crystalline.insanity.InsanityUtils;
import ext.tpz.crystalline.item.CrystallineItems;
import ext.tpz.crystalline.util.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nullable;
import java.util.List;

public class ModeRegeneration implements ICrystalMode {

    @Override
    public String getUnlocalizedName() {
        return "crystalline.mode.regeneration";
    }

    @Override
    public ActionResult<ItemStack> use(ItemStack crystal, EntityPlayer player) {
        if (CrystallineItems.crystal.getBound(crystal) == player.getName()) {
            PotionEffect regen = new PotionEffect(MobEffects.REGENERATION, 3000, 1);
            if (player.getActivePotionEffect(MobEffects.REGENERATION) == null) {
                if (CrystallineItems.crystal.getType(crystal).getReagentType().consume(player, crystal)) {
                    if ((CrystallineItems.crystal.getPotential(crystal) - 1) >= 0) {
                        CrystallineItems.crystal.setPotential(crystal, CrystallineItems.crystal.getPotential(crystal) - 1);
                    } else {
                        player.sendStatusMessage(new TextComponentString(TextFormatting.RED + "Not enough potential!"), true);
                        return new ActionResult<ItemStack>(EnumActionResult.FAIL, crystal);
                    }
                    player.addPotionEffect(regen);
                    if (!player.getEntityWorld().isRemote)
                        InsanityUtils.addInsanity(player.getEntityWorld(), player, 1);
                    return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, crystal);
                }
            }
        } else {
            player.sendStatusMessage(new TextComponentString(TextFormatting.RED + "This crystal is not bound to you!"), true);
            return new ActionResult<ItemStack>(EnumActionResult.FAIL, crystal);
        }
        return new ActionResult<ItemStack>(EnumActionResult.FAIL, crystal);
    }

    @Override
    public List<ICrystal> getValidCrystals() {
        return Lists.newArrayList(BaseModCrystals.life_crystal);
    }

    @Override
    public boolean isValidCrystal(ItemStack crystal) {
        return getValidCrystals().contains(CrystallineItems.crystal.getType(crystal));
    }

    @Override
    public ICrystalMode setRegistryName(ResourceLocation name) {
        return this;
    }

    @Nullable
    @Override
    public ResourceLocation getRegistryName() {
        return new ResourceLocation(Reference.MODID, "mode.regeneration");
    }

    @Override
    public Class<ICrystalMode> getRegistryType() {
        return ICrystalMode.class;
    }
}
