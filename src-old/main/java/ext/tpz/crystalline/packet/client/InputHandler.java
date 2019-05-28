package ext.tpz.crystalline.packet.client;

import ext.tpz.crystalline.packet.common.PacketHandler;
import ext.tpz.crystalline.packet.common.PacketSendKey;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class InputHandler {

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent e) {
        if (KeyBindings.changeCrystalOperation.isPressed()) {
            PacketHandler.INSTANCE.sendToServer(new PacketSendKey());
        }
    }

}
