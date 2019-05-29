package ext.tpz.crystalline.api.insanity.capability;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;

public class InsanityUtils {

    public static IInsanity getRawInsanity(EntityPlayer e) {
        if (e.hasCapability(InsanityCapability.CAPABILITY_INSANITY, EnumFacing.UP)) {
            return e.getCapability(InsanityCapability.CAPABILITY_INSANITY, EnumFacing.UP);
        }
        return null;
    }

    public static void setInsanity(EntityPlayer e, int i) {
        if (getRawInsanity(e) != null)
            getRawInsanity(e).setInsanity(i);
    }

    public static void subInsanity(EntityPlayer e, int i) {
        if (getRawInsanity(e) != null)
            getRawInsanity(e).subInsanity(i);
    }

    public static void addInsanity(EntityPlayer e, int i) {
        if (getRawInsanity(e) != null)
            getRawInsanity(e).addInsanity(i);
    }

    public static int getInsanity(EntityPlayer e) {
        if (getRawInsanity(e) != null)
            return getRawInsanity(e).getInsanity();
        return -1;
    }

}
