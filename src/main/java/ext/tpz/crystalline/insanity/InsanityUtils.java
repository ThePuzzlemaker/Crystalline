package ext.tpz.crystalline.insanity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

public class InsanityUtils {

    public static void addInsanity(World world, EntityPlayer player, int amount) {
        int insanity = getInsanity(world, player);
        if (insanity + amount <= 100) {
            setInsanity(world, player, insanity + amount);
        } else {
            setInsanity(world, player, 100);
        }
    }

    public static void setInsanity(World world, EntityPlayer player, int amount) {
        InsanityWorldSavedData data = InsanityWorldSavedData.get(world);
        int insanity = data.getPlayer(player.getUniqueID());
        data.setPlayer(player.getUniqueID(), amount);
        InsanityWorldSavedData.set(data, world);
    }

    public static int getInsanity(World world, EntityPlayer player) {
        return InsanityWorldSavedData.get(world).getPlayer(player.getUniqueID());
    }

    public static EnumInsanityStages insanityToStage(int insanity) {
        for (EnumInsanityStages s : EnumInsanityStages.values()) {
            if (insanity >= s.minInsanity && insanity <= s.maxInsanity) {
                return s;
            }
        }
        return EnumInsanityStages.STAGE_0;
    }

    public static String genBar(int amount, boolean isPerm) {
        if (isPerm) {
            return "[" + StringUtils.repeat("#", amount) + "]";
        } else {
            return "[" + StringUtils.repeat("*", amount) + StringUtils.repeat("-", 7-amount) + "]";
        }
    }

}
