package ext.tpz.crystalline.modes.knowledge;

import com.google.common.collect.Lists;
import ext.tpz.crystalline.api.crystal.CrystalRegistry;
import ext.tpz.crystalline.api.crystal.ICrystal;
import ext.tpz.crystalline.api.mode.ICrystalMode;
import ext.tpz.crystalline.crystals.BaseModCrystals;
import ext.tpz.crystalline.crystals.CrystalKnowledge;
import ext.tpz.crystalline.util.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.List;

public class ModeViewInsanity implements ICrystalMode {

    @Override
    public String getUnlocalizedName() {
        return "crystalline.mode.view_insanity";
    }

    @Override
    public ActionResult<ItemStack> use(ItemStack crystal, EntityPlayer player) {
        return null;
    }

    @Override
    public List<ICrystal> getValidCrystals() {
        return Lists.newArrayList(BaseModCrystals.knowledge_crystal);
    }

    @Override
    public boolean isValidCrystal(ItemStack crystal) {
      /*if (getValidCrystals().contains(CrystallineItems.crystal.getType(crystal))) {
       *
      }*/
        return true;
    }

    @Override
    public ICrystalMode setRegistryName(ResourceLocation name) {
        return null;
    }

    @Nullable
    @Override
    public ResourceLocation getRegistryName() {
        return new ResourceLocation(Reference.MODID, "mode.view_insanity");
    }

    @Override
    public Class<ICrystalMode> getRegistryType() {
        return ICrystalMode.class;
    }
}
