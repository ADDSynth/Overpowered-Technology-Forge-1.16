package addsynth.overpoweredmod.blocks.dimension;

import net.minecraft.block.AirBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Deprecated
public final class BlockAirNoDestroy extends AirBlock {

  public BlockAirNoDestroy(){
    super(Block.Properties.of(Material.AIR));
    // MAYBE: setRegistryName(new ResourceLocation(OverpoweredTechnology.MOD_ID, "air"));
  }

  @Override
  public void playerWillDestroy(World world, BlockPos pos, BlockState state, PlayerEntity player){
    if(world.isClientSide() == false){
      // world.setBlockState(pos, Init.custom_air_block.getDefaultState(), 2);
    }
  }

}
