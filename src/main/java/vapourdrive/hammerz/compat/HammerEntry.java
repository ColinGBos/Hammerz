package vapourdrive.hammerz.compat;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import vapourdrive.hammerz.Recipes;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.LexiconCategory;

public class HammerEntry extends HammerzLexicon
{

	public HammerEntry(boolean isElven, Item item, String name, LexiconCategory category)
	{
		super(name, category);
		this.setIcon(new ItemStack(item));
		this.addPage(BotaniaAPI.internalHandler.textPage("hammerz.lexicon." + name.toLowerCase() + ".text"));
		this.addPage(BotaniaAPI.internalHandler.craftingRecipePage("hammerz.lexicon." + name.toLowerCase() + ".recipe",
				Recipes.recipes.get(item)));

		if(isElven)
		{
			this.setKnowledgeType(BotaniaAPI.elvenKnowledge);
		}
	}

}
