package vapourdrive.hammerz.proxies;

import java.io.File;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import org.apache.logging.log4j.Level;

import vapourdrive.hammerz.Hammerz;
import vapourdrive.hammerz.compat.BotaniaCompat;
import vapourdrive.hammerz.compat.ThaumcraftCompat;
import vapourdrive.hammerz.config.ConfigHandler;
import vapourdrive.hammerz.creativetabs.HZCreativeTab;
import vapourdrive.hammerz.handlers.EventsHandler;
import vapourdrive.hammerz.handlers.OreDictHandler;
import vapourdrive.hammerz.handlers.UpgradeManager;
import vapourdrive.hammerz.items.HZ_Items;

public class CommonProxy
{
	public static CreativeTabs HZTab;
	public static File ConfigPath;

	
	public void preInit(FMLPreInitializationEvent event)
	{
		Hammerz.log.log(Level.INFO, "Beginning preInit");
		ConfigPath = event.getModConfigurationDirectory();
		HZTab = new HZCreativeTab(CreativeTabs.getNextID(), "SS_CreativeTab");
		OreDictHandler.earlyInit();
		ConfigHandler.earlyInit(ConfigPath);

		HZ_Items.preInit();

		ConfigHandler.lateInit(ConfigPath);
		HZ_Items.init();

	}
	
	public void Init(FMLInitializationEvent event)
	{
		Hammerz.log.log(Level.INFO, "Beginning Init");
		HZ_Items.postInit();

		EventsHandler.init();
		new UpgradeManager();
	}
	
	public void posInit(FMLPostInitializationEvent event)
	{
		Hammerz.log.log(Level.INFO, "Beginning postInit");
		if (Loader.isModLoaded("RotaryCraft"))
		{
			OreDictHandler.registerOre("RotaryCraft", "rotarycraft_block_deco", "blockHSLA", 0);
		}
		if (Loader.isModLoaded("Thaumcraft"))
		{
			//ThaumcraftCompat.init();
		}
		if (Loader.isModLoaded("Botania"))
		{
			BotaniaCompat.init();
		}
	}
}
