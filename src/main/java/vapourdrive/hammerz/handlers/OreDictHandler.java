package vapourdrive.hammerz.handlers;

import org.apache.logging.log4j.Level;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import vapourdrive.hammerz.Hammerz;
import vapourdrive.hammerz.blocks.HZ_Blocks;
import vapourdrive.hammerz.utils.RandomUtils;

public class OreDictHandler
{
	public static void earlyInit()
	{
		registerOre("Thaumcraft", "blockCosmeticSolid", "blockThaumium", 4);
		registerOre("Botania", "storage", "blockManasteel", 0);
		registerOre("Botania", "storage", "blockElvenElementium", 2);
	}
	public static void lateInit()
	{
		registerOreBlock("ingotVoid", "blockVoid", HZ_Blocks.StorageBlock, 0);
	}
	public static void registerOreBlock(String ingot, String block, Block storageBlock, int meta)
	{
		if(OreDictionary.doesOreNameExist(ingot))
		{
			OreDictionary.registerOre(block, new ItemStack(storageBlock, 1, meta));
		}
		
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
