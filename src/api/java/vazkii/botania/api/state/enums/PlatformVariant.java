package vazkii.botania.api.state.enums;

import java.util.Locale;

import net.minecraft.util.IStringSerializable;

public enum PlatformVariant implements IStringSerializable {
    ABSTRUSE,
    SPECTRAL,
    INFRANGIBLE;

    @Override
    public String getName() {
        return name().toLowerCase(Locale.ROOT);
    }
}
