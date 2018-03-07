/**
 * This class was created by <williewillus>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 */
package vazkii.botania.api.state.enums;

import net.minecraft.util.IStringSerializable;

import java.util.Locale;

public enum QuartzVariant implements IStringSerializable {
	NORMAL,
	CHISELED,
	PILLAR_Y,
	PILLAR_X,
	PILLAR_Z;

	@Override
	public String getName() {
		return name().toLowerCase(Locale.ROOT);
	}

}
