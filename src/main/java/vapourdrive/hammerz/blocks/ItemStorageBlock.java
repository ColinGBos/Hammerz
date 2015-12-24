package vapourdrive.hammerz.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemStorageBlock extends ItemBlock
{

	public ItemStorageBlock(Block block)
	{
		super(block);
		this.hasSubtypes = true;
	}
	
	@Override
	public int getMetadata(int Meta)
	{
		return Meta;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack)
	{
		int meta = itemstack.getItemDamage();
		if (meta < 0 || meta >= StorageBlock.names.length)
		{
			meta = 0;
		}
		return super.getUnlocalizedName() + StorageBlock.names[meta];
	}

}
