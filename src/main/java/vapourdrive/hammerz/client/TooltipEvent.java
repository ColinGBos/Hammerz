package vapourdrive.hammerz.client;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import vapourdrive.hammerz.Hammerz;

import java.util.Arrays;
import java.util.List;

@EventBusSubscriber(modid = Hammerz.MODID)
public class TooltipEvent {
    @SubscribeEvent
    public static void onToolTipEarly(ItemTooltipEvent event) {
        if (Hammerz.isDebugMode()) {
            List<Component> tips = event.getToolTip();
            ItemStack stack = event.getItemStack();
//            tips.add(Component.literal(Arrays.toString(stack.getTags().toArray())));
            tips.add(Component.literal(stack.getDescriptionId()));
        }
    }
}
