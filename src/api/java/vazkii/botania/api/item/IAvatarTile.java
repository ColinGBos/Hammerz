/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Oct 24, 2015, 7:00:21 PM (GMT)]
 */
package vazkii.botania.api.item;

import net.minecraft.inventory.IInventory;
import vazkii.botania.api.mana.IManaReceiver;

/**
 * A TileEntity that implements this is considered an Avatar.
 */
public interface IAvatarTile extends IInventory, IManaReceiver {

	/**
	 * Gets the amount of ticks that have elapsed on this avatar while it's functional
	 * (has redstone signal).
	 */
	public int getElapsedFunctionalTicks();

	/**
	 * Gets if this avatar is enabled (isn't powered by a redstone signal).
	 */
	public boolean isEnabled();

}
