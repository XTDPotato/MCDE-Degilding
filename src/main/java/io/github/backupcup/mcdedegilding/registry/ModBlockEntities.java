package io.github.backupcup.mcdedegilding.registry;

import io.github.backupcup.mcdedegilding.McdeDegilding;
import io.github.backupcup.mcdedegilding.block.entity.DegildingTableBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public final class ModBlockEntities {
    public static BlockEntityType<DegildingTableBlockEntity> DEGILDING_TABLE;

    private ModBlockEntities() {
    }

    public static void register() {
        DEGILDING_TABLE = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            McdeDegilding.id("degilding_table"),
            FabricBlockEntityTypeBuilder.create(DegildingTableBlockEntity::new, ModBlocks.DEGILDING_TABLE).build()
        );
    }
}
