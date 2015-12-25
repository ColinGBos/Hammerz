package vapourdrive.hammerz.compat;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;

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
import vapourdrive.hammerz.config.ConfigOptions;
import vapourdrive.hammerz.items.HZ_Items;
import vapourdrive.hammerz.utils.RandomUtils;
import cpw.mods.fml.common.Optional;

@Optional.Interface(modid = "Thaumcraft", iface = "thaumcraft.api.research.ResearchCatagories", striprefs = true)
public class ThaumcraftCompat
{
	public static void init()
	{
		Hammerz.log.log(Level.INFO, "Initializing Thaumcraft Research Module");

		ResearchCategories.registerCategory("HAMMERZ", new ResourceLocation("hammerz", "textures/misc/hammerzTab.png"),
				new ResourceLocation("thaumcraft", "textures/gui/gui_researchback.png"));
		
		if(ConfigOptions.ThaumcraftHammerEnabling[0] == true)
		{
			ResearchCategories.addResearch(getResearchItem("THAUMIUM_HAMMER", null, -2, 0, 0, new ItemStack(HZ_Items.ThaumiumHammer), Recipes.recipes.get(HZ_Items.ThaumiumHammer), new String[]{"THAUMIUM"}));
		}
		if(ConfigOptions.ThaumcraftHammerEnabling[1] == true)
		{
			ResearchCategories.addResearch(getResearchItem("VOID_HAMMER", null, 0, 0, 0, new ItemStack(HZ_Items.VoidHammer), Recipes.recipes.get(HZ_Items.VoidHammer), new String[]{"VOIDMETAL"}));
		}
		if(ConfigOptions.ThaumcraftHammerEnabling[2] == true)
		{
			InfusionRecipe recipe = addElementalHammerRecipe();
			AspectList list = new AspectList().add(Aspect.TOOL, 3).add(Aspect.FIRE, 3).add(Aspect.SENSES, 3);
			ResearchCategories.addResearch(getResearchItem("ELEMENTAL_HAMMER", list, 2, 0, 3, new ItemStack(HZ_Items.ElementalHammer), recipe, new String[]{"ELEMENTALPICK"}));
		}
	}
	
	public static ResearchItem getResearchItem(String key, AspectList aspects, int column, int row, int complexity, ItemStack displayStack, InfusionRecipe recipe, String[] parents)
	{
		ResearchItem research = new ResearchItem(key, "HAMMERZ", aspects, column, row, complexity, displayStack);
		
		ResearchPage page1 = new ResearchPage("hammerz.research_page." + key + ".1");
		ResearchPage page2 = new ResearchPage(recipe);
		
		ResearchPage[] pages = new ResearchPage[]{page1, page2};
		research.setPages(pages);
		research.setParents(parents);
		research.registerResearchItem();
		return research;
	}
	
	public static ResearchItem getResearchItem(String key, AspectList aspects, int column, int row, int complexity, ItemStack displayStack, IRecipe recipe, String[] parents)
	{
		ResearchItem research = new ResearchItem(key, "HAMMERZ", aspects, column, row, complexity, displayStack);
		
		ResearchPage page1 = new ResearchPage("hammerz.research_page." + key + ".1");
		ResearchPage page2 = new ResearchPage(recipe);
		
		ResearchPage[] pages = new ResearchPage[]{page1, page2};
		research.setPages(pages);
		research.setParents(parents);
		research.registerResearchItem();
		return research;
	}

	public static InfusionRecipe addElementalHammerRecipe()
	{
		ItemStack crystal = new ItemStack(RandomUtils.getItemStackFromString("Thaumcraft", "blockCrystal", 1).getItem(), 1, 1);
		ItemStack log = new ItemStack(RandomUtils.getItemStackFromString("Thaumcraft", "blockMagicalLog", 1).getItem(), 1, 0);
		ItemStack ThaumPick = RandomUtils.getItemStackFromString("Thaumcraft", "ItemPickaxeElemental", 1);
		AspectList aspects = new AspectList().add(Aspect.FIRE, 38).add(Aspect.TOOL, 38).add(Aspect.SENSES, 38);

		return ThaumcraftApi.addInfusionCraftingRecipe("ELEMENTALTOOLS", new ItemStack(HZ_Items.ElementalHammer), 2, aspects,
				new ItemStack(HZ_Items.ThaumiumHammer), new ItemStack[]
				{
						ThaumPick, new ItemStack(Blocks.diamond_block), crystal, crystal, log, log
				});
	}
}
