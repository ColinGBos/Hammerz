package vapourdrive.hammerz.items.hammer;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

import org.lwjgl.input.Keyboard;

import vapourdrive.hammerz.config.ConfigOptions;

public class AddInformationHelper
{

	public static void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean useExtraInformation)
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
	
	public static void addStandardInfo(ItemStack stack, EntityPlayer player, List<String> list, boolean useExtraInformation)
	{
		list.add(EnumChatFormatting.WHITE + "" + EnumChatFormatting.ITALIC + StatCollector.translateToLocal("phrase.hammerz.holdshift"));
		if (ConfigOptions.AddDurabilityInfo)
		{
			list.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("hammerz.keyword.durability") + ": "
					+ (stack.getMaxDamage() - stack.getItemDamage()) + "/" + stack.getMaxDamage());
		}
	}

	public static void addExpandedInfo(ItemStack stack, EntityPlayer player, List<String> list, boolean useExtraInformation)
	{
		list.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("phrase.hammerz.hammerinfo1"));
		list.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("phrase.hammerz.hammerinfo2"));
	}

	public static void addPermanentInfo(ItemStack stack, EntityPlayer player, List<String> list, boolean useExtraInformation)
	{
	}

}
