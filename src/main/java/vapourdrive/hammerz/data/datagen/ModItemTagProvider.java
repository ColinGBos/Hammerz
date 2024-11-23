package vapourdrive.hammerz.data.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vapourdrive.hammerz.Hammerz;
import vapourdrive.hammerz.content.hammerz.materials.ModTags;
import vapourdrive.hammerz.setup.Registration;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {

        public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, Hammerz.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(ItemTags.PICKAXES).add(Registration.WOOD_HAMMER.get());
        tag(ItemTags.PICKAXES).add(Registration.STONE_HAMMER.get());
        tag(ItemTags.PICKAXES).add(Registration.IRON_HAMMER.get());
        tag(ItemTags.PICKAXES).add(Registration.GOLD_HAMMER.get());
        tag(ItemTags.PICKAXES).add(Registration.DIAMOND_HAMMER.get());
        tag(ItemTags.PICKAXES).add(Registration.NETHERITE_HAMMER.get());
        tag(ItemTags.PICKAXES).add(Registration.OSMIUM_HAMMER.get());
        tag(ItemTags.PICKAXES).add(Registration.BRONZE_HAMMER.get());
        tag(ItemTags.PICKAXES).add(Registration.STEEL_HAMMER.get());
        tag(ItemTags.PICKAXES).add(Registration.REFINED_OBSIDIAN_HAMMER.get());
        tag(ItemTags.PICKAXES).add(Registration.REFINED_GLOWSTONE_HAMMER.get());
        tag(ItemTags.PICKAXES).add(Registration.LAPIS_LAZULI_HAMMER.get());
        tag(ItemTags.PICKAXES).add(Registration.FLUIX_HAMMER.get());

        tag(ModTags.Items.QUARTZ_HAMMERS).add(Registration.CERTUS_QUARTZ_HAMMER.get());
        tag(ModTags.Items.QUARTZ_HAMMERS).add(Registration.NETHER_QUARTZ_HAMMER.get());

    }
}
