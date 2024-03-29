package addsynth.overpoweredmod.machines.identifier;

import addsynth.core.gui.util.GuiUtil;
import addsynth.energy.lib.gui.GuiEnergyBase;
import addsynth.energy.lib.gui.widgets.WorkProgressBar;
import addsynth.overpoweredmod.game.reference.GuiReference;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

public final class GuiIdentifier extends GuiEnergyBase<TileIdentifier, ContainerIdentifier> {

  private final WorkProgressBar work_progress_bar = new WorkProgressBar(8, 75, 160, 5, 11, 184);

  public GuiIdentifier(final ContainerIdentifier container, final PlayerInventory player_inventory, final ITextComponent title){
    super(176, 169, container, player_inventory, title, GuiReference.identifier);
  }

  @Override
  protected final void renderBg(MatrixStack matrix, final float partialTicks, final int mouseX, final int mouseY){
    guiUtil.draw_background_texture(matrix);
    work_progress_bar.draw(matrix, this, tile);
  }

  @Override
  protected final void renderLabels(MatrixStack matrix, final int mouseX, final int mouseY){
    guiUtil.draw_title(matrix, this.title);
    draw_energy_usage(matrix);
    draw_status(matrix, tile.getStatus());
    GuiUtil.drawItemStack(tile.getWorkingInventory().getStackInSlot(0), 76, 41);
    GuiUtil.draw_text_center(matrix, work_progress_bar.getWorkTimeProgress(), guiUtil.center_x, 63);
  }

}
