package ext.tpz.crystalline.common.capabilities;

import ext.tpz.crystalline.api.insanity.IInsanity;
import ext.tpz.crystalline.api.insanity.InsanityCapability;
import ext.tpz.crystalline.common.Crystalline;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CCapabilities {

    public static void register() {
        CapabilityManager.INSTANCE.register(IInsanity.class, new InsanityCapability.Storage(), new InsanityCapability.Factory());
    }



}
