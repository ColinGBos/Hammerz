package vapourdrive.hammerz;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import vapourdrive.hammerz.proxies.CommonProxy;

@Mod(modid = Reference.ModID, version = Reference.Version, name = Reference.Name, dependencies = "after:techreborn;after:Thaumcraft;after:Botania", acceptedMinecraftVersions = "1.8.8;1.8.9")
public class Hammerz
{
	public static boolean hasStorageBlock = false;

	@Instance(Reference.ModID)
	public static Hammerz Instance;

	@SidedProxy(clientSide = "vapourdrive.hammerz.proxies.ClientProxy", serverSide = "vapourdrive.hammerz.proxies.CommonProxy")
	public static CommonProxy proxy;
	public static final Logger log = LogManager.getLogger(Reference.ModID);

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		
		proxy.Init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		proxy.posInit(event);
	}
}
