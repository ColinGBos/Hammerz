package vapourdrive.hammerz.compat;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import vapourdrive.hammerz.items.HZ_Items;
import vapourdrive.hammerz.items.hammer.ItemHammer;
import vapourdrive.hammerz.utils.RandomUtils;
import vazkii.botania.api.BotaniaAPI;

public class BotaniaCompat
{
	public static void init()
	{
		ItemStack manasteelHammer = new ItemStack(HZ_Items.ItemHammer);
		NBTTagCompound tagCompound = RandomUtils.getNBT(manasteelHammer);
		tagCompound.setString(ItemHammer.HammerKey, "manasteel");
		new HammerEntry("manasteel", false, manasteelHammer, "ManasteelHammer", BotaniaAPI.categoryTools);
		
		ItemStack elementiumHammer = new ItemStack(HZ_Items.ItemHammer);
		NBTTagCompound tagCompound2 = RandomUtils.getNBT(elementiumHammer);
		tagCompound2.setString(ItemHammer.HammerKey, "b_elementium");
		new HammerEntry("b_elementium", true, elementiumHammer, "ElementiumHammer", BotaniaAPI.categoryAlfhomancy);
	}

}
