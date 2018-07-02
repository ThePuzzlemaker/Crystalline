package ext.tpz.crystalline.item;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import ext.tpz.crystalline.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.model.Attributes;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.client.model.obj.OBJModel;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ItemCrystal extends Item {

    public ItemCrystal() {
        this.setRegistryName("crystal").setUnlocalizedName(Reference.MODID + ".crystal").setMaxStackSize(1);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        String type = I18n.format("crystal.type");
        String insanity;
        String bound = "";
        switch (this.getType(stack)) {
            case "void":
                type += " " + I18n.format("crystal.void"); insanity = I18n.format("crystal.insanity"); break;
            case "cleansing":
                type += " " + I18n.format("crystal.cleansing"); insanity = I18n.format("crystal.noinsanity"); break;
            case "administration":
                type += " " + I18n.format("crystal.administration"); insanity = I18n.format("crystal.insanity"); break;
            case "life":
                type += " " + I18n.format("crystal.life"); insanity = I18n.format("crystal.insanity"); bound = I18n.format("crystal.boundto") + " " + getBound(stack); break;
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
        if (getType(stack) == "life") { tooltip.add(TextFormatting.RESET + "  " + bound); }
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
            case "none":
                reagents += " " + I18n.format("reagent.none"); break;
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

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {

        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelResourceLocation knowledge = new ModelResourceLocation(getRegistryName() + "_knowledge", "inventory");
        ModelResourceLocation administration = new ModelResourceLocation(getRegistryName() + "_administration", "inventory");
        ModelResourceLocation cleansing = new ModelResourceLocation(getRegistryName() + "_cleansing", "inventory");
        ModelResourceLocation life = new ModelResourceLocation(getRegistryName() + "_life", "inventory");
        ModelResourceLocation universe = new ModelResourceLocation(getRegistryName() + "_universe", "inventory");
        ModelResourceLocation unknown = new ModelResourceLocation(getRegistryName() + "_unknown", "inventory");
        ModelResourceLocation drained = new ModelResourceLocation(getRegistryName() + "_drained", "inventory");
        ModelResourceLocation rift = new ModelResourceLocation(getRegistryName() + "_rift", "inventory");

        ModelBakery.registerItemVariants(this, knowledge, administration, cleansing, life, universe, unknown, drained, rift);

        ModelLoader.setCustomMeshDefinition(this, new ItemMeshDefinition() {
            @Override
            public ModelResourceLocation getModelLocation(ItemStack stack) {
                if (isDrained(stack))
                    return drained;
                switch (getType(stack)) {
                    case "void":
                        return unknown;
                    case "cleansing":
                        return cleansing;
                    case "administration":
                        return administration;
                    case "life":
                        return life;
                    case "knowledge":
                        return knowledge;
                    case "rift":
                        return rift;
                    case "universe":
                        return universe;
                    case "artificial":
                        return unknown;
                    case "unknown":
                        return unknown;
                    default:
                        return unknown;

                }
            }
        });
    }

    void theIntellijDebuggerIsAnnoyingSoIAddedThisMethodToEditSoTheClassesAreReloadedWithoutAnyChanges() {
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
