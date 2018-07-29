package ext.tpz.crystalline.item.dynamic;

import ext.tpz.crystalline.api.essence.liquid.EssenceLiquidRegistry;
import ext.tpz.crystalline.api.essence.liquid.EssenceLiquidUtils;
import ext.tpz.crystalline.api.essence.liquid.IEssenceLiquid;
import ext.tpz.crystalline.api.essence.powder.EssencePowderUtils;
import ext.tpz.crystalline.api.essence.powder.IEssencePowder;
import ext.tpz.crystalline.entity.DamageSourceLiquidEssence;
import ext.tpz.crystalline.essences.liquid.BaseModEssenceLiquids;
import ext.tpz.crystalline.essences.powder.BaseModEssencePowders;
import ext.tpz.crystalline.util.Reference;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemSimpleFoiled;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;

public class ItemEssencePowder extends ItemSimpleFoiled {

    public ItemEssencePowder() {
        this.setRegistryName("essence_powder").setUnlocalizedName(Reference.MODID + ".essence_powder").setMaxStackSize(1);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        String type = I18n.format("crystalline.lore.essence.type", I18n.format(getType(stack).getUnlocalizedName()));
        tooltip.add(TextFormatting.RESET + type);
    }

    public IEssencePowder getType(ItemStack stack) {
        if (stack.hasTagCompound()) {
            if (stack.getTagCompound().hasKey("type")) {
                return EssencePowderUtils.from(stack.getTagCompound().getString("type"));
            } else {
                setType(stack, BaseModEssencePowders.essence_powder_null);
                return BaseModEssencePowders.essence_powder_null;
            }
        } else {
            setType(stack, BaseModEssencePowders.essence_powder_null);
            return BaseModEssencePowders.essence_powder_null;
        }
    }

    public void setType(ItemStack stack, IEssencePowder type) {
        if (stack.hasTagCompound()) {
            stack.getTagCompound().setString("type", EssencePowderUtils.to(type));
        } else {
            NBTTagCompound tmp = new NBTTagCompound();
            tmp.setString("type", EssencePowderUtils.to(type));
            stack.setTagCompound(tmp);
        }
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        Iterator<IEssenceLiquid> iterator = EssenceLiquidRegistry.getRegistry().iterator();

        while (iterator.hasNext()) {
            IEssenceLiquid bottle = iterator.next();
            ModelBakery.registerItemVariants(this, bottle.getModel());
        }

        ModelLoader.setCustomMeshDefinition(this, new ItemMeshDefinition() {
            @Override
            public ModelResourceLocation getModelLocation(ItemStack stack) {
                return getType(stack).getModel();
            }
        });
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        if (entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entityLiving;
            player.attackEntityFrom(new DamageSourceLiquidEssence(), player.getHealth());
            return new ItemStack(Items.GLASS_BOTTLE);
        }
        return null;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.DRINK;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 32;
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        playerIn.setActiveHand(handIn);
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }

}
