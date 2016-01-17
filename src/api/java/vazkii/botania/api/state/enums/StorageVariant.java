package vazkii.botania.api.state.enums;

import java.util.Locale;

import net.minecraft.util.IStringSerializable;

public enum StorageVariant implements IStringSerializable {
    MANASTEEL,
    TERRASTEEL,
    ELEMENTIUM,
    MANA_DIAMOND,
    DRAGONSTONE;

    @Override
    public String getName() {
        return name().toLowerCase(Locale.ROOT);
    }
}
