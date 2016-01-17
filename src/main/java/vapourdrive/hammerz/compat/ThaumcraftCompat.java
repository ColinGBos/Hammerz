package vapourdrive.hammerz.compat;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Optional;

import org.apache.logging.log4j.Level;

import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;
import vapourdrive.hammerz.Hammerz;
import vapourdrive.hammerz.Recipes;
import vapourdrive.hammerz.utils.RandomUtils;

@Optional.Interface(modid = "Thaumcraft", iface = "thaumcraft.api.research.ResearchCatagories", striprefs = true)
public class ThaumcraftCompat
{
	public static void init()
	{
		Hammerz.log.log(Level.INFO, "Initializing Thaumcraft Research Module");

		ResearchCategories.registerCategory("HAMMERZ", null, new ResourceLocation("hammerz", "textures/misc/hammerzTab.png"),
				new ResourceLocation("hammerz:textures/misc/research.png"), new ResourceLocation("thaumcraft:textures/gui/gui_research_back_over.png"));

		AspectList list = new AspectList().add(Aspect.TOOL, 3).add(Aspect.METAL, 2).add(Aspect.EXCHANGE, 2);

		ResearchCategories.addResearch(getResearchItem("THAUMIUM_HAMMER", list, -2, 0, 0, RandomUtils.getHammer("thaumium"),
				Recipes.recipes.get("thaumium"), null, new String[]
				{
					"METALLURGY"
				}));

		AspectList list2 = new AspectList().add(Aspect.TOOL, 3).add(Aspect.VOID, 3).add(Aspect.DARKNESS, 2);
		ResearchCategories.addResearch(getResearchItem("VOID_HAMMER", list2, 0, 0, 0, RandomUtils.getHammer("void"),
				Recipes.recipes.get("void"), null, new String[]
				{
					"VOIDMETAL"
				}));

		InfusionRecipe infusionRecipe = addElementalHammerRecipe();
		AspectList list3 = new AspectList().add(Aspect.TOOL, 4).add(Aspect.FIRE, 2).add(Aspect.SENSES, 3);
		ResearchCategories.addResearch(getResearchItem("ELEMENTAL_HAMMER", list3, 2, 0, 3, RandomUtils.getHammer("thaumium_elemental"), null,
				infusionRecipe, new String[]
				{
					"ELEMENTALTOOLS"
				}));

		/*if (Loader.isModLoaded("ForbiddenMagic"))
		{
			InfusionRecipe infusionRecipe2 = addChameleonHammerRecipe();
			AspectList list4 = new AspectList().add(Aspect.TOOL, 4).add(Aspect.BEAST, 3).add(Aspect.ELDRITCH, 2);
			ResearchCategories.addResearch(getResearchItem("CHAMELEON_HAMMER", list4, 0, 2, 3, RandomUtils.getHammer("morph"),
					null, infusionRecipe2, new String[]
					{
						"MORPHTOOLS"
					}));
		}*/
	}

	public static ResearchItem getResearchItem(String key, AspectList aspects, int column, int row, int complexity, ItemStack displayStack,
			IRecipe recipe, InfusionRecipe infusionRecipe, String[] relations)
	{
		ResearchItem research = new ResearchItem(key, "HAMMERZ", aspects, column, row, complexity, displayStack);

		ResearchPage page1 = new ResearchPage("hammerz.research_page." + key + ".1");
		ResearchPage page2;
		if (recipe != null)
		{
			page2 = new ResearchPage(recipe);
		}
		else
		{
			page2 = new ResearchPage(infusionRecipe);
		}
		ResearchPage[] pages = new ResearchPage[]
		{
				page1, page2
		};
		research.setPages(pages);
		research.setParentsHidden(relations);
		research.registerResearchItem();
		return research;
	}

	public static InfusionRecipe addElementalHammerRecipe()
	{
		ItemStack crystal = new ItemStack(RandomUtils.getItemStackFromString("Thaumcraft", "shard", 1).getItem(), 1, 1);
		ItemStack log = new ItemStack(RandomUtils.getItemStackFromString("Thaumcraft", "log", 1).getItem(), 1, 0);
		ItemStack ThaumPick = RandomUtils.getItemStackFromString("Thaumcraft", "elemental_pick", 1);
		AspectList aspects = new AspectList().add(Aspect.FIRE, 38).add(Aspect.METAL, 38).add(Aspect.SENSES, 38);

		return ThaumcraftApi.addInfusionCraftingRecipe("ELEMENTAL_HAMMER", RandomUtils.getHammer("thaumium_elemental"), 2, aspects,
				RandomUtils.getHammer("thaumium"), new ItemStack[]
				{
						ThaumPick, new ItemStack(Blocks.diamond_block), crystal, crystal, crystal, crystal, log, log
				});
	}

	public static InfusionRecipe addChameleonHammerRecipe()
	{
		ItemStack shard = new ItemStack(RandomUtils.getItemStackFromString("ForbiddenMagic", "NetherShard", 1).getItem(), 1, 1);
		ItemStack log = new ItemStack(RandomUtils.getItemStackFromString("Thaumcraft", "ItemResource", 1).getItem(), 1, 3);
		ItemStack quicksilver = new ItemStack(RandomUtils.getItemStackFromString("Thaumcraft", "blockMagicalLog", 1).getItem(), 1, 1);
		ItemStack ChameleonHammer = new ItemStack(RandomUtils.getItemStackFromString("ForbiddenMagic", "MorphPickaxe", 1).getItem());
		AspectList aspects = new AspectList().add(Aspect.TOOL, 26).add(Aspect.BEAST, 26).add(Aspect.ELDRITCH, 26).add(Aspect.EXCHANGE, 38);

		return ThaumcraftApi.addInfusionCraftingRecipe("CHAMELEON_HAMMER", RandomUtils.getHammer("morph"), 6, aspects,
				RandomUtils.getHammer("thaumium"), new ItemStack[]
				{
						ChameleonHammer, new ItemStack(Blocks.diamond_block), shard, shard, shard, log, log, quicksilver, quicksilver
				});
	}
}
