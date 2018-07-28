package ext.tpz.crystalline.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityObliterateBlock extends EntityThrowable {

    public EntityObliterateBlock(World world) {
        super(world);
    }

    public EntityObliterateBlock(World world, EntityLivingBase thrower) {
        super(world, thrower);
    }

    public EntityObliterateBlock(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        /*if (result.typeOfHit == RayTraceResult.Type.ENTITY) {
            if (result.entityHit != null) {
                if (result.entityHit instanceof EntityLiving) {
                    if (this.getThrower() instanceof EntityPlayer) {
                        EntityPlayer player = (EntityPlayer) this.getThrower();
                        result.entityHit.attackEntityFrom(DamageSource.causePlayerDamage(player), 20);
                    }
                }
            }
        }*/
        if (result.typeOfHit == RayTraceResult.Type.BLOCK) {
            if (result.getBlockPos() != null) {
                if (this.getThrower() instanceof EntityPlayer) {
                    if (world.canMineBlockBody((EntityPlayer) this.getThrower(), result.getBlockPos())) {
                        world.destroyBlock(result.getBlockPos(), true);
                        setDead();
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
