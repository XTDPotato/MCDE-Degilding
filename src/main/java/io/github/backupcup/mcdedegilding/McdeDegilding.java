package io.github.backupcup.mcdedegilding;

import io.github.backupcup.mcdedegilding.registry.ModBlocks;
import io.github.backupcup.mcdedegilding.registry.ModBlockEntities;
import io.github.backupcup.mcdedegilding.registry.ModScreenHandlers;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class McdeDegilding implements ModInitializer {
    public static final String MOD_ID = "mcde_degilding";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static Identifier id(String path) {
        return new Identifier(MOD_ID, path);
    }

    @Override
    public void onInitialize() {
        ModBlocks.register();
        ModBlockEntities.register();
        ModScreenHandlers.register();
    }
}
