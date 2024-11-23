package vapourdrive.hammerz.setup;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import vapourdrive.hammerz.Hammerz;
import vapourdrive.hammerz.content.hammerz.*;
import vapourdrive.hammerz.content.hammerz.materials.ToolTiers;
import vapourdrive.hammerz.data.loot_modifiers.RemoveItemModifier;
import vapourdrive.vapourware.shared.utils.RegistryUtils;

import java.util.function.Supplier;


public class Registration {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM, Hammerz.MODID);

    public static final Supplier<Item> WOOD_HAMMER = ITEMS.register("wood_hammer", () -> new HammerItem(ToolTiers.WOOD));
    public static final Supplier<Item> STONE_HAMMER = ITEMS.register("stone_hammer", () -> new HammerItem(ToolTiers.STONE));
    public static final Supplier<Item> IRON_HAMMER = ITEMS.register("iron_hammer", () -> new HammerItem(ToolTiers.IRON));
    public static final Supplier<Item> GOLD_HAMMER = ITEMS.register("gold_hammer", () -> new HammerItem(ToolTiers.GOLD));
    public static final Supplier<Item> DIAMOND_HAMMER = ITEMS.register("diamond_hammer", () -> new HammerItem(ToolTiers.DIAMOND));
    public static final Supplier<Item> NETHERITE_HAMMER = ITEMS.register("netherite_hammer", () -> new HammerItem(ToolTiers.NETHERITE));
    public static final Supplier<Item> OSMIUM_HAMMER = ITEMS.register("osmium_hammer", () -> new HammerItem(ToolTiers.OSMIUM));
    public static final Supplier<Item> BRONZE_HAMMER = ITEMS.register("bronze_hammer", () -> new HammerItem(ToolTiers.BRONZE));
    public static final Supplier<Item> STEEL_HAMMER = ITEMS.register("steel_hammer", () -> new HammerItem(ToolTiers.STEEL));
    public static final Supplier<Item> REFINED_OBSIDIAN_HAMMER = ITEMS.register("refined_obsidian_hammer", () -> new HammerItem(ToolTiers.REFINED_OBSIDIAN));
    public static final Supplier<Item> REFINED_GLOWSTONE_HAMMER = ITEMS.register("refined_glowstone_hammer", () -> new HammerItem(ToolTiers.REFINED_GLOWSTONE));
    public static final Supplier<Item> LAPIS_LAZULI_HAMMER = ITEMS.register("lapis_lazuli_hammer", () -> new HammerItem(ToolTiers.LAPIS_LAZULI));
    public static final Supplier<Item> NETHER_QUARTZ_HAMMER = ITEMS.register("nether_quartz_hammer", () -> new HammerItem(ToolTiers.NETHER_QUARTZ));
    public static final Supplier<Item> CERTUS_QUARTZ_HAMMER = ITEMS.register("certus_quartz_hammer", () -> new HammerItem(ToolTiers.CERTUS_QUARTZ));
    public static final Supplier<Item> FLUIX_HAMMER = ITEMS.register("fluix_hammer", () -> new FluixHammerItem(ToolTiers.FLUIX));

    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(NeoForgeRegistries.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Hammerz.MODID);

    public static final Supplier<MapCodec<? extends IGlobalLootModifier>> REMOVE_ITEM = LOOT_MODIFIERS.register("remove_item", RemoveItemModifier.CODEC);

    public static void init(IEventBus eventBus) {
        ITEMS.register(eventBus);
        LOOT_MODIFIERS.register(eventBus);


    }

    public static void buildContents(BuildCreativeModeTabContentsEvent event) {
        // Add to ingredients tab
        if (event.getTab() == vapourdrive.vapourware.setup.Registration.VAPOUR_GROUP.get() || event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(WOOD_HAMMER.get().getDefaultInstance());
            event.accept(STONE_HAMMER.get().getDefaultInstance());
            event.accept(IRON_HAMMER.get().getDefaultInstance());
            event.accept(GOLD_HAMMER.get().getDefaultInstance());
            event.accept(DIAMOND_HAMMER.get().getDefaultInstance());
            event.accept(NETHERITE_HAMMER.get().getDefaultInstance());

            if(RegistryUtils.itemTagIsNotEmpty("c", "storage_blocks/osmium")) {
                event.accept(OSMIUM_HAMMER.get().getDefaultInstance());
            }
            if(RegistryUtils.itemTagIsNotEmpty("c", "storage_blocks/bronze")) {
                event.accept(BRONZE_HAMMER.get().getDefaultInstance());
            }
            if(RegistryUtils.itemTagIsNotEmpty("c", "storage_blocks/steel")) {
                event.accept(STEEL_HAMMER.get().getDefaultInstance());
            }
            if(RegistryUtils.itemTagIsNotEmpty("c", "storage_blocks/refined_obsidian")) {
                event.accept(REFINED_OBSIDIAN_HAMMER.get().getDefaultInstance());
            }
            if(RegistryUtils.itemTagIsNotEmpty("c", "storage_blocks/refined_glowstone")) {
                event.accept(REFINED_GLOWSTONE_HAMMER.get().getDefaultInstance());
            }
            if(RegistryUtils.modItemExists("ae2", "fluix_block")) {
                event.accept(FLUIX_HAMMER.get().getDefaultInstance());
            }
            if(RegistryUtils.modItemExists("ae2", "quartz_block")) {
                event.accept(CERTUS_QUARTZ_HAMMER.get().getDefaultInstance());
            }
            if(RegistryUtils.modItemExists("ae2", "nether_quartz_pickaxe")) {
                event.accept(NETHER_QUARTZ_HAMMER.get().getDefaultInstance());
            }
            if(RegistryUtils.modItemExists("mekanismtools", "lapis_lazuli_pickaxe")) {
                event.accept(LAPIS_LAZULI_HAMMER.get().getDefaultInstance());
            }
        }
    }


}
