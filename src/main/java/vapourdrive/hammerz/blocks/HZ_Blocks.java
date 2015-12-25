package vapourdrive.hammerz.blocks;

import vapourdrive.hammerz.Hammerz;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class HZ_Blocks
{
	public static Block StorageBlock;

	public static void init()
	{
		if (Hammerz.hasStorageBlock)
		{
			StorageBlock = new StorageBlock();
			GameRegistry.registerBlock(StorageBlock, ItemStorageBlock.class, "Storage");
		}
	}
}
