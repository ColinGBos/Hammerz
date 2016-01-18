package vapourdrive.hammerz.items.hammer;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import vapourdrive.hammerz.config.ConfigOptions;
import vazkii.botania.api.mana.ManaItemHandler;

public class DamageHandler
{
	public static final int MANA_PER_DAMAGE = 60;
	
	public static boolean handleDamage(boolean force, Block breakBlock, ItemStack stack, EntityPlayer player)
	{
		return requestDamage(force, breakBlock, stack, player, 1);
	}
	
	public static boolean requestDamage(boolean force, Block breakBlock, ItemStack stack, EntityPlayer player, int damage)
	{
		if(player.capabilities.isCreativeMode)
		{
			return true;
		}
		if(!HammerInfoHandler.getTakesDamage(stack))
		{
			return true;
		}
		if(HammerInfoHandler.getUsesMana(stack))
		{
			return handleManaDamage(force, stack, damage, player);
		}
		else if(HammerInfoHandler.getUsesEnergy(stack))
		{
			return handleEnergyDamage(force, stack, damage, player);
		}
		return handleRegularDamage(force, stack, damage, player);
	}

	private static boolean handleRegularDamage(boolean force, ItemStack stack, int damage, EntityPlayer player)
	{
		if (!force && (stack.getMaxDamage() - stack.getItemDamage()) < damage)
		{
			return false;
		}
		stack.damageItem(damage, player);
		/*if (stack.stackSize == 0)
		{
			return false;
		}*/
		return true;
	}

	private static boolean handleEnergyDamage(boolean force, ItemStack stack, int damage, EntityPlayer player)
	{
		if (EnergyHandler.extractEnergy(stack, ConfigOptions.HammerEnergyUse * damage, true) >= ConfigOptions.HammerEnergyUse * damage)
		{
			EnergyHandler.extractEnergy(stack, ConfigOptions.HammerEnergyUse * damage, false);
		}
		else
		{
			return handleRegularDamage(force, stack, damage, player);
		}
		return true;
	}

	private static boolean handleManaDamage(boolean force, ItemStack stack, int damage, EntityPlayer player)
	{
		if (!ManaItemHandler.requestManaExactForTool(stack, (EntityPlayer) player, MANA_PER_DAMAGE, true))
		{
			return handleRegularDamage(force, stack, damage, player);
		}
		return true;
	}

	public static boolean handleDamage(boolean force, ItemStack stack, EntityLivingBase target, EntityLivingBase attacker, int amount)
	{
		return requestDamage(force, null, stack, (EntityPlayer)attacker, amount);
	}

}
