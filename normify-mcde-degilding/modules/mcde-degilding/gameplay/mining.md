---
uid: 90fac7e2
id: mcde-degilding.gameplay.mining
parent: mcde-degilding.gameplay
revision: 970658398c9f9ef06cd53a71c5542f4416e9e79e
updated_at: 2026-09-18T15:33:02Z
fingerprint: 112d8353562f6bba0949dad08b0dd01a9d7487ba6d4c87699ddfa87b88656e85
state: active
name: { zh: 挖掘速度规则, en: Mining speed policy }
description: { zh: 注入褪金台的破坏进度计算，使空手可挖掘并让不同等级的镐逐级加快速度。, en: Injects Degilding Table breaking progress so it is hand-mineable while progressively faster with higher pickaxe tiers. }
source:
  - path: src/main/java/io/github/backupcup/mcdedegilding/mixin/DegildingTableMiningMixin.java
    line: 17
    end_line: 48
  - path: src/1.21.1/java/io/github/backupcup/mcdedegilding/mixin/DegildingTableMiningMixin.java
    line: 17
    end_line: 48
apis:
  - protocol: file
    path: mixin:degilding-table-breaking-delta
    description: { zh: 为褪金台计算按镐等级分档的破坏进度。 , en: Calculates pickaxe-tiered breaking progress for the Degilding Table. }
deps:
  - kind: reference
    to: mcde-degilding.bootstrap.registries.blocks
    label: { zh: 识别褪金台方块, en: Identifies the Degilding Table block }
---
