package vapourdrive.hammerz.items.hammer;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.apache.logging.log4j.Level;

import thaumcraft.api.items.IWarpingGear;
import vapourdrive.hammerz.Hammerz;
import vapourdrive.hammerz.config.ConfigOptions;
import vapourdrive.hammerz.items.HZ_Items;
import vapourdrive.hammerz.proxies.CommonProxy;
import vapourdrive.hammerz.utils.BlockUtils;
import vapourdrive.hammerz.utils.RandomUtils;
import vazkii.botania.api.mana.IManaUsingItem;
import cofh.api.energy.IEnergyContainerItem;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

@Optional.InterfaceList(
{
		@Optional.Interface(modid = "Botania", iface = "vazkii.botania.api.mana.IManaUsingItem", striprefs = true),
		@Optional.Interface(modid = "CoFHCore", iface = "cofh.api.energy.IEnergyContainerItem", striprefs = true),
		@Optional.Interface(modid = "Thaumcraft", iface = "thaumcraft.api.items.IRepairableExtended", striprefs = true),
		@Optional.Interface(modid = "Thaumcraft", iface = "thaumcraft.api.items.IWarpingGear", striprefs = true),
		@Optional.Interface(modid = "Botania", iface = "vazkii.botania.api.mana.ManaItemHandler", striprefs = true)
})
public class ItemHammer extends ItemPickaxe implements IEnergyContainerItem, IManaUsingItem, /*IRepairableExtended,*/ IWarpingGear
{
	public static final String HammerKey = "Hammerz.HammerType";
	public static final String Tag_DarkSteelEnergy = "Hammerz.hammer.darkhammer.energy";
	public static final String Tag_EnergyStored = "Hammerz.hammer.energy";
	public static final String Key_Empower = "Hammerz.upgrade.empowerment";

	public ItemHammer()
	{
		super(ToolMaterial.IRON);
		this.setCreativeTab(CommonProxy.HZTab);
		this.setUnlocalizedName("Hammer");
		this.hasSubtypes = true;
		GameRegistry.registerItem(this, this.getUnlocalizedName());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean useExtraInformation)
	{
		super.addInformation(stack, player, list, useExtraInformation);
		AddInformationHelper.addInformation(stack, player, list, useExtraInformation);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFull3D()
	{
		return true;
	}

	@Override
	public float getDigSpeed(ItemStack stack, IBlockState state)
	{
		if (state.getBlock().isToolEffective("pickaxe", state))
		{
				return HammerInfoHandler.getEfficiency((stack)) * ConfigOptions.EfficiencyMultiplier;
		}
        return (super.getDigSpeed(stack, state) * ConfigOptions.EfficiencyMultiplier);
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float floatx, float floaty,
			float floatz)
	{
		if (!player.isSneaking())
		{
			return ItemUseHandler.onItemUse(stack, player, world, pos, side, floatx, floaty, floatz);
		}
		else
		{
			return ItemUseHandler.onItemShiftUse(stack, player, world, pos, side, floatx, floaty, floatz);
		}
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity player, int par4, boolean par5)
	{
		super.onUpdate(stack, world, player, par4, par5);
		OnUpdateHandler.onUpdate(stack, world, player, par4, par5);
	}
	
	@Override
    public boolean isRepairable()
    {
        return false;
    }
	
	@Override
	public boolean getIsRepairable(ItemStack stack, ItemStack stack2)
	{
		return RepairHandler.getIsRepairable(stack, stack2);
	}

	@Override
	public int getHarvestLevel(ItemStack stack, String toolClass)
	{
		return HammerInfoHandler.getHarvestLevel(stack, toolClass);
	}
	
	@Override
	public boolean isItemTool(ItemStack stack)
    {
        return true;
    }
	
	@Override
	public boolean isDamageable()
    {
        return true;
    }

	@Override
	public int getItemEnchantability(ItemStack stack)
	{
		Hammerz.log.log(Level.INFO, "Enchantability: " + HammerInfoHandler.getItemEnchantability(stack));
		return HammerInfoHandler.getItemEnchantability(stack);
	}

	@Override
	public int getMaxDamage(ItemStack stack)
	{
		return HammerInfoHandler.getMaxDamage(stack);
	}

	@Override
	public Set<String> getToolClasses(ItemStack stack)
	{
		Set<String> ToolClass = Sets.newHashSet(new String[]
		{
			"pickaxe"
		});
		return ToolClass;
	}

	@Override
	public float getStrVsBlock(ItemStack stack, Block block)
	{
		return HammerInfoHandler.getStrengthVsBlock(stack, block);
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
	{
		return DamageHandler.handleDamage(true, stack, target, attacker, 4);
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, Block block, BlockPos pos, EntityLivingBase player)
	{
		DamageHandler.handleDamage(true, block, stack, (EntityPlayer) player);
		return true;
	}

	@Override
	public boolean onBlockStartBreak(ItemStack stack, BlockPos pos, EntityPlayer player)
	{
		World world = player.worldObj;
		IBlockState state = world.getBlockState(pos);
		Block block = state.getBlock();
		if ((player.isSneaking() && ConfigOptions.CanShiftMine) || block.getBlockHardness(world, pos) == 0)
		{
			return false;
		}

		MovingObjectPosition object = RandomUtils.raytraceFromEntity(world, player, false, 4.5D);

		if (object == null)
		{
			return super.onBlockDestroyed(stack, world, block, pos, player);
		}

		EnumFacing side = object.sideHit;
		int xmove = 0;
		int ymove = 0;
		int zmove = 0;

		if (side == EnumFacing.UP || side == EnumFacing.DOWN)
		{
			xmove = 1;
			zmove = 1;
		}
		else
		{
			ymove = 1;
			if (side == EnumFacing.WEST || side == EnumFacing.EAST)
			{
				zmove = 1;
			}
			else
			{
				xmove = 1;
			}
		}

		float strength = ForgeHooks.blockStrength(state, player, world, pos);

		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		for (int i = -xmove; i <= xmove; i++)
		{
			for (int j = -ymove; j <= ymove; j++)
			{
				for (int k = -zmove; k <= zmove; k++)
				{
					if ((x + i) != x || (y + j) != y || (z + k) != z)
					{
						checkBlockBreak(world, player, new BlockPos(x + i, y + j, z + k), stack, strength, block, side);
					}
				}
			}
		}
		return false;
	}

	public void checkBlockBreak(World world, EntityPlayer player, BlockPos pos, ItemStack stack, float strength, Block originalBlock,
			EnumFacing side)
	{
		IBlockState state = world.getBlockState(pos);
		Block breakBlock = state.getBlock();
		Material material = originalBlock.getMaterial();
		if (breakBlock.getMaterial() == material && ForgeHooks.canHarvestBlock(breakBlock, player, world, pos)
				&& stack.canHarvestBlock(breakBlock))
		{
			float newStrength = ForgeHooks.blockStrength(state, player, world, pos);
			if (newStrength > 0f && strength / newStrength <= 10f)
			{
				if ((double) breakBlock.getBlockHardness(world, pos) != 0.0D)
				{
					if (DamageHandler.handleDamage(false, breakBlock, stack, player))
					{
						BlockUtils.tryHarvestBlock(world, state, pos, side, player);
					}
				}
				else
				{
					BlockUtils.tryHarvestBlock(world, state, pos, side, player);
				}
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> list)
	{
		Iterator<HammerType> iterator = HZ_Items.hammerTypes.iterator();
		while (iterator.hasNext())
		{
			HammerType hammerType = (HammerType) iterator.next();
			ItemStack stack = new ItemStack(item);
			NBTTagCompound tagCompound = RandomUtils.getNBT(stack);
			tagCompound.setString(HammerKey, hammerType.getName());
			list.add(stack);
		}
	}

	@Override
	public int getItemStackLimit(ItemStack stack)
	{
		return 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getItemStackDisplayName(ItemStack stack)
	{
		HammerType type = HammerInfoHandler.getHammerType(stack);
		if (type != null)
		{
			return (StatCollector.translateToLocal("item.hammer." + type.getName().toLowerCase() + ".name"));
		}
		return (StatCollector.translateToLocal("item.hammer.brokenHammer.name"));
	}

	@Override
	public int receiveEnergy(ItemStack stack, int maxReceive, boolean simulate)
	{
		return EnergyHandler.receiveEnergy(stack, maxReceive, simulate);
	}

	@Override
	public int extractEnergy(ItemStack stack, int maxExtract, boolean simulate)
	{
		return EnergyHandler.extractEnergy(stack, maxExtract, simulate);
	}

	@Override
	public int getEnergyStored(ItemStack stack)
	{
		return EnergyHandler.getEnergyStored(stack);
	}

	@Override
	public int getMaxEnergyStored(ItemStack stack)
	{
		return EnergyHandler.getMaxEnergyStored(stack);
	}

	@Override
	public boolean usesMana(ItemStack stack)
	{
		return HammerInfoHandler.getUsesMana(stack);
	}

	/*@Override
	public boolean doRepair(ItemStack stack, EntityPlayer player, int enchantlevel)
	{
		return HammerInfoHandler.getCanRepair(stack);
	}*/
	
	@Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(ItemStack stack)
    {
    	Multimap<String, AttributeModifier> multimap = HashMultimap.<String, AttributeModifier>create();
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(itemModifierUUID, "Tool modifier", (double)(1.5F + HammerInfoHandler.getAttackValue(stack)), 0));
        return multimap;
    }
	
	@Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return stack.isItemEnchanted() ? HammerInfoHandler.getEnchantedRarity(stack) : HammerInfoHandler.getRarity(stack);
    }

	@Override
	public int getWarp(ItemStack stack, EntityPlayer player)
	{
		return HammerInfoHandler.getWarp(stack);
	}
}
