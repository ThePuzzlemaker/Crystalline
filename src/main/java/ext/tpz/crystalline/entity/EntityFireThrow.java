package ext.tpz.crystalline.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityFireThrow extends EntityThrowable {

    private boolean impacted = false;
    private BlockPos toSet;

    public EntityFireThrow(World world) {
        super(world);
    }

    public EntityFireThrow(World world, EntityLivingBase thrower) {
        super(world, thrower);
    }

    public EntityFireThrow(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (!impacted) {
            if (!world.isRemote) {
                if (result.typeOfHit == RayTraceResult.Type.ENTITY) {
                    if (result.entityHit != null) {
                        if (result.entityHit instanceof EntityLivingBase) {
                            if (this.getThrower() instanceof EntityPlayer) {
                                if (result.entityHit instanceof EntityPlayer) {
                                    if ((EntityPlayer)this.getThrower() != (EntityPlayer)result.entityHit) {
                                        EntityPlayer player = (EntityPlayer) this.getThrower();
                                        result.entityHit.setFire(10);
                                        setDead();
                                        impacted = true;
                                    }
                                } else {
                                    EntityPlayer player = (EntityPlayer) this.getThrower();
                                    result.entityHit.setFire(10);
                                    setDead();
                                    impacted = true;
                                }
                            }
                        }
                    }
                } else if (result.typeOfHit == RayTraceResult.Type.BLOCK) {
                    if (result.getBlockPos() != null) {
                        if (this.getThrower() instanceof EntityPlayer) {
                            toSet = result.getBlockPos().add(0, 1, 0);
                            world.setBlockState(toSet, Blocks.FIRE.getDefaultState(), 11);
                            impacted = true;
                            setDead();
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
    }
}
