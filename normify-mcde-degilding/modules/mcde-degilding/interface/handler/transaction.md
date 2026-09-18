---
uid: d61fa28c
id: mcde-degilding.interface.handler.transaction
parent: mcde-degilding.interface.handler
revision: 970658398c9f9ef06cd53a71c5542f4416e9e79e
updated_at: 2026-09-18T15:33:02Z
fingerprint: 728c152ba707047b6e8853216a100a92ac83cde018a4ca66914288279e5847d2
state: active
name: { zh: 褪金确认事务, en: Degilding confirmation transaction }
description: { zh: 校验铁锭、同一项目与 5 秒时间窗；确认后删除附魔和 MCDE 镀金标记，并在非创造模式消耗 16 个铁锭。, en: Validates iron, the same entry, and a five-second window; on confirmation removes the enchantment and MCDE gilding marker and consumes 16 iron outside Creative mode. }
source:
  - path: src/main/java/io/github/backupcup/mcdedegilding/screen/DegildingScreenHandler.java
    line: 109
    end_line: 156
  - path: src/1.21.1/java/io/github/backupcup/mcdedegilding/screen/DegildingScreenHandler.java
    line: 113
    end_line: 159
apis:
  - protocol: file
    path: minecraft:screen-button-remove-gilding
    description: { zh: 服务端处理一个镀金条目的首次选择或二次确认褪金。 , en: Server-handles a first selection or second confirmation for one gilding entry. }
deps:
  - kind: reference
    to: mcde-degilding.interface.handler.slots
    label: { zh: 读取输入槽与镀金列表, en: Reads input slot and gilding list }
  - kind: dataflow
    to: mcde-degilding.gameplay.table.storage
    label: { zh: 更新铁锭库存和脏状态, en: Updates iron inventory and dirty state }
---
