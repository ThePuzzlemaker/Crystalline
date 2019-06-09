package ext.tpz.crystalline.common.item.dyn;

import ext.tpz.crystalline.api.CStatic;
import ext.tpz.crystalline.api.crystal.CrystalMetadata;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemCrystal extends Item {

    public ItemCrystal() {
        this.setRegistryName(new ResourceLocation(CStatic.MODID, "crystal")).setUnlocalizedName(CStatic.MODID + ".crystal").setMaxStackSize(1);
    }

    public CrystalMetadata get(ItemStack stack) {
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
        if (data != null) {
            if (data.getCrystal() != null) {
                tooltip.add(I18n.format("crystalline.lore.crystal.type", I18n.format(data.getCrystal().getUnlocalizedName())));

                if (data.getCrystal().hasBinding())
                    if (data.getBound() != null) {
                        EntityPlayer p = world.getPlayerEntityByUUID(data.getBound());
                        if (p != null)
                            tooltip.add(I18n.format("crystalline.lore.crystal.bound", p.getDisplayNameString()));
                        else
                            tooltip.add(I18n.format("crystalline.lore.crystal.bound", I18n.format("crystalline.lore.crystal.bound.invalid")));
                    } else
                        tooltip.add(I18n.format("crystalline.lore.crystal.unbound"));

                if (data.getCrystal().causesInsanity())
                    tooltip.add(I18n.format("crystalline.lore.crystal.insanity.true"));
                else
                    tooltip.add(I18n.format("crystalline.lore.crystal.insanity.false"));

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
                // TODO: Reagent tooltip
            }
        }
    }
}
