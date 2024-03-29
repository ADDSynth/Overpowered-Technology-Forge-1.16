package addsynth.overpoweredmod.machines.fusion.converter;

import java.util.List;
import javax.annotation.Nullable;
import addsynth.core.game.RegistryUtil;
import addsynth.core.util.game.tileentity.TileEntityUtil;
import addsynth.energy.lib.blocks.MachineBlock;
import addsynth.overpoweredmod.assets.CreativeTabs;
import addsynth.overpoweredmod.game.reference.Names;
import addsynth.overpoweredmod.machines.data_cable.DataCable;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public final class FusionEnergyConverterBlock extends MachineBlock {

  public FusionEnergyConverterBlock(){
    super(MaterialColor.SNOW);
    RegistryUtil.register_block(this, Names.FUSION_CONVERTER, CreativeTabs.creative_tab);
    DataCable.addAttachableBlock(this);
  }

  @Override
  public final void appendHoverText(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn){
    tooltip.add(new TranslationTextComponent("gui.overpowered.tooltip.fusion_machine"));
  }

  @Override
  @Nullable
  public final TileEntity createTileEntity(BlockState state, final IBlockReader world){
    return new TileFusionEnergyConverter();
  }

  @Override
  public void setPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack){
    TileEntityUtil.setOwner(world, placer, pos);
  }

}
