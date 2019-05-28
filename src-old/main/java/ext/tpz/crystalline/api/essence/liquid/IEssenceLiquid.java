package ext.tpz.crystalline.api.essence.liquid;

import ext.tpz.crystalline.api.crystal.ICrystal;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;

public interface IEssenceLiquid extends net.minecraftforge.registries.IForgeRegistryEntry<IEssenceLiquid> {

    String getUnlocalizedName();

    ICrystal getEquivalent();

    ModelResourceLocation getModel();

}
