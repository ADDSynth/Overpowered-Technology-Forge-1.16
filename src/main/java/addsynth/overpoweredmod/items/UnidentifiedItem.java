package addsynth.overpoweredmod.items;

import javax.annotation.Nonnull;
import addsynth.core.game.items.ArmorMaterial;
import addsynth.core.game.items.EquipmentType;
import addsynth.overpoweredmod.assets.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;

public final class UnidentifiedItem extends OverpoweredItem {

  public final int ring_id;
  public final ArmorMaterial armor_material;
  public final EquipmentType equipment_type;

  public UnidentifiedItem(final int ring_id){
    super("unidentified_ring_"+ring_id, CreativeTabs.tools_creative_tab);
    this.ring_id = ring_id;
    armor_material = null;
    equipment_type = null;
  }

  public UnidentifiedItem(@Nonnull final ArmorMaterial material, @Nonnull final EquipmentType type){
    super("unidentified_"+material.name+"_"+type.name, CreativeTabs.tools_creative_tab);
    this.ring_id = -1;
    this.armor_material = material;
    this.equipment_type = type;
  }

  @Override
  public final ITextComponent getName(final ItemStack stack){
    return ((IFormattableTextComponent)super.getName(stack)).withStyle(TextFormatting.ITALIC);
  }

}
