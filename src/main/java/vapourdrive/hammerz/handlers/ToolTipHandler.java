package vapourdrive.hammerz.handlers;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;

import org.lwjgl.input.Keyboard;

import vapourdrive.hammerz.config.ConfigOptions;
import vapourdrive.hammerz.items.hammer.HammerInfoHandler;
import vapourdrive.hammerz.items.hammer.HammerType;
import vapourdrive.hammerz.items.hammer.ItemHammer;

public class ToolTipHandler
{
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void itemToolTipAddition(ItemTooltipEvent event)
	{
		ItemStack itemstack = event.itemStack;

		if (ConfigOptions.AddOreDictEntries && itemstack != null)
		{
			if (itemstack.getItem() != null)
			{
				int[] names = OreDictionary.getOreIDs(itemstack);
				for(int i = 0; i < names.length; i++)
				{
					event.toolTip.add(OreDictionary.getOreName(names[i]));
				}
			}
		}
		if (ConfigOptions.AddToolInfo)
		{
			if (itemstack != null)
			{
				if (itemstack.getItem() != null)
				{
					Item item = itemstack.getItem();
					int[] names = OreDictionary.getOreIDs(itemstack);
					for(int i = 0; i < names.length; i++)
					{
						event.toolTip.add(OreDictionary.getOreName(names[i]));
					}
					if (item instanceof ItemTool)
					{
						if (Keyboard.isKeyDown(Keyboard.KEY_RCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_LCONTROL))
						{
							ToolMaterial material = ((ItemTool) item).getToolMaterial();
							event.toolTip.add("Material: " + material);
							event.toolTip.add("HarvestLevel: " + material.getHarvestLevel());
							event.toolTip.add("Durability: " + material.getMaxUses());
							event.toolTip.add("Efficiency: " + material.getEfficiencyOnProperMaterial());
							event.toolTip.add("Damage: " + material.getDamageVsEntity());
							event.toolTip.add("Enchantbility: " + material.getEnchantability());
						}
						else
						{
							event.toolTip.add(EnumChatFormatting.WHITE + "" + EnumChatFormatting.ITALIC + StatCollector.translateToLocal("phrase.hammerz.holdctrl"));
						}
					}
					else if(item instanceof ItemHammer)
					{
						if (Keyboard.isKeyDown(Keyboard.KEY_RCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_LCONTROL))
						{
							HammerType type = HammerInfoHandler.getHammerType(itemstack);
							if(type != null)
							{
							event.toolTip.add("Material: " + type.getName());
							event.toolTip.add("HarvestLevel: " + type.getHarvestLevel());
							event.toolTip.add("Durability: " + (int)(type.getDurability() * ConfigOptions.DurabilityMultiplier));
							event.toolTip.add("Efficiency: " + type.getEfficiency());
							event.toolTip.add("Damage: " + type.getDamage());
							event.toolTip.add("Enchantbility: " + type.getEnchantability());
							}
						}
						else
						{
							event.toolTip.add(EnumChatFormatting.WHITE + "" + EnumChatFormatting.ITALIC + StatCollector.translateToLocal("phrase.hammerz.holdctrl"));
						}
					}
				}
			}
		}

		return;
	}
}
