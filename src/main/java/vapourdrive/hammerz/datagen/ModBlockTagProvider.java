package vapourdrive.hammerz.datagen;

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
        this.tag(ModTags.Blocks.NEEDS_OSMIUM_TOOL).addTags(BlockTags.NEEDS_IRON_TOOL);
        this.tag(ModTags.Blocks.INCORRECT_FOR_OSMIUM_TOOL).addTags(BlockTags.INCORRECT_FOR_IRON_TOOL).remove(ModTags.Blocks.NEEDS_OSMIUM_TOOL);

        this.tag(ModTags.Blocks.NEEDS_CERTUS_QUARTZ_TOOL).addTags(BlockTags.NEEDS_IRON_TOOL);
        this.tag(ModTags.Blocks.INCORRECT_FOR_CERTUS_QUARTZ_TOOL).addTags(BlockTags.INCORRECT_FOR_IRON_TOOL).remove(ModTags.Blocks.NEEDS_CERTUS_QUARTZ_TOOL);

        this.tag(ModTags.Blocks.NEEDS_NETHER_QUARTZ_TOOL).addTags(BlockTags.NEEDS_IRON_TOOL);
        this.tag(ModTags.Blocks.INCORRECT_FOR_NETHER_QUARTZ_TOOL).addTags(BlockTags.INCORRECT_FOR_IRON_TOOL).remove(ModTags.Blocks.NEEDS_NETHER_QUARTZ_TOOL);

        this.tag(ModTags.Blocks.NEEDS_FLUIX_TOOL).addTags(BlockTags.NEEDS_IRON_TOOL);
        this.tag(ModTags.Blocks.INCORRECT_FOR_FLUIX_TOOL).addTags(BlockTags.INCORRECT_FOR_IRON_TOOL).remove(ModTags.Blocks.NEEDS_FLUIX_TOOL);

    }
}
