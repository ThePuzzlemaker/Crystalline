package com.teamisotope.crystalline.common.advancement;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.teamisotope.crystalline.common.item.CItems;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.advancements.critereon.NBTPredicate;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.advancements.critereon.ItemPredicates;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.function.Function;

public class CrystalPredicate extends ItemPredicate {

    public static final CrystalPredicate ANY = new CrystalPredicate();
    private final String crystalId;

    public CrystalPredicate()
    {
        this.crystalId = "";
    }

    public CrystalPredicate(@Nullable String crystalId)
    {
        if (crystalId == null) {
            this.crystalId = "";
        } else {
            this.crystalId = crystalId;
        }
    }

    public boolean test(ItemStack item)
    {
        if (CItems.crystal != null && item.getItem() != CItems.crystal)
        {
            return false;
        }
        if (!similarTag(item, crystalId))
        {
            return false;
        }
        return true;
    }

    private boolean similarTag(ItemStack item, String crystalId) {
        if (item.hasTagCompound()) {
            NBTTagCompound nbt = item.getTagCompound();
            if (nbt.hasKey("data")) {
                if (nbt.getTag("data") instanceof NBTTagCompound) {
                    NBTTagCompound data = nbt.getCompoundTag("data");
                    if (data.hasKey("id")) {
                        String id = data.getString("id");
                        if (crystalId.equals("")) {
                            return true;
                        }
                        return id.equalsIgnoreCase(crystalId);
                    }
                }
            }
        }
        return false;
    }

    public static ItemPredicate deserialize(@Nullable JsonElement element)
    {
        if (element != null && !element.isJsonNull())
        {
            JsonObject jsonobject = JsonUtils.getJsonObject(element, "item");
            if (jsonobject.has("type"))
            {
                final ResourceLocation rl = new ResourceLocation(JsonUtils.getString(jsonobject, "type"));
                final Map<ResourceLocation, java.util.function.Function<JsonObject, ItemPredicate>> map = net.minecraftforge.advancements.critereon.ItemPredicates.getPredicates();
                if (map.containsKey(rl)) return map.get(rl).apply(jsonobject);
                else throw new JsonSyntaxException("There is no ItemPredicate of type "+rl);
            }
            MinMaxBounds minmaxbounds = MinMaxBounds.deserialize(jsonobject.get("count"));
            MinMaxBounds minmaxbounds1 = MinMaxBounds.deserialize(jsonobject.get("durability"));
            Integer integer = jsonobject.has("data") ? JsonUtils.getInt(jsonobject, "data") : null;
            NBTPredicate nbtpredicate = NBTPredicate.deserialize(jsonobject.get("nbt"));
            Item item = null;

            if (jsonobject.has("item"))
            {
                ResourceLocation resourcelocation = new ResourceLocation(JsonUtils.getString(jsonobject, "item"));
                item = Item.REGISTRY.getObject(resourcelocation);

                if (item == null)
                {
                    throw new JsonSyntaxException("Unknown item id '" + resourcelocation + "'");
                }
            }

            EnchantmentPredicate[] aenchantmentpredicate = EnchantmentPredicate.deserializeArray(jsonobject.get("enchantments"));
            PotionType potiontype = null;

            if (jsonobject.has("potion"))
            {
                ResourceLocation resourcelocation1 = new ResourceLocation(JsonUtils.getString(jsonobject, "potion"));

                if (!PotionType.REGISTRY.containsKey(resourcelocation1))
                {
                    throw new JsonSyntaxException("Unknown potion '" + resourcelocation1 + "'");
                }

                potiontype = PotionType.REGISTRY.getObject(resourcelocation1);
            }

            return new ItemPredicate(item, integer, minmaxbounds, minmaxbounds1, aenchantmentpredicate, potiontype, nbtpredicate);
        }
        else
        {
            return ANY;
        }
    }

    public static ItemPredicate[] deserializeArray(@Nullable JsonElement element)
    {
        if (element != null && !element.isJsonNull())
        {
            JsonArray jsonarray = JsonUtils.getJsonArray(element, "items");
            ItemPredicate[] aitempredicate = new ItemPredicate[jsonarray.size()];

            for (int i = 0; i < aitempredicate.length; ++i)
            {
                aitempredicate[i] = deserialize(jsonarray.get(i));
            }

            return aitempredicate;
        }
        else
        {
            return new ItemPredicate[0];
        }
    }

    public static void register() {
        ItemPredicates.register(new ResourceLocation("crystalline", "crystal"), new Function<JsonObject, ItemPredicate>() {
            @Override
            public ItemPredicate apply(JsonObject jsonObject) {
                return new CrystalPredicate(jsonObject.get("crystal").getAsString());
            }
        });
    }

}
