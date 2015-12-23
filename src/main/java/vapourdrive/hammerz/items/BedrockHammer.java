package vapourdrive.hammerz.items;

import java.util.List;

import vapourdrive.hammerz.config.ConfigOptions;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

public class BedrockHammer extends Hammer
{

	public BedrockHammer(ToolMaterial material, String Name)
	{
		super(material, Name);
	}

	@Override
	public void addStandardInfo(ItemStack stack, EntityPlayer player, List list, boolean useExtraInformation)
	{
		list.add(EnumChatFormatting.ITALIC + StatCollector.translateToLocal("phrase.hammerz.holdshift"));
	}

	@Override
	public void addExpandedInfo(ItemStack stack, EntityPlayer player, List list, boolean useExtraInformation)
	{
		list.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("phrase.hammerz.hammerinfo1"));
		list.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("phrase.hammerz.hammerinfo2"));
	}

	@Override
	public boolean handleDamage(Block breakBlock, ItemStack stack, EntityPlayer player)
	{
		return true;
	}

	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list)
	{
		ItemStack stack = new ItemStack(item, 1, 0);
		if (ConfigOptions.RotaryCraftSilkTouch)
		{
			stack.addEnchantment(Enchantment.silkTouch, 1);
		}
		list.add(stack);
	}

}
