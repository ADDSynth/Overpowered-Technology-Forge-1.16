package addsynth.core.gameplay.team_manager;

import addsynth.core.ADDSynthCore;
import addsynth.core.game.RegistryUtil;
import addsynth.core.gameplay.client.GuiProvider;
import addsynth.core.gameplay.reference.Names;
import addsynth.core.util.command.PermissionLevel;
import addsynth.core.util.constants.Constants;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public final class TeamManagerBlock extends Block {

  public TeamManagerBlock(){
    super(Block.Properties.of(Material.STONE, MaterialColor.METAL).sound(SoundType.STONE).strength(2.0f, Constants.block_resistance));
    RegistryUtil.register_block(this, Names.TEAM_MANAGER, ADDSynthCore.creative_tab);
  }

  @Override
  @SuppressWarnings({ "deprecation" })
  public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit){
    if(world.isClientSide){
      if(player.hasPermissions(PermissionLevel.COMMANDS)){
        GuiProvider.openTeamManagerGui(this);
      }
      else{
        player.sendMessage(new TranslationTextComponent("gui.addsynthcore.team_manager.message.you_do_not_have_permission", PermissionLevel.COMMANDS), null);
      }
    }
    return ActionResultType.SUCCESS;
  }

}
