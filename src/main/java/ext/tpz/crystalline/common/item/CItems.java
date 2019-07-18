package ext.tpz.crystalline.common.item;

import ext.tpz.crystalline.common.Crystalline;
import ext.tpz.crystalline.common.item.dyn.ItemCrystal;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class CItems {

    @GameRegistry.ObjectHolder("crystalline:crystal")
    public static ItemCrystal crystal;

    @GameRegistry.ObjectHolder("crystalline:arcanummineraliscrystal")
    public static Item arcanumMineralisCrystal;

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> e) {
        e.getRegistry().register(new ItemCrystal());
        e.getRegistry().register(new Item().setRegistryName("crystalline:arcanummineraliscrystal").setUnlocalizedName("crystalline.arcanummineraliscrystal").setCreativeTab(Crystalline.tab));
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerModels(ModelRegistryEvent e) {
        crystal.initModel();
        ModelLoader.setCustomModelResourceLocation(arcanumMineralisCrystal, 0, new ModelResourceLocation(arcanumMineralisCrystal.getRegistryName(), "inventory"));
    }

}
