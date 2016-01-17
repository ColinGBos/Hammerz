package vapourdrive.hammerz.creativetabs;

import java.util.Random;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vapourdrive.hammerz.items.HZ_Items;
import vapourdrive.hammerz.items.hammer.ItemHammer;
import vapourdrive.hammerz.utils.RandomUtils;

public class HZCreativeTab extends CreativeTabs
{
	public static int timeSinceChance = 250;
	public static ItemStack stack;

	public HZCreativeTab(int id, String name)
	{
		super(id, name);
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
	@SideOnly(Side.CLIENT)
	public ItemStack getIconItemStack()
	{
		if (timeSinceChance >= 250)
		{
			timeSinceChance = 0;
			ItemStack stack = new ItemStack(HZ_Items.ItemHammer);
			NBTTagCompound tagCompound = RandomUtils.getNBT(stack);
			Random rand = new Random();
			String name = HZ_Items.hammerTypes.get(rand.nextInt(HZ_Items.hammerTypes.size())).getName();
			tagCompound.setString(ItemHammer.HammerKey, name);
			HZCreativeTab.stack = stack;
		}
		timeSinceChance++;
		return HZCreativeTab.stack;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem()
	{
		return HZ_Items.ItemHammer;
	}

}
