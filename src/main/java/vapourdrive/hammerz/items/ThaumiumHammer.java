package vapourdrive.hammerz.items;

import thaumcraft.api.IRepairable;
import thaumcraft.api.ThaumcraftApi;
import cpw.mods.fml.common.Optional;

@Optional.Interface(modid = "Thaumcraft", iface = "thaumcraft.api.IRepairable", striprefs = true)
public class ThaumiumHammer extends Hammer implements IRepairable
{
	public ThaumiumHammer(String Name)
	{
		super(ThaumcraftApi.toolMatThaumium, Name);
	}

}
