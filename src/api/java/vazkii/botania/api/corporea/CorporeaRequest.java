/**
 * This class was created by <Vindex>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 *
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 *
 */
package vazkii.botania.api.corporea;

public class CorporeaRequest {
	public final Object matcher;
	public final boolean checkNBT;
	public int count;
	public int foundItems = 0;
	public int extractedItems = 0;

	public CorporeaRequest(Object matcher, boolean checkNBT, int count) {
		super();
		this.matcher = matcher;
		this.checkNBT = checkNBT;
		this.count = count;
	}

}
