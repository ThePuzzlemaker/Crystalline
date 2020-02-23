package com.teamisotope.crystalline.common.items.dynamic;

import com.teamisotope.crystalline.common.Crystalline;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class CrystalItem extends Item {

    public CrystalItem() {
        super(
                new Item.Properties()
                        .maxStackSize(1)
                        .group(Crystalline.setup.crystallineMainGroup)
        );
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (!isSelected) return;
        // run handler
        if (entityIn instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity)entityIn;
            if (player.getHeldItemMainhand().isItemEqual(stack)) {
                // handle main hand
            } else if (player.getHeldItemOffhand().isItemEqual(stack)) {
                // handle offhand
            }
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
        return false;
    }


}
