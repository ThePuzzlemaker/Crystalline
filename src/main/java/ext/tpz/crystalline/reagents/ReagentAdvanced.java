package ext.tpz.crystalline.reagents;

import ext.tpz.crystalline.api.reagent.IReagent;
import ext.tpz.crystalline.api.reagent.ReagentUtils;
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
        return new ModelResourceLocation(Reference.REAGENT_MODEL_BASE + ".advanced");
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
        return ReagentUtils.consume(this, player, crystalStack);
    }

    @Override
    public boolean isUnstable() {
        return false;
    }
}
