package vapourdrive.hammerz.config;

import java.io.File;

import cpw.mods.fml.common.Loader;

public class ConfigHandler
{
	public static File EnderIOCFG;
	public static File HammerzCFG;
	
	public static void preInit(File configPath)
	{
		HammerzConfig.preInit(new File(configPath + "/hammerz/" + "Hammerz.cfg"));
		
	}

	public static void init(File configPath)
	{
		if (Loader.isModLoaded("EnderIO"))
		{
			EnderIOCFG = new File(configPath + "/enderio/" + "EnderIO.cfg");
			EIOConfig.init(EnderIOCFG);
		}
		HammerzConfig.init(new File(configPath + "/hammerz/" + "Hammerz.cfg"));
	}
}
