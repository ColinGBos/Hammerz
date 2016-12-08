package vapourdrive.hammerz.items.hammer;

import net.minecraft.item.EnumRarity;


public class HammerType
{	
	private final String name;
	private final String blockName;
	private final int harvestLevel;
    private final int durability;
    private final float efficiency;
    private final float damage;
    private final float attackSpeed;
    private final int enchantability;
    /*
     * -1 doesn't take any damage
     * 0 takes regular stack damage
     * 1 takes mana damage
     * 2 takes energy damage
     * 3 can have repair on it
     */
    private final int damageType;
    private final int maxEnergyStored;
    private final EnumRarity rarity;
	
	public HammerType(int damageType, int maxEnergyStored, String blockName, String name, int harvestLevel, int durability, float efficiency, float damage, float attackSpeed, int enchantability, EnumRarity rarity) 
	{
		this.blockName = blockName;
		this.name = name;
		this.harvestLevel = harvestLevel;
		this.durability = durability;
		this.efficiency = efficiency;
		this.damage = damage;
		this.attackSpeed = attackSpeed;
		this.enchantability = enchantability;
		this.maxEnergyStored = maxEnergyStored;
		this.damageType = damageType;
		this.rarity = rarity;
    }
	
	public String getBlockName()
	{
		return blockName;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getHarvestLevel()
	{
		return harvestLevel;
	}
	
	public int getDurability()
	{
		return durability;
	}
	
	public float getEfficiency()
	{
		return efficiency;
	}
	
	public float getDamage()
	{
		return damage;
	}
	
	public double getAttackSpeed() 
	{
		return attackSpeed;
	}
	
	public int getEnchantability()
	{
		return enchantability;
	}
	
	public boolean getTakesDamage()
	{
		return damageType != -1;
	}
	public boolean getUsesMana()
	{
		return damageType == 1;
	}
	public boolean getUsesEnergy()
	{
		return damageType == 2;
	}
	public boolean getCanRepair()
	{
		return damageType == 3;
	}
	public int getMaxEnergy()
	{
		return maxEnergyStored;
	}
	public EnumRarity getEnumRarity()
	{
		return rarity;
	}

}
