package vapourdrive.hammerz.items.hammer;

import java.text.NumberFormat;
import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;

import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

import vapourdrive.hammerz.config.ConfigOptions;
import vapourdrive.hammerz.handlers.UpgradeManager;

public class AddInformationHelper
{
	public static final NumberFormat INT_NF = NumberFormat.getIntegerInstance();
	public static final NumberFormat FLOAT_NF = NumberFormat.getInstance();

	public static void addInformation(ItemStack stack, World world, List<String> list, ITooltipFlag flagIn)
	{
		addPermanentInfo(stack, world, list, flagIn);
		if (Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
		{
			addExpandedInfo(stack, world, list, flagIn);
		}
		else
		{
			addStandardInfo(stack, world, list, flagIn);
		}
		
	}
	
	public static void addStandardInfo(ItemStack stack, World world, List<String> list, ITooltipFlag flagIn)
	{
		list.add(TextFormatting.WHITE + "" + TextFormatting.ITALIC + I18n.translateToLocal("phrase.hammerz.holdshift"));
		if (ConfigOptions.AddDurabilityInfo)
		{
			list.add(TextFormatting.GRAY + I18n.translateToLocal("hammerz.keyword.durability") + ": "
					+ (stack.getMaxDamage() - stack.getItemDamage()) + "/" + stack.getMaxDamage());
		}
	}

	public static void addExpandedInfo(ItemStack stack, World world, List<String> list, ITooltipFlag flagIn)
	{
		list.add(TextFormatting.GRAY + I18n.translateToLocal("phrase.hammerz.hammerinfo1"));
		list.add(TextFormatting.GRAY + I18n.translateToLocal("phrase.hammerz.hammerinfo2"));
		if(HammerInfoHandler.isStackDarkSteelHammer(stack))
		{
			int power = HammerInfoHandler.getEmpoweredment(stack);
			if (HammerInfoHandler.isEmpowered(stack))
			{
				list.add(INT_NF.format(EnergyHandler.getEnergyStored(stack)) + "/" + INT_NF.format(EnergyHandler.getMaxEnergyStored(stack))
						+ " RF");
			}
			if (HammerInfoHandler.getEmpoweredment(stack) < 4)
			{
				list.add(TextFormatting.YELLOW + I18n.translateToLocal("enderio.tooltip.anvilupgrades"));
			}
			if (HammerInfoHandler.getEmpoweredment(stack) < 4)
			{
				int level = (HammerInfoHandler.getEmpoweredment(stack));
				String levelString = UpgradeManager.getNumberString(level + 1);
				list.add(TextFormatting.DARK_AQUA
						+ I18n.translateToLocal("enderio.darksteel.upgrade.empowered_" + levelString + ".name") + ":");
				list.add("  " + TextFormatting.DARK_AQUA + "" + TextFormatting.ITALIC
						+ UpgradeManager.getItem(level + 1).getDisplayName() + " + " + UpgradeManager.getXPCost(level + 1) + "lvs");
			}
		}
	}

	public static void addPermanentInfo(ItemStack stack, World world, List<String> list, ITooltipFlag flagIn)
	{
		if(HammerInfoHandler.isStackDarkSteelHammer(stack))
		{
			list.add("  " + TextFormatting.WHITE + "+" + ConfigOptions.EIOToolEmpoweredSpeedBoost + " "
					+ I18n.translateToLocal("enderio.item.darkSteel_pickaxe.tooltip.effPowered"));
			list.add("  " + TextFormatting.WHITE + "+" + ConfigOptions.EIOToolEmpoweredObsidianSpeedBoost + " "
					+ I18n.translateToLocal("enderio.item.darkSteel_pickaxe.tooltip.effObs"));
			list.add("    " + TextFormatting.WHITE + "(" + ConfigOptions.EIOToolObsidianEnergyUse + " RF / block)");

			String str2 = I18n.translateToLocal("enderio.darksteel.upgrade.empowered_one.tooltip.detailed.line2");
			String str3 = I18n.translateToLocal("enderio.darksteel.upgrade.empowered_one.tooltip.detailed.line3");

			str2 = str2.replaceAll("\\$D", Math.round(UpgradeManager.getChance(HammerInfoHandler.getEmpoweredment(stack)) * 100) + "");
			list.add(str2 + " " + str3);
		}
	}

}
