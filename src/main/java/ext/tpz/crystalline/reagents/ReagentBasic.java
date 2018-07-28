package ext.tpz.crystalline.reagents;

import ext.tpz.crystalline.api.reagent.IReagent;
import ext.tpz.crystalline.util.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class ReagentBasic implements IReagent {

    @Override
    public String getUnlocalizedName() {
        return "crystalline.reagent.basic";
    }

    @Override
    public int getValue() {
        return 1;
    }

    @Override
    public ModelResourceLocation getModel() {
        return null;
        //return new ModelResourceLocation(Reference.REAGENT_MODEL_BASE + ".basic");
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
        return new ResourceLocation(Reference.MODID, "reagent.basic");
    }

    @Override
    public boolean consume(EntityPlayer player, ItemStack crystalStack) {
        return false;
    }


}
