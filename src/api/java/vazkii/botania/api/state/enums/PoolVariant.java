package vazkii.botania.api.state.enums;

import java.util.Locale;

import net.minecraft.util.IStringSerializable;

public enum PoolVariant implements IStringSerializable {
    DEFAULT,
    CREATIVE,
    DILUTED,
    FABULOUS;

    @Override
    public String getName() {
        return name().toLowerCase(Locale.ROOT);
    }
}
