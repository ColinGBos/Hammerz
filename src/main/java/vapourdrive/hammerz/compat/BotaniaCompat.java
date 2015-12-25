package vapourdrive.hammerz.compat;

import vapourdrive.hammerz.items.HZ_Items;
import vazkii.botania.api.BotaniaAPI;

public class BotaniaCompat
{
	public static void init()
	{
		new HammerEntry(false, HZ_Items.ManasteelHammer, "ManasteelHammer", BotaniaAPI.categoryTools);
		new HammerEntry(true, HZ_Items.ElvenElementiumHammer, "ElementiumHammer", BotaniaAPI.categoryAlfhomancy);
	}

}
