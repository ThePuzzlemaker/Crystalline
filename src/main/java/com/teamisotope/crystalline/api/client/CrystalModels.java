package com.teamisotope.crystalline.api.client;

import net.minecraft.client.renderer.model.BlockModel;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class CrystalModels {

    private Map<ResourceLocation, BlockModel> models;

    private static final CrystalModels INSTANCE = new CrystalModels();

    private CrystalModels() {
        models = new HashMap<>();
    }

    public static CrystalModels getInstance() {
        return INSTANCE;
    }

    public void register(ResourceLocation crystal, ResourceLocation mtlLocation) {
        this.models.put(crystal, BlockModel.deserialize(String.format("{\"parent\":\"crystalline:item/crystal_base\",\"loader\":\"forge:obj\",\"model\":\"crystalline:models/item/crystal.obj\",\"materialLibraryOverride\": \"%s\"}", mtlLocation.toString())));
    }

    public void register(ResourceLocation crystal, String advancedModelJson) {
        this.models.put(crystal, BlockModel.deserialize(advancedModelJson));
    }

    public Set<Map.Entry<ResourceLocation, BlockModel>> entries() {
        return this.models.entrySet();
    }

}
