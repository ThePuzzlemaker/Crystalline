package ext.tpz.crystalline.common.item.dyn;

import ext.tpz.crystalline.api.CStatic;
import ext.tpz.crystalline.api.crystal.CrystalMetadata;
import ext.tpz.crystalline.api.crystal.CrystalRegistry;
import ext.tpz.crystalline.api.crystal.ICrystal;
import ext.tpz.crystalline.api.resonance.Resonance;
import ext.tpz.crystalline.api.resonance.WorldResonance;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;

public class ItemCrystal extends Item {

    public ItemCrystal() {
        this.setRegistryName(new ResourceLocation(CStatic.MODID, "crystal")).setUnlocalizedName(CStatic.MODID + ".crystal").setMaxStackSize(1);
    }

    public static CrystalMetadata get(ItemStack stack) {
        if (stack.hasTagCompound()) {
            if (stack.getTagCompound().hasKey("data")) {
                return new CrystalMetadata().deserialize(stack.getTagCompound().getCompoundTag("data"));
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, world, tooltip, flagIn);
        CrystalMetadata data = get(stack);
        tooltip.add(I18n.format("crystalline.lore.crystal.lore.one"));
        tooltip.add(I18n.format("crystalline.lore.crystal.lore.two"));
        if (data != null) {
            if (data.getCrystal() != null) {
                tooltip.add("");
                tooltip.add(I18n.format("crystalline.lore.crystal.type", I18n.format(data.getCrystal().getUnlocalizedName())));
                tooltip.add("");
                if (data.getCrystal().hasBinding())
                    if (data.getBound() != null) {
                        EntityPlayer p = world.getPlayerEntityByUUID(data.getBound());
                        if (p != null)
                            tooltip.add(I18n.format("crystalline.lore.crystal.bound", p.getDisplayNameString()));
                        else
                            tooltip.add(I18n.format("crystalline.lore.crystal.bound", I18n.format("crystalline.lore.crystal.bound.invalid")));
                    } else
                        tooltip.add(I18n.format("crystalline.lore.crystal.unbound"));
                tooltip.add("");
                if (data.getCrystal().causesInsanity())
                    tooltip.add(I18n.format("crystalline.lore.crystal.insanity.true"));
                else
                    tooltip.add(I18n.format("crystalline.lore.crystal.insanity.false"));
                tooltip.add("");
                if (data.getPotential() == 0)
                    tooltip.add(I18n.format("crystalline.lore.crystal.drained.true"));
                else
                    tooltip.add(I18n.format("crystalline.lore.crystal.drained.false"));

                if (data.getPotential() >= 0 && data.getPotential() < 25) {
                    tooltip.add(I18n.format("crystalline.lore.crystal.potential", I18n.format("crystalline.lore.crystal.potential.firstquartile", data.getPotential())));
                } else if (data.getPotential() >= 25 && data.getPotential() < 50) {
                    tooltip.add(I18n.format("crystalline.lore.crystal.potential", I18n.format("crystalline.lore.crystal.potential.secondquartile", data.getPotential())));
                } else if (data.getPotential() >= 50 && data.getPotential() < 75) {
                    tooltip.add(I18n.format("crystalline.lore.crystal.potential", I18n.format("crystalline.lore.crystal.potential.thirdquartile", data.getPotential())));
                } else if (data.getPotential() >= 75 && data.getPotential() <= 100) {
                    tooltip.add(I18n.format("crystalline.lore.crystal.potential", I18n.format("crystalline.lore.crystal.potential.fourthquartile", data.getPotential())));
                }
                tooltip.add("");
                if (getDifference(stack) != -1)
                    tooltip.add(I18n.format("crystalline.lore.crystal.efficiency", Math.round(calculateEfficiency(getDifference(stack)) * 100.0) / 100.0));
                else
                    tooltip.add(I18n.format("crystalline.lore.crystal.efficiency.unknown"));
                tooltip.add("");
                // TODO: Reagent tooltip
            }
        }
    }

    public static double calculateEfficiency(double difference) {
        return (-Math.pow(difference*100, 1.5) + 1000)/1000;
    }

    public static double getDifference(ItemStack stack) {
        CrystalMetadata m = get(stack);
        if (m != null && m.getCrystal() != null) {
            Resonance r = WorldResonance.INSTANCE.getResonance(m.getCrystal());
            if (r != null) {
                return ((double)m.getFrequency()) / ((double)r.getExact());
            }
        }
        return -1;
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelResourceLocation drained = new ModelResourceLocation("crystalline:crystal.drained", "inventory");

        Iterator<ICrystal> iterator = CrystalRegistry.getRegistry().iterator();

        while (iterator.hasNext()) {
            ICrystal crystal = iterator.next();
            ModelBakery.registerItemVariants(this, crystal.getModel());
        }

        ModelBakery.registerItemVariants(this, drained);

        ModelLoader.setCustomMeshDefinition(this, new ItemMeshDefinition() {
            @Override
            public ModelResourceLocation getModelLocation(ItemStack stack) {
                CrystalMetadata m = get(stack);
                if (m != null) {
                    if (m.getPotential() == 0) {
                        return drained;
                    } else if (m.getCrystal() != null) {
                        ICrystal crystal = m.getCrystal();
                        return crystal.getModel();
                    } else {
                        return drained;
                    }
                } else {
                    return drained;
                }
            }
        });
    }


}
