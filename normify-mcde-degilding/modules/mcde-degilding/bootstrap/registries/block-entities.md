---
uid: 36f0a9d2
id: mcde-degilding.bootstrap.registries.block-entities
parent: mcde-degilding.bootstrap.registries
revision: 970658398c9f9ef06cd53a71c5542f4416e9e79e
updated_at: 2026-09-18T15:33:02Z
fingerprint: 4a429e638027981bc10f43c6845f560fc003b5f8fbad217798b74277c3b0ccdb
state: active
name: { zh: 方块实体注册, en: Block entity registration }
description: { zh: 为褪金台注册持久化库存方块实体类型，并关联已注册的方块。, en: Registers the persistent-inventory block entity type for the table and associates it with the registered block. }
source:
  - path: src/main/java/io/github/backupcup/mcdedegilding/registry/ModBlockEntities.java
    line: 10
    end_line: 23
  - path: src/1.21.1/java/io/github/backupcup/mcdedegilding/registry/ModBlockEntities.java
    line: 10
    end_line: 23
apis:
  - protocol: file
    path: registry:block-entity-mcde-degilding-degilding-table
    description: { zh: 注册褪金台方块实体类型。 , en: Registers the Degilding Table block entity type. }
deps:
  - kind: reference
    to: mcde-degilding.gameplay.table.storage
    label: { zh: 绑定持久化库存实现, en: Binds persistent inventory implementation }
  - kind: reference
    to: mcde-degilding.bootstrap.registries.blocks
    label: { zh: 关联方块类型, en: Associates block type }
---
