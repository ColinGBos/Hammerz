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
	
	public static boolean handleDamage(Block breakBlock, ItemStack stack, EntityPlayer player)
	{
		return requestDamage(breakBlock, stack, player, 1);
	}
	
	public static boolean requestDamage(Block breakBlock, ItemStack stack, EntityPlayer player, int damage)
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
			return handleManaDamage(stack, damage, player);
		}
		else if(HammerInfoHandler.getUsesEnergy(stack))
		{
			return handleEnergyDamage(stack, damage, player);
		}
		return handleRegularDamage(stack, damage, player);
	}

	private static boolean handleRegularDamage(ItemStack stack, int damage, EntityPlayer player)
	{
		if ((stack.getMaxDamage() - stack.getItemDamage()) < damage)
		{
			return false;
		}
		stack.damageItem(damage, player);
		if (stack.stackSize == 0)
		{
			return false;
		}
		return true;
	}

	private static boolean handleEnergyDamage(ItemStack stack, int damage, EntityPlayer player)
	{
		if (EnergyHandler.extractEnergy(stack, ConfigOptions.HammerEnergyUse * damage, true) >= ConfigOptions.HammerEnergyUse * damage)
		{
			EnergyHandler.extractEnergy(stack, ConfigOptions.HammerEnergyUse * damage, false);
		}
		else
		{
			return handleRegularDamage(stack, damage, player);
		}
		return true;
	}

	private static boolean handleManaDamage(ItemStack stack, int damage, EntityPlayer player)
	{
		if (!ManaItemHandler.requestManaExactForTool(stack, (EntityPlayer) player, MANA_PER_DAMAGE, true))
		{
			return handleRegularDamage(stack, damage, player);
		}
		return true;
	}

	public static boolean handleDamage(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker, int amount)
	{
		return requestDamage(null, stack, (EntityPlayer)attacker, amount);
	}

}
