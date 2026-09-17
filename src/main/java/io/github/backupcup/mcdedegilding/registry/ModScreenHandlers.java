package io.github.backupcup.mcdedegilding.registry;

import io.github.backupcup.mcdedegilding.McdeDegilding;
import io.github.backupcup.mcdedegilding.screen.DegildingScreenHandler;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;

public final class ModScreenHandlers {
    public static final ScreenHandlerType<DegildingScreenHandler> DEGILDING_TABLE =
        new ScreenHandlerType<>(DegildingScreenHandler::new, FeatureFlags.VANILLA_FEATURES);

    private ModScreenHandlers() {
    }

    public static void register() {
        Registry.register(Registries.SCREEN_HANDLER, McdeDegilding.id("degilding_table"), DEGILDING_TABLE);
    }
}
