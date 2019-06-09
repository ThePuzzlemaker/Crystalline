package ext.tpz.crystalline.api.util;

import net.minecraft.nbt.NBTTagCompound;

public interface ISerializableMetadata {

    NBTTagCompound serialize();

    ISerializableMetadata deserialize(NBTTagCompound nbt);

}
