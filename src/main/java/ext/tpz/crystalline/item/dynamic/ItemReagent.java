package ext.tpz.crystalline.item.dynamic;

import ext.tpz.crystalline.api.reagent.IReagent;
import ext.tpz.crystalline.api.reagent.ReagentRegistry;
import ext.tpz.crystalline.api.reagent.ReagentUtils;
import ext.tpz.crystalline.reagents.BaseModReagents;
import ext.tpz.crystalline.util.Reference;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;

public class ItemReagent extends Item {

    public ItemReagent() {
        this.setRegistryName("reagent").setUnlocalizedName(Reference.MODID + ".reagent").setMaxStackSize(1);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        IReagent type = getType(stack);
        if (type != null) {
            String textType = I18n.format("crystalline.lore.reagent.type", "missingno");
            if (type.getUnlocalizedName() != null) {
                textType = I18n.format("crystalline.lore.reagent.type", I18n.format(type.getUnlocalizedName()));
            }
            tooltip.add(TextFormatting.RESET + textType);
            if (type.isUnstable())
                tooltip.add(TextFormatting.RESET + "" + TextFormatting.DARK_RED + I18n.format("crystalline.lore.reagent.unstable"));
        }
    }

    public IReagent getType(ItemStack stack) {
        if (stack.hasTagCompound()) {
            if (stack.getTagCompound().hasKey("type")) {
                return ReagentUtils.from(stack.getTagCompound().getString("type"));
            } else {
                setType(stack, BaseModReagents.reagent_null);
                return BaseModReagents.reagent_null;
            }
        } else {
            setType(stack, BaseModReagents.reagent_null);
            return BaseModReagents.reagent_null;
        }
    }

    public void setType(ItemStack stack, IReagent type) {
        if (stack.hasTagCompound()) {
            stack.getTagCompound().setString("type", ReagentUtils.to(type));
        } else {
            NBTTagCompound tmp = new NBTTagCompound();
            tmp.setString("type", ReagentUtils.to(type));
            stack.setTagCompound(tmp);
        }
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        Iterator<IReagent> iterator = ReagentRegistry.getRegistry().iterator();

        while (iterator.hasNext()) {
            IReagent reagent = iterator.next();
            ModelBakery.registerItemVariants(this, reagent.getModel());
        }

        ModelLoader.setCustomMeshDefinition(this, new ItemMeshDefinition() {
            @Override
            public ModelResourceLocation getModelLocation(ItemStack stack) {
                return getType(stack).getModel();
            }
        });
    }


}
