package vazkii.botania.api.state.enums;

import java.util.Locale;

import net.minecraft.util.IStringSerializable;

public enum AltGrassVariant implements IStringSerializable {
    DRY,
    GOLDEN,
    VIVID,
    SCORCHED,
    INFUSED,
    MUTATED;

    @Override
    public String getName() {
        return this.name().toLowerCase(Locale.ROOT);
    }
}
