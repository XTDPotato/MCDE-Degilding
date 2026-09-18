---
uid: 7c49f015
id: mcde-degilding.gameplay.table.block
parent: mcde-degilding.gameplay.table
revision: 970658398c9f9ef06cd53a71c5542f4416e9e79e
updated_at: 2026-09-18T15:33:02Z
fingerprint: a7db78c6dd154db3df6852e1b3af76c498d9569b497638db97dd2c6de3277887
state: active
name: { zh: 世界方块行为, en: World block behavior }
description: { zh: 提供模型渲染、13/16 格高的碰撞与选中形状、右键开界面，以及破坏时散落容器物品的行为。, en: Provides model rendering, 13/16-block collision and outline shapes, right-click screen opening, and item scattering on replacement. }
source:
  - path: src/main/java/io/github/backupcup/mcdedegilding/block/DegildingTableBlock.java
    line: 22
    end_line: 87
  - path: src/1.21.1/java/io/github/backupcup/mcdedegilding/block/DegildingTableBlock.java
    line: 21
    end_line: 88
apis:
  - protocol: file
    path: minecraft:block-use-degilding-table
    description: { zh: 玩家右键褪金台时打开容器界面。 , en: Opens the container screen when a player uses the table. }
  - protocol: file
    path: minecraft:block-shape-degilding-table
    description: { zh: 返回与模型最高点一致的 13/16 格碰撞和选中形状。 , en: Returns the 13/16-block collision and outline shape that matches the model peak. }
  - protocol: file
    path: minecraft:block-drop-contents-degilding-table
    description: { zh: 方块替换时散落保存的装备和铁锭。 , en: Scatters stored equipment and iron ingots when the block is replaced. }
deps:
  - kind: call
    to: mcde-degilding.gameplay.table.storage
    label: { zh: 创建并读取方块实体容器, en: Creates and reads the block entity container }
  - kind: call
    to: mcde-degilding.interface.handler.slots
    label: { zh: 通过容器工厂打开界面, en: Opens the screen through the container factory }
---
