package addsynth.material;

import addsynth.core.game.RegistryUtil;
import addsynth.material.compat.MaterialsCompat;
import addsynth.material.compat.recipe.BronzeModAbsentCondition;
import addsynth.material.compat.recipe.SteelModAbsentCondition;
import addsynth.overpoweredmod.config.Features;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber(modid = ADDSynthMaterials.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public final class MaterialsRegister {

  @SubscribeEvent
  public static final void registerBlocks(final RegistryEvent.Register<Block> event){
    final IForgeRegistry<Block> game = event.getRegistry();
    
    // gem blocks
    game.register(Material.RUBY.block);
    game.register(Material.TOPAZ.block);
    game.register(Material.CITRINE.block);
    game.register(Material.SAPPHIRE.block);
    game.register(Material.AMETHYST.block);
    
    // gem ore blocks
    game.register(Material.RUBY.ore);
    game.register(Material.TOPAZ.ore);
    game.register(Material.CITRINE.ore);
    game.register(Material.SAPPHIRE.ore);
    game.register(Material.AMETHYST.ore);
    
    // special ore blocks
    game.register(Material.ROSE_QUARTZ.ore);
    game.register(Material.SILICON.ore);
    
    // metal blocks
    game.register(Material.TIN.block);
    game.register(Material.COPPER.block);
    game.register(Material.ALUMINUM.block);
    game.register(Material.STEEL.block);
    game.register(Material.BRONZE.block);
    game.register(Material.SILVER.block);
    game.register(Material.PLATINUM.block);
    game.register(Material.TITANIUM.block);
    
    // metal ores
    game.register(Material.TIN.ore);
    game.register(Material.COPPER.ore);
    game.register(Material.ALUMINUM.ore);
    game.register(Material.SILVER.ore);
    game.register(Material.PLATINUM.ore);
    game.register(Material.TITANIUM.ore);
  }
  
  @SubscribeEvent
  public static final void registerItems(final RegistryEvent.Register<Item> event){
    final IForgeRegistry<Item> game = event.getRegistry();

    // Gems first
    game.register(Material.RUBY.gem);
    game.register(Material.TOPAZ.gem);
    game.register(Material.CITRINE.gem);
    game.register(Material.SAPPHIRE.gem);
    game.register(Material.AMETHYST.gem);
    
    // gem blocks
    game.register(Material.RUBY.block_item);
    game.register(Material.TOPAZ.block_item);
    game.register(Material.CITRINE.block_item);
    game.register(Material.SAPPHIRE.block_item);
    game.register(Material.AMETHYST.block_item);

    // gem ore blocks
    game.register(RegistryUtil.getItemBlock(Material.RUBY.ore));
    game.register(RegistryUtil.getItemBlock(Material.TOPAZ.ore));
    game.register(RegistryUtil.getItemBlock(Material.CITRINE.ore));
    game.register(RegistryUtil.getItemBlock(Material.SAPPHIRE.ore));
    game.register(RegistryUtil.getItemBlock(Material.AMETHYST.ore));

    if(Features.crystal_matter_generator.get()){ // REMOVE shards
      game.register(Material.RUBY.shard);
      game.register(Material.TOPAZ.shard);
      game.register(Material.CITRINE.shard);
      game.register(Material.EMERALD.shard);
      game.register(Material.DIAMOND.shard);
      game.register(Material.SAPPHIRE.shard);
      game.register(Material.AMETHYST.shard);
      game.register(Material.QUARTZ.shard);
    }

    // metal ingots
    game.register(Material.TIN.ingot);
    game.register(Material.COPPER.ingot);
    game.register(Material.ALUMINUM.ingot);
    game.register(Material.STEEL.ingot); // Now that I have the MaterialsCompat.SteelModAbsent() function, I could prevent registering Steel if I wanted to.
    game.register(Material.BRONZE.ingot);
    game.register(Material.SILVER.ingot);
    game.register(Material.PLATINUM.ingot);
    game.register(Material.TITANIUM.ingot);
    
    // metal blocks
    game.register(Material.TIN.block_item);
    game.register(Material.COPPER.block_item);
    game.register(Material.ALUMINUM.block_item);
    game.register(Material.STEEL.block_item);
    game.register(Material.BRONZE.block_item);
    game.register(Material.SILVER.block_item);
    game.register(Material.PLATINUM.block_item);
    game.register(Material.TITANIUM.block_item);
    
    // metal ores
    game.register(RegistryUtil.getItemBlock(Material.TIN.ore));
    game.register(RegistryUtil.getItemBlock(Material.COPPER.ore));
    game.register(RegistryUtil.getItemBlock(Material.ALUMINUM.ore));
    game.register(RegistryUtil.getItemBlock(Material.SILVER.ore));
    game.register(RegistryUtil.getItemBlock(Material.PLATINUM.ore));
    game.register(RegistryUtil.getItemBlock(Material.TITANIUM.ore));
    
    // metal plates
    if(MaterialsCompat.addsynth_energy_loaded){
      ADDSynthMaterials.log.info("ADDSynth Energy was detected. Registering Metal Plates...");
      
      game.register(Material.IRON.plating);
      game.register(Material.GOLD.plating);
      game.register(Material.TIN.plating);
      game.register(Material.COPPER.plating);
      game.register(Material.ALUMINUM.plating);
      game.register(Material.STEEL.plating);
      game.register(Material.BRONZE.plating);
      game.register(Material.SILVER.plating);
      game.register(Material.PLATINUM.plating);
      game.register(Material.TITANIUM.plating);
    }
    
    // other material items
    game.register(Material.SILICON.item);
    game.register(RegistryUtil.getItemBlock(Material.SILICON.ore));
    game.register(Material.ROSE_QUARTZ.gem);
    game.register(RegistryUtil.getItemBlock(Material.ROSE_QUARTZ.ore));
  }

  @SubscribeEvent
  public static final void registerRecipeSerializers(final RegistryEvent.Register<IRecipeSerializer<?>> event){
    CraftingHelper.register(SteelModAbsentCondition.Serializer.INSTANCE);
    CraftingHelper.register(BronzeModAbsentCondition.Serializer.INSTANCE);
  }

}
