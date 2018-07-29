package ext.tpz.crystalline.insanity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

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

}
