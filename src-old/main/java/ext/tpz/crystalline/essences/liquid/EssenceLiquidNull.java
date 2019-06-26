package ext.tpz.crystalline.essences.liquid;

import ext.tpz.crystalline.api.crystal.ICrystal;
import ext.tpz.crystalline.api.essence.liquid.IEssenceLiquid;
import ext.tpz.crystalline.crystals.BaseModCrystals;
import ext.tpz.crystalline.common.util.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class EssenceLiquidNull implements IEssenceLiquid {

    @Override
    public String getUnlocalizedName() {
        return "crystalline.essence.liquid.null";
    }

    @Nullable
    @Override
    public ResourceLocation getRegistryName() {
        return new ResourceLocation(Reference.MODID + ":essence.liquid.null");
    }

    @Override
    public Class<IEssenceLiquid> getRegistryType() {
        return IEssenceLiquid.class;
    }

    @Override
    public IEssenceLiquid setRegistryName(ResourceLocation name) {
        return this;
    }

    @Override
    public ModelResourceLocation getModel() {
        return new ModelResourceLocation(Reference.BOTTLE_MODEL_BASE + ".null");
    }

    @Override
    public ICrystal getEquivalent() {
        return BaseModCrystals.null_crystal;
    }

}
