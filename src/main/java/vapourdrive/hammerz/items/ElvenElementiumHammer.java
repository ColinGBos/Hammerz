package vapourdrive.hammerz.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import vazkii.botania.api.BotaniaAPI;


public class ElvenElementiumHammer extends ManasteelHammer
{

	public ElvenElementiumHammer(String Name)
	{
		super(BotaniaAPI.elementiumToolMaterial, Name);
	}
	
	@Override
	public void addExpandedInfo(ItemStack stack, EntityPlayer player, List list, boolean useExtraInformation)
	{
		list.add(EnumChatFormatting.LIGHT_PURPLE + StatCollector.translateToLocal("phrase.hammerz.botania2"));
		super.addExpandedInfo(stack, player, list, useExtraInformation);
	}

}
