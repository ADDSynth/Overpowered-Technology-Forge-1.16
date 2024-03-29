package addsynth.overpoweredmod.registers;

import addsynth.overpoweredmod.machines.advanced_ore_refinery.ContainerOreRefinery;
import addsynth.overpoweredmod.machines.crystal_matter_generator.ContainerCrystalGenerator;
import addsynth.overpoweredmod.machines.energy_extractor.ContainerCrystalEnergyExtractor;
import addsynth.overpoweredmod.machines.fusion.chamber.ContainerFusionChamber;
import addsynth.overpoweredmod.machines.gem_converter.ContainerGemConverter;
import addsynth.overpoweredmod.machines.identifier.ContainerIdentifier;
import addsynth.overpoweredmod.machines.inverter.ContainerInverter;
import addsynth.overpoweredmod.machines.laser.machine.ContainerLaserHousing;
import addsynth.overpoweredmod.machines.magic_infuser.ContainerMagicInfuser;
import addsynth.overpoweredmod.machines.matter_compressor.MatterCompressorContainer;
import addsynth.overpoweredmod.machines.plasma_generator.ContainerPlasmaGenerator;
import addsynth.overpoweredmod.machines.portal.control_panel.ContainerPortalControlPanel;
import addsynth.overpoweredmod.machines.portal.frame.ContainerPortalFrame;
import addsynth.overpoweredmod.machines.suspension_bridge.ContainerSuspensionBridge;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.fml.network.IContainerFactory;

public final class Containers {

  public static final ContainerType<ContainerCrystalEnergyExtractor> CRYSTAL_ENERGY_EXTRACTOR  =
    new ContainerType<>((IContainerFactory<ContainerCrystalEnergyExtractor>)ContainerCrystalEnergyExtractor::new);

  public static final ContainerType<ContainerInverter> INVERTER =
    new ContainerType<>((IContainerFactory<ContainerInverter>)ContainerInverter::new);

  public static final ContainerType<ContainerGemConverter> GEM_CONVERTER =
    new ContainerType<>((IContainerFactory<ContainerGemConverter>)ContainerGemConverter::new);

  public static final ContainerType<ContainerMagicInfuser> MAGIC_INFUSER =
    new ContainerType<>((IContainerFactory<ContainerMagicInfuser>)ContainerMagicInfuser::new);

  public static final ContainerType<ContainerIdentifier> IDENTIFIER =
    new ContainerType<>((IContainerFactory<ContainerIdentifier>)ContainerIdentifier::new);

  public static final ContainerType<ContainerSuspensionBridge> ENERGY_SUSPENSION_BRIDGE =
    new ContainerType<>((IContainerFactory<ContainerSuspensionBridge>)ContainerSuspensionBridge::new);

  public static final ContainerType<ContainerOreRefinery> ADVANCED_ORE_REFINERY =
    new ContainerType<>((IContainerFactory<ContainerOreRefinery>)ContainerOreRefinery::new);

  public static final ContainerType<ContainerCrystalGenerator> CRYSTAL_MATTER_GENERATOR =
    new ContainerType<>((IContainerFactory<ContainerCrystalGenerator>)ContainerCrystalGenerator::new);
  
  public static final ContainerType<ContainerPortalControlPanel> PORTAL_CONTROL_PANEL =
    new ContainerType<>((IContainerFactory<ContainerPortalControlPanel>)ContainerPortalControlPanel::new);

  public static final ContainerType<ContainerPortalFrame> PORTAL_FRAME =
    new ContainerType<>((IContainerFactory<ContainerPortalFrame>)ContainerPortalFrame::new);

  public static final ContainerType<ContainerLaserHousing> LASER_HOUSING =
    new ContainerType<>((IContainerFactory<ContainerLaserHousing>)ContainerLaserHousing::new);

  public static final ContainerType<ContainerFusionChamber> FUSION_CHAMBER =
    new ContainerType<>((IContainerFactory<ContainerFusionChamber>)ContainerFusionChamber::new);

  public static final ContainerType<ContainerPlasmaGenerator> PLASMA_GENERATOR =
    new ContainerType<>((IContainerFactory<ContainerPlasmaGenerator>)ContainerPlasmaGenerator::new);

  public static final ContainerType<MatterCompressorContainer> MATTER_COMPRESSOR =
    new ContainerType<>((IContainerFactory<MatterCompressorContainer>)MatterCompressorContainer::new);

}
