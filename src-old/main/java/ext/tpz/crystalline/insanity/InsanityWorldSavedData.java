package ext.tpz.crystalline.insanity;

import ext.tpz.crystalline.util.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;

import java.util.UUID;

public class InsanityWorldSavedData extends WorldSavedData {

    private static final String DATA_NAME = Reference.MODID + "_insanity";
    private static NBTTagCompound players = new NBTTagCompound();

    public InsanityWorldSavedData() {
        super(DATA_NAME);

    }

    public InsanityWorldSavedData(String s) {
        super(s);
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setTag("players", players);
        return compound;
    }

    public void readFromNBT(NBTTagCompound compound) {
        players = compound.getCompoundTag("players");
    }

    public void setPlayer(UUID uuid, int insanity) {
        NBTTagCompound tmp = new NBTTagCompound();
        tmp.setInteger("insanity", insanity);
        players.setTag(uuid.toString(), tmp);
        markDirty();
    }

    public int getPlayer(UUID uuid) {
        if (players.hasKey(uuid.toString())) {
            NBTTagCompound tmp = players.getCompoundTag(uuid.toString());
            if (tmp.hasKey("insanity")) {
                return tmp.getInteger("insanity");
            } else {
                tmp.setInteger("insanity", 0);
                markDirty();
                return 0;
            }
        } else {
            setPlayer(uuid, 0);
            markDirty();
            return 0;
        }
    }

    public static InsanityWorldSavedData get(World world) {
        MapStorage storage = world.getPerWorldStorage();
        InsanityWorldSavedData instance = (InsanityWorldSavedData) storage.getOrLoadData(InsanityWorldSavedData.class, DATA_NAME);

        if (instance == null) {
            instance = new InsanityWorldSavedData();
            storage.setData(DATA_NAME, instance);
        }
        return instance;
    }

    public static void set(InsanityWorldSavedData data, World world) {
        MapStorage storage = world.getPerWorldStorage();
        if (data != null) {
            storage.setData(DATA_NAME, data);
        } else {
            storage.setData(DATA_NAME, new InsanityWorldSavedData());
        }
    }

}
