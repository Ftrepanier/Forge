package net.mcfr.decoration.lighting;

import java.util.Random;

import net.mcfr.McfrItems;
import net.mcfr.commons.McfrBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLantern extends McfrBlock {
  public static final PropertyInteger ORIENTATION = PropertyInteger.create("orientation", 0, 3);
  public static final PropertyEnum<EnumPosition> POSITION = PropertyEnum.create("position", EnumPosition.class);

  private final EnumDyeColor color;

  public BlockLantern(EnumDyeColor color, boolean isPaper) {
    super(color.getName() + (isPaper ? "_paper" : "") + "_lantern", isPaper ? Material.CLOTH : Material.GLASS, isPaper ? SoundType.CLOTH : SoundType.GLASS, 0.5f, 0, null, -1, null);
    setDefaultState(this.blockState.getBaseState().withProperty(ORIENTATION, 0).withProperty(POSITION, EnumPosition.BOTTOM));
    setLightLevel(0.875f);
    this.color = color;
  }

  @Override
  public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side) {
    boolean ok = super.canPlaceBlockOnSide(worldIn, pos, side);
    BlockPos pos1 = pos.offset(side);

    return ok && worldIn.getBlockState(pos1).isSideSolid(worldIn, pos1, side);
  }

  @Override
  public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
    EnumPosition position = EnumPosition.fromFacing(facing);
    int face = position.isOnWall() ? facing.getHorizontalIndex() : getFacingIndex(placer);
    return super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(POSITION, position).withProperty(ORIENTATION, face);
  }

  private int getFacingIndex(EntityLivingBase placer) {
    int rotation = MathHelper.floor_double((placer.rotationYaw + 180) * 16 / 360 + 0.5) & 15;

    if (rotation == 0 || rotation == 8)
      return 0;
    if (rotation == 4 || rotation == 12)
      return 1;
    if (rotation >= 1 && rotation <= 3 || rotation >= 9 && rotation <= 11)
      return 2;
    return 3;
  }

  @Override
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    double f = 0.25, h = 0.5;

    if (state.getValue(POSITION) == EnumPosition.BOTTOM)
      return new AxisAlignedBB(h - f, 0, h - f, h + f, 0.6, h + f);
    else if (state.getValue(POSITION) == EnumPosition.TOP)
      return new AxisAlignedBB(h - f, 0.4, h - f, h + f, 1, h + f);
    else {
      switch (state.getValue(ORIENTATION)) {
        case 0:
          return new AxisAlignedBB(h - f, 0.2, 0, h + f, 0.8, h);
        case 1:
          return new AxisAlignedBB(h, 0.2, h - f, 1, 0.8, h + f);
        case 2:
          return new AxisAlignedBB(h - f, 0.2, h, h + f, 0.8, 1);
        case 3:
          return new AxisAlignedBB(0, 0.2, h - f, h, 0.8, h + f);
        default:
          return NULL_AABB;
      }
    }
  }

  @Override
  public boolean isOpaqueCube(IBlockState state) {
    return false;
  }

  @Override
  public boolean isFullCube(IBlockState state) {
    return false;
  }

  @Override
  protected BlockStateContainer createBlockState() {
    return new BlockStateContainer(this, POSITION, ORIENTATION);
  }

  @Override
  @SuppressWarnings("deprecation")
  public IBlockState getStateFromMeta(int meta) {
    return super.getStateFromMeta(meta).withProperty(ORIENTATION, meta & 3).withProperty(POSITION, EnumPosition.fromIndex((meta & 0xf0) >> 2));
  }

  @Override
  public int getMetaFromState(IBlockState state) {
    return (state.getValue(POSITION).ordinal() << 2) | (state.getValue(ORIENTATION) & 3);
  }

  @Override
  public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
    return new ItemStack(getRegistryName().getResourcePath().contains("paper") ? McfrItems.PAPER_LANTERN : McfrItems.LANTERN);
  }

  @Override
  public Item getItemDropped(IBlockState state, Random rand, int fortune) {
    return getRegistryName().getResourcePath().contains("paper") ? McfrItems.PAPER_LANTERN : McfrItems.LANTERN;
  }

  @Override
  public int damageDropped(IBlockState state) {
    return this.color.getMetadata();
  }

  @Override
  public BlockRenderLayer getBlockLayer() {
    return BlockRenderLayer.CUTOUT_MIPPED;
  }

  private static enum EnumPosition implements IStringSerializable {
    BOTTOM("bottom"),
    WALL("wall"),
    TOP("top");

    private final String name;

    private EnumPosition(String name) {
      this.name = name;
    }

    @Override
    public String getName() {
      return this.name;
    }

    public boolean isOnWall() {
      return this == WALL;
    }

    public static EnumPosition fromFacing(EnumFacing facing) {
      if (facing == EnumFacing.DOWN)
        return TOP;
      if (facing == EnumFacing.UP)
        return BOTTOM;
      return WALL;
    }

    public static EnumPosition fromIndex(int index) {
      return values()[index % values().length];
    }
  }
}
