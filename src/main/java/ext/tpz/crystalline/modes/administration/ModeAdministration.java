package ext.tpz.crystalline.modes.administration;

import com.google.common.collect.Lists;
import ext.tpz.crystalline.api.crystal.ICrystal;
import ext.tpz.crystalline.api.mode.ICrystalMode;
import ext.tpz.crystalline.crystals.BaseModCrystals;
import ext.tpz.crystalline.insanity.InsanityUtils;
import ext.tpz.crystalline.item.CrystallineItems;
import ext.tpz.crystalline.util.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nullable;
import java.util.List;

public class ModeAdministration implements ICrystalMode {

    @Override
    public String getUnlocalizedName() {
        return "crystalline.mode.administration";
    }

    @Override
    public ActionResult<ItemStack> use(ItemStack crystal, EntityPlayer player) {
        EntityPlayer target = player.getEntityWorld().getPlayerEntityByName(CrystallineItems.crystal.getBound(crystal));
        if (target != null) {
            if (player.isSneaking()) {
                if (CrystallineItems.crystal.getType(crystal).getReagentType().consume(player, crystal)) {
                    if ((CrystallineItems.crystal.getPotential(crystal) - 10) >= 0) {
                        CrystallineItems.crystal.setPotential(crystal, CrystallineItems.crystal.getPotential(crystal) - 10);
                    } else {
                        player.sendStatusMessage(new TextComponentString(TextFormatting.RED + "Not enough potential!"), true);
                        return new ActionResult<ItemStack>(EnumActionResult.FAIL, crystal);
                    }
                    target.setHealth(target.getHealth() - 2.5f);
                    InsanityUtils.addInsanity(player.getEntityWorld(), player, 25);
                }
            } else {
                if (CrystallineItems.crystal.getType(crystal).getReagentType().consume(player, crystal)) {
                    double strength = 0.5D;
                    if ((CrystallineItems.crystal.getPotential(crystal) - 5) >= 0) {
                        CrystallineItems.crystal.setPotential(crystal, CrystallineItems.crystal.getPotential(crystal) - 5);
                    } else {
                        player.sendStatusMessage(new TextComponentString(TextFormatting.RED + "Not enough potential!"), true);
                        return new ActionResult<ItemStack>(EnumActionResult.FAIL, crystal);
                    }
                    switch(player.getHorizontalFacing()) {
                        case UP:
                            target.setVelocity(0.0D, strength, 0.0D);
                            break;
                        case DOWN:
                            target.setVelocity(0.0D, -strength, 0.0D);
                            break;
                        case EAST:
                            target.setVelocity(strength, (-player.rotationPitch)/100, 0.0D);
                            break;
                        case WEST:
                            target.setVelocity(-strength, (-player.rotationPitch)/100, 0.0D);
                            break;
                        case NORTH:
                            target.setVelocity(0.0D, (-player.rotationPitch)/100, -strength);
                            break;
                        case SOUTH:
                            target.setVelocity(0.0D, (-player.rotationPitch)/100, strength);
                            break;
                    }
                    InsanityUtils.addInsanity(player.getEntityWorld(), player, 10);
                }
            }
        }
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, crystal);
    }

    @Override
    public List<ICrystal> getValidCrystals() {
        return Lists.newArrayList(BaseModCrystals.administration_crystal);
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
        return new ResourceLocation(Reference.MODID, "mode.administration");
    }

    @Override
    public Class<ICrystalMode> getRegistryType() {
        return ICrystalMode.class;
    }
}
