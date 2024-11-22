package vapourdrive.hammerz.config;

import net.minecraftforge.common.config.Property;

public class ConfigOptions
{
	public static boolean AddToolInfo;
	public static boolean AddOreDictEntries;
	public static boolean AddDurabilityInfo;
	public static float DurabilityMultiplier;
	public static float EfficiencyMultiplier;
	public static boolean CanShiftMine;
	
	//Enabling
	public static boolean[] VanillaHammerEnabling;
	public static boolean[] OreDictHammerEnabling;
	
	//EnderIO options
	public static int HammerEnergyUse;
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
	
	//RotaryCraft
	public static boolean RotaryCraftSilkTouch;

	public static boolean UseUnityTextures;

}
