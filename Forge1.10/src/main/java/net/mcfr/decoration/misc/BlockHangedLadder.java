package net.mcfr.decoration.misc;

import net.mcfr.utils.NameUtils;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.SoundType;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Échelle suspendue. Ce bloc ne peut tenir que s'il est contre un bloc ou sous une autre échelle
 * suspendue.
 *
 * @author Mc-Fr
 */
public class BlockHangedLadder extends BlockLadder {
  /**
   * Crée une échelle suspendue.
   * 
   * @param materialName le matériau
   * @param sound le type de son
   * @param hardness la dureté
   */
  public BlockHangedLadder(String materialName, SoundType sound, float hardness) {
    super();
    String name = materialName + "_ladder";
    setRegistryName(name);
    setUnlocalizedName(NameUtils.getUnlocalizedName(name));
    setHardness(hardness);
    setCreativeTab(CreativeTabs.DECORATIONS);
    setSoundType(sound);
  }

  @Override
  protected boolean canBlockStay(World worldIn, BlockPos pos, EnumFacing facing) {
    return super.canBlockStay(worldIn, pos, facing) || worldIn.getBlockState(pos.up()).getBlock() == this;
  }
}
