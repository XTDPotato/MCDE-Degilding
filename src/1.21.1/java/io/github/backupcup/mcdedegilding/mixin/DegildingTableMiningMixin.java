package io.github.backupcup.mcdedegilding.mixin;

import io.github.backupcup.mcdedegilding.registry.ModBlocks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.AbstractBlockState.class)
abstract class DegildingTableMiningMixin {
    @Shadow
    public abstract Block getBlock();

    @Inject(method = "calcBlockBreakingDelta", at = @At("HEAD"), cancellable = true)
    private void mcdeDegilding$setMiningSpeed(
        PlayerEntity player,
        BlockView world,
        BlockPos pos,
        CallbackInfoReturnable<Float> cir
    ) {
        if (getBlock() != ModBlocks.DEGILDING_TABLE || player.getAbilities().creativeMode) {
            return;
        }

        var tool = player.getMainHandStack();
        if (tool.isOf(Items.NETHERITE_PICKAXE)) {
            cir.setReturnValue(0.1F);
        } else if (tool.isOf(Items.DIAMOND_PICKAXE)) {
            cir.setReturnValue(1.0F / 15.0F);
        } else if (tool.isOf(Items.IRON_PICKAXE)) {
            cir.setReturnValue(0.05F);
        } else if (tool.isOf(Items.STONE_PICKAXE)) {
            cir.setReturnValue(1.0F / 30.0F);
        } else if (tool.isIn(ItemTags.PICKAXES)) {
            cir.setReturnValue(0.025F);
        } else {
            cir.setReturnValue(1.0F / 60.0F);
        }
    }
}
