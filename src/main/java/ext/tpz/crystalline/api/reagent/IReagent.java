package ext.tpz.crystalline.api.reagent;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;

public interface IReagent extends net.minecraftforge.registries.IForgeRegistryEntry<IReagent> {

    String getUnlocalizedName();

    int getValue();

    ModelResourceLocation getModel();

}
