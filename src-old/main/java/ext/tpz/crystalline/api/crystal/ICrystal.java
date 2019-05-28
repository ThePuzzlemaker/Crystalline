package ext.tpz.crystalline.api.crystal;

import ext.tpz.crystalline.api.mode.ICrystalMode;
import ext.tpz.crystalline.api.reagent.IReagent;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public interface ICrystal extends net.minecraftforge.registries.IForgeRegistryEntry<ICrystal> {

    String getUnlocalizedName();

    IReagent getReagentType();

    List<ICrystalMode> getModes();

    ModelResourceLocation getModel();

    boolean causesInsanity();

    boolean hasBinding();

}
