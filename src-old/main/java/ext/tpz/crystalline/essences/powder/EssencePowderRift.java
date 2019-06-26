package ext.tpz.crystalline.essences.powder;

import ext.tpz.crystalline.api.essence.liquid.IEssenceLiquid;
import ext.tpz.crystalline.api.essence.powder.IEssencePowder;
import ext.tpz.crystalline.common.util.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class EssencePowderRift implements IEssencePowder {

    @Override
    public String getUnlocalizedName() {
        return "crystalline.essence.powder.rift";
    }

    @Nullable
    @Override
    public ResourceLocation getRegistryName() {
        return new ResourceLocation(Reference.MODID + ":essence.powder.rift");
    }

    @Override
    public Class<IEssencePowder> getRegistryType() {
        return IEssencePowder.class;
    }

    @Override
    public IEssencePowder setRegistryName(ResourceLocation name) {
        return this;
    }

    @Override
    public ModelResourceLocation getModel() {
        return new ModelResourceLocation(Reference.POWDER_MODEL_BASE + ".rift");
    }

}
