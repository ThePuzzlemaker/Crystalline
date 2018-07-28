package ext.tpz.crystalline.util;

import ext.tpz.crystalline.insanity.InsanityWorldSavedData;
import ext.tpz.crystalline.item.CrystallineItems;
import ext.tpz.crystalline.item.EnumCrystalTypes;
import ext.tpz.crystalline.item.EnumReagentTypes;
import ext.tpz.crystalline.util.config.Config;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

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

    private static HashMap<UUID, Integer> ticks = new HashMap<>();

    @SubscribeEvent
    public void livingUpdate(LivingEvent.LivingUpdateEvent event) {
        if (event.getEntityLiving() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)event.getEntityLiving();
            World world = player.getEntityWorld();
            UUID uuid = player.getUniqueID();
            InsanityWorldSavedData data = InsanityWorldSavedData.get(world);
            int insanity = data.getPlayer(uuid);
            int tick = 0;
            if (ticks.containsKey(uuid)) {
                tick = ticks.get(uuid);
            }
            if (insanity >= 10 && insanity <= 29) {
                if (tick == Config.mpeInsanityStage1) {
                    ticks.put(uuid, 0);
                    player.addPotionEffect(new PotionEffect(MobEffects.LEVITATION, 200));
                    player.sendStatusMessage(new TextComponentString(TextFormatting.DARK_GRAY + "You suddenly felt yourself levitating."), false);
                } else {
                    tick++;
                    ticks.put(uuid, tick);
                }
            } else if (insanity >= 30 && insanity <= 49) {
                if (tick == Config.mpeInsanityStage2) {
                    ticks.put(uuid, 0);
                    ticks.put(uuid, 0);
                    player.addPotionEffect(new PotionEffect(MobEffects.LEVITATION, 200));
                    player.sendStatusMessage(new TextComponentString(TextFormatting.DARK_GRAY + "You suddenly felt yourself levitating."), false);
                } else {
                    tick++;
                    ticks.put(uuid, tick);
                }
            } else if (insanity >= 50 && insanity <= 69) {
                if (tick == Config.mpeInsanityStage3) {
                    ticks.put(uuid, 0);
                    if (new Random().nextBoolean()) {
                        player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 200));
                        player.sendStatusMessage(new TextComponentString(TextFormatting.DARK_GRAY + "You suddenly slowed down, and felt a weird ooze at your feet that didn't even exist."), false);
                    } else {
                        player.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 200));
                        player.sendStatusMessage(new TextComponentString(TextFormatting.DARK_GRAY + "You suddenly lost your vision."), false);
                    }
                } else {
                    tick++;
                    ticks.put(uuid, tick);
                }
            } else if (insanity >= 70 && insanity <= 89) {
                if (tick == Config.mpeInsanityStage4) {
                    ticks.put(uuid, 0);
                    player.addPotionEffect(new PotionEffect(MobEffects.POISON, 200));
                    player.sendStatusMessage(new TextComponentString(TextFormatting.DARK_GRAY + "Your own thoughts have somehow poisoned yourself. You find a mysterious purple powder on the ground."), false);
                    BlockPos pos = player.getPosition();
                    ItemStack stack = new ItemStack(CrystallineItems.pure_rift_essence, 8);
                    if (!player.inventory.addItemStackToInventory(stack)) {
                        EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack);
                        world.spawnEntity(item);
                    } else {
                        player.openContainer.detectAndSendChanges();
                    }
                } else {
                    tick++;
                    ticks.put(uuid, tick);
                }
            } else if (insanity >= 90 && insanity <= 99) {
                if (tick == Config.mpeInsanityStage5) {
                    ticks.put(uuid, 0);
                    player.addPotionEffect(new PotionEffect(MobEffects.POISON, 200));
                    player.sendStatusMessage(new TextComponentString(TextFormatting.DARK_GRAY + "Your own thoughts have somehow poisoned yourself. You find a mysterious purple powder on the ground."), false);
                    BlockPos pos = player.getPosition();
                    ItemStack stack = new ItemStack(CrystallineItems.pure_rift_essence, 8);
                    if (!player.inventory.addItemStackToInventory(stack)) {
                        EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack);
                        world.spawnEntity(item);
                    } else {
                        player.openContainer.detectAndSendChanges();
                    }
                } else {
                    tick++;
                    ticks.put(uuid, tick);
                }
            } else if (insanity == 100) {
                if (tick == Config.mpeInsanityStage6) {
                    ticks.put(uuid, 0);
                    player.addPotionEffect(new PotionEffect(MobEffects.POISON, 200));
                    player.sendStatusMessage(new TextComponentString(TextFormatting.DARK_GRAY + "Your own thoughts have somehow poisoned yourself. You find a mysterious purple powder on the ground."), false);
                    BlockPos pos = player.getPosition();
                    ItemStack stack = new ItemStack(CrystallineItems.pure_rift_essence, 8);
                    if (!player.inventory.addItemStackToInventory(stack)) {
                        EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack);
                        world.spawnEntity(item);
                    } else {
                        player.openContainer.detectAndSendChanges();
                    }
                } else {
                    tick++;
                    ticks.put(uuid, tick);
                }
            } else if (insanity == 101 /* permanent */) {
                if (tick == Config.mpeInsanityPerm) {
                    ticks.put(uuid, 0);
                    player.addPotionEffect(new PotionEffect(MobEffects.POISON, 200));
                    player.sendStatusMessage(new TextComponentString(TextFormatting.DARK_GRAY + "Your own thoughts have somehow poisoned yourself. You find a mysterious purple powder on the ground."), false);
                    BlockPos pos = player.getPosition();
                    ItemStack stack = new ItemStack(CrystallineItems.pure_rift_essence, 8);
                    if (!player.inventory.addItemStackToInventory(stack)) {
                        EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack);
                        world.spawnEntity(item);
                    } else {
                        player.openContainer.detectAndSendChanges();
                    }
                } else {
                    tick++;
                    ticks.put(uuid, tick);
                }
            }
        }
    }
}
