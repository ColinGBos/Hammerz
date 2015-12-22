package vapourdrive.hammerz.config;

import net.minecraftforge.common.config.Property;

public class ConfigOptions
{
	public static boolean AddToolInfo;
	public static boolean AddDurabilityInfo;
	//Enabling
	public static boolean[] VanillaHammerEnabling;
	public static boolean[] EnderIOHammerEnabling;
	public static boolean[] ThaumcraftHammerEnabling;
	public static boolean[] BotaniaHammersEnabling;
	public static boolean[] RotaryCraftHammerEnabling;
	public static boolean[] OreDictHammerEnabling;
	
	//EnderIO options
	public static int EIOToolEnergyuse;
	public static int EIOToolObsidianEnergyUse;
	public static int EIOToolBaseEnergy;
	public static int EIOToolTierOneEnergy;
	public static int EIOToolTierTwoEnergy;
	public static int EIOToolTierThreeEnergy;
	public static int EIOToolTierBaseLevelCost;
	public static int EIOToolTierOneLevelCost;
	public static int EIOToolTierTwoLevelCost;
	public static int EIOToolTierThreeLevelCost;
	public static double EIOToolEmpoweredSpeedBoost;
	public static double EIOToolEmpoweredObsidianSpeedBoost;
	public static double[] powerDamgeAbsorptionRatios = {0.5, 0.6, 0.75, 0.95};
	public static Property ChanceBase;
	public static float ChanceOne;
	public static float ChanceTwo;
	public static float ChanceThree;

	public static boolean UseUnityTextures;

}
