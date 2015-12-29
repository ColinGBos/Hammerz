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
import cpw.mods.fml.common.Loader;
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
			AspectList list = new AspectList().add(Aspect.TOOL, 3).add(Aspect.METAL, 2).add(Aspect.MAGIC, 2);
			ResearchCategories.addResearch(getResearchItem("THAUMIUM_HAMMER", list, -2, 0, 0, new ItemStack(HZ_Items.ThaumiumHammer), Recipes.recipes.get(HZ_Items.ThaumiumHammer), null, new String[]{"THAUMIUM"}));
		}
		if(ConfigOptions.ThaumcraftHammerEnabling[1] == true)
		{
			AspectList list = new AspectList().add(Aspect.TOOL, 3).add(Aspect.VOID, 3).add(Aspect.DARKNESS, 2);
			ResearchCategories.addResearch(getResearchItem("VOID_HAMMER", list, 0, 0, 0, new ItemStack(HZ_Items.VoidHammer), Recipes.recipes.get(HZ_Items.VoidHammer), null, new String[]{"VOIDMETAL"}));
		}
		if(ConfigOptions.ThaumcraftHammerEnabling[2] == true)
		{
			InfusionRecipe infusionRecipe = addElementalHammerRecipe();
			AspectList list = new AspectList().add(Aspect.TOOL, 4).add(Aspect.FIRE, 2).add(Aspect.SENSES, 3);
			ResearchCategories.addResearch(getResearchItem("ELEMENTAL_HAMMER", list, 2, 0, 3, new ItemStack(HZ_Items.ElementalHammer), null, infusionRecipe, new String[]{"ELEMENTALPICK"}));
		}
		
		if(ConfigOptions.ForbiddenMagicHammerEnabling[0] == true && Loader.isModLoaded("ForbiddenMagic"))
		{
			InfusionRecipe infusionRecipe = addChameleonHammerRecipe();
			AspectList list = new AspectList().add(Aspect.TOOL, 4).add(Aspect.BEAST, 3).add(Aspect.ELDRITCH, 2);
			ResearchCategories.addResearch(getResearchItem("CHAMELEON_HAMMER", list, 0, 2, 3, new ItemStack(HZ_Items.ChameleonHammer), null, infusionRecipe, new String[]{"MORPHTOOLS"}));
		}
	}
	
	public static ResearchItem getResearchItem(String key, AspectList aspects, int column, int row, int complexity, ItemStack displayStack, IRecipe recipe, InfusionRecipe infusionRecipe, String[] relations)
	{
		ResearchItem research = new ResearchItem(key, "HAMMERZ", aspects, column, row, complexity, displayStack);
		
		ResearchPage page1 = new ResearchPage("hammerz.research_page." + key + ".1");
		ResearchPage page2;
		if(recipe != null)
		{
			page2 = new ResearchPage(recipe);
		}
		else
		{
			page2 = new ResearchPage(infusionRecipe);
		}
		ResearchPage[] pages = new ResearchPage[]{page1, page2};
		research.setPages(pages);
		research.setParentsHidden(relations);
		research.registerResearchItem();
		return research;
	}

	public static InfusionRecipe addElementalHammerRecipe()
	{
		ItemStack crystal = new ItemStack(RandomUtils.getItemStackFromString("Thaumcraft", "blockCrystal", 1).getItem(), 1, 1);
		ItemStack log = new ItemStack(RandomUtils.getItemStackFromString("Thaumcraft", "blockMagicalLog", 1).getItem(), 1, 0);
		ItemStack ThaumPick = RandomUtils.getItemStackFromString("Thaumcraft", "ItemPickaxeElemental", 1);
		AspectList aspects = new AspectList().add(Aspect.FIRE, 38).add(Aspect.TOOL, 38).add(Aspect.SENSES, 38);

		return ThaumcraftApi.addInfusionCraftingRecipe("ELEMENTAL_HAMMER", new ItemStack(HZ_Items.ElementalHammer), 2, aspects,
				new ItemStack(HZ_Items.ThaumiumHammer), new ItemStack[]
				{
						ThaumPick, new ItemStack(Blocks.diamond_block), crystal, crystal, log, log
				});
	}
	public static InfusionRecipe addChameleonHammerRecipe()
	{
		ItemStack shard = new ItemStack(RandomUtils.getItemStackFromString("ForbiddenMagic", "NetherShard", 1).getItem(), 1, 1);
		ItemStack log = new ItemStack(RandomUtils.getItemStackFromString("Thaumcraft", "ItemResource", 1).getItem(), 1, 3);
		ItemStack quicksilver = new ItemStack(RandomUtils.getItemStackFromString("Thaumcraft", "blockMagicalLog", 1).getItem(), 1, 1);
		ItemStack ChameleonHammer = new ItemStack(RandomUtils.getItemStackFromString("ForbiddenMagic", "MorphPickaxe", 1).getItem());
		AspectList aspects = new AspectList().add(Aspect.TOOL, 26).add(Aspect.BEAST, 26).add(Aspect.ELDRITCH, 26).add(Aspect.EXCHANGE, 38);

		return ThaumcraftApi.addInfusionCraftingRecipe("CHAMELEON_HAMMER", new ItemStack(HZ_Items.ChameleonHammer), 6, aspects,
				new ItemStack(HZ_Items.ThaumiumHammer), new ItemStack[]
				{
						ChameleonHammer, new ItemStack(Blocks.diamond_block), shard, shard, shard, log, log, quicksilver, quicksilver
				});
	}
}
