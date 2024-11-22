package vapourdrive.hammerz.items.hammer;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import vapourdrive.hammerz.config.ConfigOptions;
import vapourdrive.hammerz.handlers.UpgradeManager;
import vazkii.botania.api.mana.ManaItemHandler;

import java.util.Random;

import static vapourdrive.hammerz.items.hammer.EnergyHandler.getEmpoweredment;

public class DamageHandler
{
	public static final int MANA_PER_DAMAGE = 60;
	
	public static boolean handleDamage(boolean force, IBlockState state, ItemStack stack, EntityLivingBase entityLiving)
	{
		return requestDamage(force, state, stack, entityLiving, 1);
	}
	
	public static boolean requestDamage(boolean force, IBlockState state, ItemStack stack, EntityLivingBase entityLiving, int damage)
	{
		if(entityLiving instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) entityLiving;
			if(player.capabilities.isCreativeMode)
			{
				return true;
			}
		}
		if(!HammerInfoHandler.getTakesDamage(stack))
		{
			return true;
		}
		if(HammerInfoHandler.getUsesMana(stack))
		{
			return handleManaDamage(force, stack, damage, entityLiving);
		}
		else if(HammerInfoHandler.getUsesEnergy(stack))
		{
			return handleEnergyDamage(force, state, stack, damage, entityLiving);
		}
		return handleRegularDamage(force, stack, damage, entityLiving);
	}

	private static boolean handleRegularDamage(boolean force, ItemStack stack, int damage, EntityLivingBase entityLiving)
	{
		if (!force && (stack.getMaxDamage() - stack.getItemDamage()) < damage)
		{
			return false;
		}
		stack.damageItem(damage, entityLiving);
		/*if (stack.stackSize == 0)
		{
			return false;
		}*/
		return true;
	}

	private static boolean handleEnergyDamage(boolean force, IBlockState state, ItemStack stack, int damage, EntityLivingBase entityLiving)
	{
		if(HammerInfoHandler.isStackDarkSteelHammer(stack))
		{
			return handleDarkHammerDamage(force, state, stack, damage, entityLiving);
		}
		if (EnergyHandler.extractEnergy(stack, ConfigOptions.HammerEnergyUse * damage, true) >= ConfigOptions.HammerEnergyUse * damage)
		{
			EnergyHandler.extractEnergy(stack, ConfigOptions.HammerEnergyUse * damage, false);
		}
		else
		{
			return handleRegularDamage(force, stack, damage, entityLiving);
		}
		return true;
	}

	private static boolean handleDarkHammerDamage(boolean force, IBlockState state, ItemStack stack, int damage, EntityLivingBase entityLiving)
	{
		float chance = UpgradeManager.getChance(getEmpoweredment(stack));

		Random rand = new Random();
		if (rand.nextFloat() < chance)
		{
			if (state == Blocks.OBSIDIAN)
			{
				if (EnergyHandler.extractEnergy(stack, ConfigOptions.EIOToolObsidianEnergyUse, true) >= ConfigOptions.EIOToolObsidianEnergyUse)
				{
					EnergyHandler.extractEnergy(stack, ConfigOptions.EIOToolObsidianEnergyUse, false);
					return true;
				}
			}
			else
			{
				if (EnergyHandler.extractEnergy(stack, ConfigOptions.HammerEnergyUse, true) >= ConfigOptions.HammerEnergyUse)
				{
					EnergyHandler.extractEnergy(stack, ConfigOptions.HammerEnergyUse, false);
					return true;
				}
			}
		}
		return handleRegularDamage(force, stack, damage, entityLiving);
	}

	private static boolean handleManaDamage(boolean force, ItemStack stack, int damage, EntityLivingBase entityLiving)
	{
		if (!ManaItemHandler.requestManaExactForTool(stack, (EntityPlayer) entityLiving, MANA_PER_DAMAGE, true))
		{
			return handleRegularDamage(force, stack, damage, entityLiving);
		}
		return true;
	}

	public static boolean handleDamage(boolean force, ItemStack stack, EntityLivingBase target, EntityLivingBase attacker, int amount)
	{
		return requestDamage(force, null, stack, (EntityPlayer)attacker, amount);
	}

}
