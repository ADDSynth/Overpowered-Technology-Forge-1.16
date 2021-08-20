package addsynth.overpoweredmod.machines.laser.cannon;

import java.util.List;
import javax.annotation.Nullable;
import addsynth.core.util.block.BlockShape;
import addsynth.core.util.constants.DirectionConstant;
import addsynth.core.util.world.WorldUtil;
import addsynth.overpoweredmod.OverpoweredTechnology;
import addsynth.overpoweredmod.assets.CreativeTabs;
import addsynth.overpoweredmod.game.core.Laser;
import addsynth.overpoweredmod.game.core.Machines;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public final class LaserCannon extends Block implements IWaterLoggable {

  public static final DirectionProperty FACING = BlockStateProperties.FACING; // Data Cable uses this block property.
  private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
  
  public final int color;

  private static final VoxelShape[] shapes = create_laser_cannon_shapes();

  private static final VoxelShape[] create_laser_cannon_shapes(){
    final VoxelShape[] shape = new VoxelShape[6];
    final double[] calc = { 0.0,    1.0/16,  2.0/16,  3.0/16,  4.0/16,  5.0/16,  6.0/16,  7.0/16,
                            8.0/16, 9.0/16, 10.0/16, 11.0/16, 12.0/16, 13.0/16, 14.0/16, 15.0/16, 1.0};
    // NORTH
    VoxelShape box0 = VoxelShapes.box(calc[1], calc[1], calc[14], calc[15], calc[15], calc[16]);
    VoxelShape box1 = VoxelShapes.box(calc[3], calc[3], calc[12], calc[13], calc[13], calc[14]);
    VoxelShape box2 = VoxelShapes.box(calc[4], calc[4], calc[10], calc[12], calc[12], calc[12]);
    VoxelShape box3 = VoxelShapes.box(calc[5], calc[5], calc[8], calc[11], calc[11], calc[10]);
    VoxelShape box4 = VoxelShapes.box(calc[6], calc[6], calc[6], calc[10], calc[10], calc[8]);
    VoxelShape box5 = VoxelShapes.box(calc[7], calc[7], calc[2], calc[9], calc[9], calc[6]);
    shape[DirectionConstant.NORTH] = BlockShape.combine(box0, box1, box2, box3, box4, box5);
    
    // SOUTH
    box0 = VoxelShapes.box(calc[1], calc[1], calc[0], calc[15], calc[15], calc[2]);
    box1 = VoxelShapes.box(calc[3], calc[3], calc[2], calc[13], calc[13], calc[4]);
    box2 = VoxelShapes.box(calc[4], calc[4], calc[4], calc[12], calc[12], calc[6]);
    box3 = VoxelShapes.box(calc[5], calc[5], calc[6], calc[11], calc[11], calc[8]);
    box4 = VoxelShapes.box(calc[6], calc[6], calc[8], calc[10], calc[10], calc[10]);
    box5 = VoxelShapes.box(calc[7], calc[7], calc[10], calc[9], calc[9], calc[14]);
    shape[DirectionConstant.SOUTH] = BlockShape.combine(box0, box1, box2, box3, box4, box5);
    
    // EAST
    box0 = VoxelShapes.box(calc[0], calc[1], calc[1], calc[2], calc[15], calc[15]);
    box1 = VoxelShapes.box(calc[2], calc[3], calc[3], calc[4], calc[13], calc[13]);
    box2 = VoxelShapes.box(calc[4], calc[4], calc[4], calc[6], calc[12], calc[12]);
    box3 = VoxelShapes.box(calc[6], calc[5], calc[5], calc[8], calc[11], calc[11]);
    box4 = VoxelShapes.box(calc[8], calc[6], calc[6], calc[10], calc[10], calc[10]);
    box5 = VoxelShapes.box(calc[10], calc[7], calc[7], calc[14], calc[9], calc[9]);
    shape[DirectionConstant.EAST] = BlockShape.combine(box0, box1, box2, box3, box4, box5);
    
    // WEST
    box0 = VoxelShapes.box(calc[14], calc[1], calc[1], calc[16], calc[15], calc[15]);
    box1 = VoxelShapes.box(calc[12], calc[3], calc[3], calc[14], calc[13], calc[13]);
    box2 = VoxelShapes.box(calc[10], calc[4], calc[4], calc[12], calc[12], calc[12]);
    box3 = VoxelShapes.box(calc[8], calc[5], calc[5], calc[10], calc[11], calc[11]);
    box4 = VoxelShapes.box(calc[6], calc[6], calc[6], calc[8], calc[10], calc[10]);
    box5 = VoxelShapes.box(calc[2], calc[7], calc[7], calc[6], calc[9], calc[9]);
    shape[DirectionConstant.WEST] = BlockShape.combine(box0, box1, box2, box3, box4, box5);
    
    // UP
    box0 = VoxelShapes.box(calc[1], calc[0], calc[1], calc[15], calc[2], calc[15]);
    box1 = VoxelShapes.box(calc[3], calc[2], calc[3], calc[13], calc[4], calc[13]);
    box2 = VoxelShapes.box(calc[4], calc[4], calc[4], calc[12], calc[6], calc[12]);
    box3 = VoxelShapes.box(calc[5], calc[6], calc[5], calc[11], calc[8], calc[11]);
    box4 = VoxelShapes.box(calc[6], calc[8], calc[6], calc[10], calc[10], calc[10]);
    box5 = VoxelShapes.box(calc[7], calc[10], calc[7], calc[9], calc[14], calc[9]);
    shape[DirectionConstant.UP] = BlockShape.combine(box0, box1, box2, box3, box4, box5);
    
    // DOWN
    box0 = VoxelShapes.box(calc[1], calc[14], calc[1], calc[15], calc[16], calc[15]);
    box1 = VoxelShapes.box(calc[3], calc[12], calc[3], calc[13], calc[14], calc[13]);
    box2 = VoxelShapes.box(calc[4], calc[10], calc[4], calc[12], calc[12], calc[12]);
    box3 = VoxelShapes.box(calc[5], calc[8], calc[5], calc[11], calc[10], calc[11]);
    box4 = VoxelShapes.box(calc[6], calc[6], calc[6], calc[10], calc[8], calc[10]);
    box5 = VoxelShapes.box(calc[7], calc[2], calc[7], calc[9], calc[6], calc[9]);
    shape[DirectionConstant.DOWN] = BlockShape.combine(box0, box1, box2, box3, box4, box5);

    return shape;
  }

  public LaserCannon(final String name, final int color){
    super(Block.Properties.of(Material.METAL, color >= 0 ? MaterialColor.STONE : MaterialColor.COLOR_GRAY)
      .sound(SoundType.METAL).strength(3.5f, 6.0f).dynamicShape());
    OverpoweredTechnology.registry.register_block(this, name, new Item.Properties().tab(CreativeTabs.creative_tab));
    this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
    this.color = color;
  }

  @Override
  public final void appendHoverText(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn){
    if(color == -1){
      tooltip.add(new TranslationTextComponent("gui.overpowered.tooltip.fusion_machine"));
    }
    else{
      tooltip.add(new TranslationTextComponent("gui.overpowered.tooltip.laser_machine"));
    }
  }

  @Override
  @Nullable
  @SuppressWarnings("resource")
  public BlockState getStateForPlacement(BlockItemUseContext context){
    final IWorld world = context.getLevel();
    final BlockPos position  = context.getClickedPos();
    // TODO: replace with the following:   for LaserCannon, TrophyBlock, and Wire
    // final FluidState fluidstate = context.getLevel().getFluidState(position);
    return this.defaultBlockState()
      .setValue(FACING, context.getClickedFace())
      .setValue(WATERLOGGED, world.getFluidState(position).getType() == Fluids.WATER);
  }

  @Override
  @SuppressWarnings("deprecation")
  public boolean canSurvive(BlockState state, IWorldReader world, BlockPos pos){
    final Block block = world.getBlockState(pos.relative(state.getValue(FACING).getOpposite())).getBlock();
    if(color == -1){
      return block == Machines.fusion_control_unit;
    }
    return block == Machines.laser_housing;
  }

  @Override
  @SuppressWarnings("deprecation")
  public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context){
    return shapes[state.getValue(FACING).ordinal()];
  }

  @Override
  @SuppressWarnings("deprecation")
  public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context){
    return shapes[state.getValue(FACING).ordinal()];
  }

  @Override
  @SuppressWarnings("deprecation")
  public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos){
    if(state.getValue(WATERLOGGED)){
      world.getLiquidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
    }
    return state;
  }

  @Override
  public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos){
    return !state.getValue(WATERLOGGED);
  }

  @Override
  @SuppressWarnings("deprecation")
  public FluidState getFluidState(BlockState state){
    return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
  }

  @Override
  public boolean hasTileEntity(BlockState state){
    return color >= 0;
  }

  @Override
  @Nullable
  public final TileEntity createTileEntity(BlockState state, IBlockReader world){
    return color == -1 ? null : new TileLaser();
  }

  @Override
  protected void createBlockStateDefinition(Builder<Block, BlockState> builder){
    builder.add(FACING, WATERLOGGED);
  }

  @Override
  @SuppressWarnings("deprecation")
  public final void neighborChanged(BlockState state, World world, BlockPos position, Block block, BlockPos neighbor, boolean isMoving){
    if(world.isClientSide == false){
      if(canSurvive(state, world, position) == false){
        final ItemStack stack = color >= 0 ? new ItemStack(Laser.index[color].cannon, 1) : new ItemStack(Machines.fusion_control_laser, 1);
        WorldUtil.spawnItemStack(world, position, stack);
        world.removeBlock(position, isMoving);
      }
    }
  }

}
