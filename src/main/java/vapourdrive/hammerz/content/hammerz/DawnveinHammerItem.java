package vapourdrive.hammerz.content.hammerz;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import vapourdrive.hammerz.Hammerz;
import vapourdrive.vapourware.shared.utils.CompUtils;

import java.util.List;

public class DawnveinHammerItem extends HammerItem{

    public DawnveinHammerItem(Tier tier) {
        super(tier);
    }
    List<BlockState> targetStates = List.of(
            Blocks.DEEPSLATE_DIAMOND_ORE.defaultBlockState(),
            Blocks.DIAMOND_ORE.defaultBlockState(),
            Blocks.ANCIENT_DEBRIS.defaultBlockState()
    );

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
        if(Screen.hasShiftDown()) {
            tooltipComponents.add(CompUtils.getComp(Hammerz.MODID, "dawnvein_tool").withStyle(ChatFormatting.GRAY));
            tooltipComponents.add(CompUtils.getComp(Hammerz.MODID, "dawnvein_pickaxe.info").withStyle(ChatFormatting.LIGHT_PURPLE));
        }
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    @Override
    public void inventoryTick(@NotNull ItemStack stack, @NotNull Level level, @NotNull Entity entity, int slotId, boolean isSelected) {
        inventoryTick(stack, level);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (tryTargetBlocksSearch(context.getPlayer(), level, context.getClickedPos())) {
            return InteractionResult.CONSUME;
        }
        return InteractionResult.PASS;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand usedHand) {
        ItemStack itemstack = player.getItemInHand(usedHand);

        if (tryTargetBlocksSearch(player, level, player.getOnPos().above())) {
            return InteractionResultHolder.consume(itemstack);
        }

        return InteractionResultHolder.pass(itemstack);
    }

    private boolean tryTargetBlocksSearch(Player player, Level level, BlockPos pos) {
        if (player == null || !player.isCrouching()) {
            return false;
        }
        BlockPos nearestBlockPos = findNearestBlockState(level, pos, targetStates, 16);
        if (nearestBlockPos != null) {
            player.getCooldowns().addCooldown(this, 100);
            level.playLocalSound(nearestBlockPos, SoundEvents.AMETHYST_BLOCK_RESONATE, SoundSource.BLOCKS, 1.2F, 0.75F, false);
            return true;
        }
        return false;
    }

    @Override
    public boolean shouldCauseReequipAnimation(@NotNull ItemStack oldStack, @NotNull ItemStack newStack, boolean slotChanged) {
        return false;
    }

    public static void inventoryTick(@NotNull ItemStack stack, @NotNull Level level) {
        int daytime = (int) (level.getDayTime() % 24000);
        if(daytime > 23000) {
            if (level.getRandom().nextFloat() < 0.02) {
                if (stack.getDamageValue() > 0) {
                    stack.setDamageValue(stack.getDamageValue()-1);
                }
            }
        }
    }

    public static BlockPos findNearestBlockState(Level level, BlockPos startPos, List<BlockState> targetStates, int maxRadius) {
        for (int r = 1; r <= maxRadius; r++) {
            for (int x = -r; x <= r; x++) {
                for (int y = -r; y <= r; y++) {
                    for (int z = -r; z <= r; z++) {
                        if (Math.abs(x) == r || Math.abs(y) == r || Math.abs(z) == r) {
                            BlockPos pos = startPos.offset(x, y, z);
                            if (level.isLoaded(pos) && targetStates.contains(level.getBlockState(pos))) {
                                return pos;
                            }
                        }
                    }
                }
            }
        }
        return null; // No block state found within the radius
    }
}
