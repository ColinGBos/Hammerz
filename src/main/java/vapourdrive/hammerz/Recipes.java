package vapourdrive.hammerz;

import java.util.HashMap;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

import net.minecraftforge.oredict.ShapelessOreRecipe;
import org.apache.logging.log4j.Level;

import vapourdrive.hammerz.items.HZ_Items;
import vapourdrive.hammerz.items.hammer.HammerInfoHandler;
import vapourdrive.hammerz.items.hammer.HammerType;
import vapourdrive.hammerz.items.hammer.ItemHammer;
import vapourdrive.hammerz.utils.RandomUtils;

@Optional.Interface(modid = "RotaryCraft", iface = "Reika.RotaryCraft.API.RecipeInterface", striprefs = true)
public class Recipes
{
	public static HashMap<String, IRecipe> recipes = new HashMap<String, IRecipe>();

	public static void init()
	{
		Hammerz.log.log(Level.INFO, "Starting hammerz recipe registering");
		registerHammerRecipe("stickWood", "wood", "logWood");
		registerHammerRecipe("stickWood", "stone", "stone");
		registerHammerRecipe("stickWood", "iron", "blockIron");
		registerHammerRecipe("stickWood", "gold", "blockGold");
		registerHammerRecipe("stickWood", "diamond", "blockDiamond");
		registerHammerRecipe("stickWood", "copper", "blockCopper");
		registerHammerRecipe("stickWood", "tin", "blockTin");
		registerHammerRecipe("stickWood", "silver", "blockSilver");
		registerHammerRecipe("stickWood", "lead", "blockLead");
		registerHammerRecipe("stickWood", "nickel", "blockNickel");
		registerHammerRecipe("stickWood", "electrum", "blockElectrum");
		registerHammerRecipe("stickWood", "invar", "blockInvar");
		registerHammerRecipe("stickWood", "bronze", "blockBronze");
		registerHammerRecipe("stickWood", "platinum", "blockPlatinum");
		registerHammerRecipe("stickWood", "steel", "blockSteel");
		registerHammerRecipe("livingwoodTwig", "manasteel", "blockManasteel");
		registerHammerRecipe("dreamwoodTwig", "b_elementium", "blockElvenElementium");

		registerHammerRecipe("stickWood", "soulforgedsteel", "blockSoulforgedSteel");

		registerHammerRecipe("stickWood", "darksteel", "blockDarkSteel");
		registerHammerRecipe("stickWood", "thaumium", "blockThaumium");
		registerHammerRecipe("stickWood", "void", "blockVoidMetal");

		if (Loader.isModLoaded("roots")) {
			registerLivingRecipe();
		}
	}

	public static void registerHammerRecipe(String stick, String material, String OreDict)
	{
		HammerType type = HammerInfoHandler.getHammerType(material);
		if (type != null)
		{
			ItemStack hammer = new ItemStack(HZ_Items.ItemHammer);
			NBTTagCompound tagCompound = RandomUtils.getNBT(hammer);
			tagCompound.setString(ItemHammer.HammerKey, type.getName());
			Hammerz.log.log(Level.INFO, "Registering " + material + " hammer out of " + stick + " handle material and " + OreDict + " body material");

			IRecipe recipe;
			recipe = new ShapedOreRecipe(hammer.copy(), "bbb", " s ", " s ", 's', stick, 'b', OreDict);
			GameRegistry.addRecipe(recipe);
			recipes.put(material, recipe);
		}
		else
		{
			Hammerz.log.log(Level.INFO, "hammer type: " + material + " is null");
		}
	}

	public static void registerRotaryCraftRecipe()
	{
		/*
		ItemStack hammer = new ItemStack(HZ_Items.BedrockHammer);
		if (ConfigOptions.RotaryCraftSilkTouch)
		{
			hammer.addEnchantment(Enchantment.silkTouch, 1);
		}
		ItemStack bedrock = new ItemStack(RandomUtils.getItemStackFromString("RotaryCraft", "rotarycraft_block_deco", 1).getItem(), 1, 4);
		ItemStack shaft = new ItemStack(RandomUtils.getItemStackFromString("RotaryCraft", "rotarycraft_item_shaftcraft", 1).getItem(), 1, 2);
		ShapedOreRecipe recipe = new ShapedOreRecipe(hammer, new Object[]
		{
				"DDD", " S ", " S ", 'D', bedrock, 'S', shaft
		});
		RecipeInterface.blastfurn.addAPIRecipe(hammer, 1000, recipe, 4, 0);
		recipes.put(hammer, recipe);

		*/

	}

	public static void registerLivingRecipe()
	{
		ItemStack hammer = new ItemStack(HZ_Items.ItemHammer);
		HammerType type = HammerInfoHandler.getHammerType("living");
		if(type != null) {
			NBTTagCompound tagCompound = RandomUtils.getNBT(hammer);
			tagCompound.setString(ItemHammer.HammerKey, type.getName());

			ItemStack woodHammer = new ItemStack(HZ_Items.ItemHammer);
			HammerType type2 = HammerInfoHandler.getHammerType("wood");
			NBTTagCompound tagCompound2 = RandomUtils.getNBT(woodHammer);
			tagCompound2.setString(ItemHammer.HammerKey, type2.getName());

			ItemStack sprig = new ItemStack(RandomUtils.getItemStackFromString("roots", "verdantSprig", 1).getItem());
			ItemStack oakTreeBark = new ItemStack(RandomUtils.getItemStackFromString("roots", "oakTreeBark", 1).getItem());
			ItemStack goldBlock = new ItemStack(Blocks.GOLD_BLOCK);

			if (sprig != null && oakTreeBark != null) {
				Hammerz.log.log(Level.INFO, "Registering living hammer recipe");
				ShapelessOreRecipe recipe = new ShapelessOreRecipe(hammer, new Object[]
						{
								woodHammer, sprig, sprig, sprig, sprig, oakTreeBark, oakTreeBark, oakTreeBark, goldBlock
						});
				GameRegistry.addRecipe(recipe);
				recipes.put("living", recipe);
			} else {
				Hammerz.log.log(Level.INFO, "hammer type: living is null");
			}
		}
	}
}
