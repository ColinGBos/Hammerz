package vazkii.botania.api.state.enums;

import java.util.Locale;

import net.minecraft.util.IStringSerializable;

public enum DrumVariant implements IStringSerializable {
    WILD,
    GATHERING,
    CANOPY;

    @Override
    public String getName() {
        return name().toLowerCase(Locale.ROOT);
    }
}
