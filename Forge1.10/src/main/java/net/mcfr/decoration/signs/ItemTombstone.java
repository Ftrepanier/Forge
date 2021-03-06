package net.mcfr.decoration.signs;

import net.mcfr.McfrBlocks;
import net.mcfr.commons.McfrItem;
import net.mcfr.decoration.signs.tile_entities.TileEntityTombstone;
import net.mcfr.utils.FacingUtils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Item de la pierre tombale.
 *
 * @author Mc-Fr
 */
public class ItemTombstone extends McfrItem {
  public ItemTombstone() {
    super("tombstone", 16, CreativeTabs.DECORATIONS);
  }

  @Override
  public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX,
      float hitY, float hitZ) {
    if (player.canPlayerEdit(pos, facing, stack)) {
      if (!world.isRemote && facing == EnumFacing.UP && McfrBlocks.TOMBSTONE.canPlaceBlockAt(world, pos.offset(facing))) {
        pos = pos.offset(facing);
        int rotation = FacingUtils.getSignRotation(player);
        world.setBlockState(pos, McfrBlocks.TOMBSTONE.getDefaultState().withProperty(BlockTombstone.ROTATION, rotation), 11);

        stack.stackSize--;
        TileEntity te = world.getTileEntity(pos);

        if (te != null && te instanceof TileEntityTombstone) {
          // TODO
          // McfrNetworkWrapper.getInstance().sendTo(new OpenEditTombstoneMessage(pos),
          // (EntityPlayerMP) playerIn);
        }
      }
      else
        world.playSound(player, pos, SoundEvents.BLOCK_STONE_PLACE, SoundCategory.BLOCKS, 2f, 0.75f);

      return EnumActionResult.SUCCESS;
    }

    return EnumActionResult.FAIL;
  }
}
