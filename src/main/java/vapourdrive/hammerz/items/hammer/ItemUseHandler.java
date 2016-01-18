package vapourdrive.hammerz.items.hammer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import vapourdrive.hammerz.utils.RandomUtils;

public class ItemUseHandler
{

	public static boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float floatx,
			float floaty, float floatz)
	{
		return handleRegularUse(stack, player, world, pos, side, floatx, floaty, floatz);
	}

	public static boolean onItemShiftUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float floatx,
			float floaty, float floatz)
	{
		if (HammerInfoHandler.isStackElementalHammer(stack))
		{
			return handleElementalUse(stack, player, world, pos, side, floatx, floaty, floatz);
		}
		return false;
	}

	private static boolean handleElementalUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side,
			float floatx, float floaty, float floatz)
	{
		ItemStack ThaumPick = RandomUtils.getItemStackFromString("Thaumcraft", "elemental_pick", 1);
		if (ThaumPick != null && ThaumPick.getItem() != null)
		{
			if (DamageHandler.requestDamage(false, null, stack, player, 10))
			{
				ThaumPick.getItem().onItemUse(ThaumPick, player, world, pos, side, floatx, floaty, floatz);
				return true;
			}
		}
		return false;
	}

	private static boolean handleRegularUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float floatx,
			float floaty, float floatz)
	{
		if (player.getCurrentEquippedItem() != null)
		{
			if (player.getCurrentEquippedItem().getItem() instanceof ItemHammer)
			{
				if (!player.isSneaking())
				{
					ItemStack torchStack = new ItemStack(Blocks.torch);
					if (player.inventory.hasItemStack(torchStack))
					{
						if (torchStack.getItem().onItemUse(torchStack, player, world, pos, side, floatx, floaty, floatz))
						{
							player.inventory.consumeInventoryItem(torchStack.getItem());
							return true;
						}
					}
				}
				else
				{
					return onItemShiftUse(stack, player, world, pos, side, floatx, floaty, floatz);
				}
			}
		}
		return false;
	}

}
