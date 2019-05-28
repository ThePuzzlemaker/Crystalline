package ext.tpz.crystalline.util;

import ext.tpz.crystalline.crystals.BaseModCrystals;
import ext.tpz.crystalline.essences.powder.BaseModEssencePowders;
import ext.tpz.crystalline.insanity.InsanityWorldSavedData;
import ext.tpz.crystalline.item.CrystallineItems;
import ext.tpz.crystalline.potion.PotionDirtshield;
import ext.tpz.crystalline.potion.Potions;
import ext.tpz.crystalline.reagents.BaseModReagents;
import ext.tpz.crystalline.util.config.Config;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
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
                CrystallineItems.crystal.setReagent(stack, BaseModReagents.reagent_advanced);
                CrystallineItems.crystal.setType(stack, BaseModCrystals.life_crystal);

                EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack);
                world.spawnEntity(item);
            }
        }
    }

    private void levitation(EntityPlayer player) {
        player.addPotionEffect(new PotionEffect(MobEffects.LEVITATION, 200));
        player.sendStatusMessage(new TextComponentString(TextFormatting.DARK_GRAY + "You suddenly felt yourself levitating."), false);
    }

    private void dual(EntityPlayer player) {
        if (new Random().nextBoolean()) {
            player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 200));
            player.sendStatusMessage(new TextComponentString(TextFormatting.DARK_GRAY + "You suddenly slowed down, and felt a weird ooze at your feet that didn't even exist."), false);
        } else {
            player.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 200));
            player.sendStatusMessage(new TextComponentString(TextFormatting.DARK_GRAY + "You suddenly lost your vision."), false);
        }
    }

    private void poison(EntityPlayer player) {
        player.addPotionEffect(new PotionEffect(MobEffects.POISON, 200, 2));
        player.sendStatusMessage(new TextComponentString(TextFormatting.DARK_GRAY + "Your own thoughts have somehow poisoned yourself. You find a mysterious purple powder on the ground."), false);
        BlockPos pos = player.getPosition();
        ItemStack stack = new ItemStack(CrystallineItems.essence_powder, 8);
        CrystallineItems.essence_powder.setType(stack, BaseModEssencePowders.essence_powder_rift);
        if (!player.inventory.addItemStackToInventory(stack)) {
            EntityItem item = new EntityItem(player.getEntityWorld(), pos.getX(), pos.getY(), pos.getZ(), stack);
            player.getEntityWorld().spawnEntity(item);
        } else {
            player.openContainer.detectAndSendChanges();
            player.inventoryContainer.detectAndSendChanges();
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
                    levitation(player);
                } else {
                    tick++;
                    ticks.put(uuid, tick);
                }
            } else if (insanity >= 30 && insanity <= 49) {
                if (tick == Config.mpeInsanityStage2) {
                    ticks.put(uuid, 0);
                    levitation(player);
                } else {
                    tick++;
                    ticks.put(uuid, tick);
                }
            } else if (insanity >= 50 && insanity <= 69) {
                if (tick == Config.mpeInsanityStage3) {
                    ticks.put(uuid, 0);
                    dual(player);
                } else {
                    tick++;
                    ticks.put(uuid, tick);
                }
            } else if (insanity >= 70 && insanity <= 89) {
                if (tick == Config.mpeInsanityStage4) {
                    ticks.put(uuid, 0);
                    poison(player);
                } else {
                    tick++;
                    ticks.put(uuid, tick);
                }
            } else if (insanity >= 90 && insanity <= 99) {
                if (tick == Config.mpeInsanityStage5) {
                    ticks.put(uuid, 0);
                    poison(player);
                } else {
                    tick++;
                    ticks.put(uuid, tick);
                }
            } else if (insanity == 100) {
                if (tick == Config.mpeInsanityStage6) {
                    ticks.put(uuid, 0);
                    poison(player);
                } else {
                    tick++;
                    ticks.put(uuid, tick);
                }
            } else if (insanity == 101 /* permanent */) {
                if (tick == Config.mpeInsanityPerm) {
                    ticks.put(uuid, 0);
                    poison(player);
                } else {
                    tick++;
                    ticks.put(uuid, tick);
                }
            }
        }
    }

    @SubscribeEvent
    public void onPlayerHit(LivingHurtEvent e) {
        if (e.getEntityLiving().isPotionActive(Potions.potionDirtshield)) {
            e.setAmount(0.25f * e.getAmount());
        }
    }

}
