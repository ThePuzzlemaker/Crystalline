package ext.tpz.crystalline.essences.liquid;

import ext.tpz.crystalline.api.crystal.ICrystal;
import ext.tpz.crystalline.api.essence.liquid.IEssenceLiquid;
import ext.tpz.crystalline.crystals.BaseModCrystals;
import ext.tpz.crystalline.util.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class EssenceLiquidCleansing implements IEssenceLiquid {

    @Override
    public String getUnlocalizedName() {
        return "crystalline.essence.liquid.cleansing";
    }

    @Nullable
    @Override
    public ResourceLocation getRegistryName() {
        return new ResourceLocation(Reference.MODID + ":essence.liquid.cleansing");
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
        return new ModelResourceLocation(Reference.BOTTLE_MODEL_BASE + ".cleansing");
    }

    @Override
    public ICrystal getEquivalent() {
        return BaseModCrystals.cleansing_crystal;
    }

}
