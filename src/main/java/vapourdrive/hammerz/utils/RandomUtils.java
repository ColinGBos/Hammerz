package vapourdrive.hammerz.utils;

import org.apache.logging.log4j.Level;

import vapourdrive.hammerz.Hammerz;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;

public class RandomUtils
{
	
	public static ItemStack getItemStackFromString(String ModId, String ItemStackName, int StackSize)
	{
		ItemStack stack = GameRegistry.findItemStack(ModId, ItemStackName, StackSize);
		if(stack == null)
		{
			Hammerz.log.log(Level.INFO, "Attempt to find: " + ModId + ", " + ItemStackName + " Failed");
		}
		return stack;
	}

	public static MovingObjectPosition raytraceFromEntity(World world, Entity player, boolean par3, double range)
	{
		float f = 1.0F;
		float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
		float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;
		double d0 = player.prevPosX + (player.posX - player.prevPosX) * (double) f;
		double d1 = player.prevPosY + (player.posY - player.prevPosY) * (double) f;

		if (!world.isRemote && player instanceof EntityPlayer)
		{
			d1 = d1 + 1.62D;
		}

		double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double) f;
		Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
		float f3 = MathHelper.cos(-f2 * 0.017453292F - (float) Math.PI);
		float f4 = MathHelper.sin(-f2 * 0.017453292F - (float) Math.PI);
		float f5 = -MathHelper.cos(-f1 * 0.017453292F);
		float f6 = MathHelper.sin(-f1 * 0.017453292F);
		float f7 = f4 * f5;
		float f8 = f3 * f5;
		double d3 = range;

		if (player instanceof EntityPlayerMP)
		{
			d3 = ((EntityPlayerMP) player).theItemInWorldManager.getBlockReachDistance();
		}

		Vec3 vec31 = vec3.addVector((double) f7 * d3, (double) f6 * d3, (double) f8 * d3);

		return world.func_147447_a(vec3, vec31, par3, !par3, par3);
	}

	public static boolean breakBlock(World world, Block block, int x, int y, int z, int side, EntityPlayer player)
	{
		if (world.isAirBlock(x, y, z))
		{
			return false;
		}

		EntityPlayerMP playerMP = null;

		if (player instanceof EntityPlayerMP)
		{
			playerMP = (EntityPlayerMP) player;
		}

		int meta = world.getBlockMetadata(x, y, z);

		ItemStack stack = player.getCurrentEquippedItem();
		if (stack == null || !ForgeHooks.canHarvestBlock(block, player, meta))
		{
			return false;
		}

		if (playerMP != null)
		{
			BreakEvent event = ForgeHooks.onBlockBreakEvent(world, playerMP.theItemInWorldManager.getGameType(), playerMP, x, y, z);

			int drop = event.getExpToDrop();
			block.dropXpOnBlockBreak(world, x, y, z, drop);

			if (event.isCanceled())
			{
				return false;
			}
		}

		if (player.capabilities.isCreativeMode)
		{
			if (!world.isRemote)
			{
				world.playAuxSFX(2001, x, y, z, Block.getIdFromBlock(block) | (world.getBlockMetadata(x, y, z) << 12));
				block.onBlockHarvested(world, x, y, z, meta, player);
			}

			if (block.removedByPlayer(world, player, x, y, z, false))
			{

				block.onBlockDestroyedByPlayer(world, x, y, z, meta);
			}

			if (!world.isRemote)
			{
				playerMP.playerNetServerHandler.sendPacket(new S23PacketBlockChange(x, y, z, world));
			}
			else
			{
				Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C07PacketPlayerDigging(2, x, y, z, side));
			}
			
			return true;
		}

		if (!world.isRemote)
		{
			block.onBlockHarvested(world, x, y, z, meta, player);

			if (block.removedByPlayer(world, player, x, y, z, true))
			{
				block.onBlockDestroyedByPlayer(world, x, y, z, meta);
				if (!player.capabilities.isCreativeMode)
				{
					block.harvestBlock(world, player, x, y, z, meta);
				}
				world.playAuxSFX(2001, x, y, z, Block.getIdFromBlock(block) | (world.getBlockMetadata(x, y, z) << 12));
			}

			playerMP.playerNetServerHandler.sendPacket(new S23PacketBlockChange(x, y, z, world));
		}

		else
		{
			if (block.removedByPlayer(world, player, x, y, z, true))
			{
				block.onBlockDestroyedByPlayer(world, x, y, z, meta);
			}

			Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C07PacketPlayerDigging(2, x, y, z, side));
		}
		return true;
	}
	
	
	public static NBTTagCompound getNBT(ItemStack stack)
	{
		if (stack.stackTagCompound == null) {
            stack.stackTagCompound = new NBTTagCompound();
        }
        return stack.stackTagCompound;
	}

}
