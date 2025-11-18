package vapourdrive.hammerz.config;

import net.neoforged.neoforge.common.ModConfigSpec;
import vapourdrive.hammerz.Hammerz;

public class ConfigSettings {
    public static final String CATEGORY_MOD = "hammerz";

    public static final ModConfigSpec SERVER_CONFIG;
    public static final ModConfigSpec CLIENT_CONFIG;
    public static final String SUBCATEGORY_GENERAL = "general";
    public static ModConfigSpec.BooleanValue ALLOW_SNEAK_MINE;
    public static ModConfigSpec.BooleanValue SHOW_TOOLTIP_DURABILITY;
    public static ModConfigSpec.BooleanValue RENDER_EXTENDED_HITBOX;
    public static ModConfigSpec.BooleanValue ALWAYS_RENDER_CENTER_BLOCK_HITBOX;

    static {
        Hammerz.LOGGER.info("Initiating Configs for Hammerz");
        ModConfigSpec.Builder SERVER_BUILDER = new ModConfigSpec.Builder();
        ModConfigSpec.Builder CLIENT_BUILDER = new ModConfigSpec.Builder();

        SERVER_BUILDER.comment("Hammerz Settings").push(CATEGORY_MOD);

        setupFirstBlockConfig(SERVER_BUILDER, CLIENT_BUILDER);

        SERVER_BUILDER.pop();

        SERVER_CONFIG = SERVER_BUILDER.build();
        CLIENT_CONFIG = CLIENT_BUILDER.build();
    }

    private static void setupFirstBlockConfig(ModConfigSpec.Builder SERVER_BUILDER, ModConfigSpec.Builder CLIENT_BUILDER) {
        SERVER_BUILDER.comment("Hammerz Settings").push(SUBCATEGORY_GENERAL);
        ALLOW_SNEAK_MINE = SERVER_BUILDER.comment("Allow mining a single block when sneaking").define("allowSneakSingleBlockMine", true);
        SERVER_BUILDER.pop();

        CLIENT_BUILDER.comment("Hammerz Settings").push(SUBCATEGORY_GENERAL);
        SHOW_TOOLTIP_DURABILITY = CLIENT_BUILDER.comment("Show durability information in the tooltip").define("showToolTipDurability", true);
        RENDER_EXTENDED_HITBOX = CLIENT_BUILDER.comment("Render 3x3 hitbox when using a hammer").define("renderLargeHitbox", true);
        ALWAYS_RENDER_CENTER_BLOCK_HITBOX = CLIENT_BUILDER.comment("Always render the center block's hitbox when using a hammer").define("renderCenterHitbox", false);
        CLIENT_BUILDER.pop();

    }

}
