package addsynth.overpoweredmod.blocks.dimension.tree;

import addsynth.overpoweredmod.game.reference.Names;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public final class UnknownLeaves extends Block {

  public UnknownLeaves(){
    super(Block.Properties.of(Material.LEAVES).strength(0.2f, 0.0f).sound(SoundType.GRASS).noOcclusion());
    setRegistryName(Names.UNKNOWN_LEAVES);
  }

}
