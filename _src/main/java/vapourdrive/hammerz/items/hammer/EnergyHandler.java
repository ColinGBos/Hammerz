package vapourdrive.hammerz.items.hammer;

import net.minecraft.item.ItemStack;
import vapourdrive.hammerz.config.ConfigOptions;
import vapourdrive.hammerz.utils.RandomUtils;

public class EnergyHandler
{
	public static int receiveEnergy(ItemStack stack, int maxReceive, boolean simulate)
	{
		int energy = getEnergyStored(stack);
		int energyRecieved;
		energyRecieved = Math.min(getMaxEnergyStored(stack) - energy, maxReceive);
		if (!simulate)
		{
			energy += energyRecieved;
			RandomUtils.getNBT(stack).setInteger(ItemHammer.Tag_DarkSteelEnergy, energy);
		}
		return energyRecieved;
	}

	public static int extractEnergy(ItemStack stack, int maxExtract, boolean simulate)
	{
		int energy = getEnergyStored(stack);
		int energyExtracted = Math.min(energy, maxExtract);
		if (!simulate)
		{
			energy -= energyExtracted;
			if(HammerInfoHandler.isStackDarkSteelHammer(stack))
			{
				RandomUtils.getNBT(stack).setInteger(ItemHammer.Tag_DarkSteelEnergy, energy);
			}
			else
			{
				RandomUtils.getNBT(stack).setInteger(ItemHammer.Tag_EnergyStored, energy);
			}
		}
		return energyExtracted;
	}

	public static int getEnergyStored(ItemStack stack)
	{
		if(HammerInfoHandler.isStackDarkSteelHammer(stack))
		{
			return RandomUtils.getNBT(stack).getInteger(ItemHammer.Tag_DarkSteelEnergy);
		}
		else
		{
			return RandomUtils.getNBT(stack).getInteger(ItemHammer.Tag_EnergyStored);
		}
	}

	public static int getMaxEnergyStored(ItemStack stack)
	{
		if (HammerInfoHandler.isStackDarkSteelHammer(stack) && isEmpowered(stack))
		{
			switch (RandomUtils.getNBT(stack).getInteger(ItemHammer.Key_Empower))
			{
				case 1:
					return ConfigOptions.EIOToolBaseEnergy;
				case 2:
					return ConfigOptions.EIOToolTierOneEnergy;
				case 3:
					return ConfigOptions.EIOToolTierTwoEnergy;
				case 4:
					return ConfigOptions.EIOToolTierThreeEnergy;
				default:
					return 0;
			}
		}
		return 0;	}
	
	public static boolean isEmpowered(ItemStack stack)
	{
		if (getEmpoweredment(stack) > 0)
		{
			return true;
		}
		return false;
	}

	public static int getEmpoweredment(ItemStack stack)
	{
		int level = RandomUtils.getNBT(stack).getInteger(ItemHammer.Key_Empower);
		if (level > 0)
		{
			return level;
		}
		return 0;
	}
}
