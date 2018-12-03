package ext.tpz.crystalline.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityObliterateEntity extends EntityThrowable {

    public EntityObliterateEntity(World world) {
        super(world);
    }

    public EntityObliterateEntity(World world, EntityLivingBase thrower) {
        super(world, thrower);
    }

    public EntityObliterateEntity(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (!world.isRemote) {
            if (result.typeOfHit == RayTraceResult.Type.ENTITY) {
                if (result.entityHit != null) {
                    if (result.entityHit instanceof EntityLivingBase) {
                        if (this.getThrower() instanceof EntityPlayer) {
                            if (result.entityHit instanceof EntityPlayer) {
                                if ((EntityPlayer)this.getThrower() != (EntityPlayer)result.entityHit) {
                                    EntityPlayer player = (EntityPlayer) this.getThrower();
                                    result.entityHit.attackEntityFrom(new DamageSourceObliterate(player), ((EntityLivingBase) result.entityHit).getHealth());
                                    setDead();
                                }
                            } else {
                                EntityPlayer player = (EntityPlayer) this.getThrower();
                                result.entityHit.attackEntityFrom(new DamageSourceObliterate(player), ((EntityLivingBase) result.entityHit).getHealth());
                                setDead();
                            }
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
