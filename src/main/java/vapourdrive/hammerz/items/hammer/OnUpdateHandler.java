package vapourdrive.hammerz.items.hammer;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import vazkii.botania.api.mana.ManaItemHandler;

import java.util.Random;

public class OnUpdateHandler
{
	public static Random random = new Random();

	public static void onUpdate(ItemStack stack, World world, Entity player, int par4, boolean par5)
	{
		if (HammerInfoHandler.getUsesMana(stack))
		{
			handleManaRepair(stack, world, player);
		}
		if (HammerInfoHandler.isStackVoidHammer(stack))
		{
			handleVoidRepair(stack, world, player);
		}
		if (HammerInfoHandler.isStackLivingHammer(stack))
		{
			handleLivingRepair(stack, world, player);
		}
	}
	
	public static void handleVoidRepair(ItemStack stack, World world, Entity player)
	{
		if (stack.isItemDamaged() && player != null && player.ticksExisted % 20 == 0 && player instanceof EntityLivingBase)
		{
			stack.damageItem(-1, (EntityLivingBase) player);
		}
	}

	public static void handleManaRepair(ItemStack stack, World world, Entity player)
	{
		if (!world.isRemote && player instanceof EntityPlayer && stack.getItemDamage() > 0
				&& ManaItemHandler.requestManaExactForTool(stack, (EntityPlayer) player, DamageHandler.MANA_PER_DAMAGE * 2, true))
		{
			stack.setItemDamage(stack.getItemDamage() - 1);
		}
	}

	public static void handleLivingRepair(ItemStack stack, World world, Entity player)
	{
		if (!world.isRemote && player instanceof EntityPlayer && stack.getItemDamage() > 0 && random.nextInt(80) == 0)
		{
			stack.setItemDamage(stack.getItemDamage() - 1);
		}
	}
}
