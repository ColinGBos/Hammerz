package vapourdrive.hammerz.items.hammer;

import java.util.Iterator;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import vapourdrive.hammerz.config.ConfigOptions;
import vapourdrive.hammerz.items.HZ_Items;
import vapourdrive.hammerz.utils.RandomUtils;

public class HammerInfoHandler
{
	public static int getHarvestLevel(ItemStack stack, String toolClass)
	{
		return getHammerType(stack).getHarvestLevel();
	}

	public static float getEfficiency(ItemStack stack)
	{
		return getHammerType(stack).getEfficiency();
	}

	public static int getItemEnchantability(ItemStack stack)
	{
		return getHammerType(stack).getEnchantability();
	}

	public static int getMaxDamage(ItemStack stack)
	{
		if (getHammerType(stack) == null)
		{
			return 100;
		}
		return (int) (getHammerType(stack).getDurability() * ConfigOptions.DurabilityMultiplier);
	}

	public static float getStrengthVsBlock(ItemStack stack, Block block)
	{
		return block.getMaterial() != Material.iron && block.getMaterial() != Material.anvil && block.getMaterial() != Material.rock ? 1.0F
				: getEfficiency(stack);
	}

	public static boolean getUsesMana(ItemStack stack)
	{
		return getHammerType(stack).getUsesMana();
	}

	public static boolean getUsesEnergy(ItemStack stack)
	{
		return getHammerType(stack).getUsesEnergy();
	}

	public static boolean canHarvestBlock(Block block, ItemStack stack)
	{
		return block == Blocks.obsidian ? getHarvestLevel(stack, "pickaxe") == 3
				: (block != Blocks.diamond_block && block != Blocks.diamond_ore
						? (block != Blocks.emerald_ore && block != Blocks.emerald_block
								? (block != Blocks.gold_block && block != Blocks.gold_ore
										? (block != Blocks.iron_block && block != Blocks.iron_ore
												? (block != Blocks.lapis_block && block != Blocks.lapis_ore
														? (block != Blocks.redstone_ore && block != Blocks.lit_redstone_ore
																? (block.getMaterial() == Material.rock ? true
																		: (block.getMaterial() == Material.iron ? true
																				: block.getMaterial() == Material.anvil))
																: getHarvestLevel(stack, "pickaxe") >= 2)
														: getHarvestLevel(stack, "pickaxe") >= 1)
												: getHarvestLevel(stack, "pickaxe") >= 1)
										: getHarvestLevel(stack, "pickaxe") >= 2)
								: getHarvestLevel(stack, "pickaxe") >= 2)
						: getHarvestLevel(stack, "pickaxe") >= 2);
	}

	public static HammerType getHammerType(ItemStack stack)
	{
		if (stack.getItem() != null && stack.getItem() == HZ_Items.ItemHammer)
		{
			NBTTagCompound tagCompound = RandomUtils.getNBT(stack);
			String hammerTypeName = tagCompound.getString(ItemHammer.HammerKey);
			return getHammerType(hammerTypeName);
		}
		return null;
	}

	public static HammerType getHammerType(String hammerTypeName)
	{
		if (hammerTypeName != null)
		{
			Iterator<HammerType> iterator = HZ_Items.hammerTypes.iterator();
			while (iterator.hasNext())
			{
				HammerType type = (HammerType) iterator.next();
				if (type != null)
				{
					if (type.getName().toLowerCase().contentEquals(hammerTypeName.toLowerCase()))
					{
						return type;
					}
				}
			}
		}
		return null;
	}

	public static boolean getTakesDamage(ItemStack stack)
	{
		return getHammerType(stack).getTakesDamage();
	}

	public static boolean isStackElementalHammer(ItemStack stack)
	{
		return isStackHammerType(stack, "thaumium_elemental");
	}

	public static boolean isStackElvenElementalHammer(ItemStack stack)
	{
		return isStackHammerType(stack, "b_elemental");
	}

	public static boolean isStackDarkSteelHammer(ItemStack stack)
	{
		return isStackHammerType(stack, "darksteel");
	}

	public static boolean isStackVoidHammer(ItemStack stack)
	{
		return isStackHammerType(stack, "void");
	}
	
	public static boolean isStackHammerType(ItemStack stack, String name)
	{
		if (isStackHammer(stack))
		{
			return getHammerType(stack).getName().toLowerCase().contentEquals(name);
		}
		return false;
	}

	public static boolean isStackHammer(ItemStack stack)
	{
		if (stack.getItem() != null && stack.getItem() == HZ_Items.ItemHammer)
		{
			HammerType type = getHammerType(stack);
			if (type != null)
			{
				return true;
			}
		}
		return false;
	}

	public static boolean getCanRepair(ItemStack stack)
	{
		return getHammerType(stack).getCanRepair();
	}

	public static double getAttackValue(ItemStack stack)
	{
		return getHammerType(stack).getDamage();
	}

	public static EnumRarity getEnchantedRarity(ItemStack stack)
	{
		EnumRarity rarity = getHammerType(stack).getEnumRarity();
		switch (rarity)
		{
			case COMMON:
				return EnumRarity.RARE;
			case UNCOMMON:
				return EnumRarity.RARE;
			case RARE:
				return EnumRarity.EPIC;
			case EPIC:
				return EnumRarity.EPIC;
			default:
				return EnumRarity.UNCOMMON;
		}
	}

	public static EnumRarity getRarity(ItemStack stack)
	{
		return getHammerType(stack).getEnumRarity();
	}

	public static int getWarp(ItemStack stack)
	{
		if (isStackVoidHammer(stack))
		{
			return 1;
		}
		return 0;
	}
}
