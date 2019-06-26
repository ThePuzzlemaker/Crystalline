package ext.tpz.crystalline.modes;

import com.google.common.collect.Lists;
import ext.tpz.crystalline.api.crystal.CrystalRegistry;
import ext.tpz.crystalline.api.crystal.ICrystal;
import ext.tpz.crystalline.api.mode.ICrystalMode;
import ext.tpz.crystalline.crystals.BaseModCrystals;
import ext.tpz.crystalline.crystals.CrystalKnowledge;
import ext.tpz.crystalline.item.CrystallineItems;
import ext.tpz.crystalline.common.util.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.List;

public class ModeNull implements ICrystalMode {

    @Override
    public String getUnlocalizedName() {
        return "crystalline.mode.null";
    }

    @Override
    public ActionResult<ItemStack> use(ItemStack crystal, EntityPlayer player) {
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, crystal);
    }

    @Override
    public List<ICrystal> getValidCrystals() { // Allow on all crystals
        return Lists.newArrayList(CrystalRegistry.getRegistry().getValuesCollection());
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
        return new ResourceLocation(Reference.MODID, "mode.null");
    }

    @Override
    public Class<ICrystalMode> getRegistryType() {
        return ICrystalMode.class;
    }
}
