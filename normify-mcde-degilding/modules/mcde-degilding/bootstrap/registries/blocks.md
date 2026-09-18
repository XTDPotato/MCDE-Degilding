---
uid: 29c4d1be
id: mcde-degilding.bootstrap.registries.blocks
parent: mcde-degilding.bootstrap.registries
revision: 970658398c9f9ef06cd53a71c5542f4416e9e79e
updated_at: 2026-09-18T15:33:02Z
fingerprint: 8ac88dbc81bbf6799cdd91e825a47f403ddb96f391a52b3b609e9cab962baa06
state: active
name: { zh: 褪金台方块注册, en: Degilding Table block registration }
description: { zh: 注册非不透明的褪金台方块及其物品形式，并加入功能性物品栏。, en: Registers the non-opaque Degilding Table block and item form, then adds it to the functional item group. }
source:
  - path: src/main/java/io/github/backupcup/mcdedegilding/registry/ModBlocks.java
    line: 17
    end_line: 41
  - path: src/1.21.1/java/io/github/backupcup/mcdedegilding/registry/ModBlocks.java
    line: 16
    end_line: 40
apis:
  - protocol: file
    path: registry:block-mcde-degilding-degilding-table
    description: { zh: 注册 mcde_degilding:degilding_table 方块及物品。 , en: Registers the mcde_degilding:degilding_table block and item. }
deps:
  - kind: reference
    to: mcde-degilding.gameplay.table.block
    label: { zh: 实例化方块逻辑, en: Instantiates block behavior }
---
