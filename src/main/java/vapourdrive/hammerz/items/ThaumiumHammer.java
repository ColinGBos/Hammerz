package vapourdrive.hammerz.items;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import thaumcraft.api.IRepairable;
import thaumcraft.api.ThaumcraftApi;
import cpw.mods.fml.common.Optional;

@Optional.Interface(modid = "Thaumcraft", iface = "thaumcraft.api.IRepairable", striprefs = true)
public class ThaumiumHammer extends Hammer implements IRepairable
{
	public ThaumiumHammer(String Name)
	{
		super(ThaumcraftApi.toolMatThaumium, Name);
	}
	
	public EnumRarity getRarity(ItemStack stack)
    {
        return stack.isItemEnchanted() ? EnumRarity.rare : EnumRarity.uncommon;
    }

}
