package addsynth.overpoweredmod.items;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;

public final class LensItem extends OverpoweredItem {

  public final int index;
  private final TextFormatting color_code;
  // https://minecraft.gamepedia.com/Formatting_codes

  public LensItem(final int index, final String name, final TextFormatting format_code){
    super(name);
    this.index = index;
    color_code = format_code;
  }

  @Override
  public ITextComponent getName(final ItemStack stack){
    return ((IFormattableTextComponent)super.getName(stack)).withStyle(color_code);
  }

}
