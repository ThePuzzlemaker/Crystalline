package ext.tpz.crystalline.common.capabilities;

import ext.tpz.crystalline.api.insanity.capability.IInsanity;
import ext.tpz.crystalline.api.insanity.capability.InsanityCapability;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CCapabilities {

    public static void register() {
        CapabilityManager.INSTANCE.register(IInsanity.class, new InsanityCapability.Storage(), new InsanityCapability.Factory());
    }



}
