package addsynth.core.gui;

import addsynth.core.gui.util.GuiUtil;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

/** Extend from this class if your gui screen contains item slots. */
public abstract class GuiContainerBase<T extends Container> extends ContainerScreen<T> {

  protected final GuiUtil guiUtil;
  
  public GuiContainerBase(final T container, final PlayerInventory player_inventory, final ITextComponent title, final ResourceLocation gui_texture){
    super(container, player_inventory, title);
    guiUtil = new GuiUtil(gui_texture, 176, 166);
  }

  public GuiContainerBase(int width, int height, T container, PlayerInventory player_inventory, ITextComponent title, ResourceLocation gui_texture){
    super(container, player_inventory, title);
    guiUtil = new GuiUtil(gui_texture, width, height);
    this.imageWidth = width;
    this.imageHeight = height;
  }

  @Override
  public void render(MatrixStack matrix, final int mouseX, final int mouseY, final float partialTicks){
    this.renderBackground(matrix);
    super.render(matrix, mouseX, mouseY, partialTicks);
    this.renderTooltip(matrix, mouseX, mouseY);
  }

  @Override
  protected void renderBg(MatrixStack matrix, final float partialTicks, final int mouseX, final int mouseY){
    guiUtil.draw_background_texture(matrix);
  }

}
