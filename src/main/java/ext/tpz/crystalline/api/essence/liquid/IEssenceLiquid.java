package ext.tpz.crystalline.api.essence.liquid;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;

public interface IEssenceLiquid extends net.minecraftforge.registries.IForgeRegistryEntry<IEssenceLiquid> {

    String getUnlocalizedName();

    ModelResourceLocation getModel();

}
