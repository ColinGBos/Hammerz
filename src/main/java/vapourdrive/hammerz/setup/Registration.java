package vapourdrive.hammerz.setup;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import vapourdrive.hammerz.content.hammerz.*;
import vapourdrive.hammerz.content.hammerz.materials.ToolTiers;
import vapourdrive.vapourware.shared.utils.RegistryUtils;

import java.util.function.Supplier;

import static vapourdrive.hammerz.Hammerz.MODID;

public class Registration {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM, MODID);

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
    public static final Supplier<Item> NETHER_QUARTZ_HAMMER = ITEMS.register("nether_quartz_hammer", () -> new HammerItem(ToolTiers.NETHER_QUARTZ));
    public static final Supplier<Item> CERTUS_QUARTZ_HAMMER = ITEMS.register("certus_quartz_hammer", () -> new HammerItem(ToolTiers.CERTUS_QUARTZ));
    public static final Supplier<Item> FLUIX_HAMMER = ITEMS.register("fluix_hammer", () -> new FluixHammerItem(ToolTiers.FLUIX));

    public static void init(IEventBus eventBus) {
        ITEMS.register(eventBus);

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
            if(RegistryUtils.modItemExists("ae2", "fluix_block")) {
                event.accept(FLUIX_HAMMER.get().getDefaultInstance());
            }
            if(RegistryUtils.modItemExists("ae2", "quartz_block")) {
                event.accept(CERTUS_QUARTZ_HAMMER.get().getDefaultInstance());
            }
            if(RegistryUtils.modItemExists("ae2", "nether_quartz_pickaxe")) {
                event.accept(NETHER_QUARTZ_HAMMER.get().getDefaultInstance());
            }
        }
    }


}
