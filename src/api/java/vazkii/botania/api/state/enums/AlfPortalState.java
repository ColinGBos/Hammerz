package vazkii.botania.api.state.enums;

import java.util.Locale;

import net.minecraft.util.IStringSerializable;

public enum AlfPortalState implements IStringSerializable {
    OFF,
    ON_Z,
    ON_X;

    @Override
    public String getName() {
        return name().toLowerCase(Locale.ROOT);
    }
}
