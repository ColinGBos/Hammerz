package vapourdrive.hammerz.items;

import thaumcraft.api.IRepairable;
import thaumcraft.api.ThaumcraftApi;
import vapourdrive.hammerz.utils.RandomUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.Optional;

@Optional.Interface(modid = "Thaumcraft", iface = "thaumcraft.api.IRepairable", striprefs = true)
public class ElementalHammer extends Hammer implements IRepairable
{

	public ElementalHammer(String Name)
	{
		super(ThaumcraftApi.toolMatElemental, Name);
	}

	public EnumRarity getRarity(ItemStack stack)
	{
		return stack.isItemEnchanted() ? EnumRarity.epic : EnumRarity.rare;
	}

	@Override
	public boolean getIsRepairable(ItemStack stack, ItemStack stack2)
	{
		int[] names = OreDictionary.getOreIDs(stack2);
		for (int i = 0; i < names.length; i++)
		{
			if (("blockThaumium").contentEquals(OreDictionary.getOreName(names[i])))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean onItemShiftUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float floatx,
			float floaty, float floatz)
	{
		ItemStack ThaumPick = RandomUtils.getItemStackFromString("Thaumcraft", "ItemPickaxeElemental", 1);
		if (ThaumPick != null && ThaumPick.getItem() != null)
		{
			if (this.requestDamage(null, stack, player, 10))
			{
				return ThaumPick.getItem().onItemUse(ThaumPick, player, world, x, y, z, side, floatx, floaty, floatz);
			}
		}
		return false;
	}

}
