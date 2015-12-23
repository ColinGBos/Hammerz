package vapourdrive.hammerz.handlers;

import org.lwjgl.input.Keyboard;

import vapourdrive.hammerz.config.ConfigOptions;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ToolTipHandler
{
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void itemToolTipAddition(ItemTooltipEvent event)
	{
		if (ConfigOptions.AddToolInfo)
		{
			ItemStack itemstack = event.itemStack;

			if (itemstack != null)
			{
				if (itemstack.getItem() != null)
				{
					Item item = itemstack.getItem();
					if (item instanceof ItemTool)
					{
						if (Keyboard.isKeyDown(Keyboard.KEY_RCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_LCONTROL))
						{
							ToolMaterial material = ((ItemTool) item).func_150913_i();
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
				}
			}
		}

		return;
	}
}
