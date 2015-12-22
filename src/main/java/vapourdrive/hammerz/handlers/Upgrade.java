package vapourdrive.hammerz.handlers;

import net.minecraft.item.ItemStack;

public class Upgrade implements IUpgrade
{
	public final int Id;
	public final int level;
	public final ItemStack item;
	public final int cost;
	public final int matCost;
	public final String levelString;
	public final float energyChance;
	
	public Upgrade(int id, int Level, ItemStack Item, int Cost, int MatCost, String LevelString, float EnergyChance)
	{
		this.Id = id;
		this.level = Level;
		this.item = Item;
		this.cost = Cost;
		this.matCost = MatCost;
		this.levelString = LevelString;
		this.energyChance = EnergyChance;
	}
	
	@Override
	public int getID()
	{
		return this.Id;
	}
	@Override
	public int getLevel()
	{
		return this.level;
	}
	@Override
	public ItemStack getItem()
	{
		return this.item;
	}
	@Override
	public int getCost()
	{
		return this.cost;
	}
	@Override
	public int getMatCost()
	{
		return this.matCost;
	}
	@Override
	public String getLevelString()
	{
		return this.levelString;
	}
	@Override
	public float getEnergyChance()
	{
		return this.energyChance;
	}
}
