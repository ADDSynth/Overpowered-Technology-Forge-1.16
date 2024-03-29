package addsynth.overpoweredmod.items.tools;

import addsynth.core.game.item.constants.ToolConstants;
import addsynth.overpoweredmod.assets.CreativeTabs;
import addsynth.overpoweredmod.game.reference.Names;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.Rarity;

public class OverpoweredPickaxe extends PickaxeItem {

  public OverpoweredPickaxe(){
    super(OverpoweredTiers.CELESTIAL_PICKAXE, ToolConstants.pickaxe_damage, ToolConstants.pickaxe_speed,
      new Item.Properties().tab(CreativeTabs.creative_tab));
    setRegistryName(Names.CELESTIAL_PICKAXE);
  }

  @Override
  public boolean isEnchantable(ItemStack stack){
    return false;
  }

  @Override
  public Rarity getRarity(ItemStack stack){
    return Rarity.RARE;
  }
}
