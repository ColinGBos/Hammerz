package vapourdrive.hammerz.compat;

import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.IAddonEntry;
import vazkii.botania.api.lexicon.LexiconCategory;
import vazkii.botania.api.lexicon.LexiconEntry;

public class HammerzLexicon extends LexiconEntry implements IAddonEntry
{

	public HammerzLexicon(String name, LexiconCategory category)
	{
		super(name, category);
		BotaniaAPI.addEntry(this, category);
	}

	@Override
	public String getSubtitle()
	{
		return "[Hammerz x Botania]";
	}
	
	@Override
    public String getUnlocalizedName() {
        return "hammerz.lexicon." + super.getUnlocalizedName().toLowerCase();
    }

}
