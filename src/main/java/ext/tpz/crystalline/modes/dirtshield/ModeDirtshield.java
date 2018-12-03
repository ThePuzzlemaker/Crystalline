package ext.tpz.crystalline.modes.dirtshield;

import com.google.common.collect.Lists;
import ext.tpz.crystalline.api.crystal.ICrystal;
import ext.tpz.crystalline.api.mode.ICrystalMode;
import ext.tpz.crystalline.crystals.BaseModCrystals;
import ext.tpz.crystalline.entity.EntityWaterThrow;
import ext.tpz.crystalline.insanity.InsanityUtils;
import ext.tpz.crystalline.item.CrystallineItems;
import ext.tpz.crystalline.potion.Potions;
import ext.tpz.crystalline.util.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nullable;
import java.util.List;

public class ModeDirtshield implements ICrystalMode {

    @Override
    public String getUnlocalizedName() {
        return "crystalline.mode.dirt_shield";
    }

    @Override
    public ActionResult<ItemStack> use(ItemStack crystal, EntityPlayer player) {
        if (CrystallineItems.crystal.getReagent(crystal).consume(player, crystal)) {
            if ((CrystallineItems.crystal.getPotential(crystal) - 10) >= 0) {
                CrystallineItems.crystal.setPotential(crystal, CrystallineItems.crystal.getPotential(crystal) - 10);
            } else {
                player.sendStatusMessage(new TextComponentString(TextFormatting.DARK_RED + "Not enough potential!"), true);
                return new ActionResult<ItemStack>(EnumActionResult.FAIL, crystal);
            }
            if (player.getEntityWorld().isRemote) {
                Potions.potionDirtshield.apply(player, 20*5);
                InsanityUtils.addInsanity(player.getEntityWorld(), player, 25);
            }
            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, crystal);
        }
        return new ActionResult<ItemStack>(EnumActionResult.FAIL, crystal);
    }

    @Override
    public List<ICrystal> getValidCrystals() {
        return Lists.newArrayList(BaseModCrystals.dirtshield_crystal);
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
        return new ResourceLocation(Reference.MODID, "mode.dirt_shield");
    }

    @Override
    public Class<ICrystalMode> getRegistryType() {
        return ICrystalMode.class;
    }

}
