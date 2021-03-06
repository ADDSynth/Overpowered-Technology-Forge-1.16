package addsynth.overpoweredmod.items;

import java.util.List;
import javax.annotation.Nullable;
import addsynth.core.game.items.ItemValue;
import addsynth.overpoweredmod.compatability.curios.RingEffects;
import addsynth.overpoweredmod.config.Config;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public final class Ring extends OverpoweredItem implements ICurioItem {

  public Ring(final int ring_id){
    super("magic_ring_"+ring_id, new Item.Properties().stacksTo(1));
  }

  @Override
  public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack){
  }
  
  @Override
  public void curioTick(String identifier, int index, LivingEntity livingEntity, ItemStack stack){
    if(livingEntity.level.isClientSide == false){
      // Example Ring in the Curios mod checks the livingEntity.ticksExisted and modulos it with 20,
      // then Re-adds the effect every second. This is far superior than checking to see if the
      // entity has the effect, and then if the effect is about to run out.
      // But we still need the special case for the Extra Health effect.
      RingEffects.checkEntityHasEffect(stack, livingEntity, Config.rings_have_particle_effects.get());
    }
  }
  
  @Override
  public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack){
    RingEffects.removeEffectsFromEntity(stack, slotContext.getWearer());
  }

  @Override
  public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn){
    RingEffects.assignToolTip(stack, tooltip);
  }

  @Override
  public Rarity getRarity(ItemStack stack){
    return ItemValue.values()[RingEffects.get_ring_rarity(stack)].rarity;
  }

  @Override
  public boolean isFoil(final ItemStack stack){
    return RingEffects.get_ring_rarity(stack) >= ItemValue.EPIC.value;
  }

}
