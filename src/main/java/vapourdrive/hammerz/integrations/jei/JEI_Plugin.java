package vapourdrive.hammerz.integrations.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IModIngredientRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import vapourdrive.hammerz.Hammerz;
import vapourdrive.hammerz.config.ConfigSettings;
import vapourdrive.hammerz.setup.Registration;
import vapourdrive.vapourware.shared.utils.DeferredComponent;

@JeiPlugin
public class JEI_Plugin implements IModPlugin {


    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(Hammerz.MODID, "jei_plugin");
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
//        registration.addIngredientInfo(new ItemStack(Registration.PRIMITIVE_QUARRY_ITEM.get()), VanillaTypes.ITEM_STACK, Component.translatable("primitivequarry.primitive_quarry.info"));
        DeferredComponent comp = new DeferredComponent(Hammerz.MODID, "hammerz.info");
        registration.addIngredientInfo(new ItemStack(Registration.WOOD_HAMMER.get()), VanillaTypes.ITEM_STACK, comp.get());
    }

}
