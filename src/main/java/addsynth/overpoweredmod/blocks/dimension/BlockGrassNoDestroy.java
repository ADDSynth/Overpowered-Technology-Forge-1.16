package addsynth.overpoweredmod.blocks.dimension;

import addsynth.core.util.game.MinecraftUtility;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public final class BlockGrassNoDestroy extends Block {

  public BlockGrassNoDestroy(String name){
    super(MinecraftUtility.setUnbreakable(Block.Properties.of(Material.GRASS)));
    // MAYBE: Registers.add(this, name, false);
  }

  /*
  @Override
  public void onBlockDestroyedByPlayer(World world, BlockPos pos, IBlockState state) {
    if(world.isRemote == false){
      world.setBlockState(pos, Init.custom_grass_block.getDefaultState(), 2);
    }
  }
  */

}
