package vazkii.botania.api.state.enums;

import java.util.Locale;

import net.minecraft.util.IStringSerializable;

public enum FutureStoneVariant implements IStringSerializable {
    ANDESITE,
    BASALT,
    DIORITE,
    GRANITE,
    POLISHED_ANDESITE,
    POLISHED_BASALT,
    POLISHED_DIORITE,
    POLISHED_GRANITE,
    ANDESITE_BRICK,
    BASALT_BRICK,
    DIORITE_BRICK,
    GRANITE_BRICK,
    CHISELED_ANDESITE,
    CHISELED_BASALT,
    CHISELED_DIORITE,
    CHISELED_GRANITE;

    @Override
    public String getName() {
        return name().toLowerCase(Locale.ROOT);
    }
}
