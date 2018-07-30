package ext.tpz.crystalline.modes.cleansing;

import com.google.common.collect.Lists;
import ext.tpz.crystalline.api.crystal.ICrystal;
import ext.tpz.crystalline.api.mode.ICrystalMode;
import ext.tpz.crystalline.crystals.BaseModCrystals;
import ext.tpz.crystalline.insanity.InsanityUtils;
import ext.tpz.crystalline.item.CrystallineItems;
import ext.tpz.crystalline.util.Reference;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nullable;
import java.util.List;

public class ModeCleansingReagent implements ICrystalMode {

    @Override
    public String getUnlocalizedName() {
        return "crystalline.mode.cleansing_reagent";
    }

    @Override
    public ActionResult<ItemStack> use(ItemStack crystal, EntityPlayer player) {
        if (CrystallineItems.crystal.getType(crystal).getReagentType().consume(player, crystal)) {
            BlockPos pos = player.getPosition();
            ItemStack s = new ItemStack(CrystallineItems.cleansing_reagent, 1);
            if ((CrystallineItems.crystal.getPotential(crystal) - 10) > 0) {
                CrystallineItems.crystal.setPotential(crystal, CrystallineItems.crystal.getPotential(crystal) - 10);
            } else {
                player.sendStatusMessage(new TextComponentString(TextFormatting.RED + "Not enough potential!"), true);
                return new ActionResult<ItemStack>(EnumActionResult.FAIL, crystal);
            }
            if (!player.inventory.addItemStackToInventory(s)) {
                EntityItem item = new EntityItem(player.getEntityWorld(), pos.getX(), pos.getY(), pos.getZ(), s);
                player.getEntityWorld().spawnEntity(item);
            } else {
                player.openContainer.detectAndSendChanges();
            }
            InsanityUtils.addInsanity(player.getEntityWorld(), player, 1);
        }
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, crystal);
    }

    @Override
    public List<ICrystal> getValidCrystals() {
        return Lists.newArrayList(BaseModCrystals.cleansing_crystal);
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
        return new ResourceLocation(Reference.MODID, "mode.cleansing_reagent");
    }

    @Override
    public Class<ICrystalMode> getRegistryType() {
        return ICrystalMode.class;
    }
}
