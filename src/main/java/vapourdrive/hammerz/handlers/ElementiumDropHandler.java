package vapourdrive.hammerz.handlers;

import java.util.Arrays;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.oredict.OreDictionary;
import vapourdrive.hammerz.items.HZ_Items;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ElementiumDropHandler
{
	static final List<String> validBlocks = Arrays.asList(new String[]
	{
			"dirt", "sand", "gravel", "cobblestone", "netherrack"
	});

	@SubscribeEvent
	public void onHarvestDrops(HarvestDropsEvent event)
	{
		if (event.harvester != null)
		{
			ItemStack stack = event.harvester.getCurrentEquippedItem();
			if (stack != null && (stack.getItem() == HZ_Items.ElvenElementiumHammer))
			{
				for (int i = 0; i < event.drops.size(); i++)
				{
					ItemStack drop = event.drops.get(i);
					if (drop != null)
					{
						Block block = Block.getBlockFromItem(drop.getItem());
						if (block != null && isDisposable(block))
						{
							event.drops.remove(i);
						}
					}
				}
			}
		}
	}

	public static boolean isDisposable(Block block)
	{
		for (int id : OreDictionary.getOreIDs(new ItemStack(block)))
		{
			if (validBlocks.contains(OreDictionary.getOreName(id)))
			{
				return true;
			}
		}

		return false;
	}
}
