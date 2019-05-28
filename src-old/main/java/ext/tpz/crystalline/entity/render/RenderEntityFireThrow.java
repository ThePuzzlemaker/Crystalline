package ext.tpz.crystalline.entity.render;

import ext.tpz.crystalline.entity.EntityFireThrow;
import ext.tpz.crystalline.entity.EntityObliterateBlock;
import ext.tpz.crystalline.entity.model.ObliterateModel;
import ext.tpz.crystalline.util.Reference;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nullable;

public class RenderEntityFireThrow extends Render<EntityFireThrow> {

    private static final ResourceLocation texture = new ResourceLocation(Reference.MODID + ":textures/items/crystals/hellfire_entity.png");
    private ModelBase model;

    public static Factory FACTORY = new Factory();

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityFireThrow entity) {
        return texture;
    }

    public RenderEntityFireThrow(RenderManager manager) {
        super(manager);
        model = new ObliterateModel();
    }

    @Override
    public void doRender(EntityFireThrow entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();
        bindTexture(texture);
        GlStateManager.translate(x, y - 1.25D, z);
        model.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.125F);
        GlStateManager.popMatrix();
    }

    public static class Factory implements IRenderFactory<EntityFireThrow> {

        @Override
        public Render<? super EntityFireThrow> createRenderFor(RenderManager manager) {
            return new RenderEntityFireThrow(manager);
        }

    }

}
