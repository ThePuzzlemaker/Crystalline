package com.teamisotope.crystalline.common.base.crystal;

import com.teamisotope.crystalline.api.crystal.ICrystal;
import com.teamisotope.crystalline.api.insanity.capability.IInsanity;
import com.teamisotope.crystalline.api.insanity.capability.InsanityUtils;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.text.DecimalFormat;

public class CKnowledge implements ICrystal {

    @Override
    public String getUnlocalizedName() {
        return "crystalline.crystal.knowledge";
    }

    @Override
    public ModelResourceLocation getModel() {
        return new ModelResourceLocation("crystalline:crystal.knowledge", "inventory");
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
        return new ResourceLocation("crystalline", "crystal.knowledge");
    }

    @Override
    public void onHold(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {

        if (!world.isRemote) {
            int insanity = InsanityUtils.getInsanity(player);
            if (insanity != -1) {
                if (insanity == 101)
                    player.sendStatusMessage(new TextComponentString(I18n.format("crystalline.crystal.knowledge.msg", I18n.format("crystalline.crystal.knowledge.permanent"))), true);
                else
                    player.sendStatusMessage(new TextComponentString(I18n.format("crystalline.crystal.knowledge.msg", "§r§4" + new DecimalFormat("###").format(insanity) + "%")), true);
            } else {
                player.sendStatusMessage(new TextComponentString(I18n.format("crystalline.crystal.knowledge.msg", I18n.format("crystalline.crystal.knowledge.error"))), true);
            }
        }

    }
}
