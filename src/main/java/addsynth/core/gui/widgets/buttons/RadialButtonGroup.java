package addsynth.core.gui.widgets.buttons;

import java.util.function.Consumer;
import addsynth.core.ADDSynthCore;
import addsynth.core.gui.util.GuiUtil;
import addsynth.core.gui.widgets.WidgetUtil;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.widget.button.AbstractButton;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

public final class RadialButtonGroup extends AbstractButton {

  private static final ResourceLocation texture = new ResourceLocation(ADDSynthCore.MOD_ID, "textures/gui/gui_textures.png");

  public static final int radial_gui_size = 12;
  public static final int line_spacing = 3;

  private static final int radial_texture_x = 216;
  private static final int radial_texture_y = 0;
  private static final int radial_selected_texture_y = 40;
  private static final int radial_texture_size = 40;
  
  private static final int line_height = radial_gui_size + line_spacing;

  private final String[] options;
  private int buttons;
  public int option_selected = 0;
  private int i;
  private final int[] button_height;

  private Consumer<Integer> onSelectionChanged;

  public RadialButtonGroup(int x, int y, String[] options){
    this(x, y, options, 0, null);
  }
  
  public RadialButtonGroup(int x, int y, String[] options, int default_option){
    this(x, y, options, default_option, null);
  }
  
  public RadialButtonGroup(int x, int y, String[] options, Consumer<Integer> responder){
    this(x, y, options, 0, responder);
  }
  public RadialButtonGroup(int x, int y, String[] options, int default_option, Consumer<Integer> responder){
    super(x, y, radial_gui_size, line_height*options.length, new StringTextComponent(""));
    this.options = options;
    this.option_selected = default_option;
    this.onSelectionChanged = responder;
    
    buttons = options.length;
    button_height = new int[buttons];
    for(i = 0; i < buttons; i++){
      button_height[i] = this.y + (line_height * i);
    }
  }

  @Override
  public void renderButton(MatrixStack matrix, int p_renderButton_1_, int p_renderButton_2_, float p_renderButton_3_){
    WidgetUtil.common_button_render_setup(texture);
    for(i = 0; i < buttons; i++){
      blit(matrix, x, y + (line_height*i), radial_gui_size, radial_gui_size, radial_texture_x, i == option_selected ? radial_selected_texture_y : radial_texture_y, radial_texture_size, radial_texture_size, 256, 256);
    }
    for(i = 0; i < buttons; i++){
      GuiUtil.draw_text_left(matrix, options[i], x + 16, y + (line_height*i) + 2);
    }
  }

  @Override
  public void onClick(double mouse_x, double mouse_y){
    for(i = 0; i < buttons; i++){
      if(mouse_y >= button_height[i] && mouse_y <= button_height[i] + radial_gui_size){
        if(i != option_selected){
          option_selected = i;
          if(onSelectionChanged != null){
            onSelectionChanged.accept(i);
          }
        }
        break;
      }
    }
  }

  @Override
  public void onPress(){
  }

  // public final void setResponder(final Consumer<Integer> responder){
  //   this.onSelected = responder;
  // }

  public final int getSelectedOption(){
    return option_selected;
  }

  @Override
  public void playDownSound(SoundHandler p_playDownSound_1_){
  }

}
