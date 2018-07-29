package ext.tpz.crystalline.essences.liquid;

import ext.tpz.crystalline.api.essence.liquid.IEssenceLiquid;
import ext.tpz.crystalline.util.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class EssenceLiquidKnowledge implements IEssenceLiquid {

    @Override
    public String getUnlocalizedName() {
        return "crystalline.essence.knowledge";
    }

    @Nullable
    @Override
    public ResourceLocation getRegistryName() {
        return new ModelResourceLocation(Reference.MODID + ":essence.liquid.knowledge");
    }

    @Override
    public Class<IEssenceLiquid> getRegistryType() {
        return IEssenceLiquid.class;
    }

    @Override
    public IEssenceLiquid setRegistryName(ResourceLocation name) {
        return null;
    }

    @Override
    public ModelResourceLocation getModel() {
        return new ModelResourceLocation(Reference.BOTTLE_MODEL_BASE + ".knowledge");
    }

}
