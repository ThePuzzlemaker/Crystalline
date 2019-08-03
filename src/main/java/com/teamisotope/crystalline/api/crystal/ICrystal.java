package com.teamisotope.crystalline.api.crystal;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryEntry;

public interface ICrystal extends IForgeRegistryEntry<ICrystal> {

    String getUnlocalizedName();

    ModelResourceLocation getModel();

    boolean causesInsanity();

    boolean hasBinding();

    boolean canDrain();

    @Override
    default ICrystal setRegistryName(ResourceLocation name) {
        return this;
    }

    default String serialize() {
        return this.getRegistryName() == null ? this.getRegistryName().toString() : null;
    }

}
