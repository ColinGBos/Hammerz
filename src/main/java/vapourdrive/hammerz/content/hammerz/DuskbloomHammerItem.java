package vapourdrive.hammerz.content.hammerz;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import vapourdrive.hammerz.Hammerz;
import vapourdrive.vapourware.shared.utils.CompUtils;

import java.util.List;

public class DuskbloomHammerItem extends HammerItem{

    public DuskbloomHammerItem(Tier tier) {
        super(tier);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, Item.@NotNull TooltipContext context, List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
        tooltipComponents.add(CompUtils.getComp(Hammerz.MODID, "duskbloom_tool").withStyle(ChatFormatting.GRAY));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    @Override
    public void inventoryTick(@NotNull ItemStack stack, @NotNull Level level, @NotNull Entity entity, int slotId, boolean isSelected) {
        long daytime = level.dayTime();
        if(daytime > 12500 && daytime < 13500) {
            if (level.getRandom().nextFloat() > 0.95) {
                if (stack.getDamageValue() > 0) {
                    stack.setDamageValue(stack.getDamageValue()-1);
                }
            }
        }
    }
}
