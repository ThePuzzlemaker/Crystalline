package com.teamisotope.crystalline.api.mode;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nullable;

public interface IMode extends IForgeRegistryEntry<IMode> {

    @Override
    default Class<IMode> getRegistryType() {
        return IMode.class;
    }

    @Nullable
    @Override
    ResourceLocation getRegistryName();

    @Override
    IMode setRegistryName(ResourceLocation name);

    String getTranslationKey();

    ResourceLocation[] getValidCrystalIDs();

    void handleHold(ItemStack stack, World world, Entity entity, Hand heldIn);



}
