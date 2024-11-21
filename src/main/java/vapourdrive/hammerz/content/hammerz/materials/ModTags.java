package vapourdrive.hammerz.content.hammerz.materials;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import vapourdrive.hammerz.Hammerz;

import static vapourdrive.vapourware.shared.utils.RegistryUtils.getBlockTag;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_OSMIUM_TOOL = getBlockTag(Hammerz.MODID, "needs_osmium_tool");
        public static final TagKey<Block> INCORRECT_FOR_OSMIUM_TOOL = getBlockTag(Hammerz.MODID,"incorrect_for_osmium_tool");

//        private static TagKey<Block> createTag(String name) {
//            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(Hammerz.MODID, name));
//        }
    }

//    public static class Items {
//        public static final TagKey<Item> TRANSFORMABLE_ITEMS = createTag("transformable_items");
//
//        private static TagKey<Item> createTag(String name) {
//            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(Hammerz.MODID, name));
//        }
//    }
}
