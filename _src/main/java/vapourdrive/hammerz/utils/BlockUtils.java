package vapourdrive.hammerz.utils;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketPlayerDigging.Action;
import net.minecraft.network.play.server.SPacketBlockChange;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import org.apache.logging.log4j.Level;
import vapourdrive.hammerz.Hammerz;

public class BlockUtils
{	
	public static boolean tryHarvestBlock(World world, IBlockState state, BlockPos pos, EnumFacing side, EntityPlayer player)
	{		
		Block block = state.getBlock();
		
		if (block.isAir(state, world, pos))
		{
			//Hammerz.log.log(Level.INFO, "Block @ " + pos.toString() + " is air");
			return false;
		}
		
		EntityPlayerMP playerMP = null;
		if (player instanceof EntityPlayerMP)
		{
			playerMP = (EntityPlayerMP) player;
		}

		ItemStack item = player.getHeldItemMainhand();
		
		if(item == null || item.getItem() == null)
		{
			return false;
		}
		
		if (!(item.getItem().getToolClasses(item).contains(block.getHarvestTool(state)) || item.getItem().getDestroySpeed(item, state) > 1.0f))
		{
			//Hammerz.log.log(Level.INFO, "Apparently wrong tool class");
			return false;
		}

		if (!ForgeHooks.canHarvestBlock(block, player, world, pos))
		{

			return false;
		}
		
		int event = 0;
		if (playerMP != null)
		{
			//Hammerz.log.log(Level.INFO, "player isn't null");

			event = ForgeHooks.onBlockBreakEvent(world, world.getWorldInfo().getGameType(), playerMP, pos);
			if (event == -1)
			{
				return false;
			}
		}
		
        world.playEvent(playerMP, 2001, pos, Block.getStateId(state));

		
		if (player.capabilities.isCreativeMode)
		{
			if (!world.isRemote)
			{
				block.onBlockHarvested(world, pos, state, player);
			}
			if (block.removedByPlayer(state, world, pos, player, false))
			{
				block.onBlockDestroyedByPlayer(world, pos, state);
			}
			if (!world.isRemote)
			{
				playerMP.connection.sendPacket(new SPacketBlockChange(world, pos));
			}
			else
			{
				Minecraft.getMinecraft().getConnection().sendPacket(new CPacketPlayerDigging(Action.STOP_DESTROY_BLOCK, pos, side));
			}
			return true;
		}
		if (!world.isRemote)
		{
			block.onBlockHarvested(world, pos, state, player);
			if (block.removedByPlayer(state, world, pos, player, true))
			{
				//Hammerz.log.log(Level.INFO, "Block @ " + pos.toString() + " was removed by the player");
				block.onBlockDestroyedByPlayer(world, pos, state);
				block.harvestBlock(world, player, pos, state, null, item);
				block.dropXpOnBlockBreak(world, pos, event);
			}
			playerMP.connection.sendPacket(new SPacketBlockChange(world, pos));
		}
		else
		{
			if (block.removedByPlayer(state, world, pos, player, true))
			{
				//Hammerz.log.log(Level.INFO, "Block @ " + pos.toString() + " removed by player client");

				block.onBlockDestroyedByPlayer(world, pos, state);
			}
			Minecraft.getMinecraft().getConnection().sendPacket(new CPacketPlayerDigging(Action.STOP_DESTROY_BLOCK, pos, side));

		}
		return true;
	}
}
