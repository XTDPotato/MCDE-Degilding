package io.github.backupcup.mcdedegilding;

import io.github.backupcup.mcdedegilding.registry.ModScreenHandlers;
import io.github.backupcup.mcdedegilding.screen.DegildingScreen;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public final class McdeDegildingClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(ModScreenHandlers.DEGILDING_TABLE, DegildingScreen::new);
    }
}
