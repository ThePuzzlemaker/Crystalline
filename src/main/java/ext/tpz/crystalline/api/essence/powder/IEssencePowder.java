package ext.tpz.crystalline.api.essence.powder;

import ext.tpz.crystalline.api.crystal.ICrystal;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;

import java.util.List;

public interface IEssencePowder extends net.minecraftforge.registries.IForgeRegistryEntry<IEssencePowder> {

    String getUnlocalizedName();

    ModelResourceLocation getModel();

}
