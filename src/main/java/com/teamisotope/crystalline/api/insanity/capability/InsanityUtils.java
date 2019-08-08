package com.teamisotope.crystalline.api.insanity.capability;

import com.teamisotope.crystalline.common.Crystalline;
import com.teamisotope.crystalline.common.network.PacketHandler;
import com.teamisotope.crystalline.common.network.PacketInsanityChanged;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class InsanityUtils {

    // WARNING WARNING WARNING: DO NOT USE THIS, PLEASE USE InsanityUtils.addInsanity, setInsanity, and subInsanity
    // DOES NOTHING ON CLIENT
    public static IInsanity getRawInsanity(EntityPlayer e) {
        if (e.hasCapability(InsanityCapability.CAPABILITY_INSANITY, EnumFacing.UP)) {
            return e.getCapability(InsanityCapability.CAPABILITY_INSANITY, EnumFacing.UP);
        }
        return null;
    }

    // DOES NOTHING ON CLIENT
    public static void setInsanity(EntityPlayer e, int i) {
        if (!e.getEntityWorld().isRemote) {
            if (getRawInsanity(e) != null) {
                getRawInsanity(e).setInsanity(i);
                PacketHandler.INSTANCE.sendTo(new PacketInsanityChanged(getInsanity(e)), (EntityPlayerMP) e);
            }
        }
    }

    // DOES NOTHING ON CLIENT
    public static void subInsanity(EntityPlayer e, int i) {
        if (!e.getEntityWorld().isRemote) {
            if (getRawInsanity(e) != null) {
                getRawInsanity(e).subInsanity(i);
                PacketHandler.INSTANCE.sendTo(new PacketInsanityChanged(getInsanity(e)), (EntityPlayerMP) e);
            }
        }
    }

    // DOES NOTHING ON CLIENT
    public static void addInsanity(EntityPlayer e, int i) {
        if (!e.getEntityWorld().isRemote) {
            if (getRawInsanity(e) != null) {
                getRawInsanity(e).addInsanity(i);
                PacketHandler.INSTANCE.sendTo(new PacketInsanityChanged(getInsanity(e)), (EntityPlayerMP) e);
            }
        }
    }

    // DOES NOTHING ON CLIENT
    public static int getInsanity(EntityPlayer e) {
        if (getRawInsanity(e) != null)
            return getRawInsanity(e).getInsanity();
        return -1;
    }

}
