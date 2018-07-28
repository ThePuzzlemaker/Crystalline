package ext.tpz.crystalline.item.dynamic;

import ext.tpz.crystalline.insanity.InsanityWorldSavedData;
import ext.tpz.crystalline.item.EnumCrystalTypes;
import ext.tpz.crystalline.util.Reference;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class ItemEssenceBottle extends Item {

    public ItemEssenceBottle() {
        this.setRegistryName("essence_bottle").setUnlocalizedName(Reference.MODID + ".essence_bottle").setMaxStackSize(1);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        String type = I18n.format("essence.type");
        switch (this.getType(stack)) {
            case "void":
                type += " " + I18n.format("crystal.void"); break;
            case "cleansing":
                type += " " + I18n.format("crystal.cleansing"); break;
            case "administration":
                type += " " + I18n.format("crystal.administration"); break;
            case "life":
                type += " " + I18n.format("crystal.life"); break;
            case "knowledge":
                type += " " + I18n.format("crystal.knowledge"); break;
            case "rift":
                type += " " + I18n.format("crystal.rift"); break;
            case "universe":
                type += " " + I18n.format("crystal.universe"); break;
            case "artificial":
                type += " " + I18n.format("crystal.artificial"); break;
            case "unknown":
                type += " " + I18n.format("crystal.unknown"); break;
            default:
                type += " " + I18n.format("crystal.unknown"); break;
        }
        tooltip.add(TextFormatting.RESET + type);
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

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelResourceLocation knowledge = new ModelResourceLocation(getRegistryName() + "_knowledge", "inventory");
        ModelResourceLocation administration = new ModelResourceLocation(getRegistryName() + "_administration", "inventory");
        ModelResourceLocation cleansing = new ModelResourceLocation(getRegistryName() + "_cleansing", "inventory");
        ModelResourceLocation life = new ModelResourceLocation(getRegistryName() + "_life", "inventory");
        ModelResourceLocation universe = new ModelResourceLocation(getRegistryName() + "_universe", "inventory");
        ModelResourceLocation unknown = new ModelResourceLocation(getRegistryName() + "_unknown", "inventory");
        ModelResourceLocation rift = new ModelResourceLocation(getRegistryName() + "_rift", "inventory");

        ModelBakery.registerItemVariants(this, knowledge, administration, cleansing, life, universe, unknown, rift);

        ModelLoader.setCustomMeshDefinition(this, new ItemMeshDefinition() {
            @Override
            public ModelResourceLocation getModelLocation(ItemStack stack) {
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

}
