package ext.tpz.crystalline.api.reagent;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IReagent extends net.minecraftforge.registries.IForgeRegistryEntry<IReagent> {

    String getUnlocalizedName();

    /**
     * value:
     * -1:  individual, no other reagent can be used
     *  0:  doesn't exist.
     *  1:  basic
     *  2:  advanced
     *  3:  extreme
     *  4:  rift
     *  5:  universe
     * 6+:  above
     */
    int getValue();

    ModelResourceLocation getModel();

    boolean consume(EntityPlayer player, ItemStack crystalStack);

    boolean isUnstable();

}
