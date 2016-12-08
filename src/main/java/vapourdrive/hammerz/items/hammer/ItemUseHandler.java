package vapourdrive.hammerz.items.hammer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import vapourdrive.hammerz.utils.RandomUtils;

public class ItemUseHandler
{

	public static EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float floatx,
			float floaty, float floatz)
	{
		return handleRegularUse(player, world, pos, hand, side, floatx, floaty, floatz);
	}

	public static EnumActionResult onItemShiftUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float floatx,
			float floaty, float floatz)
	{
		if (HammerInfoHandler.isStackElementalHammer(player.getHeldItemMainhand()))
		{
			return handleElementalUse(player, world, pos, hand, side, floatx, floaty, floatz);
		}
		return EnumActionResult.PASS;
	}

	private static EnumActionResult handleElementalUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side,
			float floatx, float floaty, float floatz)
	{
		ItemStack ThaumPick = RandomUtils.getItemStackFromString("Thaumcraft", "elemental_pick", 1);
		if (ThaumPick != null && ThaumPick.getItem() != null)
		{
			if (DamageHandler.requestDamage(false, null, player.getHeldItemMainhand(), player, 10))
			{
				ThaumPick.getItem().onItemUse(player, world, pos, hand, side, floatx, floaty, floatz);
				return EnumActionResult.SUCCESS;
			}
		}
		return EnumActionResult.PASS;
	}

	private static EnumActionResult handleRegularUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float floatx,
			float floaty, float floatz)
	{
		if (player.getHeldItemMainhand() != null)
		{
			if (player.getHeldItemMainhand().getItem() instanceof ItemHammer)
			{
				if (!player.isSneaking())
				{
					ItemStack torchStack = new ItemStack(Blocks.TORCH);
					if (player.inventory.hasItemStack(torchStack))
					{
						if (torchStack.getItem().onItemUse(player, world, pos, hand, side, floatx, floaty, floatz) == EnumActionResult.SUCCESS)
						{
							RandomUtils.consumeInventoryItem(player.inventory, torchStack.getItem());
							return EnumActionResult.SUCCESS;
						}
					}
				}
				else
				{
					return onItemShiftUse(player, world, pos, hand, side, floatx, floaty, floatz);
				}
			}
		}
		return EnumActionResult.PASS;
	}

}
