package vapourdrive.hammerz.handlers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.item.ItemStack;
import vapourdrive.hammerz.config.ConfigOptions;
import vapourdrive.hammerz.utils.RandomUtils;

public class UpgradeManager
{
	private static final UpgradeManager instance = new UpgradeManager();
	public List upgrades = new ArrayList();
	
	public UpgradeManager()
	{
		this.addUpgrade(1, 1, "EnderIO", "itemBasicCapacitor", 2, ConfigOptions.EIOToolTierBaseLevelCost, 1, "one", (float) ConfigOptions.powerDamgeAbsorptionRatios[0]);
		this.addUpgrade(1, 2, "EnderIO", "blockCapBank", 1, ConfigOptions.EIOToolTierOneLevelCost, 1, "two", (float) ConfigOptions.powerDamgeAbsorptionRatios[1]);
		this.addUpgrade(1, 3, "EnderIO", "blockCapBank", 2, ConfigOptions.EIOToolTierTwoLevelCost, 1, "three", (float) ConfigOptions.powerDamgeAbsorptionRatios[2]);
		this.addUpgrade(1, 4, "EnderIO", "blockCapBank", 3, ConfigOptions.EIOToolTierThreeLevelCost, 1, "four", (float) ConfigOptions.powerDamgeAbsorptionRatios[3]);
	}

	public void addUpgrade(int id, int level, String modID, String itemName, int meta, int xpCost, int matCost, String levelString, float chance)
	{
		ItemStack stack = RandomUtils.getItemStackFromString(modID, itemName, 1);
		if (stack != null)
		{
			Upgrade upgrade = new Upgrade(id, level, new ItemStack(stack.getItem(), 1, meta), xpCost, matCost, levelString, chance);
			upgrades.add(upgrade);
		}
	}

	public static UpgradeManager getInstance()
	{
		return instance;
	}

	public List getUpgradeList()
	{
		return this.upgrades;
	}
	
	public static float getChance(int level)
	{
		List<IUpgrade> upgrades = UpgradeManager.getInstance().getUpgradeList();
		Iterator<IUpgrade> iterator = upgrades.iterator();
		while (iterator.hasNext())
		{
			IUpgrade upgrade = iterator.next();
			if(level == upgrade.getLevel())
			{
				return upgrade.getEnergyChance();
			}
		}
		return 0.0f;
	}
	
	public static String getNumberString(int level)
	{
		List<IUpgrade> upgrades = UpgradeManager.getInstance().getUpgradeList();
		Iterator<IUpgrade> iterator = upgrades.iterator();
		while (iterator.hasNext())
		{
			IUpgrade upgrade = iterator.next();
			if(level == upgrade.getLevel())
			{
				return upgrade.getLevelString();
			}
		}
		return "";
	}
	
	public static ItemStack getItem(int level)
	{
		List<IUpgrade> upgrades = UpgradeManager.getInstance().getUpgradeList();
		Iterator<IUpgrade> iterator = upgrades.iterator();
		while (iterator.hasNext())
		{
			IUpgrade upgrade = iterator.next();
			if(level == upgrade.getLevel())
			{
				return upgrade.getItem();
			}
		}
		return null;
	}
	
	public static int getXPCost(int level)
	{
		List<IUpgrade> upgrades = UpgradeManager.getInstance().getUpgradeList();
		Iterator<IUpgrade> iterator = upgrades.iterator();
		while (iterator.hasNext())
		{
			IUpgrade upgrade = iterator.next();
			if(level == upgrade.getLevel())
			{
				return upgrade.getCost();
			}
		}
		return 0;
	}
}
