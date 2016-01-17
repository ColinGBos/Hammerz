package vazkii.botania.api.state.enums;

import java.util.Locale;

import net.minecraft.util.IStringSerializable;

public enum LuminizerVariant implements IStringSerializable {
    DEFAULT,
    DETECTOR;

    @Override
    public String getName() {
        return name().toLowerCase(Locale.ROOT);
    }
}
