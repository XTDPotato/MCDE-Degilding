---
uid: c5e8041b
id: mcde-degilding.interface.handler.slots
parent: mcde-degilding.interface.handler
revision: 970658398c9f9ef06cd53a71c5542f4416e9e79e
updated_at: 2026-09-18T15:33:02Z
fingerprint: 728c152ba707047b6e8853216a100a92ac83cde018a4ca66914288279e5847d2
state: active
name: { zh: 槽位与镀金枚举, en: Slots and gilding discovery }
description: { zh: 建立装备和铁锭材料槽，限制可放入物品，并从 MCDE EnchantmentSlots 中读取已镀金的附魔 ID。, en: Creates equipment and iron-material slots, constrains inserted items, and reads gilded enchantment IDs from MCDE EnchantmentSlots. }
source:
  - path: src/main/java/io/github/backupcup/mcdedegilding/screen/DegildingScreenHandler.java
    line: 24
    end_line: 107
  - path: src/1.21.1/java/io/github/backupcup/mcdedegilding/screen/DegildingScreenHandler.java
    line: 25
    end_line: 110
apis:
  - protocol: file
    path: minecraft:screen-handler-degilding-table
    description: { zh: 初始化包含装备槽、铁锭槽与玩家库存的同步容器。 , en: Initializes the synchronized container with equipment, iron, and player inventory slots. }
  - protocol: file
    path: mcde:list-gilded-enchantments
    description: { zh: 返回输入装备的排序后 MCDE 镀金附魔 ID 列表。 , en: Returns sorted MCDE gilded enchantment IDs from the input equipment. }
---
