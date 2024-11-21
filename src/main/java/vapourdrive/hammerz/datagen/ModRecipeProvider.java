package vapourdrive.hammerz.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.neoforged.neoforge.common.conditions.NotCondition;
import net.neoforged.neoforge.common.conditions.TagEmptyCondition;
import org.jetbrains.annotations.NotNull;
import vapourdrive.hammerz.Hammerz;
import vapourdrive.hammerz.setup.Registration;

import java.util.concurrent.CompletableFuture;

import static vapourdrive.vapourware.shared.utils.RegistryUtils.getIngredientFromTag;
import static vapourdrive.vapourware.shared.utils.RegistryUtils.getItemTag;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Registration.WOOD_HAMMER.get())
                .pattern("HHH").pattern(" R ").pattern(" R ")
                .define('H', ItemTags.LOGS)
                .define('R', getIngredientFromTag("c", "rods/wooden"))
                .unlockedBy("has_sticks", has(getItemTag("c", "rods/wooden")))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Registration.STONE_HAMMER.get())
                .pattern("HHH").pattern(" R ").pattern(" R ")
                .define('H', ItemTags.STONE_TOOL_MATERIALS)
                .define('R', getIngredientFromTag("c", "rods/wooden"))
                .unlockedBy("has_stone", has(ItemTags.STONE_TOOL_MATERIALS))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Registration.IRON_HAMMER.get())
                .pattern("HHH").pattern(" R ").pattern(" R ")
                .define('H', getIngredientFromTag("c", "storage_blocks/iron"))
                .define('R', getIngredientFromTag("c", "rods/wooden"))
                .unlockedBy("has_iron", has(getItemTag("c", "ingots/iron")))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Registration.GOLD_HAMMER.get())
                .pattern("HHH").pattern(" R ").pattern(" R ")
                .define('H', getIngredientFromTag("c", "storage_blocks/gold"))
                .define('R', getIngredientFromTag("c", "rods/wooden"))
                .unlockedBy("has_gold", has(getItemTag("c", "ingots/gold")))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Registration.DIAMOND_HAMMER.get())
                .pattern("HHH").pattern(" R ").pattern(" R ")
                .define('H', getIngredientFromTag("c", "storage_blocks/diamond"))
                .define('R', getIngredientFromTag("c", "rods/wooden"))
                .unlockedBy("has_diamond", has(getItemTag("c", "gems/diamond")))
                .save(output);
        SmithingTransformRecipeBuilder.smithing(
                Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                Ingredient.of(Registration.DIAMOND_HAMMER.get()),
                getIngredientFromTag("c", "storage_blocks/netherite"),
                RecipeCategory.TOOLS,
                Registration.NETHERITE_HAMMER.get()
        )
                .unlocks("has_netherite_ingot", has(Items.NETHERITE_INGOT))
                        .save(output, ResourceLocation.fromNamespaceAndPath(Hammerz.MODID, "netherite_hammer_smithing"));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Registration.OSMIUM_HAMMER.get())
                .pattern("HHH").pattern(" R ").pattern(" R ")
                .define('H', getIngredientFromTag("c", "storage_blocks/osmium"))
                .define('R', getIngredientFromTag("c", "rods/wooden"))
                .unlockedBy("has_osmium", has(getItemTag("c", "ingots/osmium")))
                .save(output.withConditions(
                        new NotCondition(new TagEmptyCondition("c","storage_blocks/osmium"))
                ));

    }
}
