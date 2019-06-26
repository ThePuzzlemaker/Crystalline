package ext.tpz.crystalline.modes.knowledge;

import com.google.common.collect.Lists;
import ext.tpz.crystalline.api.crystal.CrystalRegistry;
import ext.tpz.crystalline.api.crystal.ICrystal;
import ext.tpz.crystalline.api.mode.ICrystalMode;
import ext.tpz.crystalline.crystals.BaseModCrystals;
import ext.tpz.crystalline.crystals.CrystalKnowledge;
import ext.tpz.crystalline.insanity.InsanityUtils;
import ext.tpz.crystalline.insanity.InsanityWorldSavedData;
import ext.tpz.crystalline.item.CrystallineItems;
import ext.tpz.crystalline.common.util.Reference;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;

import javax.annotation.Nullable;
import java.util.List;

public class ModeViewInsanity implements ICrystalMode {

    @Override
    public String getUnlocalizedName() {
        return "crystalline.mode.view_insanity";
    }

    @Override
    public ActionResult<ItemStack> use(ItemStack crystal, EntityPlayer player) {
        InsanityWorldSavedData data = InsanityWorldSavedData.get(player.getEntityWorld());
        int insanity = data.getPlayer(player.getUniqueID());
        if (player.isSneaking()) {
            player.sendStatusMessage(new TextComponentString(I18n.format("crystalline.insanity.desc.s"+ InsanityUtils.insanityToStage(insanity).ordinal())), true);
        } else {
            player.sendStatusMessage(new TextComponentString(InsanityUtils.genBar(InsanityUtils.insanityToStage(insanity).ordinal(), insanity == 101)), true);
        }
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, crystal);
    }

    @Override
    public List<ICrystal> getValidCrystals() {
        return Lists.newArrayList(BaseModCrystals.knowledge_crystal);
    }

    @Override
    public boolean isValidCrystal(ItemStack crystal) {
        return getValidCrystals().contains(CrystallineItems.crystal.getType(crystal));
    }

    @Override
    public ICrystalMode setRegistryName(ResourceLocation name) {
        return this;
    }

    @Nullable
    @Override
    public ResourceLocation getRegistryName() {
        return new ResourceLocation(Reference.MODID, "mode.view_insanity");
    }

    @Override
    public Class<ICrystalMode> getRegistryType() {
        return ICrystalMode.class;
    }
}
