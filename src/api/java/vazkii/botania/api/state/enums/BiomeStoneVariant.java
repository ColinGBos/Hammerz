package vazkii.botania.api.state.enums;

import java.util.Locale;

import net.minecraft.util.IStringSerializable;

public enum BiomeStoneVariant implements IStringSerializable {
    FOREST,
    PLAINS,
    MOUNTAIN,
    FUNGAL,
    SWAMP,
    DESERT,
    TAIGA,
    MESA,
    FOREST_COBBLE,
    PLAINS_COBBLE,
    MOUNTAIN_COBBLE,
    FUNGAL_COBBLE,
    SWAMP_COBBLE,
    DESERT_COBBLE,
    TAIGA_COBBLE,
    MESA_COBBLE;

    @Override
    public String getName() {
        return name().toLowerCase(Locale.ROOT);
    }
}
