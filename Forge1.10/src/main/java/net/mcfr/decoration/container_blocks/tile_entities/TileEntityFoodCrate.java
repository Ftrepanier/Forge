package net.mcfr.decoration.container_blocks.tile_entities;

import net.mcfr.decoration.container_blocks.BlockFoodCrate;
import net.mcfr.decoration.container_blocks.guis.ContainerRestricted;

/**
 * Tile entity du fût de nourriture.
 *
 * @author Mc-Fr
 */
public class TileEntityFoodCrate extends TileEntityRestricted {
  public TileEntityFoodCrate() {
    super("food_crate", ContainerRestricted.SIZE, 64, true, BlockFoodCrate.class, ContainerRestricted.class);
  }
}
