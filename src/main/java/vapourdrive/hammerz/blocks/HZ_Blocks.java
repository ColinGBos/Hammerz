package vapourdrive.hammerz.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class HZ_Blocks
{
	public static Block StorageBlock;
	public static void init()
	{
		StorageBlock = new StorageBlock();
		GameRegistry.registerBlock(StorageBlock, ItemStorageBlock.class, "Storage");
	}
}
