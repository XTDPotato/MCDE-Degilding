---
uid: 5a91f0e8
id: mcde-degilding.content.data.drops
parent: mcde-degilding.content.data
revision: 970658398c9f9ef06cd53a71c5542f4416e9e79e
updated_at: 2026-09-18T15:33:02Z
fingerprint: ce754f495edace66cdc0dab1f75f34bb3dbd23a2f65ae1925f4c913c56673862
state: active
name: { zh: 掉落与工具标签, en: Drops and tool tag }
description: { zh: 让褪金台在非爆炸破坏时掉落自身，并标记为镐可更快挖掘的方块。, en: Makes the table drop itself outside explosions and tags it as a block mined faster by pickaxes. }
source:
  - path: src/main/resources/data/mcde_degilding/loot_tables/blocks/degilding_table.json
  - path: src/main/resources/data/minecraft/tags/block/mineable/pickaxe.json
apis:
  - protocol: file
    path: minecraft:loot-degilding-table
    description: { zh: 定义褪金台自身掉落。 , en: Defines the Degilding Table self-drop. }
  - protocol: file
    path: minecraft:tag-mineable-pickaxe-degilding-table
    description: { zh: 将褪金台加入镐挖掘标签。 , en: Adds the table to the pickaxe-minable tag. }
---
