package vazkii.botania.api.state.enums;

import java.util.Locale;

import net.minecraft.util.IStringSerializable;

public enum SpreaderVariant implements IStringSerializable {
    MANA,
    REDSTONE,
    ELVEN,
    GAIA;

    @Override
    public String getName() {
        return name().toLowerCase(Locale.ROOT);
    }
}
