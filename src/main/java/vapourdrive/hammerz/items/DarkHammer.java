package vapourdrive.hammerz.items;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.ForgeHooks;
import vapourdrive.hammerz.config.ConfigOptions;
import vapourdrive.hammerz.handlers.AnvilEvent;
import vapourdrive.hammerz.handlers.UpgradeManager;
import vapourdrive.hammerz.utils.RandomUtils;
import cofh.api.energy.IEnergyContainerItem;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Optional.Interface(modid = "CoFHCore", iface = "cofh.api.energy.IEnergyContainerItem", striprefs = true)
public class DarkHammer extends Hammer implements IEnergyContainerItem
{
	public static final String Tag_Energy = "hammerz.hammer.energy";

	public DarkHammer(ToolMaterial material, String Name)
	{
		super(material, Name);
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase entityHit, EntityLivingBase player)
	{
		if (this.extractEnergy(stack, ConfigOptions.EIOToolEnergyuse, true) >= ConfigOptions.EIOToolEnergyuse)
		{
			this.extractEnergy(stack, ConfigOptions.EIOToolEnergyuse, false);
		}
		else
		{
			super.hitEntity(stack, entityHit, player);
		}
		return true;
	}

	@Override
	public boolean handleDamage(Block breakBlock, ItemStack stack, EntityPlayer player)
	{
		if (isEmpowered(stack))
		{
			if (!player.capabilities.isCreativeMode)
			{
				return attemptDamage(breakBlock, stack, UpgradeManager.getChance(getEmpoweredment(stack)), player);
			}
			return true;
		}
		else
		{
			return super.handleDamage(breakBlock, stack, player);
		}
	}

	public boolean attemptDamage(Block breakBlock, ItemStack stack, float chance, EntityPlayer player)
	{
		boolean wasEnergised = false;
		Random rand = new Random();
		if (rand.nextFloat() < chance)
		{
			if (breakBlock == Blocks.obsidian)
			{
				if (this.extractEnergy(stack, ConfigOptions.EIOToolObsidianEnergyUse, true) >= ConfigOptions.EIOToolObsidianEnergyUse)
				{
					this.extractEnergy(stack, ConfigOptions.EIOToolObsidianEnergyUse, false);
					wasEnergised = true;
					return true;
				}
			}
			else
			{
				if (this.extractEnergy(stack, ConfigOptions.EIOToolEnergyuse, true) >= ConfigOptions.EIOToolEnergyuse)
				{
					this.extractEnergy(stack, ConfigOptions.EIOToolEnergyuse, false);
					wasEnergised = true;
					return true;
				}
			}
		}
		if (!wasEnergised)
		{
			return super.handleDamage(breakBlock, stack, player);
		}
		return false;
	}
	
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List list)
    {
        list.add(new ItemStack(item, 1, 0));
        ItemStack stack = new ItemStack(item, 1, 0);
        NBTTagCompound tagCompound = RandomUtils.getNBT(stack);
        tagCompound.setInteger(AnvilEvent.Key_Empower, 4);
        tagCompound.setInteger(Tag_Energy, this.getMaxEnergyStored(stack));
        list.add(stack);

    }

	@Override
	public void addStandardInfo(ItemStack stack, EntityPlayer player, List list, boolean useExtraInformation)
	{
		list.add(EnumChatFormatting.WHITE + "" + EnumChatFormatting.ITALIC + StatCollector.translateToLocal("phrase.hammerz.holdshift"));
	}

	@Override
	public void addExpandedInfo(ItemStack stack, EntityPlayer player, List list, boolean useExtraInformation)
	{
		int power = getEmpoweredment(stack);
		if (isEmpowered(stack))
		{
			addAdvancedInfo(power, stack, list);
			list.add(String.format("%,6d", (this.getEnergyStored(stack))) + "/" + String.format("%,6d", this.getMaxEnergyStored(stack))
					+ " RF");
		}
		if (getEmpoweredment(stack) < 4)
		{
			list.add(EnumChatFormatting.YELLOW + StatCollector.translateToLocal("enderio.tooltip.anvilupgrades"));
		}
		addUpgradeInfo(power, stack, list);
	}

	@Override
	public void addPermanentInfo(ItemStack stack, EntityPlayer player, List list, boolean useExtraInformation)
	{
		int power = getEmpoweredment(stack);
		addEmpowerInfo(power, stack, list);
	}

	public void addEmpowerInfo(int power, ItemStack stack, List list)
	{
		if (isEmpowered(stack))
		{
			String level = UpgradeManager.getNumberString(getEmpoweredment(stack));
			list.add(EnumChatFormatting.DARK_AQUA
					+ StatCollector.translateToLocal("enderio.darksteel.upgrade.empowered_" + level + ".name"));
		}
	}

	private void addAdvancedInfo(int power, ItemStack stack, List list)
	{
		list.add("  " + EnumChatFormatting.WHITE + "+" + ConfigOptions.EIOToolEmpoweredSpeedBoost + " "
				+ StatCollector.translateToLocal("enderio.item.darkSteel_pickaxe.tooltip.effPowered"));
		list.add("  " + EnumChatFormatting.WHITE + "+" + ConfigOptions.EIOToolEmpoweredObsidianSpeedBoost + " "
				+ StatCollector.translateToLocal("enderio.item.darkSteel_pickaxe.tooltip.effObs"));
		list.add("    " + EnumChatFormatting.WHITE + "(" + ConfigOptions.EIOToolObsidianEnergyUse + " RF / block)");

		String str2 = StatCollector.translateToLocal("enderio.darksteel.upgrade.empowered_one.tooltip.detailed.line2");
		String str3 = StatCollector.translateToLocal("enderio.darksteel.upgrade.empowered_one.tooltip.detailed.line3");

		str2 = str2.replaceAll("\\$D", String.valueOf((int) (UpgradeManager.getChance(power) * 100f)));
		list.add(str2 + " " + str3);

	}

	public void addUpgradeInfo(int power, ItemStack stack, List list)
	{
		if (getEmpoweredment(stack) < 4)
		{
			int level = (getEmpoweredment(stack));
			String levelString = UpgradeManager.getNumberString(level + 1);
			list.add(EnumChatFormatting.DARK_AQUA
					+ StatCollector.translateToLocal("enderio.darksteel.upgrade.empowered_" + levelString + ".name") + ":");
			list.add("  " + EnumChatFormatting.DARK_AQUA + "" + EnumChatFormatting.ITALIC
					+ UpgradeManager.getItem(level + 1).getDisplayName() + " + " + UpgradeManager.getXPCost(level + 1));
		}
	}

	@Override
	public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate)
	{
		return receiveEnergy(container, maxReceive, simulate, true);
	}

	public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate, boolean restricted)
	{
		int energy = this.getEnergyStored(container);
		int energyRecieved;
		if (!restricted)
		{
			energyRecieved = Math.min(this.getMaxEnergyStored(container) - energy, maxReceive);
		}
		else
		{
			energyRecieved = Math.min(this.getMaxEnergyStored(container) - energy,
					Math.min(maxReceive, this.getMaxEnergyStored(container) / 1000));

		}
		if (!simulate)
		{
			energy += energyRecieved;
			RandomUtils.getNBT(container).setInteger(Tag_Energy, energy);
		}
		return energyRecieved;
	}

	@Override
	public int extractEnergy(ItemStack container, int maxExtract, boolean simulate)
	{
		int energy = this.getEnergyStored(container);
		int energyExtracted = Math.min(energy, maxExtract);
		if (!simulate)
		{
			energy -= energyExtracted;
			RandomUtils.getNBT(container).setInteger(Tag_Energy, energy);
		}
		return energyExtracted;
	}

	@Override
	public int getEnergyStored(ItemStack container)
	{
		return RandomUtils.getNBT(container).getInteger(Tag_Energy);
	}

	@Override
	public int getMaxEnergyStored(ItemStack container)
	{
		if (isEmpowered(container))
		{
			switch (RandomUtils.getNBT(container).getInteger(AnvilEvent.Key_Empower))
			{
				case 1:
					return ConfigOptions.EIOToolBaseEnergy;
				case 2:
					return ConfigOptions.EIOToolTierOneEnergy;
				case 3:
					return ConfigOptions.EIOToolTierTwoEnergy;
				case 4:
					return ConfigOptions.EIOToolTierThreeEnergy;
				default:
					return 0;
			}
		}
		return 0;
	}

	public static boolean isEmpowered(ItemStack stack)
	{
		if (getEmpoweredment(stack) > 0)
		{
			return true;
		}
		return false;
	}

	public static int getEmpoweredment(ItemStack stack)
	{
		int level = RandomUtils.getNBT(stack).getInteger(AnvilEvent.Key_Empower);
		if (level > 0)
		{
			return level;
		}
		return 0;
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack)
	{
		return false;
	}

	@Override
	public float getDigSpeed(ItemStack stack, Block block, int meta)
	{
		float speed = efficiencyOnProperMaterial * 0.3f;
		if (ForgeHooks.isToolEffective(stack, block, meta))
		{
			if (isEmpowered(stack))
			{
				speed = (float) (speed + ConfigOptions.EIOToolEmpoweredSpeedBoost);
			}
		}
		if (block == Blocks.obsidian)
		{
			return (((float) (ConfigOptions.EIOToolEmpoweredObsidianSpeedBoost)) / 3);
		}
		return super.getDigSpeed(stack, block, meta) * 0.5f;
	}
}
