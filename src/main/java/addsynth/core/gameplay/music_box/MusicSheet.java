package addsynth.core.gameplay.music_box;

import java.util.List;
import javax.annotation.Nullable;
import addsynth.core.gameplay.Core;
import addsynth.core.gameplay.items.CoreItem;
import addsynth.core.gameplay.reference.Names;
import addsynth.core.util.game.MessageUtil;
import addsynth.core.util.player.PlayerUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext.FluidMode;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

/**
 * Man did I have trouble with this one. I don't feel like explaining how it works right now.
 * Just run in debug mode and step through the code yourself to see how it works.
 * @see net.minecraft.block.TNTBlock#use
 * @see net.minecraft.item.BucketItem
 * @see net.minecraft.item.EnderEyeItem
 */
@SuppressWarnings("resource")
public final class MusicSheet extends CoreItem {

  // See how vanilla handles the Ender Eye, an item that is used on blocks and by itself.

  public MusicSheet(){
    super(Names.MUSIC_SHEET);
  }

  @Override
  public final ActionResult<ItemStack> use(final World world, final PlayerEntity player, final Hand hand){
    final ItemStack stack = player.getMainHandItem();
    final RayTraceResult raytrace = getPlayerPOVHitResult(world, player, FluidMode.NONE);
    
    // if we're hitting block
    if(raytrace.getType() == RayTraceResult.Type.BLOCK){
      if(world.getBlockState(((BlockRayTraceResult)raytrace).getBlockPos()).getBlock() == Core.music_box){
        return new ActionResult<ItemStack>(ActionResultType.PASS, stack);
      }
    }
    
    player.startUsingItem(hand);
    if(world.isClientSide == false){
      if(player.isCrouching()){
        stack.setTag(null);
        MessageUtil.send_to_player(player, "gui.addsynthcore.music_sheet.clear");
        return new ActionResult<ItemStack>(ActionResultType.SUCCESS, stack);
      }
    }
    
    return new ActionResult<ItemStack>(ActionResultType.SUCCESS, stack);
  }

  @Override
  public final ActionResultType useOn(final ItemUseContext context){
    final World world = context.getLevel();
    final BlockPos pos = context.getClickedPos();
    if(world.getBlockState(pos).getBlock() == Core.music_box){
      if(world.isClientSide){
        return ActionResultType.SUCCESS;
      }
      final PlayerEntity player = context.getPlayer();
      final ItemStack stack = context.getItemInHand();
      final TileMusicBox tile = (TileMusicBox)world.getBlockEntity(pos);
      if(tile != null && player != null){
        if(player.isCrouching() == false){
          final CompoundNBT nbt = stack.getTag();
          if(nbt != null){
            tile.getMusicGrid().load_from_nbt(nbt);
            tile.changed = true;
            MessageUtil.send_to_player(player, "gui.addsynthcore.music_sheet.paste");
            return ActionResultType.SUCCESS;
          }
        }
        // is sneaking
        copy_music_data(stack, player, tile);
        return ActionResultType.SUCCESS;
      }
    }
    return ActionResultType.PASS;
  }

  private static final void copy_music_data(final ItemStack stack, final PlayerEntity player, final TileMusicBox tile){
    final CompoundNBT nbt = new CompoundNBT();
    tile.getMusicGrid().save_to_nbt(nbt);
      
    if(stack.getCount() == 1){
      stack.setTag(nbt);
    }
    else{
      stack.shrink(1);
      final ItemStack music_sheet = new ItemStack(Core.music_sheet, 1);
      music_sheet.setTag(nbt);
      PlayerUtil.add_to_player_inventory(player, music_sheet);
    }
      
    MessageUtil.send_to_player(player, "gui.addsynthcore.music_sheet.copy");
  }

  @Override
  public final void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag){
    if(stack.getTag() == null){
      tooltip.add(new TranslationTextComponent("gui.addsynthcore.music_sheet.no_data"));
    }
    else{
      tooltip.add(new TranslationTextComponent("gui.addsynthcore.music_sheet.has_data"));
    }
  }

}
