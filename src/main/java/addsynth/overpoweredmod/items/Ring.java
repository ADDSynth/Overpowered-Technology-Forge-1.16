package addsynth.overpoweredmod.items;

import java.util.List;
import javax.annotation.Nullable;
import addsynth.core.compat.Compatibility;
import addsynth.core.game.item.constants.ItemValue;
import addsynth.overpoweredmod.compatability.curios.CuriosCapabilityProvider;
import addsynth.overpoweredmod.compatability.curios.RingEffects;
import addsynth.overpoweredmod.game.reference.Names;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

public final class Ring extends OverpoweredItem {

  public Ring(final int ring_id){
    super(Names.MAGIC_RING[ring_id], new Item.Properties().stacksTo(1));
  }

  @Override
  @Nullable
  public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt){
    return Compatibility.CURIOS.loaded ? new CuriosCapabilityProvider(
      new ICurio(){
      
        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack){
        }
        
        @Override
        public void curioTick(String identifier, int index, LivingEntity livingEntity){
          if(livingEntity.level.isClientSide == false){
            // Example Ring in the Curios mod checks the livingEntity.ticksExisted and modulos it with 20,
            // then Re-adds the effect every second. This is far superior than checking to see if the
            // entity has the effect, and then if the effect is about to run out.
            // But we still need the special case for the Extra Health effect.
            RingEffects.checkEntityHasEffect(stack, livingEntity);
          }
        }
        
        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack){
          RingEffects.removeEffectsFromEntity(stack, slotContext.getWearer());
        }
        
      }
    ) : null;
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
