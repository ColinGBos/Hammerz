package vazkii.botania.api.state.enums;

import java.util.Locale;

import net.minecraft.util.IStringSerializable;

public enum EndBrickVariant implements IStringSerializable {
    END_STONE_BRICKS,
    CHISELED_END_STONE_BRICKS,
    ENDER_BRICKS,
    PILLAR_ENDER_BRICKS;

    @Override
    public String getName() {
        return name().toLowerCase(Locale.ROOT);
    }
}
