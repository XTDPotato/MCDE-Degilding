package io.github.backupcup.mcdedegilding.screen;

import io.github.backupcup.mcdedegilding.registry.ModBlocks;
import io.github.backupcup.mcdedegilding.registry.ModScreenHandlers;
import java.util.Comparator;
import java.util.List;
import net.backupcup.mcde.util.EnchantmentSlots;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.sound.SoundEvents;

public final class DegildingScreenHandler extends ScreenHandler {
    public static final int IRON_COST = 16;
    public static final int RESET_PENDING_REMOVAL_BUTTON = -1;
    public static final int INPUT_SLOT_X = 18;
    public static final int INPUT_SLOT_Y = 14;
    public static final int MATERIAL_SLOT_X = 18;
    public static final int MATERIAL_SLOT_Y = 42;
    private static final int INPUT_SLOT = 0;
    private static final int MATERIAL_SLOT = 1;
    private static final long CONFIRMATION_WINDOW_MILLIS = 5_000L;
    private final Inventory inventory;
    private final ScreenHandlerContext context;
    private int pendingRemovalId = -1;
    private ItemStack pendingRemovalStack = ItemStack.EMPTY;
    private long pendingRemovalTime;

    public DegildingScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(2), ScreenHandlerContext.EMPTY);
    }

    public DegildingScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        this(syncId, playerInventory, new SimpleInventory(2), context);
    }

    public DegildingScreenHandler(
        int syncId,
        PlayerInventory playerInventory,
        Inventory inventory,
        ScreenHandlerContext context
    ) {
        super(ModScreenHandlers.DEGILDING_TABLE, syncId);
        checkSize(inventory, 2);
        this.inventory = inventory;
        this.context = context;
        inventory.onOpen(playerInventory.player);

        this.addSlot(new Slot(inventory, INPUT_SLOT, INPUT_SLOT_X, INPUT_SLOT_Y) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return !getGildedEnchantments(stack).isEmpty();
            }

            @Override
            public int getMaxItemCount() {
                return 1;
            }
        });

        this.addSlot(new Slot(inventory, MATERIAL_SLOT, MATERIAL_SLOT_X, MATERIAL_SLOT_Y) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isOf(Items.IRON_INGOT);
            }
        });

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public ItemStack getInputStack() {
        return inventory.getStack(INPUT_SLOT);
    }

    public ItemStack getMaterialStack() {
        return inventory.getStack(MATERIAL_SLOT);
    }

    public boolean hasEnoughIron() {
        ItemStack stack = getMaterialStack();
        return stack.isOf(Items.IRON_INGOT) && stack.getCount() >= IRON_COST;
    }

    public static List<RegistryEntry<Enchantment>> getGildedEnchantments(ItemStack stack) {
        return EnchantmentSlots.fromItemStack(stack)
            .filter(EnchantmentSlots::hasGilding)
            .map(slots -> slots.getGilding().stream()
                .sorted(Comparator.comparing(entry -> entry.getKey()
                    .map(key -> key.getValue().toString())
                    .orElse("")))
                .toList())
            .orElseGet(List::of);
    }

    @Override
    public boolean onButtonClick(PlayerEntity player, int id) {
        if (id == RESET_PENDING_REMOVAL_BUTTON) {
            resetPendingRemoval();
            return true;
        }

        ItemStack stack = getInputStack();
        List<RegistryEntry<Enchantment>> gildedEnchantments = getGildedEnchantments(stack);
        if (id < 0 || id >= gildedEnchantments.size()) {
            return false;
        }
        if (!player.isCreative() && !hasEnoughIron()) {
            return false;
        }
        long now = System.currentTimeMillis();
        if (pendingRemovalId != id
            || !ItemStack.areEqual(stack, pendingRemovalStack)
            || now - pendingRemovalTime > CONFIRMATION_WINDOW_MILLIS) {
            pendingRemovalId = id;
            pendingRemovalStack = stack.copy();
            pendingRemovalTime = now;
            return true;
        }

        RegistryEntry<Enchantment> selected = gildedEnchantments.get(id);
        EnchantmentSlots slots = EnchantmentSlots.fromItemStack(stack).orElse(null);
        if (slots == null || !slots.hasGilding(selected)) {
            return false;
        }

        ItemEnchantmentsComponent.Builder enchantments =
            new ItemEnchantmentsComponent.Builder(EnchantmentHelper.getEnchantments(stack));
        enchantments.remove(entry -> entry.matches(selected));
        stack.set(DataComponentTypes.ENCHANTMENTS, enchantments.build());
        stack.set(EnchantmentSlots.COMPONENT_TYPE, slots.withoutGilding(selected));
        if (!player.isCreative()) {
            getMaterialStack().decrement(IRON_COST);
        }
        inventory.markDirty();
        resetPendingRemoval();
        player.playSound(SoundEvents.BLOCK_GRINDSTONE_USE, 0.7F, 1.0F);
        return true;
    }

    @Override
    public void onSlotClick(int slotIndex, int button, SlotActionType actionType, PlayerEntity player) {
        resetPendingRemoval();
        super.onSlotClick(slotIndex, button, actionType, player);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slotIndex) {
        ItemStack result = ItemStack.EMPTY;
        Slot slot = slots.get(slotIndex);
        if (slot == null || !slot.hasStack()) {
            return result;
        }

        ItemStack original = slot.getStack();
        result = original.copy();
        resetPendingRemoval();
        if (slotIndex < inventory.size()) {
            if (!insertItem(original, inventory.size(), slots.size(), true)) {
                return ItemStack.EMPTY;
            }
        } else {
            if (!insertItem(original, INPUT_SLOT, INPUT_SLOT + 1, false)
                && !insertItem(original, MATERIAL_SLOT, MATERIAL_SLOT + 1, false)) {
                return ItemStack.EMPTY;
            }
        }

        if (original.isEmpty()) {
            slot.setStack(ItemStack.EMPTY);
        } else {
            slot.markDirty();
        }
        return result;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return canUse(context, player, ModBlocks.DEGILDING_TABLE);
    }

    @Override
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);
        resetPendingRemoval();
    }

    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                addSlot(new Slot(playerInventory, column + row * 9 + 9, 2 + column * 18, 80 + row * 19));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int column = 0; column < 9; column++) {
            addSlot(new Slot(playerInventory, column, 2 + column * 18, 144));
        }
    }

    private void resetPendingRemoval() {
        pendingRemovalId = -1;
        pendingRemovalStack = ItemStack.EMPTY;
        pendingRemovalTime = 0L;
    }
}
