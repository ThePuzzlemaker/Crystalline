package com.teamisotope.crystalline.common.capabilities;

import com.teamisotope.crystalline.api.insanity.capability.IInsanity;
import com.teamisotope.crystalline.api.insanity.capability.InsanityCapability;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CCapabilities {

    public static void register() {
        CapabilityManager.INSTANCE.register(IInsanity.class, new InsanityCapability.Storage(), new InsanityCapability.Factory());
    }



}
