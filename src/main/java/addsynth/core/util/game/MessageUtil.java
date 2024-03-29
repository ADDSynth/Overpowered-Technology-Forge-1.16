package addsynth.core.util.game;

import javax.annotation.Nonnull;
import addsynth.core.ADDSynthCore;
import addsynth.core.util.player.PlayerUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.LanguageMap;
import net.minecraft.world.World;
import net.minecraftforge.server.command.TextComponentHelper;

public final class MessageUtil {

  /** Sends a SYSTEM type message to the player.<br>
   *  This must be called on the server side. Calling on the client side will only translate to English.
   * @param player
   * @param translation_key
   */
  public static final void send_to_player(final PlayerEntity player, final String translation_key, final Object ... arguments){
    @SuppressWarnings("resource")
    final MinecraftServer server = player.getServer();
    if(server != null){
      send_to_player(server, player, translation_key, arguments);
    }
  }

  /** Sends a SYSTEM type message to the player.<br>
   *  This must be called on the server side. Calling on the client side will only translate to English.
   * @param player
   * @param translation_key
   */
  public static final void send_to_player(@Nonnull MinecraftServer server, PlayerEntity player, String translation_key, Object ... arguments){
    if(LanguageMap.getInstance().has(translation_key) == false){
      ADDSynthCore.log.warn("Missing translated text for: "+translation_key);
    }
    player.sendMessage(TextComponentHelper.createComponentTranslation(server, translation_key, arguments), null);
  }

  public static final void send_to_all_players(final World world, final String translation_key, final Object ... arguments){
    @SuppressWarnings("resource")
    final MinecraftServer server = world.getServer();
    if(server != null){
      send_to_all_players(server, TextComponentHelper.createComponentTranslation(server, translation_key, arguments));
    }
  }

  public static final void send_to_all_players(final World world, final ITextComponent text_component){
    @SuppressWarnings("resource")
    final MinecraftServer server = world.getServer();
    if(server != null){
      send_to_all_players(server, text_component);
    }
  }
  
  private static final void send_to_all_players(final MinecraftServer server, final ITextComponent text_component){
    final PlayerList player_list = server.getPlayerList();
    if(player_list != null){
      player_list.broadcastMessage(text_component, ChatType.SYSTEM, null);
    }
  }

  public static final void send_to_all_players_in_world(final World world, final String translation_key, final Object ... arguments){
    @SuppressWarnings("resource")
    final MinecraftServer server = world.getServer();
    if(server != null){
      PlayerUtil.allPlayersInWorld(server, world, (ServerPlayerEntity player) -> {
        player.sendMessage(TextComponentHelper.createComponentTranslation(server, translation_key, arguments), null);
      });
    }
  }

  public static final void send_to_all_players_in_world(final World world, final ITextComponent text_component){
    @SuppressWarnings("resource")
    final MinecraftServer server = world.getServer();
    if(server != null){
      PlayerUtil.allPlayersInWorld(server, world, (ServerPlayerEntity player) -> {
        player.sendMessage(text_component, null);
      });
    }
  }

}
