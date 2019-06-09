package ext.tpz.crystalline.api.util;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public interface SerializableMetadata {

    NBTTagCompound serialize();

    SerializableMetadata deserialize(NBTTagCompound nbt);

}
