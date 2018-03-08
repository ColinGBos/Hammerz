package vapourdrive.hammerz.handlers;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
import vapourdrive.hammerz.config.ConfigOptions;
import vapourdrive.hammerz.items.HZ_Items;
import vapourdrive.hammerz.items.hammer.HammerInfoHandler;
import vapourdrive.hammerz.items.hammer.HammerType;
import vapourdrive.hammerz.items.hammer.ItemHammer;
import vapourdrive.hammerz.utils.RandomUtils;

import java.util.Iterator;

public class ToolTipHandler
{
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void itemToolTipAddition(ItemTooltipEvent event)
	{
		ItemStack itemstack = event.getItemStack();

		if (ConfigOptions.AddOreDictEntries)
		{
			int[] names = OreDictionary.getOreIDs(itemstack);
			for (int name : names) {
				event.getToolTip().add(OreDictionary.getOreName(name));
			}
		}
		if (ConfigOptions.AddToolInfo)
		{
			Item item = itemstack.getItem();
			if(item instanceof ItemHammer)
			{
				if (Keyboard.isKeyDown(Keyboard.KEY_RCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_LCONTROL))
				{
					HammerType type = HammerInfoHandler.getHammerType(itemstack);
					if(type != null)
					{
						event.getToolTip().add("###### Hammer ######");
						event.getToolTip().add("Material: " + type.getName());
						event.getToolTip().add("HarvestLevel: " + type.getHarvestLevel());
						event.getToolTip().add("Durability: " + (int)(type.getDurability() * ConfigOptions.DurabilityMultiplier));
						event.getToolTip().add("Efficiency: " + type.getEfficiency());
						event.getToolTip().add("Damage: " + type.getDamage());
						event.getToolTip().add("Enchantbility: " + type.getEnchantability());
					}
				}
				else
				{
					event.getToolTip().add(TextFormatting.WHITE + "" + TextFormatting.ITALIC + I18n.format("phrase.hammerz.holdctrl"));
				}
			}
			else if (item instanceof ItemTool)
			{
				if (Keyboard.isKeyDown(Keyboard.KEY_RCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_LCONTROL))
				{
					//I'm not quite sure what this is for; this fix is bad but it should at least cause something relatively accurate to display
					//Also why the hell was getToolMaterial removed anyways?
					String name = ((ItemTool) item).getToolMaterialName();
					HammerType type = HammerInfoHandler.getHammerType(name);

					event.getToolTip().add("Material: " + name);
					event.getToolTip().add("HarvestLevel: " + type.getHarvestLevel());
					event.getToolTip().add("Durability: " + type.getDurability());
					event.getToolTip().add("Efficiency: " + type.getEfficiency());
					event.getToolTip().add("Damage: " + type.getDamage());
					event.getToolTip().add("Enchantbility: " + type.getEnchantability());
				}
				else
				{
					event.getToolTip().add(TextFormatting.WHITE + "" + TextFormatting.ITALIC + I18n.format("phrase.hammerz.holdctrl"));
				}
			}
		}
	}
}
