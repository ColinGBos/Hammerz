package vazkii.botania.api.state.enums;

import java.util.Locale;

import net.minecraft.util.IStringSerializable;

public enum LivingRockVariant implements IStringSerializable {
    DEFAULT,
    BRICK,
    MOSSY_BRICK,
    CRACKED_BRICK,
    CHISELED_BRICK;

    @Override
    public String getName() {
        return name().toLowerCase(Locale.ROOT);
    }
}
