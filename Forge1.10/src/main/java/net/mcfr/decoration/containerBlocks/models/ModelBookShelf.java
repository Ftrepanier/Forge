package net.mcfr.decoration.containerBlocks.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelBookShelf extends ModelBase {
  private ModelRenderer block;

  public ModelBookShelf() {
    this.block = new ModelRenderer(this, 0, 0).setTextureSize(128, 64);
    this.block.addBox(0, 0, 0, 32, 32, 32);
  }

  public void renderModel(float scale) {
    this.block.render(scale);
  }
}
