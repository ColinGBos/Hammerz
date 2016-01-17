package vapourdrive.hammerz.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class EIOConfig
{
	public static Configuration config;
	public static void init(File enderIOCFG)
	{
		config = new Configuration(enderIOCFG);
		config.load();
		ConfigOptions.EIOToolBaseEnergy = (config.getInt("darkSteelPowerStorageLevelThree", "Dark Steel", 500000, 1000, 1000000, "")/2);
		ConfigOptions.EIOToolTierOneEnergy = config.getInt("capacitorBankTierOneMaxStorageRF", "Dark Steel", 1000000, 10000, 1000000, "");
		ConfigOptions.EIOToolTierTwoEnergy = config.getInt("capacitorBankTierTwoMaxStorageRF", "Dark Steel", 5000000, 10000, 100000000, "");
		ConfigOptions.EIOToolTierThreeEnergy = config.getInt("capacitorBankTierThreeMaxStorageRF", "Dark Steel", 25000000, 10000, 100000000, "");
		ConfigOptions.EIOToolObsidianEnergyUse = config.getInt("darkSteelPickPowerUseObsidian", "Dark Steel", 10000, 100, 100000, "");
		ConfigOptions.EIOToolTierBaseLevelCost = config.getInt("darkSteelUpgradeVibrantCost", "Dark Steel", 10, 1, 100, "");
		ConfigOptions.EIOToolTierOneLevelCost = config.getInt("darkSteelUpgradePowerOneCost", "Dark Steel", 10, 1, 100, "");
		ConfigOptions.EIOToolTierTwoLevelCost = config.getInt("darkSteelUpgradePowerTwoCost", "Dark Steel", 15, 1, 100, "");
		ConfigOptions.EIOToolTierThreeLevelCost = config.getInt("darkSteelUpgradePowerThreeCost", "Dark Steel", 20, 1, 100, "");
		ConfigOptions.EIOToolEmpoweredSpeedBoost = config.getFloat("darkSteelPickEffeciencyBoostWhenPowered", "Dark Steel", 2.0f, 1.0f, 3.0f, "");
		ConfigOptions.EIOToolEmpoweredObsidianSpeedBoost = config.getFloat("darkSteelPickEffeciencyObsidian", "Dark Steel", 40.0f, 1.0f, 100.0f, "");
		ConfigOptions.powerDamgeAbsorptionRatios = config.get("Dark Steel", "darkSteelPowerDamgeAbsorptionRatios", ConfigOptions.powerDamgeAbsorptionRatios, "", 0.0, 1.0, true, 4).getDoubleList();

	}

}
