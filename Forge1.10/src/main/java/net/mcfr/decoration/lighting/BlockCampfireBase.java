package net.mcfr.decoration.lighting;

import net.mcfr.commons.McfrBlock;
import net.mcfr.decoration.lighting.tileEntities.TileEntityCampfire;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class BlockCampfireBase extends McfrBlock implements ITileEntityProvider {
  public BlockCampfireBase(boolean isBurning) {
    super((isBurning ? "lit_" : "") + "campfire_block", Material.WOOD, SoundType.WOOD, 1, 0, null, -1, null);
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
  public EnumBlockRenderType getRenderType(IBlockState state) {
    return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
  }

  @Override
  public TileEntity createNewTileEntity(World worldIn, int meta) {
    return new TileEntityCampfire();
  }

  @Override
  public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
    worldIn.setTileEntity(pos, createNewTileEntity(worldIn, stack.getMetadata()));
  }
}