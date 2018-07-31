package ext.tpz.crystalline.item.dynamic;

import ext.tpz.crystalline.api.crystal.CrystalRegistry;
import ext.tpz.crystalline.api.crystal.CrystalUtils;
import ext.tpz.crystalline.api.crystal.ICrystal;
import ext.tpz.crystalline.api.mode.ICrystalMode;
import ext.tpz.crystalline.api.mode.ModeUtils;
import ext.tpz.crystalline.api.reagent.IReagent;
import ext.tpz.crystalline.api.reagent.ReagentUtils;
import ext.tpz.crystalline.crystals.BaseModCrystals;
import ext.tpz.crystalline.modes.BaseModModes;
import ext.tpz.crystalline.reagents.BaseModReagents;
import ext.tpz.crystalline.util.Reference;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.*;

public class ItemCrystal extends Item {

    public ItemCrystal() {
        this.setRegistryName("crystal").setUnlocalizedName(Reference.MODID + ".crystal").setMaxStackSize(1);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        ICrystal type = getType(stack);
        if (getType(stack) != null) {
            String textType = I18n.format("crystalline.lore.crystal.type", I18n.format(type.getUnlocalizedName()));
            String insanity = I18n.format("crystalline.lore.crystal.insanity", I18n.format(type.causesInsanity() ? "crystalline.i18n.can" : "crystalline.i18n.doesnot"));
            String bound = I18n.format("crystalline.lore.crystal.bound", type.hasBinding() ? getBound(stack) : "");

            tooltip.add(TextFormatting.RESET + textType);
            tooltip.add(TextFormatting.RESET + I18n.format("crystalline.lore.crystal.drained", isDrained(stack) ? I18n.format("crystalline.i18n.yes") : I18n.format("crystalline.i18n.no")));
            tooltip.add(TextFormatting.RESET + insanity);
            tooltip.add(TextFormatting.RESET + I18n.format("crystalline.lore.crystal.properties"));
            tooltip.add(TextFormatting.RESET + "  " + I18n.format("crystalline.lore.crystal.potential", Integer.toString(getPotential(stack)) + "%"));
            if (type.hasBinding()) {
                tooltip.add(TextFormatting.RESET + "  " + bound);
            }
            IReagent reagent = getReagent(stack);
            if (reagent != null) {
                String textReagent = "  " + I18n.format("crystalline.lore.crystal.reagent", I18n.format(reagent.getUnlocalizedName()));
                tooltip.add(TextFormatting.RESET + textReagent);
            }
        }
    }

    public ICrystal getType(ItemStack stack) {
        if (stack.hasTagCompound()) {
            if (stack.getTagCompound().hasKey("type")) {
                return CrystalUtils.from(stack.getTagCompound().getString("type"));
            } else {
                setType(stack, BaseModCrystals.null_crystal);
                return BaseModCrystals.null_crystal;
            }
        } else {
            setType(stack, BaseModCrystals.null_crystal);
            return BaseModCrystals.null_crystal;
        }
    }

    public void setType(ItemStack stack, ICrystal type) {
        if (stack.hasTagCompound()) {
            stack.getTagCompound().setString("type", CrystalUtils.to(type));
        } else {
            NBTTagCompound tmp = new NBTTagCompound();
            tmp.setString("type", CrystalUtils.to(type));
            stack.setTagCompound(tmp);
        }
    }

    public IReagent getReagent(ItemStack stack) {
        if (stack.hasTagCompound()) {
            if (stack.getTagCompound().hasKey("reagent")) {
                return ReagentUtils.from(stack.getTagCompound().getString("reagent"));
            } else {
                setReagent(stack, BaseModReagents.reagent_null);
                return BaseModReagents.reagent_null;
            }
        } else {
            setReagent(stack, BaseModReagents.reagent_null);
            return BaseModReagents.reagent_null;
        }
    }

    public void setReagent(ItemStack stack, IReagent reagent) {
        if (stack.hasTagCompound()) {
            stack.getTagCompound().setString("reagent", ReagentUtils.to(reagent));
        } else {
            NBTTagCompound tmp = new NBTTagCompound();
            tmp.setString("reagent", ReagentUtils.to(reagent));
            stack.setTagCompound(tmp);
        }
    }


    public boolean isDrained(ItemStack stack) {
        if (stack.hasTagCompound()) {
            if (stack.getTagCompound().hasKey("drained")) {
                return stack.getTagCompound().getBoolean("drained");
            } else {
                setDrained(stack, false);
                return false;
            }
        } else {
            setDrained(stack, false);
            return false;
        }
    }

    public void setDrained(ItemStack stack, boolean drained) {
        if (stack.hasTagCompound()) {
            stack.getTagCompound().setBoolean("drained", drained);
        } else {
            NBTTagCompound tmp = new NBTTagCompound();
            tmp.setBoolean("type", drained);
            stack.setTagCompound(tmp);
        }
    }

    public int getPotential(ItemStack stack) {
        if (stack.hasTagCompound()) {
            if (stack.getTagCompound().hasKey("potential")) {
                return stack.getTagCompound().getInteger("potential");
            } else {
                setPotential(stack, 100);
                return 100;
            }
        } else {
            setPotential(stack, 100);
            return 100;
        }
    }

    public void setPotential(ItemStack stack, int potential) {
        if (stack.hasTagCompound()) {
            stack.getTagCompound().setInteger("potential", potential);
        } else {
            NBTTagCompound tmp = new NBTTagCompound();
            tmp.setInteger("potential", potential);
            stack.setTagCompound(tmp);
        }
    }

    public ICrystalMode getMode(ItemStack stack) {
        if (stack.hasTagCompound()) {
            if (stack.getTagCompound().hasKey("mode")) {
                return ModeUtils.from(stack.getTagCompound().getString("mode"));
            } else {
                setMode(stack, BaseModModes.mode_null);
                return BaseModModes.mode_null;
            }
        } else {
            setMode(stack, BaseModModes.mode_null);
            return BaseModModes.mode_null;
        }
    }

    public void setMode(ItemStack stack, ICrystalMode mode) {
        if (stack.hasTagCompound()) {
            stack.getTagCompound().setString("mode", ModeUtils.to(mode));
        } else {
            NBTTagCompound tmp = new NBTTagCompound();
            tmp.setString("mode", ModeUtils.to(mode));
            stack.setTagCompound(tmp);
        }
    }

    public ICrystalMode cycleMode(ItemStack stack) {
        if (getType(stack) != null && getMode(stack) != null) {
            ICrystal type = getType(stack);
            ICrystalMode currentMode = getMode(stack);
            List<ICrystalMode> modes = type.getModes();
            if (!modes.isEmpty()) {
                int currentModeId = modes.indexOf(currentMode);
                int nextModeId = currentModeId + 1;
                if (currentModeId + 1 > modes.size()-1) {
                    nextModeId = 0;
                }
                ICrystalMode nextMode = modes.get(nextModeId);
                setMode(stack, nextMode);
                return nextMode;
            }
            return BaseModModes.mode_null;
        } else {
            return BaseModModes.mode_null;
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        if (!isDrained(stack) && getPotential(stack) > 0) {
            getMode(stack).use(stack, player);
        } else {
            player.sendStatusMessage(new TextComponentString(TextFormatting.RED + "This crystal is drained of potential!"), true);
            return ActionResult.newResult(EnumActionResult.FAIL, stack);
        }
        if (getPotential(stack) == 0) {
            setDrained(stack, true);
        }
        return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelResourceLocation drained = new ModelResourceLocation(Reference.CRYSTAL_MODEL_BASE + ".drained", "inventory");

        Iterator<ICrystal> iterator  = CrystalRegistry.getRegistry().iterator();

        while (iterator.hasNext()) {
            ICrystal crystal = iterator.next();
            ModelBakery.registerItemVariants(this, crystal.getModel());
        }

        ModelBakery.registerItemVariants(this, drained);

        ModelLoader.setCustomMeshDefinition(this, new ItemMeshDefinition() {
            @Override
            public ModelResourceLocation getModelLocation(ItemStack stack) {
                if (isDrained(stack))
                    return drained;
                if (getType(stack) != null) {
                    return getType(stack).getModel();
                } else {
                    return BaseModCrystals.null_crystal.getModel();
                }
            }
        });
    }

    String theIntellijDebuggerIsAnnoyingSoIAddedThisMethodToEditSoTheClassesAreReloadedWithoutAnyChanges() {
        // If this is in an actual release, blame IntelliJ.
        String a = "This is so stupid.";
        String b = "Here you see my ramblings as I try to get this stupid thing to work.";
        String c = "Am I done yet?";
        String d = "When will this work?";
        String e = "Getting closer...";
        String f = "Almost there...";
        String g = "Nearing the end...";
        String h = "Some more testing...";
        String i = "Come on";
        String z = "I'm skipping to z...Because I can.";
        return a + " " + b + " " + c + " " + d + " " + e + " " + f + " " + g + " " + h + " " + i + " " + z;
    }

    public void setBound(ItemStack stack, String playerName) {
        if (stack.hasTagCompound()) {
            stack.getTagCompound().setString("bound", playerName);
        } else {
            NBTTagCompound tmp = new NBTTagCompound();
            tmp.setString("bound", playerName);
            stack.setTagCompound(tmp);
        }
    }

    public String getBound(ItemStack stack) {
        if (stack.hasTagCompound()) {
            if (stack.getTagCompound().hasKey("bound")) {
                return stack.getTagCompound().getString("bound");
            } else {
                setBound(stack, "SickPlayerNameThatDoesNotExist");
                return "SickPlayerNameThatDoesNotExist";
            }
        } else {
            setBound(stack, "SickPlayerNameThatDoesNotExist");
            return "SickPlayerNameThatDoesNotExist";
        }
    }
}
