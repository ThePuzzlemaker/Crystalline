package ext.tpz.crystalline.modes.knowledge;

import com.google.common.collect.Lists;
import ext.tpz.crystalline.api.crystal.CrystalRegistry;
import ext.tpz.crystalline.api.crystal.ICrystal;
import ext.tpz.crystalline.api.mode.ICrystalMode;
import ext.tpz.crystalline.crystals.BaseModCrystals;
import ext.tpz.crystalline.crystals.CrystalKnowledge;
import ext.tpz.crystalline.insanity.InsanityWorldSavedData;
import ext.tpz.crystalline.item.CrystallineItems;
import ext.tpz.crystalline.util.Reference;
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
        String msg = "Insanity: ";
        String msg2 = "You have Stage ";
        if (insanity >= 0 && insanity <= 9) {
            msg += "[-------] (" + insanity + "%)";
            msg2 += "0 insanity. This means you will not experience any effects.";
        } else if (insanity >= 10 && insanity <= 29) {
            msg += "[*------] (" + insanity + "%)";
            msg2 += "1 insanity. This means you may experience some minor effects.";
        } else if (insanity >= 30 && insanity <= 49) {
            msg += "[**-----] (" + insanity + "%)";
            msg2 += "2 insanity. This means you may experience some minor effects.";
        } else if (insanity >= 50 && insanity <= 69) {
            msg += "[***----] (" + insanity + "%)";
            msg2 += "3 insanity. This means you may experience some possibly damaging effects.";
        } else if (insanity >= 70 && insanity <= 89) {
            msg += "[****---] (" + insanity + "%)";
            msg2 += "4 insanity. This means you may experience some possibly lethal effects.";
        } else if (insanity >= 90 && insanity <= 99) {
            msg += "[*****--] (" + insanity + "%)";
            msg2 += "5 insanity. This means you may experience some possibly lethal effects.";
        } else if (insanity == 100) {
            msg += "[******-] (" + insanity + "%)";
            msg2 += "6 insanity. This means you may experience some possibly lethal effects.";
        } else if (insanity == 101) {
            msg += "[#######] (" + insanity + "%)";
            msg2 = "You are permanently insane. You may experience possibly lethal effects.";
        }

        if (player.isSneaking()) {
            player.sendStatusMessage(new TextComponentString(msg2), true);
        } else {
            player.sendStatusMessage(new TextComponentString(msg), true);
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
