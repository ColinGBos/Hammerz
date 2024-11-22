package vapourdrive.hammerz.items.hammer;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class RepairHandler
{
	public static boolean getIsRepairable(ItemStack stack, ItemStack stack2)
	{
		int[] names = OreDictionary.getOreIDs(stack2);
		for (int i = 0; i < names.length; i++)
		{
			if ((HammerInfoHandler.getHammerType(stack).getBlockName()).contentEquals(OreDictionary.getOreName(names[i])))
			{
				return true;
			}
		}
		return false;
	}

}
