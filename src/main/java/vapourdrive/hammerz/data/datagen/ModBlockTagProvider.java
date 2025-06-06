package vapourdrive.hammerz.data.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vapourdrive.hammerz.Hammerz;
import vapourdrive.hammerz.content.hammerz.materials.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Hammerz.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        this.tag(ModTags.Blocks.NEEDS_DUSKBLOOM_TOOL).addTags(BlockTags.NEEDS_IRON_TOOL);
        this.tag(ModTags.Blocks.INCORRECT_FOR_DUSKBLOOM_TOOL).addTags(BlockTags.INCORRECT_FOR_IRON_TOOL).remove(ModTags.Blocks.NEEDS_DUSKBLOOM_TOOL);

        this.tag(ModTags.Blocks.NEEDS_OSMIUM_TOOL).addTags(BlockTags.NEEDS_IRON_TOOL);
        this.tag(ModTags.Blocks.INCORRECT_FOR_OSMIUM_TOOL).addTags(BlockTags.INCORRECT_FOR_IRON_TOOL).remove(ModTags.Blocks.NEEDS_OSMIUM_TOOL);

        this.tag(ModTags.Blocks.NEEDS_LAPIS_LAZULI_TOOL).addTags(BlockTags.NEEDS_STONE_TOOL);
        this.tag(ModTags.Blocks.INCORRECT_FOR_LAPIS_LAZULI_TOOL).addTags(BlockTags.INCORRECT_FOR_STONE_TOOL).remove(ModTags.Blocks.NEEDS_LAPIS_LAZULI_TOOL);

        this.tag(ModTags.Blocks.NEEDS_BRONZE_TOOL).addTags(BlockTags.NEEDS_IRON_TOOL);
        this.tag(ModTags.Blocks.INCORRECT_FOR_BRONZE_TOOL).addTags(BlockTags.INCORRECT_FOR_IRON_TOOL).remove(ModTags.Blocks.NEEDS_BRONZE_TOOL);

        this.tag(ModTags.Blocks.NEEDS_STEEL_TOOL).addTags(BlockTags.NEEDS_DIAMOND_TOOL);
        this.tag(ModTags.Blocks.INCORRECT_FOR_STEEL_TOOL).addTags(BlockTags.INCORRECT_FOR_DIAMOND_TOOL).remove(ModTags.Blocks.NEEDS_STEEL_TOOL);

        this.tag(ModTags.Blocks.NEEDS_REFINED_OBSIDIAN_TOOL).addTags(BlockTags.NEEDS_DIAMOND_TOOL);
        this.tag(ModTags.Blocks.INCORRECT_FOR_REFINED_OBSIDIAN_TOOL).addTags(BlockTags.INCORRECT_FOR_DIAMOND_TOOL).remove(ModTags.Blocks.NEEDS_REFINED_OBSIDIAN_TOOL);

        this.tag(ModTags.Blocks.NEEDS_REFINED_GLOWSTONE_TOOL).addTags(BlockTags.NEEDS_DIAMOND_TOOL);
        this.tag(ModTags.Blocks.INCORRECT_FOR_REFINED_GLOWSTONE_TOOL).addTags(BlockTags.INCORRECT_FOR_DIAMOND_TOOL).remove(ModTags.Blocks.NEEDS_REFINED_GLOWSTONE_TOOL);

        this.tag(ModTags.Blocks.NEEDS_CERTUS_QUARTZ_TOOL).addTags(BlockTags.NEEDS_IRON_TOOL);
        this.tag(ModTags.Blocks.INCORRECT_FOR_CERTUS_QUARTZ_TOOL).addTags(BlockTags.INCORRECT_FOR_IRON_TOOL).remove(ModTags.Blocks.NEEDS_CERTUS_QUARTZ_TOOL);

        this.tag(ModTags.Blocks.NEEDS_NETHER_QUARTZ_TOOL).addTags(BlockTags.NEEDS_IRON_TOOL);
        this.tag(ModTags.Blocks.INCORRECT_FOR_NETHER_QUARTZ_TOOL).addTags(BlockTags.INCORRECT_FOR_IRON_TOOL).remove(ModTags.Blocks.NEEDS_NETHER_QUARTZ_TOOL);

        this.tag(ModTags.Blocks.NEEDS_FLUIX_TOOL).addTags(BlockTags.NEEDS_IRON_TOOL);
        this.tag(ModTags.Blocks.INCORRECT_FOR_FLUIX_TOOL).addTags(BlockTags.INCORRECT_FOR_IRON_TOOL).remove(ModTags.Blocks.NEEDS_FLUIX_TOOL);

    }
}
