package ext.tpz.crystalline.packet.client;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public class KeyBindings {

    public static KeyBinding changeCrystalOperation;

    public static void init() {
        changeCrystalOperation = new KeyBinding("key.change_crystal_operation", Keyboard.KEY_O, "key.categories.crystalline");
        ClientRegistry.registerKeyBinding(changeCrystalOperation);
    }

}
