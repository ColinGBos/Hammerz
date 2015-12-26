package vapourdrive.hammerz.blocks;

import net.minecraft.block.Block;
import vapourdrive.hammerz.Hammerz;
import cpw.mods.fml.common.registry.GameRegistry;

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
