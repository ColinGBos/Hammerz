package vapourdrive.hammerz.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thaumcraft.api.IRepairable;
import thaumcraft.api.ThaumcraftApi;
import vapourdrive.hammerz.Reference;
import vapourdrive.hammerz.utils.RandomUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ChameleonHammer extends Hammer implements IRepairable
{
	public IIcon[] icon;
	public String PhaseKey = "Hammerz.phase";
	public String EnchantKey = "Hammerz.enchant";

	public ChameleonHammer(String Name)
	{
		super(ThaumcraftApi.toolMatElemental, Name);
		this.setHarvestLevel("pickaxe", 4);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister register)
	{
		icon = new IIcon[2];
		this.icon[0] = register.registerIcon(Reference.ResourcePath + "ChameleonHammer");
		this.icon[1] = register.registerIcon(Reference.ResourcePath + "Eye");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIconFromDamageForRenderPass(int par1, int renderPass)
	{
		return renderPass != 1 ? icon[0] : icon[1];
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getColorFromItemStack(ItemStack itemstack, int renderpass)
	{
		if (renderpass != 1)
		{
			return 16777215;
		}
		else
		{
			if (!itemstack.hasTagCompound())
			{
				return 0x980000;
			}
			byte phase = itemstack.getTagCompound().getByte(PhaseKey);
			if (phase == 1)
			{
				return 0x0010CC;
			}
			else if (phase == 2)
			{
				return 0xE5DA00;
			}
			return 0x980000;
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean requiresMultipleRenderPasses()
	{
		return true;
	}

	public EnumRarity getRarity(ItemStack stack)
	{
		return EnumRarity.epic;
	}

	public void addExpandedInfo(ItemStack stack, EntityPlayer player, List list, boolean useExtraInformation)
	{
		boolean flag = false;
		if (stack.hasTagCompound())
		{
			NBTTagCompound tags = RandomUtils.getNBT(stack);
			for (int j = 0; j <= 2; j++)
			{
				NBTTagList nbttaglist = (NBTTagList) (tags.getTag(EnchantKey + j));

				if (nbttaglist != null)
				{
					flag = true;
					list.add(EnumChatFormatting.WHITE + StatCollector.translateToLocal("hammerz.keyword.phase") + " " + (j + 1));
					for (int i = 0; i < nbttaglist.tagCount(); ++i)
					{
						short short1 = nbttaglist.getCompoundTagAt(i).getShort("id");
						short short2 = nbttaglist.getCompoundTagAt(i).getShort("lvl");

						if (Enchantment.enchantmentsList[short1] != null)
						{
							list.add("    " + Enchantment.enchantmentsList[short1].getTranslatedName(short2));
						}
					}
				}
			}
		}
		if(flag)
		{
			list.add("");
		}
		super.addExpandedInfo(stack, player, list, useExtraInformation);
	}

	@Override
	public boolean getIsRepairable(ItemStack stack, ItemStack stack2)
	{
		ItemStack shard = RandomUtils.getItemStackFromString("ForbiddenMagic", "NetherShard", 8);
		if (stack2.isItemEqual(new ItemStack(shard.getItem(), 8, 1)))
		{
			if (stack2.stackSize >= 8)
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
	{
		if (player.isSneaking() && itemstack.hasTagCompound() && getMaxDamage(itemstack) - itemstack.getItemDamage() > 5)
		{
			NBTTagCompound tags = itemstack.getTagCompound();
			byte phase = tags.getByte(PhaseKey);
			if (tags.hasKey("ench"))
			{
				NBTTagList enchants = itemstack.getEnchantmentTagList();
				tags.setTag(EnchantKey + phase, enchants);
			}
			else
			{
				tags.removeTag(EnchantKey + phase);
			}

			if (++phase > 2)
			{
				phase = 0;
			}
			tags.setByte(PhaseKey, phase);
			if (tags.hasKey(EnchantKey + phase))
			{
				NBTTagList enchants = (NBTTagList) (tags.getTag(EnchantKey + phase));
				tags.setTag("ench", enchants);
			}
			else
			{
				tags.removeTag("ench");
			}

			itemstack.setTagCompound(tags);
			itemstack.damageItem(5, player);
			player.swingItem();
			world.playSoundEffect(player.posX, player.posY, player.posZ, "thaumcraft:wandfail", 0.2F, 0.2F + world.rand.nextFloat() * 0.2F);
		}
		return itemstack;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getItemStackDisplayName(ItemStack stack)
	{
		NBTTagCompound tag = RandomUtils.getNBT(stack);
		return super.getItemStackDisplayName(stack) + " " + StatCollector.translateToLocal("hammerz.keyword.phase") + " "
				+ (tag.getByte(PhaseKey) + 1);
	}

}
