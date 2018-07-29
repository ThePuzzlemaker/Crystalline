package ext.tpz.crystalline.api.recipe;

import ext.tpz.crystalline.api.essence.liquid.IEssenceLiquid;
import net.minecraft.item.ItemStack;

public interface IDistillationRecipe extends net.minecraftforge.registries.IForgeRegistryEntry<IDistillationRecipe> {

    ItemStack getInput();

    IEssenceLiquid getOutput();

}
