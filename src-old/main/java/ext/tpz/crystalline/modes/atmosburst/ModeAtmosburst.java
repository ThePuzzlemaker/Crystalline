package ext.tpz.crystalline.modes.atmosburst;

import com.google.common.collect.Lists;
import ext.tpz.crystalline.api.crystal.ICrystal;
import ext.tpz.crystalline.api.mode.ICrystalMode;
import ext.tpz.crystalline.crystals.BaseModCrystals;
import ext.tpz.crystalline.insanity.InsanityUtils;
import ext.tpz.crystalline.item.CrystallineItems;
import ext.tpz.crystalline.potion.Potions;
import ext.tpz.crystalline.common.util.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ModeAtmosburst implements ICrystalMode {

    @Override
    public String getUnlocalizedName() {
        return "crystalline.mode.atmos_burst";
    }

    @Override
    public ActionResult<ItemStack> use(ItemStack crystal, EntityPlayer player) {
        if (CrystallineItems.crystal.getReagent(crystal).consume(player, crystal)) {
            if ((CrystallineItems.crystal.getPotential(crystal) - 5) >= 0) {
                CrystallineItems.crystal.setPotential(crystal, CrystallineItems.crystal.getPotential(crystal) - 5);
            } else {
                player.sendStatusMessage(new TextComponentString(TextFormatting.DARK_RED + "Not enough potential!"), true);
                return new ActionResult<ItemStack>(EnumActionResult.FAIL, crystal);
            }
            if (!player.getEntityWorld().isRemote) {
                for (int i = 0; i <= 2100; i++) {
                    double pX = player.posX;
                    double pY = player.posY;
                    double pZ = player.posZ;
                    World world = player.getEntityWorld();
                    AxisAlignedBB q1 = new AxisAlignedBB(pX, pY - 2 , pZ, pX - 5, pY + 2, pZ + 5);
                    AxisAlignedBB q2 = new AxisAlignedBB(pX, pY - 2, pZ, pX + 5, pY + 2, pZ + 5);
                    AxisAlignedBB q3 = new AxisAlignedBB(pX, pY - 2, pZ, pX + 5, pY + 2, pZ - 5);
                    AxisAlignedBB q4 = new AxisAlignedBB(pX, pY - 2, pZ, pX - 5, pX + 2, pZ - 5);
                    List<EntityLivingBase> q1e = world.getEntitiesWithinAABB(EntityLivingBase.class, q1);
                    List<EntityLivingBase> q2e = world.getEntitiesWithinAABB(EntityLivingBase.class, q2);
                    List<EntityLivingBase> q3e = world.getEntitiesWithinAABB(EntityLivingBase.class, q3);
                    List<EntityLivingBase> q4e = world.getEntitiesWithinAABB(EntityLivingBase.class, q4);
                    for (EntityLivingBase e : q1e) {
                        e.setVelocity(1.0f, 0.2f, 1.0f);
                    }
                    for (EntityLivingBase e : q2e) {
                        e.setVelocity(-1.0f, 0.2f, 1.0f);
                    }
                    for (EntityLivingBase e : q3e) {
                        e.setVelocity(-1.0f, 0.2f, -1.0f);
                    }
                    for (EntityLivingBase e : q4e) {
                        e.setVelocity(1.0f, 0.2f, -1.0f);
                    }
                    InsanityUtils.addInsanity(player.getEntityWorld(), player, 10);
                }
            }
            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, crystal);
        }
        return new ActionResult<ItemStack>(EnumActionResult.FAIL, crystal);
    }

    @Override
    public List<ICrystal> getValidCrystals() {
        return Lists.newArrayList(BaseModCrystals.atmosburst_crystal);
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
        return new ResourceLocation(Reference.MODID, "mode.atmos_burst");
    }

    @Override
    public Class<ICrystalMode> getRegistryType() {
        return ICrystalMode.class;
    }

}
