package ext.tpz.crystalline.reagents;

import ext.tpz.crystalline.api.reagent.IReagent;
import ext.tpz.crystalline.util.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class ReagentAdvanced implements IReagent {

    @Override
    public String getUnlocalizedName() {
        return "crystalline.reagent.advanced";
    }

    @Override
    public int getValue() {
        return 2;
    }

    @Override
    public ModelResourceLocation getModel() {
        return null;
        //return new ModelResourceLocation(Reference.REAGENT_MODEL_BASE + ".advanced");
    }

    @Override
    public IReagent setRegistryName(ResourceLocation name) {
        return null;
    }

    @Override
    public Class<IReagent> getRegistryType() {
        return IReagent.class;
    }

    @Nullable
    @Override
    public ResourceLocation getRegistryName() {
        return new ResourceLocation(Reference.MODID, "reagent.advanced");
    }

    @Override
    public boolean consume(EntityPlayer player, ItemStack crystalStack) {
        return false;
    }

}
