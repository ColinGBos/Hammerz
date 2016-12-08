/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 *
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 *
 * File Created @ [Oct 24, 2015, 11:16:00 PM (GMT)]
 */
package vazkii.botania.api.item;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * A Block that implements this can be uprooted by the various horns in Botania.
 */
public interface IHornHarvestable {

	/**
	 * Returns true if this block can be uprooted.
	 * Note that the stack param can be null if it's a drum breaking it.
	 */
	public boolean canHornHarvest(World world, BlockPos pos, ItemStack stack, EnumHornType hornType);

	/**
	 * Returns true if harvestByHorn() should be called. If false it just uses the normal
	 * block breaking method.
	 * Note that the stack param can be null if it's a drum breaking it.
	 */
	public boolean hasSpecialHornHarvest(World world, BlockPos pos, ItemStack stack, EnumHornType hornType);

	/**
	 * Called to harvest by a horn.
	 * Note that the stack param can be null if it's a drum breaking it.
	 */
	public void harvestByHorn(World world, BlockPos pos, ItemStack stack, EnumHornType hornType);

	public static enum EnumHornType {

		/**
		 * Horn of the Wild, for grass and crops
		 */
		WILD,

		/**
		 * Horn of the Canopy, for leaves
		 */
		CANOPY,

		/**
		 * Horn of the Covering, for snow
		 */
		COVERING;

		public static EnumHornType getTypeForMeta(int meta) {
			EnumHornType[] values = EnumHornType.values();
			return values[Math.min(values.length - 1, meta)];
		}

	};

}
