package ext.tpz.crystalline.recipe;

import ext.tpz.crystalline.item.CrystallineItems;
import ext.tpz.crystalline.item.EnumCrystalTypes;
import ext.tpz.crystalline.item.EnumReagentTypes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

public class EventHandlers {

    @SubscribeEvent
    public void onPlayerDeath(LivingDeathEvent e) {
        Random random = new Random();
        if (e.getEntity() instanceof EntityPlayer) {
            if (random.nextBoolean() && random.nextBoolean()) {
                EntityPlayer player = (EntityPlayer) e.getEntity();
                World world = player.getEntityWorld();
                BlockPos pos = player.getPosition();

                ItemStack stack = new ItemStack(CrystallineItems.crystal);
                CrystallineItems.crystal.setBound(stack, player.getName());
                CrystallineItems.crystal.setDrained(stack, false);
                CrystallineItems.crystal.setPotential(stack, 100);
                CrystallineItems.crystal.setReagent(stack, EnumReagentTypes.ADVANCED);
                CrystallineItems.crystal.setType(stack, EnumCrystalTypes.LIFE);

                EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack);
                world.spawnEntity(item);
            }
        }
    }




}
