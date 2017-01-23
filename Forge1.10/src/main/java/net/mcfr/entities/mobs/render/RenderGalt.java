package net.mcfr.entities.mobs.render;

import net.mcfr.Constants;
import net.mcfr.entities.mobs.entity.EntityGalt;
import net.mcfr.entities.mobs.model.ModelGalt;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderGalt extends RenderLiving<EntityGalt> {
  private static final ResourceLocation GALT_TEXTURES = new ResourceLocation(Constants.MOD_ID, "textures/entity/galt.png");

  public RenderGalt(RenderManager renderManagerIn, ModelGalt modelbaseIn, float shadowSizeIn) {
    super(renderManagerIn, modelbaseIn, shadowSizeIn);
  }

  /**
   * Returns the location of an entity's texture. Doesn't seem to be called
   * unless you call Render.bindEntityTexture.
   */
  protected ResourceLocation getEntityTexture(EntityGalt entity) {
    return GALT_TEXTURES;
  }

  /**
   * Renders the desired {@code T} type Entity.
   */
  @Override
  public void doRender(EntityGalt entity, double x, double y, double z, float entityYaw, float partialTicks) {
    super.doRender(entity, x, y, z, entityYaw, partialTicks);
  }
}