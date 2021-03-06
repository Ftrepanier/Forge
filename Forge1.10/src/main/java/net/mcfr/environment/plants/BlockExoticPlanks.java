package net.mcfr.environment.plants;

import java.util.List;

import net.mcfr.commons.IBlockWithVariants;
import net.mcfr.commons.McfrBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Planches "exotiques".
 *
 * @author Mc-Fr
 * @see EnumExoticWoodType
 */
public class BlockExoticPlanks extends McfrBlock implements IBlockWithVariants {
  public static final PropertyEnum<EnumExoticWoodType> VARIANT = PropertyEnum.create("variant", EnumExoticWoodType.class);

  public BlockExoticPlanks() {
    super("exotic_planks", Material.WOOD, SoundType.WOOD, 2, 5, "axe", 0, CreativeTabs.BUILDING_BLOCKS);
    setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumExoticWoodType.APPLE_TREE));
  }

  @Override
  public String getVariantName(int meta) {
    return getStateFromMeta(meta).getValue(VARIANT).getName();
  }

  @Override
  public int damageDropped(IBlockState state) {
    return state.getValue(VARIANT).getMetadata();
  }

  @Override
  @SideOnly(Side.CLIENT)
  public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
    for (EnumExoticWoodType type : EnumExoticWoodType.values()) {
      list.add(new ItemStack(itemIn, 1, type.getMetadata()));
    }
  }

  @Override
  public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
    return new ItemStack(this, 1, getMetaFromState(state));
  }

  @Override
  public IBlockState getStateFromMeta(int meta) {
    return getDefaultState().withProperty(VARIANT, EnumExoticWoodType.byMetadata(meta));
  }

  @Override
  public int getMetaFromState(IBlockState state) {
    return state.getValue(VARIANT).getMetadata();
  }

  @Override
  protected BlockStateContainer createBlockState() {
    return new BlockStateContainer(this, VARIANT);
  }
}
