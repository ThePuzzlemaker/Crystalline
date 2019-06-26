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

public class CrystalAdministration implements ICrystal {

    @Override
    public String getUnlocalizedName() {
        return "crystalline.crystal.administration";
    }

    @Override
    public IReagent getReagentType() {
        return BaseModReagents.reagent_extreme;
    }

    @Override
    public List<ICrystalMode> getModes() {
        return Lists.newArrayList(BaseModModes.mode_null, BaseModModes.administration);
    }

    @Override
    public ModelResourceLocation getModel() {
        return new ModelResourceLocation(Reference.CRYSTAL_MODEL_BASE + ".administration");
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
        return new ResourceLocation(Reference.MODID, "crystal.administration");
    }

    @Override
    public boolean causesInsanity() {
        return true;
    }

    @Override
    public boolean hasBinding() {
        return true;
    }
}
