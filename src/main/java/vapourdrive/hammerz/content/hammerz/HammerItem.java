package vapourdrive.hammerz.content.hammerz;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;
import vapourdrive.hammerz.Hammerz;
import vapourdrive.hammerz.config.ConfigSettings;
import vapourdrive.vapourware.shared.utils.DeferredComponent;

import java.text.DecimalFormat;
import java.util.List;

public class HammerItem extends PickaxeItem {
    protected final DeferredComponent component;
    final DecimalFormat df = new DecimalFormat("#,###");
    int damageMultiplier = 8;

    public HammerItem(Tier tier) {
        super(tier, new Item.Properties().attributes(PickaxeItem.createAttributes(tier, 5.0F, -3.0F)));
        component = new DeferredComponent(Hammerz.MODID, "hammer.info");
    }

    public HammerItem(Tier tier, int damageMultiplier) {
        super(tier, new Item.Properties().attributes(PickaxeItem.createAttributes(tier, 5.0F, -3.0F)));
        component = new DeferredComponent(Hammerz.MODID, "hammer.info");
        this.damageMultiplier = damageMultiplier;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, Item.@NotNull TooltipContext context, List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
        tooltipComponents.add(this.component.get().withStyle(ChatFormatting.GRAY));
        String durability = df.format(this.getMaxDamage(stack)-this.getDamage(stack)) + "/" + df.format(this.getMaxDamage(stack));
        tooltipComponents.add(Component.translatable("hammerz.keyword.durability", durability).withStyle(ChatFormatting.GRAY));
    }

    @Override
    public float getDestroySpeed(ItemStack stack, @NotNull BlockState state) {
        Tool tool = stack.get(DataComponents.TOOL);
        return tool != null ? tool.getMiningSpeed(state)*0.5F : 0.3F;
    }

    public boolean mineBlock(ItemStack stack, @NotNull Level level, @NotNull BlockState state, @NotNull BlockPos pos, @NotNull LivingEntity miningEntity) {
        Tool tool = stack.get(DataComponents.TOOL);
        if (tool == null) {
            return false;
        } else {
            int damagePerBlock = tool.damagePerBlock();
            if (!level.isClientSide && state.getDestroySpeed(level, pos) != 0.0F && tool.damagePerBlock() > 0) {
                stack.hurtAndBreak(damagePerBlock, miningEntity, EquipmentSlot.MAINHAND);
            }

            if (miningEntity instanceof Player player) {
                if ((player.isCrouching() && ConfigSettings.ALLOW_SNEAK_MINE.get()))                {
                    return true;
                }
                BlockHitResult blockhitresult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);
                if (blockhitresult.getType() != HitResult.Type.BLOCK) {
                    return true;
                } else {
                    Direction side = blockhitresult.getDirection();
                    int xmove = 0;
                    int ymove = 0;
                    int zmove = 0;

                    if (side == Direction.UP || side == Direction.DOWN) {
                        xmove = 1;
                        zmove = 1;
                    }
                    else                    {
                        ymove = 1;
                        if (side == Direction.WEST || side == Direction.EAST) {
                            zmove = 1;
                        } else
                        {
                            xmove = 1;
                        }
                    }

                    float speed = state.getDestroySpeed(level, pos);

                    int x = pos.getX();
                    int y = pos.getY();
                    int z = pos.getZ();

                    for (int i = -xmove; i <= xmove; i++)
                    {
                        for (int j = -ymove; j <= ymove; j++)
                        {
                            for (int k = -zmove; k <= zmove; k++)
                            {
                                if ((x + i) != x || (y + j) != y || (z + k) != z)
                                {
                                    checkBlockBreak(level, player, new BlockPos(x + i, y + j, z + k), stack, speed, damagePerBlock);
                                }
                            }
                        }
                    }
                }
            }
            return true;
        }
    }

    public void checkBlockBreak(Level world, Player player, BlockPos pos, ItemStack stack, float baseSpeed, int damage) {
        if (stack.isEmpty()){
            return;
        }
        BlockState state = world.getBlockState(pos);
        float testSpeed = state.getDestroySpeed(world, pos);
        if (testSpeed > 2*baseSpeed){
            return;
        }
        Block breakBlock = state.getBlock();
        BlockEntity blockentity = world.getBlockEntity(pos);
        if (stack.isCorrectToolForDrops(state)){
            breakBlock.playerDestroy(world, player, pos, state, blockentity, stack);
            state.onDestroyedByPlayer(world, pos, player, true, world.getFluidState(pos));
            stack.hurtAndBreak(damage, player, EquipmentSlot.MAINHAND);
        }
    }

    @Override
    public int getMaxDamage(@NotNull ItemStack stack){
        return stack.getOrDefault(DataComponents.MAX_DAMAGE, 0) * this.damageMultiplier;
    }

}
