package vapourdrive.hammerz.proxies;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import vapourdrive.hammerz.items.HZ_Items;

public class ClientProxy extends CommonProxy
{
	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		super.preInit(event);
		HZ_Items.clientInit(event);
	}
	
	@Override
	public void Init(FMLInitializationEvent event)
	{
		super.Init(event);
		if (Loader.isModLoaded("EnderIO"))
		{
			//PoweredItemRenderer renderer = new PoweredItemRenderer();
			//MinecraftForgeClient.registerItemRenderer(HZ_Items.DarkSteelHammer, renderer);
		}

	}
}
