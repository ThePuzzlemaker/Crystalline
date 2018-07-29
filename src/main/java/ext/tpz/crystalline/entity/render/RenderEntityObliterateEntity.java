package ext.tpz.crystalline.entity.render;

import ext.tpz.crystalline.entity.EntityObliterateBlock;
import ext.tpz.crystalline.entity.EntityObliterateEntity;
import ext.tpz.crystalline.entity.model.ObliterateModel;
import ext.tpz.crystalline.util.Reference;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nullable;

public class RenderEntityObliterateEntity extends Render<EntityObliterateEntity> {

    private static final ResourceLocation texture = new ResourceLocation(Reference.MODID + ":textures/items/crystal.universe.png");
    private ModelBase model;

    public static Factory FACTORY = new Factory();

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityObliterateEntity entity) {
        return texture;
    }

    public RenderEntityObliterateEntity(RenderManager manager) {
        super(manager);
        model = new ObliterateModel();
    }

    @Override
    public void doRender(EntityObliterateEntity entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();
        bindTexture(texture);
        GlStateManager.translate(x, y - 1.25D, z);
        model.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.125F);
        GlStateManager.popMatrix();
    }

    public static class Factory implements IRenderFactory<EntityObliterateEntity> {

        @Override
        public Render<? super EntityObliterateEntity> createRenderFor(RenderManager manager) {
            return new RenderEntityObliterateEntity(manager);
        }

    }

}
