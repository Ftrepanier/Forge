package net.mcfr.decoration.signs;

import java.util.Random;

import net.mcfr.McfrItems;
import net.mcfr.decoration.signs.tileEntities.TileEntityNormalSign;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockWallNormalSign extends McfrBlockWallSign {
  public BlockWallNormalSign() {
    super(Material.WOOD, "wall_sign");
    setSoundType(SoundType.WOOD);
  }

  @Override
  public TileEntity createNewTileEntity(World worldIn, int meta) {
    return new TileEntityNormalSign();
  }

  @Override
  public Item getItemDropped(IBlockState state, Random rand, int fortune) {
    return McfrItems.SIGN;
  }

  @Override
  public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
    return new ItemStack(McfrItems.SIGN);
  }
}