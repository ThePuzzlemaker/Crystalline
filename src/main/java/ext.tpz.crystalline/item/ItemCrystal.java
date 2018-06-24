package ext.tpz.crystalline.item;

import ext.tpz.crystalline.util.Reference;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemCrystal extends Item {

    public ItemCrystal() {
        this.setRegistryName("crystal").setUnlocalizedName(Reference.MODID + ".crystal");
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        String type = I18n.format("crystal.type");
        String insanity;
        switch (this.getType(stack)) {
            case "void":
                type += " " + I18n.format("crystal.void"); insanity = I18n.format("crystal.insanity"); break;
            case "cleansing":
                type += " " + I18n.format("crystal.cleansing"); insanity = I18n.format("crystal.noinsanity"); break;
            case "administration":
                type += " " + I18n.format("crystal.administration"); insanity = I18n.format("crystal.insanity"); break;
            case "life":
                type += " " + I18n.format("crystal.life"); insanity = I18n.format("crystal.insanity"); break;
            case "knowledge":
                type += " " + I18n.format("crystal.knowledge"); insanity = I18n.format("crystal.noinsanity"); break;
            case "rift":
                type += " " + I18n.format("crystal.rift"); insanity = I18n.format("crystal.insanity"); break;
            case "universe":
                type += " " + I18n.format("crystal.universe"); insanity = I18n.format("crystal.noinsanity"); break;
            case "artificial":
                type += " " + I18n.format("crystal.artificial"); insanity = I18n.format("crystal.insanity"); break;
            case "unknown":
                type += " " + I18n.format("crystal.unknown"); insanity = I18n.format("crystal.noinsanity"); break;
            default:
                type += " " + I18n.format("crystal.unknown"); insanity = I18n.format("crystal.noinsanity"); break;
        }
        tooltip.add(TextFormatting.RESET + type);
        tooltip.add(TextFormatting.RESET + I18n.format("crystal.drained") + " " + (isDrained(stack) ? I18n.format("i18n.yes") : I18n.format("i18n.no")));
        tooltip.add(TextFormatting.RESET + insanity);
        tooltip.add(TextFormatting.RESET + I18n.format("crystal.properties"));
        tooltip.add(TextFormatting.RESET + "  " + I18n.format("crystal.potential") + " " + Integer.toString(getPotential(stack)) + "%");
        String reagents = "  " + I18n.format("crystal.reagent");
        switch (getReagent(stack)) {
            case "basic":
                reagents += " " + I18n.format("reagent.basic"); break;
            case "advanced":
                reagents += " " + I18n.format("reagent.advanced"); break;
            case "extreme":
                reagents += " " + I18n.format("reagent.extreme"); break;
            case "rift":
                reagents += " " + I18n.format("reagent.rift"); break;
            case "universe":
                reagents += " " + I18n.format("reagent.universe"); break;
            default:
                reagents += " " + I18n.format("reagent.basic"); break;
        }
        tooltip.add(TextFormatting.RESET + reagents);
    }

    public String getType(ItemStack stack) {
        if (stack.hasTagCompound()) {
            if (stack.getTagCompound().hasKey("type")) {
                return stack.getTagCompound().getString("type");
            } else {
                setType(stack, EnumCrystalTypes.UNKNOWN);
                return "unknown";
            }
        } else {
            setType(stack, EnumCrystalTypes.UNKNOWN);
            return "unknown";
        }
    }

    public void setType(ItemStack stack, EnumCrystalTypes type) {
        if (stack.hasTagCompound()) {
            stack.getTagCompound().setString("type", type.getName());
        } else {
            NBTTagCompound tmp = new NBTTagCompound();
            tmp.setString("type", type.getName());
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

    public String getReagent(ItemStack stack) {
        if (stack.hasTagCompound()) {
            if (stack.getTagCompound().hasKey("reagent")) {
                return stack.getTagCompound().getString("reagent");
            } else {
                setReagent(stack, EnumReagentTypes.BASIC);
                return "basic";
            }
        } else {
            setReagent(stack, EnumReagentTypes.BASIC);
            return "basic";
        }
    }

    public void setReagent(ItemStack stack, EnumReagentTypes type) {
        if (stack.hasTagCompound()) {
            stack.getTagCompound().setString("reagent", type.getName());
        } else {
            NBTTagCompound tmp = new NBTTagCompound();
            tmp.setString("reagent", type.getName());
            stack.setTagCompound(tmp);
        }
    }


}
