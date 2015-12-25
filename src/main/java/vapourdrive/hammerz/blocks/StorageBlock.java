package vapourdrive.hammerz.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import vapourdrive.hammerz.Hammerz;
import vapourdrive.hammerz.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class StorageBlock extends Block
{
	public static IIcon[] blocks;
	public static String[] names = new String[]
	{
		"Void"
	};

	public StorageBlock()
	{
		super(Material.iron);
		this.setHardness(5.0F);
		this.setResistance(10.0F);
		this.setStepSound(soundTypeMetal);
		if (Hammerz.hasStorageBlock)
		{
			this.setCreativeTab(CreativeTabs.tabBlock);
		}
		this.setBlockName("BlockStorage");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register)
	{
		blocks = new IIcon[names.length];

		for (int i = 0; i < (names.length); ++i)
		{
			blocks[i] = register.registerIcon(Reference.ResourcePath + names[i] + "Block");
		}
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		return blocks[meta];
	}

	@Override
	public int getDamageValue(World world, int x, int y, int z)
	{
		return world.getBlockMetadata(x, y, z);
	}

	@Override
	public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < names.length; ++i)
		{
			if (OreDictionary.doesOreNameExist("ingot" + names[i]))
			{
				list.add(new ItemStack(block, 1, i));
			}
		}
	}

}
