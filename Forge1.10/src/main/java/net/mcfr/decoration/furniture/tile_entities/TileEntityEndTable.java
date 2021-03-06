package net.mcfr.decoration.furniture.tile_entities;

import net.mcfr.decoration.container_blocks.guis.ContainerRestrictable;
import net.mcfr.decoration.container_blocks.tile_entities.TileEntityRestrictable;

/**
 * Tile entity de la table basse.
 *
 * @author Mc-Fr
 */
public class TileEntityEndTable extends TileEntityRestrictable {
  public TileEntityEndTable() {
    super("end_table", ContainerRestrictable.LARGE_LINES_NB, 64, false, null, ContainerRestrictable.class);
  }
}
