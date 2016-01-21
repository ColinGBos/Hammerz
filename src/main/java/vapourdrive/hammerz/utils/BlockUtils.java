package vapourdrive.hammerz.utils;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C07PacketPlayerDigging.Action;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class BlockUtils
{	
	public static boolean tryHarvestBlock(World world, IBlockState state, BlockPos pos, EnumFacing side, EntityPlayer player)
	{		
		Block block = state.getBlock();
		
		if (block.isAir(world, pos))
		{
			return false;
		}
		
		EntityPlayerMP playerMP = null;
		if (player instanceof EntityPlayerMP)
		{
			playerMP = (EntityPlayerMP) player;
		}

		ItemStack item = player.getCurrentEquippedItem();
		
		if (!(item.getItem().getToolClasses(item).contains(block.getHarvestTool(state)) || item.getItem().getStrVsBlock(item, block) > 1.0f))
		{
			return false;
		}

		if (!ForgeHooks.canHarvestBlock(block, player, world, pos))
		{
			return false;
		}
		
		int event = 0;
		if (playerMP != null)
		{
			event = ForgeHooks.onBlockBreakEvent(world, playerMP.theItemInWorldManager.getGameType(), playerMP, pos);
			if (event == -1)
			{
				return false;
			}
		}
		
		world.playAuxSFXAtEntity(playerMP, 2001, pos, Block.getStateId(state));
		
		if (player.capabilities.isCreativeMode)
		{
			if (!world.isRemote)
			{
				block.onBlockHarvested(world, pos, state, player);
			}

			if (block.removedByPlayer(world, pos, player, false))
			{
				block.onBlockDestroyedByPlayer(world, pos, state);
			}
			if (!world.isRemote)
			{
				playerMP.playerNetServerHandler.sendPacket(new S23PacketBlockChange(world, pos));
			}
			else
			{
				Minecraft.getMinecraft().getNetHandler()
						.addToSendQueue(new C07PacketPlayerDigging(Action.STOP_DESTROY_BLOCK, pos, side));
			}
			return true;
		}
		if (!world.isRemote)
		{
			block.onBlockHarvested(world, pos, state, player);
			if (block.removedByPlayer(world, pos, player, true))
			{
				block.onBlockDestroyedByPlayer(world, pos, state);
				block.harvestBlock(world, player, pos, state, null);
				if (event != -1)
				{
					block.dropXpOnBlockBreak(world, pos, event);
				}
			}
			playerMP.playerNetServerHandler.sendPacket(new S23PacketBlockChange(world, pos));
		}
		else
		{
			if (block.removedByPlayer(world, pos, player, true))
			{
				block.onBlockDestroyedByPlayer(world, pos, state);
			}
			Minecraft.getMinecraft().getNetHandler()
					.addToSendQueue(new C07PacketPlayerDigging(Action.STOP_DESTROY_BLOCK, pos, side));
		}
		return true;
	}
}
