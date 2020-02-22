package com.teamisotope.crystalline.api.mode;

import net.minecraft.util.ResourceLocation;
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

    void handleHold();

}
