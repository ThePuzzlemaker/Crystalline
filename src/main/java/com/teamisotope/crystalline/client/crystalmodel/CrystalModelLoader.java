package com.teamisotope.crystalline.client.crystalmodel;

import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.teamisotope.crystalline.api.client.CrystalModels;
import net.minecraft.client.renderer.model.BlockModel;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelLoader;

import java.util.Map;

public class CrystalModelLoader implements IModelLoader<CrystalModel> {

    public static final CrystalModelLoader INSTANCE = new CrystalModelLoader();

    @Override
    public void onResourceManagerReload(IResourceManager resourceManager) {

    }

    @Override
    public CrystalModel read(JsonDeserializationContext deserializationContext, JsonObject modelContents) {
        JsonObject obj = JSONUtils.getJsonObject(modelContents, "crystalTypes");
        ImmutableMap.Builder<String, BlockModel> builder = ImmutableMap.builder();
        for(Map.Entry<String, JsonElement> kv : obj.entrySet())
        {
            BlockModel model = deserializationContext.deserialize(kv.getValue(), BlockModel.class);
            builder.put(kv.getKey(), model);
        }
        for (Map.Entry<ResourceLocation, BlockModel> kv : CrystalModels.getInstance().entries()) {
            builder.put(kv.getKey().toString(), kv.getValue());
        }
        return new CrystalModel("crystal", builder.build());
    }
}
