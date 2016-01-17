package vazkii.botania.api.state.enums;

import java.util.Locale;

import net.minecraft.util.IStringSerializable;

public enum LivingWoodVariant implements IStringSerializable {
    DEFAULT,
    PLANKS,
    MOSSY_PLANKS,
    FRAMED,
    PATTERN_FRAMED,
    GLIMMERING;

    @Override
    public String getName() {
        return name().toLowerCase(Locale.ROOT);
    }
}
