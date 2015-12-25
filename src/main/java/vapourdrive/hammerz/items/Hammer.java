package vapourdrive.hammerz.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.oredict.OreDictionary;

import org.apache.logging.log4j.Level;
import org.lwjgl.input.Keyboard;

import vapourdrive.hammerz.Hammerz;
import vapourdrive.hammerz.Reference;
import vapourdrive.hammerz.config.ConfigOptions;
import vapourdrive.hammerz.utils.RandomUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Hammer extends ItemPickaxe
{
	public String name;

	public Hammer(ToolMaterial material, String Name)
	{
		super(material);
		this.setUnlocalizedName(Name + "Hammer");
		this.setCreativeTab(Hammerz.HZTab);
		this.setMaxDamage((int) (this.getMaxDamage() * ConfigOptions.DurabilityMultiplier));
		name = Name;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register)
	{
		itemIcon = register.registerIcon(Reference.ResourcePath + name + "Hammer");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean useExtraInformation)
	{
		addPermanentInfo(stack, player, list, useExtraInformation);
		if (Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
		{
			addExpandedInfo(stack, player, list, useExtraInformation);
		}
		else
		{
			addStandardInfo(stack, player, list, useExtraInformation);
		}
	}

	public void addStandardInfo(ItemStack stack, EntityPlayer player, List list, boolean useExtraInformation)
	{
		list.add(EnumChatFormatting.WHITE + "" + EnumChatFormatting.ITALIC + StatCollector.translateToLocal("phrase.hammerz.holdshift"));
		if (ConfigOptions.AddDurabilityInfo)
		{
			list.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("hammerz.keyword.durability") + ": "
					+ (stack.getMaxDamage() - stack.getItemDamage()) + "/" + stack.getMaxDamage());
		}
	}

	public void addExpandedInfo(ItemStack stack, EntityPlayer player, List list, boolean useExtraInformation)
	{
		list.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("phrase.hammerz.hammerinfo1"));
		list.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("phrase.hammerz.hammerinfo2"));
	}

	public void addPermanentInfo(ItemStack stack, EntityPlayer player, List list, boolean useExtraInformation)
	{
	}

	@SideOnly(Side.CLIENT)
	public boolean isFull3D()
	{
		return true;
	}

	@Override
	public boolean onBlockStartBreak(ItemStack stack, int x, int y, int z, EntityPlayer player)
	{
		World world = player.worldObj;
		Block block = world.getBlock(x, y, z);
		if ((player.isSneaking() && ConfigOptions.CanShiftMine) || block.getBlockHardness(world, x, y, z) == 0)
		{
			return false;
		}

		MovingObjectPosition object = RandomUtils.raytraceFromEntity(world, player, false, 4.5D);

		if (object == null)
		{
			return super.onBlockDestroyed(stack, world, block, x, y, z, player);
		}

		int side = object.sideHit;
		int xmove = 0;
		int ymove = 0;
		int zmove = 0;

		if (side == 0 || side == 1)
		{
			xmove = 1;
			zmove = 1;
		}
		else
		{
			ymove = 1;
			if (side == 4 || side == 5)
			{
				zmove = 1;
			}
			else
			{
				xmove = 1;
			}
		}

		float strength = ForgeHooks.blockStrength(block, player, world, x, y, z);

		for (int i = -xmove; i <= xmove; i++)
		{
			for (int j = -ymove; j <= ymove; j++)
			{
				for (int k = -zmove; k <= zmove; k++)
				{
					if (i != x && j != y && k != z)
					{
						checkBlockBreak(world, player, x + i, y + j, z + k, stack, strength, block, side);
					}
				}
			}
		}

		return false;
	}

	public void checkBlockBreak(World world, EntityPlayer player, int x, int y, int z, ItemStack stack, float strength,
			Block originalBlock, int side)
	{
		Block breakBlock = world.getBlock(x, y, z);
		int metadata = world.getBlockMetadata(x, y, z);
		Material material = originalBlock.getMaterial();
		if (breakBlock.getMaterial() == material && ForgeHooks.canHarvestBlock(breakBlock, player, metadata)
				&& stack.getItem().func_150897_b(breakBlock))
		{
			float newStrength = ForgeHooks.blockStrength(breakBlock, player, world, x, y, z);
			if (newStrength > 0f && strength / newStrength <= 10f)
			{
				if ((double) breakBlock.getBlockHardness(world, x, y, z) != 0.0D)
				{
					if (handleDamage(breakBlock, stack, player))
					{
						RandomUtils.breakBlock(world, breakBlock, x, y, z, side, player);
					}
				}
				else
				{
					RandomUtils.breakBlock(world, breakBlock, x, y, z, side, player);
				}
			}
		}
	}

	public void log(World world, Object object)
	{
		if (world.isRemote)
		{
			Hammerz.log.log(Level.INFO, object);
		}
	}

	public boolean requestDamage(Block breakBlock, ItemStack stack, EntityPlayer player, int damage)
	{
		if((stack.getMaxDamage() - stack.getItemDamage()) < damage)
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
	
	public boolean handleDamage(Block breakBlock, ItemStack stack, EntityPlayer player)
	{
		return requestDamage(breakBlock, stack, player, 1);
	}

	@Override
	public float getDigSpeed(ItemStack stack, Block block, int meta)
	{
		return super.getDigSpeed(stack, block, meta) * ConfigOptions.EfficiencyMultiplier;
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float floatx, float floaty,
			float floatz)
	{
		if (player.getCurrentEquippedItem() != null)
		{
			if (player.getCurrentEquippedItem().getItem() instanceof Hammer)
			{
				if (!player.isSneaking())
				{
					ItemStack torchStack = new ItemStack(Blocks.torch);
					if (player.inventory.hasItemStack(torchStack))
					{
						torchStack.getItem().onItemUse(torchStack, player, world, x, y, z, side, floatx, floaty, floatz);
						player.inventory.consumeInventoryItem(torchStack.getItem());
						return true;
					}
				}
				else
				{
					return onItemShiftUse(stack, player, world, x, y, z, side, floatx, floaty, floatz);
				}
			}
		}
		return false;
	}

	public boolean onItemShiftUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float floatx,
			float floaty, float floatz)
	{
		return false;
	}

	@Override
	public boolean getIsRepairable(ItemStack stack, ItemStack stack2)
	{
		int[] names = OreDictionary.getOreIDs(stack2);
		for (int i = 0; i < names.length; i++)
		{
			if (("block" + name).contentEquals(OreDictionary.getOreName(names[i])))
			{
				return true;
			}
		}
		return false;
	}

	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list)
	{
		list.add(new ItemStack(item, 1, 0));
	}
}
