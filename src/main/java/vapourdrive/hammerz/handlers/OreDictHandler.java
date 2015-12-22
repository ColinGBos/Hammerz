package vapourdrive.hammerz.handlers;

import org.apache.logging.log4j.Level;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import vapourdrive.hammerz.Hammerz;
import vapourdrive.hammerz.utils.RandomUtils;

public class OreDictHandler
{
	public static void init()
	{
		registerOre("Thaumcraft", "blockCosmeticSolid", "blockThaumium", 4);
		registerOre("Botania", "storage", "blockManasteel", 0);
		registerOre("Botania", "storage", "blockElvenElementium", 2);
		
	}
	public static void registerOre(String ModId, String ItemStackName, String name, int meta)
	{
		ItemStack stack = RandomUtils.getItemStackFromString(ModId, ItemStackName, 1);
		if(stack != null)
		{
			Item item = stack.getItem();
			if(item != null)
			{
				ItemStack register = new ItemStack(item, 1, meta);
				OreDictionary.registerOre(name, register);
				Hammerz.log.log(Level.INFO, "Successfully added oreDict entry: " + name + " to: '" + register.getDisplayName() +"' from mod: " + ModId);
			}
		}
	}
}
