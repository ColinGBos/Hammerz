package vapourdrive.hammerz;

import com.google.gson.JsonObject;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.RegistryNamespaced;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.ShapedOreRecipe;
import org.apache.logging.log4j.Level;
import vapourdrive.hammerz.compat.HammerEntry;
import vapourdrive.hammerz.items.HZ_Items;
import vapourdrive.hammerz.items.hammer.HammerInfoHandler;
import vapourdrive.hammerz.items.hammer.HammerType;
import vapourdrive.hammerz.utils.RandomUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Iterator;

public class HammerRecipeFactory implements IRecipeFactory {
    public IRecipe parse(JsonContext context, JsonObject json) {
        ShapedOreRecipe recipe = ShapedOreRecipe.factory(context, json);
        CraftingHelper.ShapedPrimer primer = new CraftingHelper.ShapedPrimer();
        primer.width = recipe.getWidth();
        primer.height = recipe.getHeight();
        primer.mirrored = JsonUtils.getBoolean(json, "mirrored", true);
        primer.input = recipe.getIngredients();

        return new HammerRecipe(new ResourceLocation(Reference.ModID, "hammer_crafting"),
                recipe.getRecipeOutput(), primer);
    }

    public static class HammerRecipe extends ShapedOreRecipe {

        public HammerRecipe(ResourceLocation group, ItemStack result, CraftingHelper.ShapedPrimer primer) {
            super(group, result, primer);
        }

        @Override
        @Nonnull
        public ItemStack getCraftingResult(@Nonnull InventoryCrafting var1) {
            return getRecipeOutput();
        }

        @Nonnull
        @Override
        public ItemStack getRecipeOutput() {
            ItemStack craftingMaterial = this.input.get(0).getMatchingStacks()[0];
            String typeName = craftingMaterial.getUnlocalizedName();
            int lastColon = typeName.lastIndexOf(":");
            int lastPeriod = typeName.lastIndexOf(".");
            int nameStart = lastColon > lastPeriod ? lastColon : lastPeriod;

            typeName = typeName.substring(nameStart + 1).replaceFirst("block", "").replaceFirst("log", "");

            if(typeName.equals("steel_"))
                typeName = "SoulforgedSteel";
            if(typeName.equals("oak"))
                typeName = "wood";
            if(typeName.equals("storage0"))
                typeName = "Manasteel";
            if(typeName.equals("storage1"))
                typeName = "Terrasteel";
            if(typeName.equals("storage2"))
                typeName = "b_elementium";



            //Hammerz.log.log(Level.INFO, "Found Recipe for " + typeName + " hammer.");

            if(HammerInfoHandler.getHammerType(typeName) != null) {
                ItemStack newOutput;
                //Hammerz.log.log(Level.INFO, "Getting hammer type " + typeName);
                newOutput = RandomUtils.getHammer(typeName);

                //Hammerz.log.log(Level.INFO, "Recipe created successfully!");
                return newOutput;
            }
            else {
                return this.output;
            }
        }
    }
}
