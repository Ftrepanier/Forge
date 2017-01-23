package net.mcfr.decoration.signs;

import java.util.Random;

import javax.annotation.Nullable;

import net.mcfr.McfrItems;
import net.mcfr.decoration.signs.tileEntities.TileEntityWallNote;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockWallNote extends McfrBlockWallSign {
  public BlockWallNote() {
    super(Material.CLOTH, "wall_note_block");
    setSoundType(SoundType.WOOD);
  }

  @Override
  public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem,
      EnumFacing side, float hitX, float hitY, float hitZ) {
    // TODO
    return super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem, side, hitX, hitY, hitZ);
  }

  @Override
  public TileEntity createNewTileEntity(World worldIn, int meta) {
    return new TileEntityWallNote();
  }

  @Override
  public Item getItemDropped(IBlockState state, Random rand, int fortune) {
    return McfrItems.WALL_NOTE;
  }

  @Override
  public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
    return new ItemStack(McfrItems.WALL_NOTE);
  }
}