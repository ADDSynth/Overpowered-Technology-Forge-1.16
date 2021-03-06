package addsynth.core.recipe.shapeless;

import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

/** REPLICA: Nearly all of this code was copied from {@link net.minecraft.item.crafting.ShapelessRecipe} */
public abstract class AbstractRecipe implements IRecipe<IInventory> {

   private final ResourceLocation id;
   private final String group;
   private final ItemStack result;
   private final NonNullList<Ingredient> recipeItems;

  public AbstractRecipe(ResourceLocation id, String group, ItemStack output, NonNullList<Ingredient> input){
    this.id = id;
    this.group = group;
    this.result = output;
    this.recipeItems = input;
    // this.isSimple = input.stream().allMatch(Ingredient::isSimple);
  }

  @Override
  public String getGroup(){
    return group;
  }

  @Override
  public NonNullList<Ingredient> getIngredients(){
    return recipeItems; // what are the consequences of returning the EXACT list object? it can be manipulated from an external source.
  }

  /** Gets all valid ItemStacks for each Ingredient in this recipe. */
  public final ItemStack[][] getItemStackIngredients(){
    final int number_of_ingredients = recipeItems.size();
    final ItemStack[][] stacks = new ItemStack[number_of_ingredients][];
    int i;
    for(i = 0; i < number_of_ingredients; i++){
      stacks[i] = recipeItems.get(i).getItems();
    }
    return stacks;
  }

  @Override
  public boolean matches(IInventory inv, World world){
    final RecipeItemHelper recipeitemhelper = new RecipeItemHelper();
    int items_in_inventory = 0;
    int j;

    for(j = 0; j < inv.getContainerSize(); j++){
      final ItemStack itemstack = inv.getItem(j);
      if(itemstack.isEmpty() == false){
        items_in_inventory += 1;
        recipeitemhelper.accountStack(itemstack, 1);
      }
    }

    return items_in_inventory == this.recipeItems.size() && recipeitemhelper.canCraft(this, (IntList)null);
  }

  @Override
  public boolean canCraftInDimensions(int width, int height){
    return width * height >= this.recipeItems.size();
  }

  /** @return A copy of the output ItemStack. */
  @Override
  public ItemStack assemble(IInventory inv){
    return result.copy();
  }

  /** @return EXACT object. DO NOT EDIT! */
  @Override
  public ItemStack getResultItem(){
    return result;
  }

  @Override
  public ResourceLocation getId(){
    return id;
  }

  @Override
  public String toString(){
    return getClass().getSimpleName()+"(Ingredients: "+recipeItems.size()+", Output: "+result.toString()+")";
  }

}
