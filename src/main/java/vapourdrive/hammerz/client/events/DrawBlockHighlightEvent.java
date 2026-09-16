package vapourdrive.hammerz.client.events;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.shapes.*;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderHighlightEvent;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import vapourdrive.hammerz.Hammerz;
import vapourdrive.hammerz.config.ConfigSettings;
import vapourdrive.hammerz.content.hammerz.HammerItem;

//Thanks Direwolf20 for the basis of the render code

@EventBusSubscriber(modid = Hammerz.MODID, value = Dist.CLIENT)
public class DrawBlockHighlightEvent {
    @SubscribeEvent
    static void renderBlockHighlight(RenderHighlightEvent.Block event) {
        if(!ConfigSettings.RENDER_EXTENDED_HITBOX.get()){
            return;
        }
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (mc.player == null || (player.isCrouching() && ConfigSettings.ALLOW_SNEAK_MINE.get()))
            return;
        ItemStack itemStack = player.getMainHandItem();
        if (!(itemStack.getItem() instanceof HammerItem)){
            return;
        }
        BlockPos pos = event.getTarget().getBlockPos();
        Vec3 vec3 = event.getCamera().getPosition();
        VertexConsumer vc = event.getMultiBufferSource().getBuffer(RenderType.lines());
        Direction direction = event.getTarget().getDirection();
        renderHitOutline(event.getPoseStack(), vc, vec3.x(), vec3.y(), vec3.z(), pos, direction, mc, player, itemStack);
        if(!ConfigSettings.ALWAYS_RENDER_CENTER_BLOCK_HITBOX.get()) {
            event.setCanceled(true);
        }
    }

    private static void renderHitOutline(PoseStack pPoseStack, VertexConsumer pConsumer, double pCamX, double pCamY, double pCamZ, BlockPos pPos, Direction direction, Minecraft mc, Player player, ItemStack stack) {
        int xmove = 0;
        int ymove = 0;
        int zmove = 0;

        if (direction == Direction.UP || direction == Direction.DOWN) {
            xmove = 1;
            zmove = 1;
        } else {
            ymove = 1;
            if (direction == Direction.WEST || direction == Direction.EAST) {
                zmove = 1;
            } else {
                xmove = 1;
            }
        }

        Level level = player.level();
        float baseSpeed = level.getBlockState(pPos).getDestroySpeed(level, pPos);

        for (int i = -xmove; i <= xmove; i++) {
            for (int j = -ymove; j <= ymove; j++) {
                for (int k = -zmove; k <= zmove; k++) {
                    BlockPos targetPos = pPos.offset(i, j, k);
                    BlockState state = level.getBlockState(targetPos);

                    if (isValidBlock(level, targetPos, state, stack, baseSpeed) || (i == 0 && j == 0 && k == 0)) {
                        VoxelShape shape = state.getShape(level, targetPos);
                        double px = (double) targetPos.getX() - pCamX;
                        double py = (double) targetPos.getY() - pCamY;
                        double pz = (double) targetPos.getZ() - pCamZ;
                        renderShape(pPoseStack, pConsumer, shape, px, py, pz);
                    }
                }
            }
        }
    }

    private static boolean isValidBlock(Level world, BlockPos pos, BlockState state, ItemStack stack, float baseSpeed) {
        if (state.isAir()) {
            return false;
        }
        float testSpeed = state.getDestroySpeed(world, pos);
        if (testSpeed < 0 || testSpeed > 2 * baseSpeed) {
            return false;
        }
        return stack.isCorrectToolForDrops(state);
    }

    private static void renderShape(PoseStack pPoseStack, VertexConsumer pConsumer, VoxelShape pShape, double pX, double pY, double pZ) {
        PoseStack.Pose posestack$pose = pPoseStack.last();
        pShape.forAllEdges(
                (x0, y0, z0, x1, y1, z2) -> {
                    float f = (float) (x1 - x0);
                    float f1 = (float) (y1 - y0);
                    float f2 = (float) (z2 - z0);
                    float f3 = Mth.sqrt(f * f + f1 * f1 + f2 * f2);
                    f /= f3;
                    f1 /= f3;
                    f2 /= f3;
                    pConsumer.addVertex(posestack$pose.pose(), (float) (x0 + pX), (float) (y0 + pY), (float) (z0 + pZ))
                            .setColor(0.0f, 0.0f, 0.0f, 0.4f)
                            .setNormal(posestack$pose, f, f1, f2);
                    pConsumer.addVertex(posestack$pose.pose(), (float) (x1 + pX), (float) (y1 + pY), (float) (z2 + pZ))
                            .setColor(0.0f, 0.0f, 0.0f, 0.4f)
                            .setNormal(posestack$pose, f, f1, f2);
                }
        );
    }
}
