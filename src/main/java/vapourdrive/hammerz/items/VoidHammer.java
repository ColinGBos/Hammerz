package vapourdrive.hammerz.items;

import cpw.mods.fml.common.Optional;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thaumcraft.api.IWarpingGear;
import thaumcraft.api.ThaumcraftApi;

@Optional.Interface(modid = "Thaumcraft", iface = "thaumcraft.api.IWarpingGear", striprefs = true)
public class VoidHammer extends Hammer implements IWarpingGear
{
	public VoidHammer(String Name)
	{
		super(ThaumcraftApi.toolMatVoid, Name);
	}

	@Override
	public int getWarp(ItemStack itemstack, EntityPlayer player)
	{
		return 1;
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int par3, boolean par4)
	{
		super.onUpdate(stack, world, entity, par3, par4);

		if (stack.isItemDamaged() && entity != null && entity.ticksExisted % 20 == 0 && entity instanceof EntityLivingBase)
		{
			stack.damageItem(-1, (EntityLivingBase) entity);
		}
	}
	
	public EnumRarity getRarity(ItemStack stack)
    {
        return stack.isItemEnchanted() ? EnumRarity.rare : EnumRarity.uncommon;
    }

}
