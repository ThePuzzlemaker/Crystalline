package ext.tpz.crystalline.reagents;

import ext.tpz.crystalline.api.reagent.IReagent;
import ext.tpz.crystalline.api.reagent.ReagentUtils;
import ext.tpz.crystalline.insanity.InsanityUtils;
import ext.tpz.crystalline.insanity.InsanityWorldSavedData;
import ext.tpz.crystalline.item.CrystallineItems;
import ext.tpz.crystalline.util.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class ReagentRift implements IReagent {

    @Override
    public String getUnlocalizedName() {
        return "crystalline.reagent.rift";
    }

    @Override
    public int getValue() {
        return 4;
    }

    @Override
    public ModelResourceLocation getModel() {
        return new ModelResourceLocation(Reference.REAGENT_MODEL_BASE + ".rift");
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
        return new ResourceLocation(Reference.MODID, "reagent.rift");
    }

    @Override
    public boolean consume(EntityPlayer player, ItemStack crystalStack) {
        return ReagentUtils.consume(this, player, crystalStack);
    }

    @Override
    public boolean isUnstable() {
        return true;
    }
}
