package vapourdrive.hammerz.creativetabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import vapourdrive.hammerz.items.HZ_Items;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class HZCreativeTab extends CreativeTabs
{

	public HZCreativeTab(int id, String name)
	{
		super(id, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getTabLabel()
	{
		return "Hammerz";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getTranslatedTabLabel()
	{
		return "Hammerz";
	}

	@Override
	public Item getTabIconItem()
	{
		return HZ_Items.DiamondHammer;
	}

}
