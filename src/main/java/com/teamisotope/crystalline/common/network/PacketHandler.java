package com.teamisotope.crystalline.common.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {

    private static int packetId = 0;

    public static SimpleNetworkWrapper INSTANCE = null;

    public PacketHandler() {
    }

    public static int nextID() {
        return packetId++;
    }

    public static void registerMessages(String channelName) {
        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(channelName);
        registerMessages();
    }

    public static void registerMessages() {
        // Register messages which are sent from the client to the server here:
        INSTANCE.registerMessage(PacketTMTune.Handler.class, PacketTMTune.class, nextID(), Side.SERVER);
        INSTANCE.registerMessage(PacketTMTest.Handler.class, PacketTMTest.class, nextID(), Side.SERVER);
        INSTANCE.registerMessage(PacketTMFreqChange.Handler.class, PacketTMFreqChange.class, nextID(), Side.SERVER);
        // Messages from server to client:
        INSTANCE.registerMessage(PacketInsanityChanged.Handler.class, PacketInsanityChanged.class, nextID(), Side.CLIENT);
    }

}
