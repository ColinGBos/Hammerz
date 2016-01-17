package vazkii.botania.api.state.enums;

import java.util.Locale;

import net.minecraft.util.IStringSerializable;

public enum CrateVariant implements IStringSerializable {
    OPEN,
    CRAFTY;

    @Override
    public String getName() {
        return name().toLowerCase(Locale.ROOT);
    }
}
