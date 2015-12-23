package vapourdrive.hammerz;

import java.io.File;

import net.minecraft.creativetab.CreativeTabs;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import vapourdrive.hammerz.config.ConfigHandler;
import vapourdrive.hammerz.creativetabs.HZCreativeTab;
import vapourdrive.hammerz.handlers.EventsHandler;
import vapourdrive.hammerz.handlers.OreDictHandler;
import vapourdrive.hammerz.handlers.UpgradeManager;
import vapourdrive.hammerz.items.HZ_Items;
import vapourdrive.hammerz.proxies.CommonProxy;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.ModID, version = Reference.Version, name = Reference.Name)
public class Hammerz
{
	@Instance(Reference.ModID)
	public static Hammerz Instance;

	@SidedProxy(clientSide = "vapourdrive.hammerz.proxies.ClientProxy", serverSide = "vapourdrive.hammerz.proxies.CommonProxy")
	public static CommonProxy proxy;
	public static CreativeTabs HZTab;
	public static final Logger log = LogManager.getLogger(Reference.ModID);
	public static File ConfigPath;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		Hammerz.log.log(Level.INFO, "Beginning preInit");
		ConfigPath = event.getModConfigurationDirectory();
		HZTab = new HZCreativeTab(CreativeTabs.getNextID(), "SS_CreativeTab");
		OreDictHandler.init();
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		Hammerz.log.log(Level.INFO, "Beginning Init");
		ConfigHandler.preInit(ConfigPath);
		HZ_Items.preInit();
		ConfigHandler.init(ConfigPath);
		HZ_Items.init();
		Recipes.init();

		EventsHandler.init();
		new UpgradeManager();
		proxy.load();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		if(Loader.isModLoaded("RotaryCraft"))
		{
			OreDictHandler.registerOre("RotaryCraft", "rotarycraft_block_deco", "blockHSLA", 0);
			Recipes.registerRotaryCraftRecipe();
			Recipes.registerHammerRecipe("stickWood", HZ_Items.HSLAHammer, "blockHSLA");
		}
	}
}
