package net.mcfr.economy;

import java.util.List;

import net.mcfr.commons.IEnumType;
import net.mcfr.commons.McfrItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Jeton.
 *
 * @author Mc-Fr
 */
public class ItemToken extends McfrItem {
  public ItemToken() {
    super("token", CreativeTabs.MISC);
    setHasSubtypes(true);
  }

  @Override
  public String getUnlocalizedName(ItemStack stack) {
    return getUnlocalizedName() + "." + EnumType.byMetadata(stack.getMetadata()).getName();
  }

  @Override
  @SideOnly(Side.CLIENT)
  public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
    for (int i = 0; i < EnumType.values().length; i++) {
      subItems.add(new ItemStack(itemIn, 1, i));
    }
  }

  /**
   * Types de jetons :
   * <ul>
   * <li>icône ancienne abimée</li>
   * <li>icône ancienne</li>
   * <li>pièce ancienne</li>
   * <li>tablette ancienne</li>
   * <li>fragment ancien</li>
   * </ul>
   *
   * @author Darmo
   */
  public static enum EnumType implements IEnumType<EnumType> {
    // NORMAL("normal"),
    DAMAGED_ANCIENT_ICON("damaged_ancient_icon"),
    ANCIENT_ICON("ancient_icon"),
    ANCIENT_COIN("ancient_coin"),
    ANCIENT_TABLET("ancient_tablet"),
    ANCIENT_FRAGMENT("ancient_fragment");

    private final String name;

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
}
