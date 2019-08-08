package com.teamisotope.crystalline.api.insanity.capability;

import net.minecraft.entity.player.EntityPlayer;

public class ClientInsanityHelper {

    private int insanity;
    private EntityPlayer player;
    private static ClientInsanityHelper INSTANCE;

    private ClientInsanityHelper(EntityPlayer player) {
        this.player = player;
    }

    public static void init(EntityPlayer player) {
        if (INSTANCE == null) {
            INSTANCE = new ClientInsanityHelper(player);
        }
    }

    public static ClientInsanityHelper getInstance() {
        return INSTANCE;
    }

    public void setInsanity(int i) {
        this.insanity = i;
    }

    public int getInsanity() {
        return insanity;
    }

}
