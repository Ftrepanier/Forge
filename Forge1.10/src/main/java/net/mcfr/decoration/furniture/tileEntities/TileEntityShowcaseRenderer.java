package net.mcfr.decoration.furniture.tileEntities;

import static net.mcfr.utils.RenderUtils.*;
import net.mcfr.decoration.furniture.BlockShowcase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;

public class TileEntityShowcaseRenderer extends TileEntitySpecialRenderer<TileEntityShowcase> {
  @Override
  @SuppressWarnings("incomplete-switch")
  public void renderTileEntityAt(TileEntityShowcase te, double x, double y, double z, float partialTicks, int destroyStage) {
    if (!te.hasItem()) return;
    EnumFacing facing = getFacing(te);
    final float scale = 0.75f;
    
    GlStateManager.pushMatrix();
    GlStateManager.translate(x + 0.5, y + 1, z + 0.5);
    switch (facing) {
      case NORTH:
        GlStateManager.rotate(90, 1, 0, 0);
        GlStateManager.rotate(90, 0, 0, 1);
        GlStateManager.rotate(30, 0, 1, 0);
        break;
      case EAST:
        GlStateManager.rotate(-90, 1, 0, 0);
        GlStateManager.rotate(90, 0, 0, 1);
        GlStateManager.rotate(30, 1, 0, 0);
        break;
      case SOUTH:
        GlStateManager.rotate(90, 1, 0, 0);
        GlStateManager.rotate(-90, 0, 0, 1);
        GlStateManager.rotate(30, 0, 1, 0);
        break;
      case WEST:
        GlStateManager.rotate(-90, 1, 0, 0);
        GlStateManager.rotate(-90, 0, 0, 1);
        GlStateManager.rotate(30, 1, 0, 0);
        break;
    }
    GlStateManager.scale(scale, scale, scale);
    renderItem(te.getItem());
    
    GlStateManager.popMatrix();
  }
  
  private EnumFacing getFacing(TileEntityShowcase te) {
    return te.getWorld().getBlockState(te.getPos()).getValue(BlockShowcase.FACING);
  }
}