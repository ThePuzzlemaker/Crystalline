package ext.tpz.crystalline.entity.render;

import ext.tpz.crystalline.entity.EntityObliterateBlock;
import ext.tpz.crystalline.entity.model.ObliterateModel;
import ext.tpz.crystalline.util.Reference;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelSnowMan;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nullable;

public class RenderEntityObliterate extends Render<EntityObliterateBlock> {

    private static final ResourceLocation texture = new ResourceLocation(Reference.MODID + ":textures/items/crystals/universe.png");
    private ModelBase model;

    public static Factory FACTORY = new Factory();

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityObliterateBlock entity) {
        return texture;
    }

    public RenderEntityObliterate(RenderManager manager) {
        super(manager);
        model = new ObliterateModel();
    }

    @Override
    public void doRender(EntityObliterateBlock entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();
        bindTexture(texture);
        GlStateManager.translate(x, y - 1.25D, z);
        model.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.125F);
        GlStateManager.popMatrix();
    }

    public static class Factory implements IRenderFactory<EntityObliterateBlock> {

        @Override
        public Render<? super EntityObliterateBlock> createRenderFor(RenderManager manager) {
            return new RenderEntityObliterate(manager);
        }

    }

}
