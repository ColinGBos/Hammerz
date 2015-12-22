package vapourdrive.hammerz.handlers;

import net.minecraft.item.ItemStack;

public interface IUpgrade
{
	int getLevel();

	ItemStack getItem();

	int getID();

	int getCost();

	int getMatCost();

	String getLevelString();

	float getEnergyChance();
}
