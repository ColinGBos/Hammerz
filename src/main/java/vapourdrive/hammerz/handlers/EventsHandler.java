package vapourdrive.hammerz.handlers;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;

public class EventsHandler
{
	public static void init()
	{
		if (Loader.isModLoaded("EnderIO"))
		{
			MinecraftForge.EVENT_BUS.register(new AnvilEvent());
		}
		MinecraftForge.EVENT_BUS.register(new ToolTipHandler());
		MinecraftForge.EVENT_BUS.register(new DropHandler());
	}
}
