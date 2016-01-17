package vapourdrive.hammerz.handlers;

import java.util.Iterator;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
import vapourdrive.hammerz.items.HZ_Items;
import vapourdrive.hammerz.items.hammer.HammerInfoHandler;
import vapourdrive.hammerz.items.hammer.ItemHammer;
import vapourdrive.hammerz.utils.RandomUtils;
import cofh.api.energy.IEnergyContainerItem;

public class AnvilEvent
{
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void anvilEvent(AnvilUpdateEvent event)
	{
		ItemStack leftInput = event.left;
		ItemStack rightInput = event.right;

		if (leftInput == null || rightInput == null)
		{
			return;
		}

		int[] ids = OreDictionary.getOreIDs(rightInput);
		for (int i = 0; i < ids.length; i++)
		{
			if (OreDictionary.getOreName(ids[i]).equals("ingotDarkSteel") || OreDictionary.getOreName(ids[i]).equals("blockDarkSteel"))
			{
				handleRepair(leftInput, rightInput, event);
				return;
			}
		}
		handleUpgrade(leftInput, rightInput, event);
	}

	public void handleRepair(ItemStack leftInput, ItemStack rightInput, AnvilUpdateEvent event)
	{
		// TODO Auto-generated method stub

	}

	public void handleUpgrade(ItemStack leftInput, ItemStack rightInput, AnvilUpdateEvent event)
	{
		if (leftInput.getItem() == HZ_Items.ItemHammer && rightInput.stackSize == 1 && HammerInfoHandler.isStackDarkSteelHammer(leftInput))
		{
			ItemStack Output = leftInput.copy();

			List<IUpgrade> upgrades = UpgradeManager.getInstance().getUpgradeList();
			Iterator<IUpgrade> iterator = upgrades.iterator();

			while (iterator.hasNext())
			{
				IUpgrade upgrade = iterator.next();
				if (rightInput.getItem() == upgrade.getItem().getItem() && rightInput.getItemDamage() == upgrade.getItem().getItemDamage())
				{
					NBTTagCompound tagCompound = RandomUtils.getNBT(Output);
					if (tagCompound.getInteger(ItemHammer.Key_Empower) == upgrade.getLevel() - 1)
					{
						tagCompound.setInteger(ItemHammer.Key_Empower, upgrade.getLevel());

						Output.setTagCompound(tagCompound);
						if(rightInput.getItem() instanceof IEnergyContainerItem)
						{
							IEnergyContainerItem item = (IEnergyContainerItem) rightInput.getItem();
							int energy = item.getEnergyStored(rightInput);
							if(Output.getItem() instanceof ItemHammer)
							{
								item = (ItemHammer) Output.getItem();
								NBTTagCompound finalCompound = RandomUtils.getNBT(Output);
								int hammerEnergy = finalCompound.getInteger(ItemHammer.Tag_DarkSteelEnergy);
								if(hammerEnergy + energy > item.getMaxEnergyStored(Output))
								{
									finalCompound.setInteger(ItemHammer.Tag_DarkSteelEnergy, item.getMaxEnergyStored(Output));
								}
								else
								{
									finalCompound.setInteger(ItemHammer.Tag_DarkSteelEnergy, hammerEnergy + energy);
								}
							}
						}
					}
					event.cost = upgrade.getCost();
					event.materialCost = 1;
					event.output = Output;

					return;
				}
			}
		}

	}
}
