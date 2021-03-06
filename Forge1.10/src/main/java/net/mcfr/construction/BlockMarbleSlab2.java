package net.mcfr.construction;

import java.util.Random;

import net.mcfr.McfrBlocks;
import net.mcfr.commons.IEnumType;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Dalle de marbre (2).
 * 
 * @author Mc-Fr
 */
public abstract class BlockMarbleSlab2 extends McfrBlockSlab<BlockMarbleSlab2.EnumType> {
  public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.create("variant", EnumType.class);

  public BlockMarbleSlab2() {
    super("marble", "2", Material.ROCK, SoundType.STONE, 5, 10, "pickaxe", 0, EnumType.class);
  }

  @Override
  public String getVariantName(int meta) {
    return getStateFromMeta(meta).getValue(VARIANT).getName();
  }

  @Override
  protected BlockStateContainer createBlockState() {
    return isDouble() ? new BlockStateContainer(this, VARIANT) : new BlockStateContainer(this, HALF, VARIANT);
  }

  @Override
  public String getUnlocalizedName(int meta) {
    return super.getUnlocalizedName() + "." + getVariantName(meta);
  }

  @Override
  public IProperty<?> getVariantProperty() {
    return VARIANT;
  }

  @Override
  public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
    return new ItemStack(McfrBlocks.MARBLE_SLAB2, 1, getMetaFromState(state) & 7);
  }

  @Override
  public IBlockState getStateFromMeta(int meta) {
    return super.getStateFromMeta(meta).withProperty(VARIANT, EnumType.byMetadata(meta & 7));
  }

  @Override
  public int getMetaFromState(IBlockState state) {
    return super.getMetaFromState(state) | state.getValue(VARIANT).getMetadata();
  }

  @Override
  public int damageDropped(IBlockState state) {
    return state.getValue(VARIANT).getMetadata();
  }

  @SideOnly(Side.CLIENT)
  @Override
  public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
    Item item = Item.getItemFromBlock(McfrBlocks.MARBLE_SLAB2);
    return item == null ? null : new ItemStack(item, 1, damageDropped(state));
  }

  @Override
  public Item getItemDropped(IBlockState blockState, Random random, int fortune) {
    return Item.getItemFromBlock(McfrBlocks.MARBLE_SLAB2);
  }

  /**
   * Variantes de la dalle de marbre 2 :
   * <ul>
   * <li>noire, brute</li>
   * <li>noire, colonne</li>
   * <li>noire, lisse</li>
   * </ul>
   * 
   * @author Mc-Fr
   */
  public static enum EnumType implements IEnumType<EnumType> {
    ROUGH_BLACK("rough_black"),
    COLUMN_BLACK("column_black"),
    SMOOTH_BLACK("smooth_black");

    private String name;

    private EnumType(String name) {
      this.name = name;
    }

    @Override
    public String getName() {
      return this.name;
    }

    @Override
    public int getMetadata() {
      return ordinal();
    }

    @Override
    public String toString() {
      return getName();
    }

    public static EnumType byMetadata(int meta) {
      return values()[meta % values().length];
    }
  }

  public static class BlockHalfMarbleSlab2 extends BlockMarbleSlab2 {
    @Override
    public boolean isDouble() {
      return false;
    }
  }

  public static class BlockDoubleMarbleSlab2 extends BlockMarbleSlab2 {
    @Override
    public boolean isDouble() {
      return true;
    }
  }
}
