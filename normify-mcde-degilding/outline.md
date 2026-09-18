# MCDE Degilding Architecture Outline

`mcde-degilding` - MCDE Fabric add-on for removing selected gilded enchantments.

- `build`
  - `targets` - Minecraft/Java/MCDE target selection and artifact naming.
- `bootstrap`
  - `common` - Fabric common initializer.
  - `client` - Fabric client initializer.
  - `registries`
    - `blocks` - Degilding Table block and item registration.
    - `block-entities` - persistent table inventory registration.
    - `screen-handlers` - synchronized container type registration.
- `gameplay`
  - `table`
    - `block` - world interaction, 13/16 shape, and content drops.
    - `storage` - two-slot NBT-backed inventory and menu factory.
  - `mining` - hand-mineable and pickaxe-tier breaking speed injection.
- `interface`
  - `handler`
    - `slots` - input/material constraints and MCDE gilding discovery.
    - `transaction` - 16-iron cost and five-second double-click removal.
    - `lifecycle` - confirmation reset and inventory quick-move lifecycle.
  - `client`
    - `rendering` - Dungeon-style layout, icons, tooltips, and material state.
    - `interaction` - entry clicks, scroll wheel, draggable scrollbar, cancel.
- `content`
  - `data`
    - `recipe` - amethyst shard over three deepslate blocks.
    - `drops` - self-drop and pickaxe tag.
  - `assets`
    - `models` - block state, block model, and 3D item model.
    - `textures` - block textures and mod icon.
    - `gui` - GUI background texture.
    - `localization` - English and Simplified Chinese text.
  - `metadata` - Fabric metadata and Mixin configuration.
- `documentation` - Modrinth description and bilingual build guides.
