package vapourdrive.hammerz.handlers;

import java.util.Iterator;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.oredict.OreDictionary;
import vapourdrive.hammerz.items.DarkHammer;
import vapourdrive.hammerz.items.HZ_Items;
import vapourdrive.hammerz.utils.RandomUtils;
import cofh.api.energy.IEnergyContainerItem;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class AnvilEvent
{
	public static final String Key_Empower = "hammerz.upgrade.empowerment";

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
		if (leftInput.getItem() == HZ_Items.DarkSteelHammer && rightInput.stackSize == 1)
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
					if (tagCompound.getInteger(Key_Empower) == upgrade.getLevel() - 1)
					{
						tagCompound.setInteger(Key_Empower, upgrade.getLevel());

						Output.setTagCompound(tagCompound);
						if(rightInput.getItem() instanceof IEnergyContainerItem)
						{
							IEnergyContainerItem item = (IEnergyContainerItem) rightInput.getItem();
							int energy = item.getEnergyStored(rightInput);
							if(Output.getItem() instanceof DarkHammer)
							{
								item = (DarkHammer) Output.getItem();
								NBTTagCompound finalCompound = RandomUtils.getNBT(Output);
								int hammerEnergy = finalCompound.getInteger(DarkHammer.Tag_Energy);
								if(hammerEnergy + energy > item.getMaxEnergyStored(Output))
								{
									finalCompound.setInteger(DarkHammer.Tag_Energy, item.getMaxEnergyStored(Output));
								}
								else
								{
									finalCompound.setInteger(DarkHammer.Tag_Energy, hammerEnergy + energy);
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
