package vapourdrive.hammerz.utils;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C07PacketPlayerDigging.Action;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import org.apache.logging.log4j.Level;

import vapourdrive.hammerz.Hammerz;
import vapourdrive.hammerz.items.HZ_Items;
import vapourdrive.hammerz.items.hammer.HammerInfoHandler;
import vapourdrive.hammerz.items.hammer.HammerType;
import vapourdrive.hammerz.items.hammer.ItemHammer;

public class RandomUtils
{
	public static ItemStack getHammer(String material)
	{
		ItemStack hammer = new ItemStack(HZ_Items.ItemHammer);
		HammerType type = HammerInfoHandler.getHammerType(material);
		NBTTagCompound tagCompound = getNBT(hammer);
		tagCompound.setString(ItemHammer.HammerKey, type.getName());
		return hammer.copy();
	}
	
	public static ItemStack getItemStackFromString(String ModId, String ItemStackName, int StackSize)
	{
		Item item = GameRegistry.findItem(ModId, ItemStackName);
		if (item != null)
		{
			ItemStack stack = new ItemStack(item, StackSize);
			if (stack != null)
			{
				return stack;
			}
		}
		else
		{
			Block b = GameRegistry.findBlock(ModId, ItemStackName);
			if (b != null)
			{
				ItemStack stack = new ItemStack(b, StackSize);
				if (stack != null)
				{
					return stack;
				}
			}
		}
		Hammerz.log.log(Level.INFO, "Attempt to find: " + ModId + ", " + ItemStackName + " Failed");
		return null;

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
		// Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
		Vec3 vec3 = new Vec3(d0, d1, d2);

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

		return world.rayTraceBlocks(vec3, vec31, par3, !par3, par3);
	}

	public static boolean breakBlock(World world, IBlockState state, BlockPos pos, EnumFacing side, EntityPlayer player)
	{
		Block block = state.getBlock();
		if (world.isAirBlock(pos))
		{
			return false;
		}
		EntityPlayerMP playerMP = null;
		if (player instanceof EntityPlayerMP)
		{
			playerMP = (EntityPlayerMP) player;
		}
		// only effective materials
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
		if (player.capabilities.isCreativeMode)
		{
			if (!world.isRemote)
			{
				block.onBlockHarvested(world, pos, state, player);
			}
			else
			{
				world.playAuxSFX(2001, pos, Block.getStateId(state));
			}

			if (block.removedByPlayer(world, pos, player, false))
			{
				world.playAuxSFX(2001, pos, Block.getStateId(state));
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
				world.playAuxSFX(2001, pos, Block.getStateId(state));
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

	public static NBTTagCompound getNBT(ItemStack stack)
	{
		if (stack.getTagCompound() == null)
		{
			stack.setTagCompound(new NBTTagCompound());
		}
		return stack.getTagCompound();
	}

	public static boolean doesOreNameExist(String ore)
	{
		String[] registeredOresStrings = OreDictionary.getOreNames();
		for (int i = 0; i < registeredOresStrings.length; i++)
		{
			if (registeredOresStrings[i].contentEquals(ore))
			{
				return true;
			}
		}
		return false;
	}

}
