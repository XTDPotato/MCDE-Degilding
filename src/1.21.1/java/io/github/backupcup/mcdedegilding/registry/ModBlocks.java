package io.github.backupcup.mcdedegilding.registry;

import io.github.backupcup.mcdedegilding.McdeDegilding;
import io.github.backupcup.mcdedegilding.block.DegildingTableBlock;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;

public final class ModBlocks {
    public static final Block DEGILDING_TABLE = Registry.register(
        Registries.BLOCK,
        McdeDegilding.id("degilding_table"),
        new DegildingTableBlock(AbstractBlock.Settings.create()
            .mapColor(MapColor.GOLD)
            .requiresTool()
            .strength(3.0F, 6.0F)
            .sounds(BlockSoundGroup.METAL))
    );

    public static final Item DEGILDING_TABLE_ITEM = Registry.register(
        Registries.ITEM,
        McdeDegilding.id("degilding_table"),
        new BlockItem(DEGILDING_TABLE, new Item.Settings())
    );

    private ModBlocks() {
    }

    public static void register() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL)
            .register(entries -> entries.add(DEGILDING_TABLE_ITEM));
    }
}
