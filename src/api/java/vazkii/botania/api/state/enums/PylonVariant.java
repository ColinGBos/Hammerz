package vazkii.botania.api.state.enums;

import java.util.Locale;

import net.minecraft.util.IStringSerializable;

public enum PylonVariant implements IStringSerializable {
    MANA,
    NATURA,
    GAIA;

    @Override
    public String getName() {
        return name().toLowerCase(Locale.ROOT);
    }
}
