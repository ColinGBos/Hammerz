package vapourdrive.hammerz.content.hammerz;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FluixHammerItem extends HammerItem{

    public FluixHammerItem(Tier tier) {
        super(tier);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, Item.@NotNull TooltipContext context, List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("phrase.hammerz.fortune").withStyle(ChatFormatting.GRAY));
        super.appendHoverText(stack,context,tooltipComponents,tooltipFlag);
    }

    @Override
    public boolean isFoil(@NotNull ItemStack stack) {
        return true;
    }

    @Override
    public int getEnchantmentLevel(ItemStack stack, @NotNull Holder<Enchantment> enchantment) {
        ItemEnchantments itemenchantments = stack.getTagEnchantments();
        if(enchantment.is(Enchantments.FORTUNE) || enchantment.is(Enchantments.LOOTING)){
            return Math.max(itemenchantments.getLevel(enchantment), 1);
        }
        return itemenchantments.getLevel(enchantment);
    }
}
