package ext.tpz.crystalline.api.mode;

import ext.tpz.crystalline.api.crystal.ICrystal;
import ext.tpz.crystalline.api.reagent.IReagent;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ResourceLocation;

import java.util.List;
import java.util.function.Function;

public interface ICrystalMode extends net.minecraftforge.registries.IForgeRegistryEntry<ICrystalMode> {

    String getUnlocalizedName();

    ActionResult<ItemStack> use(ItemStack crystal, EntityPlayer player);

    List<ICrystal> getValidCrystals();

    boolean isValidCrystal(ItemStack crystal);

}
