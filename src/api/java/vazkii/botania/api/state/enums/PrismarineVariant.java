package vazkii.botania.api.state.enums;

import java.util.Locale;

import net.minecraft.util.IStringSerializable;

public enum PrismarineVariant implements IStringSerializable {
    PRISMARINE,
    PRISMARINE_BRICKS,
    DARK_PRISMARINE;

    @Override
    public String getName() {
        return name().toLowerCase(Locale.ROOT);
    }
}
