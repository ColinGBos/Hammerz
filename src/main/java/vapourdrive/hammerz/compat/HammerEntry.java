package vapourdrive.hammerz.compat;

import net.minecraft.item.ItemStack;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.LexiconCategory;

public class HammerEntry extends HammerzLexicon
{

	public HammerEntry(String recipeName, boolean isElven, ItemStack stack, String name, LexiconCategory category)
	{
		super(name, category);
		this.setIcon(stack);
		this.addPage(BotaniaAPI.internalHandler.textPage("hammerz.lexicon." + name.toLowerCase() + ".text"));/*
		this.addPage(BotaniaAPI.internalHandler.craftingRecipePage("hammerz.lexicon." + name.toLowerCase() + ".recipe",
				Recipes.recipes.get(recipeName)));*/

		if(isElven)
		{
			this.setKnowledgeType(BotaniaAPI.elvenKnowledge);
		}
	}

}
