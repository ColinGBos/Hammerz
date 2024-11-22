package vapourdrive.hammerz.content.hammerz.materials;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import vapourdrive.hammerz.Hammerz;
import vapourdrive.vapourware.shared.utils.RegistryUtils;


public class ModTags {
    public static class Blocks {

        public static final TagKey<Block> NEEDS_OSMIUM_TOOL = RegistryUtils.getBlockTag(Hammerz.MODID, "needs_osmium_tool");
        public static final TagKey<Block> INCORRECT_FOR_OSMIUM_TOOL = RegistryUtils.getBlockTag(Hammerz.MODID,"incorrect_for_osmium_tool");

        public static final TagKey<Block> NEEDS_CERTUS_QUARTZ_TOOL = RegistryUtils.getBlockTag(Hammerz.MODID, "needs_certus_quartz_tool");
        public static final TagKey<Block> INCORRECT_FOR_CERTUS_QUARTZ_TOOL = RegistryUtils.getBlockTag(Hammerz.MODID,"incorrect_for_certus_quartz_tool");

        public static final TagKey<Block> NEEDS_NETHER_QUARTZ_TOOL = RegistryUtils.getBlockTag(Hammerz.MODID, "needs_nether_quartz_tool");
        public static final TagKey<Block> INCORRECT_FOR_NETHER_QUARTZ_TOOL = RegistryUtils.getBlockTag(Hammerz.MODID,"incorrect_for_nether_quartz_tool");

        public static final TagKey<Block> NEEDS_FLUIX_TOOL = RegistryUtils.getBlockTag(Hammerz.MODID, "needs_fluix_tool");
        public static final TagKey<Block> INCORRECT_FOR_FLUIX_TOOL = RegistryUtils.getBlockTag(Hammerz.MODID,"incorrect_for_fluix_tool");

//        private static TagKey<Block> createTag(String name) {
//            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(Hammerz.MODID, name));
//        }
    }

    public static class Items {
        public static final TagKey<Item> QUARTZ_HAMMERS = RegistryUtils.getItemTag(Hammerz.MODID, "quartz_hammers");
    }
}
