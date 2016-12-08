package vapourdrive.hammerz.utils;

import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.Level;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
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
		ResourceLocation resourceLocation = new ResourceLocation(ModId, ItemStackName);

		Item item = Item.REGISTRY.getObject(resourceLocation);
		if (item != null)
		{
			return new ItemStack(item, StackSize);
		}
		else
		{
			Block block = Block.REGISTRY.getObject(resourceLocation);
			return new ItemStack(block, StackSize);
		}
	}

	public static RayTraceResult raytraceFromEntity(World world, Entity player, boolean par3, double range)
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
		Vec3d vec3 = new Vec3d(d0, d1, d2);

		float f3 = MathHelper.cos(-f2 * 0.017453292F - (float) Math.PI);
		float f4 = MathHelper.sin(-f2 * 0.017453292F - (float) Math.PI);
		float f5 = -MathHelper.cos(-f1 * 0.017453292F);
		float f6 = MathHelper.sin(-f1 * 0.017453292F);
		float f7 = f4 * f5;
		float f8 = f3 * f5;
		double d3 = range;

		if (player instanceof EntityPlayerMP)
		{
			d3 = ((EntityPlayerMP) player).interactionManager.getBlockReachDistance();
		}

		Vec3d vec31 = vec3.addVector((double) f7 * d3, (double) f6 * d3, (double) f8 * d3);

		return world.rayTraceBlocks(vec3, vec31, par3, !par3, par3);
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
		return OreDictionary.doesOreNameExist(ore);
	}
	
	public static boolean consumeInventoryItem(InventoryPlayer inv, Item itemIn)
    {
        int i = getInventorySlotContainItem(inv, itemIn);

        if (i < 0)
        {
            return false;
        }
        else
        {
			int j = inv.mainInventory.get(i).func_190916_E() - 1;
            if (j <= 0)
            {
            	inv.mainInventory.set(i, null);
            }

            return true;
        }
    }
	
	private static int getInventorySlotContainItem(InventoryPlayer inv, Item itemIn)
    {
        for (int i = 0; i < inv.mainInventory.size(); ++i)
        {
            if (inv.mainInventory.get(i) != null && inv.mainInventory.get(i).getItem() == itemIn)
            {
                return i;
            }
        }

        return -1;
    }


}
