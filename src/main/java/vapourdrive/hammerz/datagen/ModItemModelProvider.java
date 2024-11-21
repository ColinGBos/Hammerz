package vapourdrive.hammerz.datagen;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import vapourdrive.hammerz.Hammerz;
import vapourdrive.hammerz.setup.Registration;

public class ModItemModelProvider  extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Hammerz.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        itemHammerModel(Registration.OSMIUM_HAMMER.get());
        itemHammerModel(Registration.NETHERITE_HAMMER.get());
//        handheldHammer(Registration.OSMIUM_HAMMER);
    }

    public void itemHammerModel(Item item) {
        String name = itemName(item);
        withExistingParent(name, ResourceLocation.fromNamespaceAndPath(Hammerz.MODID,"item/hammer")).texture("layer0", "item/"+name);
    }

    private String itemName(Item item) {
        return BuiltInRegistries.ITEM.getKey(item).getPath();
    }
}
