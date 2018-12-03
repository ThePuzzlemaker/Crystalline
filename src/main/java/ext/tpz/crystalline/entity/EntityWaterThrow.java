package ext.tpz.crystalline.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityWaterThrow extends EntityThrowable {

    private boolean impacted = false;

    public EntityWaterThrow(World world) {
        super(world);
    }

    public EntityWaterThrow(World world, EntityLivingBase thrower) {
        super(world, thrower);
    }

    public EntityWaterThrow(World world, double x, double y, double z) {
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
                                EntityPlayer player = (EntityPlayer) this.getThrower();
                                result.entityHit.extinguish();
                                if (result.entityHit instanceof EntityBlaze)
                                    result.entityHit.attackEntityFrom(DamageSource.DROWN, 3);
                                setDead();
                                impacted = true;
                            }
                        }
                    }
                } else if (result.typeOfHit == RayTraceResult.Type.BLOCK) {
                    if (result.getBlockPos() != null) {
                        if (this.getThrower() instanceof EntityPlayer) {
                            world.extinguishFire(null, result.getBlockPos(), result.sideHit);
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
