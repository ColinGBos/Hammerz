package vapourdrive.hammerz.items;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.Level;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;
import vapourdrive.hammerz.Hammerz;
import vapourdrive.hammerz.config.ConfigOptions;
import vapourdrive.hammerz.config.HammerzConfig;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public class HZ_Items
{
	public static LinkedHashMap<Item, String> oreDictItems = new LinkedHashMap<Item, String>();
	public static Item WoodHammer;
	public static Item StoneHammer;
	public static Item IronHammer;
	public static Item GoldHammer;
	public static Item DiamondHammer;
	public static Item CopperHammer;
	public static Item TinHammer;
	public static Item SilverHammer;
	public static Item LeadHammer;
	public static Item NickelHammer;
	public static Item ElectrumHammer;
	public static Item InvarHammer;
	public static Item BronzeHammer;
	public static Item PlatinumHammer;
	public static Item DarkSteelHammer;
	public static Item SteelHammer;
	public static Item ThaumiumHammer;
	public static Item ManasteelHammer;
	public static Item ElvenElementiumHammer;
	public static Item HSLAHammer;
	public static Item BedrockHammer;
	public static Item VoidHammer;

	public static void preInit()
	{
		CopperHammer = new Hammer(EnumHelper.addToolMaterial("Copper", 1, 175, 4.0F, 0.5F, 6), "Copper");
		TinHammer = new Hammer(EnumHelper.addToolMaterial("Tin", 1, 200, 4.5F, 1.0F, 7), "Tin");
		SilverHammer = new Hammer(EnumHelper.addToolMaterial("Silver", 2, 200, 6.0F, 1.5F, 20), "Silver");
		LeadHammer = new Hammer(EnumHelper.addToolMaterial("Lead", 1, 150, 5F, 1.0F, 9), "Lead");
		NickelHammer = new Hammer(EnumHelper.addToolMaterial("Nickel", 2, 300, 6.5F, 2.5F, 18), "Nickel");
		ElectrumHammer = new Hammer(EnumHelper.addToolMaterial("Electrum", 0, 100, 14.0F, 0.5F, 30), "Electrum");
		InvarHammer = new Hammer(EnumHelper.addToolMaterial("Invar", 2, 450, 7.0F, 3.0F, 16), "Invar");
		BronzeHammer = new Hammer(EnumHelper.addToolMaterial("Bronze", 2, 500, 6.0F, 2.0F, 15), "Bronze");
		PlatinumHammer = new Hammer(EnumHelper.addToolMaterial("Platinum", 4, 1700, 9.0F, 4.0F, 9), "Platinum");
		SteelHammer = new Hammer(EnumHelper.addToolMaterial("Steel", 2, 500, 7.0F, 2.0F, 9), "Steel");
		
		if (Loader.isModLoaded("Thaumcraft"))
		{
			Hammerz.log.log(Level.INFO, "ThaumcraftCompat loading");

			ThaumiumHammer = new ThaumiumHammer("Thaumium");
			VoidHammer = new VoidHammer("Void");
			Hammerz.hasStorageBlock = true;
		}
		if (Loader.isModLoaded("Botania"))
		{
			Hammerz.log.log(Level.INFO, "BotaniaCompat loading");
			ManasteelHammer = new ManasteelHammer("Manasteel");
			ElvenElementiumHammer = new ElvenElementiumHammer("ElvenElementium");
		}
		if (Loader.isModLoaded("EnderIO"))
		{
			Hammerz.log.log(Level.INFO, "EnderIOCompat loading");

			DarkSteelHammer = new DarkHammer(EnumHelper.addToolMaterial("DarkSteel", 5, 1561, 7.0F, 2.0F, 25), "DarkSteel");
		}
		if(Loader.isModLoaded("RotaryCraft"))
		{
			Hammerz.log.log(Level.INFO, "RotaryCraftCompat loading");
			HSLAHammer = new Hammer(EnumHelper.addToolMaterial("HSLA", 2, 600, 6.0F, 3.0F, 14), "HSLA");
			BedrockHammer = new BedrockHammer(EnumHelper.addToolMaterial("Bedrock", 3, 0, 8.0F, 3.0F, 10), "Bedrock");
		}

		WoodHammer = new Hammer(ToolMaterial.WOOD, "Wood");
		StoneHammer = new Hammer(ToolMaterial.STONE, "Stone");
		IronHammer = new Hammer(ToolMaterial.IRON, "Iron");
		GoldHammer = new Hammer(ToolMaterial.GOLD, "Gold");
		DiamondHammer = new Hammer(ToolMaterial.EMERALD, "Diamond");

		setupOreDictionaryHammer(CopperHammer, "Copper", ConfigOptions.OreDictHammerEnabling, 0);
		setupOreDictionaryHammer(TinHammer, "Tin", ConfigOptions.OreDictHammerEnabling, 1);
		setupOreDictionaryHammer(SilverHammer, "Silver", ConfigOptions.OreDictHammerEnabling, 2);
		setupOreDictionaryHammer(LeadHammer, "Lead", ConfigOptions.OreDictHammerEnabling, 3);
		setupOreDictionaryHammer(NickelHammer, "Nickel", ConfigOptions.OreDictHammerEnabling, 4);
		setupOreDictionaryHammer(ElectrumHammer, "Electrum", ConfigOptions.OreDictHammerEnabling, 5);
		setupOreDictionaryHammer(InvarHammer, "Invar", ConfigOptions.OreDictHammerEnabling, 6);
		setupOreDictionaryHammer(BronzeHammer, "Bronze", ConfigOptions.OreDictHammerEnabling, 7);
		setupOreDictionaryHammer(PlatinumHammer, "Platinum", ConfigOptions.OreDictHammerEnabling, 8);
		setupOreDictionaryHammer(SteelHammer, "Steel", ConfigOptions.OreDictHammerEnabling, 9);
	}

	public static void init()
	{
		registerItem(WoodHammer, "WoodHammer", ConfigOptions.VanillaHammerEnabling, 0);
		registerItem(StoneHammer, "StoneHammer", ConfigOptions.VanillaHammerEnabling, 1);
		registerItem(IronHammer, "IronHammer", ConfigOptions.VanillaHammerEnabling, 2);
		registerItem(GoldHammer, "GoldHammer", ConfigOptions.VanillaHammerEnabling, 3);
		registerItem(DiamondHammer, "DiamondHammer", ConfigOptions.VanillaHammerEnabling, 4);

		if (Loader.isModLoaded("EnderIO"))
		{
			registerItem(DarkSteelHammer, "DarkSteelHammer", ConfigOptions.EnderIOHammerEnabling, 0);
		}
		if(Loader.isModLoaded("Botania"))
		{
			registerItem(ManasteelHammer, "Manasteel", ConfigOptions.BotaniaHammersEnabling, 0);
			registerItem(ElvenElementiumHammer, "ElvenElementium", ConfigOptions.BotaniaHammersEnabling, 1);
		}
		if (Loader.isModLoaded("Thaumcraft"))
		{
			registerItem(ThaumiumHammer, "Thaumium", ConfigOptions.ThaumcraftHammerEnabling, 0);
			registerItem(VoidHammer, "Void", ConfigOptions.ThaumcraftHammerEnabling, 1);
		}
		if(Loader.isModLoaded("RotaryCraft"))
		{
			registerItem(HSLAHammer, "HSLA", ConfigOptions.RotaryCraftHammerEnabling, 0);
			registerItem(BedrockHammer, "Bedrock", ConfigOptions.RotaryCraftHammerEnabling, 1);
		}
		
		registerOreDictHammers(oreDictItems, ConfigOptions.OreDictHammerEnabling);
	}

	public static void registerItem(Item item, String name, boolean[] hammerEnabling, int booleanCheck)
	{
		if (hammerEnabling[booleanCheck] == true)
		{
			GameRegistry.registerItem(item, name);
		}
	}

	private static void setupOreDictionaryHammer(Item item, String name, boolean[] hammerEnabling, int i)
	{
		setupOreDictionaryHammer(false,"", item, name, hammerEnabling, i);
	}
	
	private static void setupOreDictionaryHammer(Boolean requiresMod, String ModId, Item item, String name, boolean[] hammerEnabling, int i)
	{
		if (OreDictionary.doesOreNameExist("block" + name))
		{
			oreDictItems.put(item, name);
			HammerzConfig.enabledOreDictHammersComment = HammerzConfig.enabledOreDictHammersComment + name + ", ";
			HammerzConfig.enabledOreDictHammersArrayList.add(true);
		}
	}

	public static void registerOreDictHammers(HashMap<Item, String> map, boolean[] hammerEnabling)
	{
		Set set = map.entrySet();
		Iterator iterator = set.iterator();
		int i = 0;
		while (iterator.hasNext())
		{
			Map.Entry<Item, String> entry = (Map.Entry<Item, String>) iterator.next();
			if (hammerEnabling[i] == true)
			{
				GameRegistry.registerItem((Item) entry.getKey(), (String) entry.getValue() + "Hammer");
			}
			i++;
		}
	}
}
