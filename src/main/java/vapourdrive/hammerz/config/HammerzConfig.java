package vapourdrive.hammerz.config;

import java.io.File;
import java.util.ArrayList;

import net.minecraftforge.common.config.Configuration;
import vapourdrive.hammerz.Reference;

public class HammerzConfig
{
	public static Configuration config;
	public static String CatEnable = "Feature Enabling";
	public static String CatHammer = "Hammer Values";
	public static String CatTextures = "Texture Set";
	public static String CatRandom = "Random features";

	public static boolean[] enabledVanillaHammers =
	{
			true, true, true, true, true
	};
	public static boolean[] enabledEnderIOHammers =
	{
		true
	};
	public static boolean[] enabledBotaniaHammers =
	{
			true, true
	};
	public static boolean[] enabledRotaryCraftHammers =
	{
			true, true
	};
	public static boolean[] enabledThaumcrafHammers =
	{
			true, true, true
	};
	public static ArrayList enabledOreDictHammersArrayList = new ArrayList();

	public static String enabledHammersComment = "Enabled Vanilla Hammers: Wood, Stone, Iron, Gold, Diamond";
	public static String enabledEnderIOHammersComment = "Enabled EnderIO Hammers: DarkSteel";
	public static String enabledThaumcraftHammersComment = "Enabled Thaumcraft Hammers: Thaumium, Void, HammerOfTheCore";
	public static String enabledBotaniaHammersComment = "Enabled Botania Hammers: Manasteel, Elementium";
	public static String enabledRotaryCraftHammersComment = "Enabled RotaryCraft Hammers: HSLA, Bedrock";
	public static String enabledOreDictHammersComment = "Enabled OreDictionary Hammers: ";
	public static String unityTextureComment = "Use unified texture set vs mod-specific look";
	public static String enableToolInfoComment = "Adds information about tool's materials to their tooltips";
	public static String enableDurabilityInfoComment = "Adds information about hammer's current damage their tooltips";
	public static String enableShiftMineComment = "Allows the hammer to mine a single block while sneaking";
	public static String enableRocSilkTouchComment = "RotaryCraft Hammer will have Silk Touch upon crafting";
	public static String durabilityMultiplierComment = "Durability Multiplier for hammers from base toolset durability";
	public static String efficiencyMultiplierComment = "Efficiency Multiplier for hammers from base toolset efficiency";

	public static void preInit(File hammerzCFG)
	{
		config = new Configuration(hammerzCFG);
		config.load();
		ConfigOptions.DurabilityMultiplier = config.getFloat("Hammer Durability Multiplier", CatHammer, 6.0f, 1.0f, 9.0f,
				durabilityMultiplierComment);
		config.save();

	}

	public static void init(File hammerzCFG)
	{
		config = new Configuration(hammerzCFG);
		config.load();
		boolean[] enabledOreDictHammers = generateArray();
		ConfigOptions.VanillaHammerEnabling = config.get(CatEnable, "VanillaHammerEnabling", enabledVanillaHammers, enabledHammersComment,
				true, enabledVanillaHammers.length).getBooleanList();

		ConfigOptions.EnderIOHammerEnabling = config.get(CatEnable, "EnderIOHammerEnabling", enabledEnderIOHammers,
				enabledEnderIOHammersComment, true, enabledEnderIOHammers.length).getBooleanList();

		ConfigOptions.ThaumcraftHammerEnabling = config.get(CatEnable, "ThaumcraftHammerEnabling", enabledThaumcrafHammers,
				enabledThaumcraftHammersComment, true, enabledThaumcrafHammers.length).getBooleanList();

		ConfigOptions.BotaniaHammersEnabling = config.get(CatEnable, "BotaniaHammerEnabling", enabledBotaniaHammers,
				enabledBotaniaHammersComment, true, enabledBotaniaHammers.length).getBooleanList();

		ConfigOptions.RotaryCraftHammerEnabling = config.get(CatEnable, "RotaryCraftHammerEnabling", enabledRotaryCraftHammers,
				enabledRotaryCraftHammersComment, true, enabledRotaryCraftHammers.length).getBooleanList();

		ConfigOptions.OreDictHammerEnabling = config.get(CatEnable, "OreDictHammerEnabling", enabledOreDictHammers,
				enabledOreDictHammersComment, true, enabledOreDictHammers.length).getBooleanList();

		ConfigOptions.UseUnityTextures = config.getBoolean("Use Unity Style Textures", CatTextures, true, unityTextureComment);
		if (ConfigOptions.UseUnityTextures)
		{
			Reference.ResourcePath = Reference.ResourcePath + "unity/";
		}
		ConfigOptions.AddToolInfo = config.getBoolean("Enable Tool Info Addition", CatRandom, false, enableToolInfoComment);
		ConfigOptions.AddDurabilityInfo = config.getBoolean("Enable Hammer Durability Info Addition", CatRandom, false,
				enableDurabilityInfoComment);
		ConfigOptions.CanShiftMine = config.getBoolean("Enable Hammer Shift Mine", CatEnable, true, enableShiftMineComment);
		ConfigOptions.RotaryCraftSilkTouch = config.getBoolean("Enable RotaryCraft Hammer Silk Touch", CatEnable, true,
				enableRocSilkTouchComment);
		ConfigOptions.EfficiencyMultiplier = config.getFloat("Hammer Efficiency Multiplier", CatHammer, 0.25f, 0.05f, 1.0f,
				efficiencyMultiplierComment);

		config.save();
	}

	public static boolean[] generateArray()
	{
		boolean[] hammers = new boolean[enabledOreDictHammersArrayList.size()];
		for (int i = 0; i < enabledOreDictHammersArrayList.size(); i++)
		{
			if (enabledOreDictHammersArrayList.get(i) != null)
			{
				hammers[i] = (Boolean) enabledOreDictHammersArrayList.get(i);
			}
		}
		return hammers;

	}

}
