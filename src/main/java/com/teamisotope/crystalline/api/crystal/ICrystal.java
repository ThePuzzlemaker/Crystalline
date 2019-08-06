package com.teamisotope.crystalline.api.crystal;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry;

public interface ICrystal extends IForgeRegistryEntry<ICrystal> {

    String getUnlocalizedName();

    ModelResourceLocation getModel();

    boolean causesInsanity();

    boolean hasBinding();

    boolean canDrain();

    // TODO: Move this method to modes once they're done
    void onHold(ItemStack stack, World world, EntityPlayer player, EnumHand hand);

    @Override
    default ICrystal setRegistryName(ResourceLocation name) {
        return this;
    }

    default String serialize() {
        return this.getRegistryName() == null ? this.getRegistryName().toString() : "crystalline:crystal.base";
    }

}
