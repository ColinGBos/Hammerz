package vapourdrive.hammerz.content.hammerz.materials;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

import vapourdrive.vapourware.shared.utils.RegistryUtils;

public class ToolTiers {

    public static final Tier WOOD = new SimpleTier(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 59, 2.0f, 0.0f, 15,
            () -> Ingredient.of(ItemTags.LOGS));
    public static final Tier STONE = new SimpleTier(BlockTags.INCORRECT_FOR_STONE_TOOL, 131, 4.0f, 1.0f, 5,
            () -> Ingredient.of(ItemTags.STONE_TOOL_MATERIALS));
    public static final Tier IRON = new SimpleTier(BlockTags.INCORRECT_FOR_IRON_TOOL, 250, 6.0F, 2.0F, 14,
            () -> RegistryUtils.getIngredientFromTag("c", "storage_blocks/iron"));
    public static final Tier GOLD = new SimpleTier(BlockTags.INCORRECT_FOR_GOLD_TOOL, 32, 12.0F, 0.0F, 22,
            () -> RegistryUtils.getIngredientFromTag("c", "storage_blocks/gold"));
    public static final Tier DIAMOND = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1561, 8.0F, 3.0F, 10,
            () -> RegistryUtils.getIngredientFromTag("c", "storage_blocks/diamond"));
    public static final Tier NETHERITE = new SimpleTier(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 2031, 9.0F, 4.0F, 15,
            () -> RegistryUtils.getIngredientFromTag("c", "storage_blocks/netherite"));
    public static final Tier OSMIUM = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_OSMIUM_TOOL, 1024, 4.0f, 4.0f, 14,
            () -> RegistryUtils.getIngredientFromTag("c", "storage_blocks/osmium"));
    public static final Tier CERTUS_QUARTZ = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_CERTUS_QUARTZ_TOOL, 250, 6.0F, 2.0F, 14,
            () -> Ingredient.of(RegistryUtils.getModItem("ae2", "quartz_block")));
    public static final Tier NETHER_QUARTZ = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_NETHER_QUARTZ_TOOL, 250, 6.0F, 2.0F, 14,
            () -> Ingredient.of(Items.QUARTZ_BLOCK));
    public static final Tier FLUIX = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_FLUIX_TOOL, 750, 7.5f, 2.4f, 14,
            () -> Ingredient.of(RegistryUtils.getModItem("ae2", "fluix_block")));


}
