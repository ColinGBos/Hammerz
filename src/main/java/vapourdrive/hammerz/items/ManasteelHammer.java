package vapourdrive.hammerz.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import cpw.mods.fml.common.Optional;

@Optional.InterfaceList(
{
		@Optional.Interface(modid = "Botania", iface = "vazkii.botania.api.mana.IManaUsingItem", striprefs = true),
		@Optional.Interface(modid = "Botania", iface = "vazkii.botania.api.mana.ManaItemHandler", striprefs = true)
})
public class ManasteelHammer extends Hammer implements IManaUsingItem
{
	public static final int MANA_PER_DAMAGE = 60;

	public ManasteelHammer(String Name)
	{
		super(BotaniaAPI.manasteelToolMaterial, Name);
	}

	public ManasteelHammer(ToolMaterial material, String name)
	{
		super(material, name);
	}

	@Override
	public void addExpandedInfo(ItemStack stack, EntityPlayer player, List list, boolean useExtraInformation)
	{
		list.add(EnumChatFormatting.BLUE + StatCollector.translateToLocal("phrase.hammerz.botania1"));
		super.addExpandedInfo(stack, player, list, useExtraInformation);
	}

	@Override
	public boolean usesMana(ItemStack stack)
	{
		return true;
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase entityHit, EntityLivingBase player)
	{
		if (!ManaItemHandler.requestManaExactForTool(stack, (EntityPlayer) player, MANA_PER_DAMAGE, true))
		{
			stack.damageItem(1, player);
		}
		else
		{
			super.hitEntity(stack, entityHit, player);
		}
		return true;
	}

	public boolean handleDamage(Block breakBlock, ItemStack stack, EntityPlayer player)
	{
		if (!ManaItemHandler.requestManaExactForTool(stack, (EntityPlayer) player, MANA_PER_DAMAGE, true))
		{
			return super.handleDamage(breakBlock, stack, player);
		}
		else
		{
			return true;
		}
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity player, int par4, boolean par5)
	{
		if (!world.isRemote && player instanceof EntityPlayer && stack.getItemDamage() > 0
				&& ManaItemHandler.requestManaExactForTool(stack, (EntityPlayer) player, MANA_PER_DAMAGE * 2, true))
		{
			stack.setItemDamage(stack.getItemDamage() - 1);
		}
	}

}
