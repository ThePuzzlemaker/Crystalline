package ext.tpz.crystalline.modes.aquagust;

import com.google.common.collect.Lists;
import ext.tpz.crystalline.api.crystal.ICrystal;
import ext.tpz.crystalline.api.mode.ICrystalMode;
import ext.tpz.crystalline.crystals.BaseModCrystals;
import ext.tpz.crystalline.entity.EntityFireThrow;
import ext.tpz.crystalline.entity.EntityWaterThrow;
import ext.tpz.crystalline.insanity.InsanityUtils;
import ext.tpz.crystalline.item.CrystallineItems;
import ext.tpz.crystalline.common.util.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nullable;
import java.util.List;

public class ModeWaterThrow implements ICrystalMode {

    @Override
    public String getUnlocalizedName() {
        return "crystalline.mode.water_throw";
    }

    @Override
    public ActionResult<ItemStack> use(ItemStack crystal, EntityPlayer player) {
        if (CrystallineItems.crystal.getReagent(crystal).consume(player, crystal)) {
            if ((CrystallineItems.crystal.getPotential(crystal) - 1) >= 0) {
                CrystallineItems.crystal.setPotential(crystal, CrystallineItems.crystal.getPotential(crystal) - 1);
            } else {
                player.sendStatusMessage(new TextComponentString(TextFormatting.DARK_RED + "Not enough potential!"), true);
                return new ActionResult<ItemStack>(EnumActionResult.FAIL, crystal);
            }
            EntityWaterThrow eOB = new EntityWaterThrow(player.getEntityWorld(), player);
            eOB.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F, 0.0F);
            player.getEntityWorld().spawnEntity(eOB);
            if (!player.getEntityWorld().isRemote)
                InsanityUtils.addInsanity(player.getEntityWorld(), player, 10);
            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, crystal);
        }
        return new ActionResult<ItemStack>(EnumActionResult.FAIL, crystal);
    }

    @Override
    public List<ICrystal> getValidCrystals() {
        return Lists.newArrayList(BaseModCrystals.aquagust_crystal);
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
        return new ResourceLocation(Reference.MODID, "mode.water_throw");
    }

    @Override
    public Class<ICrystalMode> getRegistryType() {
        return ICrystalMode.class;
    }

}
