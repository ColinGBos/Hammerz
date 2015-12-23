package vapourdrive.hammerz;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import vapourdrive.hammerz.config.ConfigOptions;
import vapourdrive.hammerz.items.HZ_Items;
import vapourdrive.hammerz.utils.RandomUtils;
import Reika.RotaryCraft.API.RecipeInterface;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.registry.GameRegistry;

@Optional.Interface(modid = "RotaryCraft", iface = "Reika.RotaryCraft.API.RecipeInterface", striprefs = true)
public class Recipes
{
	public static void init()
	{
		registerHammerRecipe("stickWood", HZ_Items.WoodHammer, Blocks.log);
		registerHammerRecipe("stickWood", HZ_Items.StoneHammer, Blocks.stone);
		registerHammerRecipe("stickWood", HZ_Items.IronHammer, Blocks.iron_block);
		registerHammerRecipe("stickWood", HZ_Items.GoldHammer, Blocks.gold_block);
		registerHammerRecipe("stickWood", HZ_Items.DiamondHammer, Blocks.diamond_block);
		registerHammerRecipe("stickWood", HZ_Items.DarkSteelHammer, "blockDarkSteel");
		registerHammerRecipe("stickWood", HZ_Items.CopperHammer, "blockCopper");
		registerHammerRecipe("stickWood", HZ_Items.TinHammer, "blockTin");
		registerHammerRecipe("stickWood", HZ_Items.SilverHammer, "blockSilver");
		registerHammerRecipe("stickWood", HZ_Items.LeadHammer, "blockLead");
		registerHammerRecipe("stickWood", HZ_Items.NickelHammer, "blockNickel");
		registerHammerRecipe("stickWood", HZ_Items.ElectrumHammer, "blockElectrum");
		registerHammerRecipe("stickWood", HZ_Items.InvarHammer, "blockInvar");
		registerHammerRecipe("stickWood", HZ_Items.BronzeHammer, "blockBronze");
		registerHammerRecipe("stickWood", HZ_Items.PlatinumHammer, "blockPlatinum");
		registerHammerRecipe("stickWood", HZ_Items.SteelHammer, "blockSteel");
		registerHammerRecipe("stickWood", HZ_Items.ThaumiumHammer, "blockThaumium");
		registerHammerRecipe("livingwoodTwig", HZ_Items.ManasteelHammer, "blockManasteel");
		registerHammerRecipe("dreamwoodTwig", HZ_Items.ElvenElementiumHammer, "blockElvenElementium");
	}

	public static void registerHammerRecipe(String stick, Item result, Block block)
	{
		registerHammerRecipe(stick, result, new ItemStack(block));
	}

	public static void registerHammerRecipe(String stick, Item result, Item item)
	{
		registerHammerRecipe(stick, result, new ItemStack(item));
	}

	public static void registerHammerRecipe(String stick, Item result, ItemStack stack)
	{
		int[] ids = OreDictionary.getOreIDs(stack);
		for (int i = 0; i < ids.length; i++)
		{
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(result), new Object[]
			{
					"bbb", " s ", " s ", 's', stick, 'b', OreDictionary.getOreName(ids[i])
			}));
		}
	}

	public static void registerHammerRecipe(String stick, Item result, String OreDict)
	{
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(result), new Object[]
		{
				"bbb", " s ", " s ", 's', stick, 'b', OreDict
		}));
	}

	public static void registerRotaryCraftRecipe()
	{
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
	}
}
