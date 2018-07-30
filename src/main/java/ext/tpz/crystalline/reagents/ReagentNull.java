package ext.tpz.crystalline.reagents;

import ext.tpz.crystalline.api.reagent.IReagent;
import ext.tpz.crystalline.util.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class ReagentNull implements IReagent {

    @Override
    public String getUnlocalizedName() {
        return "crystalline.reagent.null";
    }

    @Override
    public int getValue() {
        return 0;
    }

    @Override
    public ModelResourceLocation getModel() {
        return new ModelResourceLocation(Reference.REAGENT_MODEL_BASE + ".unknown");
    }

    @Override
    public IReagent setRegistryName(ResourceLocation name) {
        return this;
    }

    @Override
    public Class<IReagent> getRegistryType() {
        return IReagent.class;
    }

    @Nullable
    @Override
    public ResourceLocation getRegistryName() {
        return new ResourceLocation(Reference.MODID, "reagents.null");
    }

    @Override
    public boolean consume(EntityPlayer player, ItemStack crystalStack) {
        // Always return true, we can always consume a reagent that doesn't need a reagent. Clear as mud, right?
        return true;
    }

    @Override
    public boolean isUnstable() {
        return false;
    }
}
