package vapourdrive.hammerz.proxies;

import cpw.mods.fml.common.Loader;
import net.minecraftforge.client.MinecraftForgeClient;
import vapourdrive.hammerz.items.HZ_Items;
import vapourdrive.hammerz.items.PoweredItemRenderer;

public class ClientProxy extends CommonProxy
{
	@Override
	public void load()
	{
		super.load();
		if (Loader.isModLoaded("EnderIO"))
		{
			PoweredItemRenderer renderer = new PoweredItemRenderer();
			MinecraftForgeClient.registerItemRenderer(HZ_Items.DarkSteelHammer, renderer);
		}
	}
}
