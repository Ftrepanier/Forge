package net.mcfr.equipment;

import java.util.List;

import net.mcfr.utils.NameUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;

/**
 * Pelle.
 *
 * @author Mc-Fr
 */
public class McfrItemSpade extends ItemSpade {
  /**
   * Crée une pelle.
   * 
   * @param name le nom
   * @param material le matériau
   */
  public McfrItemSpade(String name, Item.ToolMaterial material) {
    super(material);
    name += "_shovel";
    setRegistryName(name);
    setUnlocalizedName(NameUtils.getUnlocalizedName(name));
  }

  @Override
  public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
    super.addInformation(stack, playerIn, tooltip, advanced);
    NameUtils.addItemInformation(this, stack, playerIn, tooltip);
  }
}
