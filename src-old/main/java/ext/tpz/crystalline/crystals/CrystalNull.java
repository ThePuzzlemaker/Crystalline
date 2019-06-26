package ext.tpz.crystalline.crystals;

import com.google.common.collect.Lists;
import ext.tpz.crystalline.api.crystal.ICrystal;
import ext.tpz.crystalline.api.mode.ICrystalMode;
import ext.tpz.crystalline.api.reagent.IReagent;
import ext.tpz.crystalline.modes.BaseModModes;
import ext.tpz.crystalline.reagents.BaseModReagents;
import ext.tpz.crystalline.common.util.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.List;

public class CrystalNull implements ICrystal {

    @Override
    public String getUnlocalizedName() {
        return "crystalline.crystal.null";
    }

    @Override
    public IReagent getReagentType() {
        return BaseModReagents.reagent_null;
    }

    @Override
    public List<ICrystalMode> getModes() {
        return Lists.newArrayList(BaseModModes.mode_null);
    }

    @Override
    public ModelResourceLocation getModel() {
        return new ModelResourceLocation(Reference.CRYSTAL_MODEL_BASE + ".null");
    }

    @Override
    public ICrystal setRegistryName(ResourceLocation name) {
        return this;
    }

    @Override
    public Class<ICrystal> getRegistryType() {
        return ICrystal.class;
    }

    @Nullable
    @Override
    public ResourceLocation getRegistryName() {
        return new ResourceLocation(Reference.MODID, "crystal.null");
    }

    @Override
    public boolean causesInsanity() {
        return false;
    }

    @Override
    public boolean hasBinding() {
        return false;
    }

}
