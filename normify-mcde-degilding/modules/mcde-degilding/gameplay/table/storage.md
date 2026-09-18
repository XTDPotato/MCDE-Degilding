---
uid: 8de37cb1
id: mcde-degilding.gameplay.table.storage
parent: mcde-degilding.gameplay.table
revision: 970658398c9f9ef06cd53a71c5542f4416e9e79e
updated_at: 2026-09-18T15:33:02Z
fingerprint: b18548e4d4c554fef8f6b875b9f6bcfe9da935bd07b613faebbff971610ff7e6
state: active
name: { zh: 持久化库存, en: Persistent inventory }
description: { zh: 管理装备和铁锭两个库存槽，负责 NBT 序列化、玩家距离校验及 ScreenHandler 创建。, en: Manages equipment and iron-ingot slots, handles NBT serialization, player-distance checks, and ScreenHandler creation. }
source:
  - path: src/main/java/io/github/backupcup/mcdedegilding/block/entity/DegildingTableBlockEntity.java
    line: 19
    end_line: 111
  - path: src/1.21.1/java/io/github/backupcup/mcdedegilding/block/entity/DegildingTableBlockEntity.java
    line: 20
    end_line: 112
apis:
  - protocol: file
    path: minecraft:block-entity-inventory-degilding-table
    description: { zh: 维护褪金台的双格库存并持久化到 NBT。 , en: Maintains the table's two-slot inventory and persists it to NBT. }
  - protocol: file
    path: minecraft:block-entity-menu-degilding-table
    description: { zh: 为可用范围内的玩家创建同步容器界面。 , en: Creates the synchronized container screen for players within use range. }
deps:
  - kind: call
    to: mcde-degilding.interface.handler.slots
    label: { zh: 创建同步容器, en: Creates synchronized container }
---
