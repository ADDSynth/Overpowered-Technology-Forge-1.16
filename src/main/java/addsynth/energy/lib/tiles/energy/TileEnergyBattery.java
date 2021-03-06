package addsynth.energy.lib.tiles.energy;

import javax.annotation.Nullable;
import addsynth.core.block_network.BlockNetworkUtil;
import addsynth.energy.lib.energy_network.EnergyNetwork;
import addsynth.energy.lib.energy_network.tiles.AbstractEnergyNetworkTile;
import addsynth.energy.lib.main.Energy;
import addsynth.energy.lib.main.IEnergyUser;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntityType;

/** TileEntities that act as standard Batteries that should be part of the Energy Network
 *  should extend from this class.
 */
public abstract class TileEnergyBattery extends AbstractEnergyNetworkTile implements IEnergyUser {

  protected final Energy energy;

  public TileEnergyBattery(final TileEntityType type, final Energy energy){
    super(type);
    this.energy = energy;
  }

  @Override
  public void tick(){
    if(onServerSide()){
      if(network == null){
        BlockNetworkUtil.create_or_join(level, this, EnergyNetwork::new);
      }
      network.tick(this);
      if(energy.tick()){
        update_data();
      }
    }
  }

  @Override
  public void load(final BlockState blockstate, final CompoundNBT nbt){
    super.load(blockstate, nbt);
    if(energy != null){
      energy.loadFromNBT(nbt);
    }
  }

  @Override
  public CompoundNBT save(final CompoundNBT nbt){
    super.save(nbt);
    if(energy != null){
      energy.saveToNBT(nbt);
    }
    return nbt;
  }

  @Override
  public final void setRemoved(){
    super.setRemoved();
    if(onServerSide()){
      network.drain_battery(energy);
      BlockNetworkUtil.tileentity_was_removed(this, EnergyNetwork::new);
    }
  }

  @Override
  public final void setBlockNetwork(final EnergyNetwork network){
    this.network = network;
  }

  @Override
  public final @Nullable EnergyNetwork getBlockNetwork(){
    return network;
  }

  @Override
  public final Energy getEnergy(){
    return energy;
  }

}
