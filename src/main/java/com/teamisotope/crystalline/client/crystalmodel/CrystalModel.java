package com.teamisotope.crystalline.client.crystalmodel;

import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.model.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.IModelConfiguration;
import net.minecraftforge.client.model.geometry.IModelGeometry;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.client.model.obj.OBJModel;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Function;

public class CrystalModel implements IModelGeometry<CrystalModel> {

    final String key;
    final Map<String, BlockModel> modelMap;

    public CrystalModel(String key, Map<String, BlockModel> modelMap)
    {
        this.key = key;
        this.modelMap = modelMap;
    }

    @Override
    public IBakedModel bake(IModelConfiguration owner, ModelBakery bakery, Function<Material, TextureAtlasSprite> spriteGetter, IModelTransform modelTransform, ItemOverrideList overrides, ResourceLocation modelLocation)
    {
        return new BakedModel(
                spriteGetter.apply(owner.resolveTexture("particle")),
                owner.isSideLit(), bakery, owner.getOwnerModel(), bakery::getUnbakedModel, spriteGetter, key,
                Maps.transformEntries(modelMap, (k, v) -> {
                    return v.bakeModel(bakery, v, spriteGetter, modelTransform, new ResourceLocation(modelLocation.getNamespace(), "crystals." + k.replaceAll(":", ".")), owner.isSideLit());
                })
        );
    }

    @Override
    public Collection<Material> getTextures(IModelConfiguration owner, Function<ResourceLocation, IUnbakedModel> modelGetter, Set<Pair<String, String>> missingTextureErrors)
    {
        Set<Material> materials = new HashSet<>();
        for(BlockModel model : modelMap.values())
            materials.addAll(model.getTextures(modelGetter, missingTextureErrors));
        return materials;
    }

    public static class BakedModel implements IBakedModel
    {
        private final boolean isSideLit;
        private final TextureAtlasSprite particle;
        private final ItemOverrideList overrides;

        public BakedModel(TextureAtlasSprite particle, boolean isSideLit, ModelBakery bakery, IUnbakedModel ownerModel, Function<ResourceLocation, IUnbakedModel> modelGetter, Function<Material, TextureAtlasSprite> textureGetter,
                          String key, Map<String, IBakedModel> modelMap)
        {
            this.isSideLit = isSideLit;
            this.particle = particle;
            this.overrides = new ItemOverrideList(bakery, ownerModel, modelGetter, textureGetter, Collections.emptyList())
            {
                final String nbtKey = key;
                final Map<String, IBakedModel> models = modelMap;

                @Nullable
                @Override
                public IBakedModel getModelWithOverrides(IBakedModel model, ItemStack stack, @Nullable World worldIn, @Nullable LivingEntity entityIn)
                {
                    CompoundNBT tag = stack.getTag();
                    INBT tagValue = (tag != null) ? tag.get(nbtKey) : null;

                    String value = tagValue != null ? tagValue.getString() : null;
                    if (value == null)
                    {
                        value = "";
                    }
                    return models.getOrDefault(value, models.getOrDefault("", model));
                }
            };
        }

        @Override
        public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, Random rand)
        {
            return Collections.emptyList();
        }

        @Override
        public boolean isAmbientOcclusion()
        {
            return true;
        }

        @Override
        public boolean isGui3d()
        {
            return true;
        }

        @Override
        public boolean func_230044_c_()
        {
            return isSideLit;
        }

        @Override
        public boolean isBuiltInRenderer()
        {
            return false;
        }

        @Override
        public TextureAtlasSprite getParticleTexture()
        {
            return particle;
        }

        @Override
        public ItemOverrideList getOverrides()
        {
            return overrides;
        }
    }


}
