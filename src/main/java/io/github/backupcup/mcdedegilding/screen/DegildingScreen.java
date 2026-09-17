package io.github.backupcup.mcdedegilding.screen;

import java.util.List;
import net.backupcup.mcde.MCDEnchantments;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public final class DegildingScreen extends HandledScreen<DegildingScreenHandler> {
    private static final Identifier TEXTURE =
        new Identifier("mcde_degilding", "textures/gui/degilding_table.png");
    private static final Identifier MISSING_ENCHANTMENT_ICON =
        new Identifier(MCDEnchantments.MOD_ID, "textures/gui/icons/missing_no.png");
    private static final ItemStack MATERIAL_HINT = Items.IRON_INGOT.getDefaultStack();

    private static final int TEXTURE_WIDTH = 256;
    private static final int TEXTURE_HEIGHT = 256;
    private static final int BACKGROUND_U = 2;
    private static final int BACKGROUND_V = 20;
    private static final int BACKGROUND_DRAW_X = -2;
    private static final int BACKGROUND_WIDTH = 168;
    private static final int BACKGROUND_HEIGHT = 167;

    private static final int LIST_X = 45;
    private static final int LIST_Y = 10;
    private static final int LIST_WIDTH = 103;
    private static final int ROW_HEIGHT = 20;
    private static final int VISIBLE_ENTRIES = 3;
    private static final int ICON_SIZE = 16;
    private static final int SCROLLBAR_X = 151;
    private static final int SCROLLBAR_Y = 11;
    private static final int SCROLLBAR_WIDTH = 3;
    private static final int SCROLLBAR_HEIGHT = 54;
    private static final int MIN_THUMB_HEIGHT = 10;
    private static final long CONFIRMATION_WINDOW_MILLIS = 5_000L;

    private int scrollOffset;
    private boolean draggingScrollbar;
    private int pendingRemovalIndex = -1;
    private ItemStack pendingRemovalStack = ItemStack.EMPTY;
    private long pendingRemovalTime;

    public DegildingScreen(DegildingScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        backgroundWidth = 176;
        backgroundHeight = 166;
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        context.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        context.drawTexture(
            TEXTURE,
            x + BACKGROUND_DRAW_X,
            y,
            BACKGROUND_U,
            BACKGROUND_V,
            BACKGROUND_WIDTH,
            BACKGROUND_HEIGHT,
            TEXTURE_WIDTH,
            TEXTURE_HEIGHT
        );

        if (handler.getMaterialStack().isEmpty()) {
            context.setShaderColor(1.0F, 1.0F, 1.0F, 0.5F);
            context.drawItem(
                MATERIAL_HINT,
                x + DegildingScreenHandler.MATERIAL_SLOT_X,
                y + DegildingScreenHandler.MATERIAL_SLOT_Y
            );
            context.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        }

        List<Identifier> gilded = DegildingScreenHandler.getGildedEnchantments(handler.getInputStack());
        normalizeScroll(gilded.size());
        normalizePendingRemoval(handler.getInputStack(), gilded.size());
        if (hasEnoughMaterial()) {
            for (int row = 0; row < visibleEntries(gilded.size()); row++) {
                int index = scrollOffset + row;
                if (isOverEntry(mouseX, mouseY, row) || pendingRemovalIndex == index) {
                    int rowTop = y + LIST_Y + row * ROW_HEIGHT;
                    context.fill(
                        x + LIST_X,
                        rowTop,
                        x + LIST_X + LIST_WIDTH,
                        rowTop + ROW_HEIGHT - 2,
                        pendingRemovalIndex == index ? 0xA8683B9D : 0x7A765093
                    );
                }
            }
        }
        drawScrollbar(context, gilded.size());
    }

    @Override
    protected void drawForeground(DrawContext context, int mouseX, int mouseY) {
        ItemStack stack = handler.getInputStack();
        List<Identifier> gilded = DegildingScreenHandler.getGildedEnchantments(stack);
        normalizeScroll(gilded.size());
        boolean materialReady = hasEnoughMaterial();

        for (int row = 0; row < visibleEntries(gilded.size()); row++) {
            int index = scrollOffset + row;
            Enchantment enchantment = Registries.ENCHANTMENT.get(gilded.get(index));
            if (enchantment == null) {
                continue;
            }

            int level = EnchantmentHelper.getLevel(enchantment, stack);
            Text name = enchantment.getName(Math.max(level, 1)).copy().formatted(Formatting.GOLD);
            int iconY = LIST_Y + 1 + row * ROW_HEIGHT;
            if (!materialReady) {
                context.setShaderColor(0.45F, 0.45F, 0.45F, 1.0F);
            }
            context.drawTexture(
                getEnchantmentIcon(gilded.get(index)),
                LIST_X + 2,
                iconY,
                0.0F,
                0.0F,
                ICON_SIZE,
                ICON_SIZE,
                32,
                32
            );
            if (!materialReady) {
                context.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            }
            context.drawText(
                textRenderer,
                textRenderer.trimToWidth(name.getString(), LIST_WIDTH - ICON_SIZE - 9),
                LIST_X + ICON_SIZE + 5,
                LIST_Y + 5 + row * ROW_HEIGHT,
                materialReady ? 0xFFF0D77A : 0xFF7C746A,
                false
            );
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) {
            List<Identifier> gilded = DegildingScreenHandler.getGildedEnchantments(handler.getInputStack());
            normalizeScroll(gilded.size());

            if (gilded.size() > VISIBLE_ENTRIES && isOverScrollbar(mouseX, mouseY)) {
                cancelPendingRemoval();
                draggingScrollbar = true;
                updateScrollFromMouse(mouseY, gilded.size());
                return true;
            }

            for (int row = 0; row < visibleEntries(gilded.size()); row++) {
                if (isOverEntry((int) mouseX, (int) mouseY, row)) {
                    if (!hasEnoughMaterial()) {
                        cancelPendingRemoval();
                        return true;
                    }
                    int index = scrollOffset + row;
                    if (isPendingRemoval(index)) {
                        resetPendingRemoval();
                    } else {
                        pendingRemovalIndex = index;
                        pendingRemovalStack = handler.getInputStack().copy();
                        pendingRemovalTime = System.currentTimeMillis();
                    }
                    client.interactionManager.clickButton(handler.syncId, index);
                    return true;
                }
            }

        }
        cancelPendingRemoval();
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (button == 0 && draggingScrollbar) {
            draggingScrollbar = false;
            return true;
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (button == 0 && draggingScrollbar) {
            cancelPendingRemoval();
            int totalEntries = DegildingScreenHandler.getGildedEnchantments(handler.getInputStack()).size();
            updateScrollFromMouse(mouseY, totalEntries);
            return true;
        }
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        List<Identifier> gilded = DegildingScreenHandler.getGildedEnchantments(handler.getInputStack());
        if (isOverList(mouseX, mouseY) && gilded.size() > VISIBLE_ENTRIES && amount != 0.0D) {
            cancelPendingRemoval();
            scrollOffset = MathHelper.clamp(scrollOffset - (int) Math.signum(amount), 0, maxScroll(gilded.size()));
            return true;
        }
        return super.mouseScrolled(mouseX, mouseY, amount);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        super.render(context, mouseX, mouseY, delta);

        ItemStack stack = handler.getInputStack();
        List<Identifier> gilded = DegildingScreenHandler.getGildedEnchantments(stack);
        normalizeScroll(gilded.size());
        normalizePendingRemoval(stack, gilded.size());
        for (int row = 0; row < visibleEntries(gilded.size()); row++) {
            if (!isOverEntry(mouseX, mouseY, row)) {
                continue;
            }
            Enchantment enchantment = Registries.ENCHANTMENT.get(gilded.get(scrollOffset + row));
            if (!hasEnoughMaterial()) {
                context.drawTooltip(
                    textRenderer,
                    Text.translatable("screen.mcde_degilding.need_iron", DegildingScreenHandler.IRON_COST),
                    mouseX,
                    mouseY
                );
            } else if (pendingRemovalIndex == scrollOffset + row) {
                context.drawTooltip(
                    textRenderer,
                    Text.translatable("screen.mcde_degilding.confirm_remove"),
                    mouseX,
                    mouseY
                );
            } else if (enchantment != null) {
                int level = EnchantmentHelper.getLevel(enchantment, stack);
                context.drawTooltip(textRenderer, enchantment.getName(Math.max(level, 1)), mouseX, mouseY);
            }
            break;
        }
        drawMouseoverTooltip(context, mouseX, mouseY);
    }

    private void drawScrollbar(DrawContext context, int totalEntries) {
        if (totalEntries <= VISIBLE_ENTRIES) {
            return;
        }

        context.fill(
            x + SCROLLBAR_X,
            y + SCROLLBAR_Y,
            x + SCROLLBAR_X + SCROLLBAR_WIDTH,
            y + SCROLLBAR_Y + SCROLLBAR_HEIGHT,
            0xC735261D
        );
        int thumbHeight = thumbHeight(totalEntries);
        int thumbY = thumbY(totalEntries, thumbHeight);
        context.fill(
            x + SCROLLBAR_X,
            y + thumbY,
            x + SCROLLBAR_X + SCROLLBAR_WIDTH,
            y + thumbY + thumbHeight,
            draggingScrollbar ? 0xFFFFDB78 : 0xFFE4B64C
        );
        context.fill(
            x + SCROLLBAR_X + 1,
            y + thumbY + 1,
            x + SCROLLBAR_X + SCROLLBAR_WIDTH - 1,
            y + thumbY + thumbHeight - 1,
            0xFFF4D987
        );
    }

    private void updateScrollFromMouse(double mouseY, int totalEntries) {
        int maxScroll = maxScroll(totalEntries);
        if (maxScroll == 0) {
            scrollOffset = 0;
            return;
        }

        int thumbHeight = thumbHeight(totalEntries);
        double travel = SCROLLBAR_HEIGHT - thumbHeight;
        double relative = mouseY - y - SCROLLBAR_Y - thumbHeight / 2.0D;
        scrollOffset = MathHelper.clamp((int) Math.round(relative / travel * maxScroll), 0, maxScroll);
    }

    private int thumbHeight(int totalEntries) {
        return Math.max(MIN_THUMB_HEIGHT, SCROLLBAR_HEIGHT * VISIBLE_ENTRIES / totalEntries);
    }

    private int thumbY(int totalEntries, int thumbHeight) {
        int maxScroll = maxScroll(totalEntries);
        if (maxScroll == 0) {
            return SCROLLBAR_Y;
        }
        return SCROLLBAR_Y
            + Math.round((SCROLLBAR_HEIGHT - thumbHeight) * (scrollOffset / (float) maxScroll));
    }

    private boolean isOverEntry(int mouseX, int mouseY, int row) {
        int rowTop = y + LIST_Y + row * ROW_HEIGHT;
        return mouseX >= x + LIST_X
            && mouseX < x + LIST_X + LIST_WIDTH
            && mouseY >= rowTop
            && mouseY < rowTop + ROW_HEIGHT - 2;
    }

    private boolean isOverList(double mouseX, double mouseY) {
        return mouseX >= x + LIST_X
            && mouseX < x + SCROLLBAR_X + SCROLLBAR_WIDTH
            && mouseY >= y + LIST_Y
            && mouseY < y + LIST_Y + VISIBLE_ENTRIES * ROW_HEIGHT;
    }

    private boolean isOverScrollbar(double mouseX, double mouseY) {
        return mouseX >= x + SCROLLBAR_X
            && mouseX < x + SCROLLBAR_X + SCROLLBAR_WIDTH
            && mouseY >= y + SCROLLBAR_Y
            && mouseY < y + SCROLLBAR_Y + SCROLLBAR_HEIGHT;
    }

    private int visibleEntries(int totalEntries) {
        return Math.max(0, Math.min(VISIBLE_ENTRIES, totalEntries - scrollOffset));
    }

    private int maxScroll(int totalEntries) {
        return Math.max(0, totalEntries - VISIBLE_ENTRIES);
    }

    private void normalizeScroll(int totalEntries) {
        scrollOffset = Math.min(scrollOffset, maxScroll(totalEntries));
    }

    private void normalizePendingRemoval(ItemStack inputStack, int totalEntries) {
        if (pendingRemovalIndex >= totalEntries
            || !ItemStack.areEqual(inputStack, pendingRemovalStack)
            || System.currentTimeMillis() - pendingRemovalTime > CONFIRMATION_WINDOW_MILLIS) {
            resetPendingRemoval();
        }
    }

    private void resetPendingRemoval() {
        pendingRemovalIndex = -1;
        pendingRemovalStack = ItemStack.EMPTY;
        pendingRemovalTime = 0L;
    }

    private void cancelPendingRemoval() {
        if (pendingRemovalIndex < 0) {
            return;
        }
        resetPendingRemoval();
        client.interactionManager.clickButton(handler.syncId, DegildingScreenHandler.RESET_PENDING_REMOVAL_BUTTON);
    }

    private boolean isPendingRemoval(int index) {
        return pendingRemovalIndex == index
            && ItemStack.areEqual(handler.getInputStack(), pendingRemovalStack)
            && System.currentTimeMillis() - pendingRemovalTime <= CONFIRMATION_WINDOW_MILLIS;
    }

    private boolean hasEnoughMaterial() {
        return handler.hasEnoughIron() || (client.player != null && client.player.isCreative());
    }

    private static Identifier getEnchantmentIcon(Identifier enchantmentId) {
        Identifier icon = new Identifier(
            MCDEnchantments.MOD_ID,
            "textures/gui/icons/" + enchantmentId.getNamespace() + "/" + enchantmentId.getPath() + ".png"
        );
        return MinecraftClient.getInstance().getResourceManager().getResource(icon).isPresent()
            ? icon
            : MISSING_ENCHANTMENT_ICON;
    }
}
