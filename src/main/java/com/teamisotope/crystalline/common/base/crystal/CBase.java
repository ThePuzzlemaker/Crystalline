package com.teamisotope.crystalline.common.base.crystal;

import com.teamisotope.crystalline.api.crystal.ICrystal;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class CBase implements ICrystal {

    @Override
    public String getUnlocalizedName() {
        return "crystalline.crystal.base";
    }

    @Override
    public ModelResourceLocation getModel() {
        return new ModelResourceLocation("crystalline:crystal.drained", "inventory");
    }

    @Override
    public boolean causesInsanity() {
        return false;
    }

    @Override
    public boolean hasBinding() {
        return false;
    }

    @Override
    public boolean canDrain() {
        return false;
    }

    @Nullable
    @Override
    public ResourceLocation getRegistryName() {
        return new ResourceLocation("crystalline", "crystal.base");
    }

    @Override
    public Class<ICrystal> getRegistryType() {
        return ICrystal.class;
    }

    @Override
    public void onHold(ItemStack stack, World world, EntityPlayer player, EnumHand hand) { }
}
