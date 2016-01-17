package vazkii.botania.api.state.enums;

import java.util.Locale;

import net.minecraft.util.IStringSerializable;

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
