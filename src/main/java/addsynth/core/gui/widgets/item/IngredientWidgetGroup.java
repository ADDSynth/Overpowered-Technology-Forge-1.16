package addsynth.core.gui.widgets.item;

import addsynth.core.util.java.ArrayUtil;
import addsynth.core.util.time.TickHandler;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.ItemStack;

public final class IngredientWidgetGroup {

  private IngredientWidget[] ingredient;
  private int max_length;
  private int length;
  private static final TickHandler tick_handler = new TickHandler();
  private int i;

  public IngredientWidgetGroup(int max_number_of_ingredients){
    setIngredientArray(max_number_of_ingredients);
  }

  public IngredientWidgetGroup(int max_number_of_ingredients, final ItemStack[][] recipe){
    setIngredientArray(max_number_of_ingredients);
    setRecipe(recipe);
  }

  private final void setIngredientArray(int max_number_of_ingredients){
    max_length = max_number_of_ingredients;
    ingredient = new IngredientWidget[max_number_of_ingredients];
    for(i = 0; i < max_length; i++){
      ingredient[i] = new IngredientWidget();
    }
  }

  public final void tick(){
    if(tick_handler.tick()){
      for(i = 0; i < length; i++){
        ingredient[i].update();
      }
    }
  }

  public final void setRecipe(final ItemStack[][] recipe){
    length = recipe.length;
    // if(length > max_length){
      // automatically increase the size of the IngredientWidget array.
      // setIngredientArray(length);
    // }
    for(i = 0; i < length; i++){
      ingredient[i].setIngredient(recipe[i]);
    }
    tick_handler.reset();
  }

  /* DELETE? And also remove the ArrayUtil import.
  public final IngredientWidget get(final int index){
    if(ArrayUtil.isInsideBounds(index, ingredient.length)){
      return ingredient[index];
    }
    return null;
  }

  public final void setIngredient(final int index, final ItemStack[] stack){
    if(ArrayUtil.isInsideBounds(index,  ingredient.length)){
      if(ingredient[index] != null){
        ingredient[index].setIngredient(stack);
        tick = 0;
      }
    }
  }
  */

  public final int getLength(){
    return length;
  }

  public final void drawIngredient(int index, int x, int y){
    ingredient[index].draw(x, y);
  }
  
  // public final void draw(int[] x, int[] y){
  // }
  
  public final void draw(int guiLeft, int[] x, int guiTop, int[] y){
    for(i = 0; i < length; i++){
      ingredient[i].draw(guiLeft + x[i], guiTop + y[i]);
    }
  }

  public final void drawTooltip(MatrixStack matrix, Screen screen, int index, int x, int y, int mouse_x, int mouse_y){
    ingredient[index].drawTooltip(matrix, screen, x, y, mouse_x, mouse_y);
  }

  // public final void drawTooltip(PoseStack matrix, Screen screen, int[] x, int[] y, mouse_x, mouse_y){
  // }
  
  public final void drawTooltips(MatrixStack matrix, Screen screen, int guiLeft, int[] x, int guiTop, int[] y, int mouse_x, int mouse_y){
    for(i = 0; i < length; i++){
      ingredient[i].drawTooltip(matrix, screen, guiLeft + x[i], guiTop + y[i], mouse_x, mouse_y);
    }
  }

}
