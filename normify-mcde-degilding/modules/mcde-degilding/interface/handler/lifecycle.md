---
uid: e49bc63a
id: mcde-degilding.interface.handler.lifecycle
parent: mcde-degilding.interface.handler
revision: 970658398c9f9ef06cd53a71c5542f4416e9e79e
updated_at: 2026-09-18T15:33:02Z
fingerprint: 728c152ba707047b6e8853216a100a92ac83cde018a4ca66914288279e5847d2
state: active
name: { zh: 确认状态生命周期, en: Confirmation state lifecycle }
description: { zh: 在槽位点击、快速移动、容器关闭与显式客户端取消时重置待确认的褪金状态，并限制玩家必须能继续使用方块。, en: Resets pending degilding confirmation on slot clicks, quick moves, container closing, and explicit client cancellation, while enforcing continued block usability. }
source:
  - path: src/main/java/io/github/backupcup/mcdedegilding/screen/DegildingScreenHandler.java
    line: 158
    end_line: 245
  - path: src/1.21.1/java/io/github/backupcup/mcdedegilding/screen/DegildingScreenHandler.java
    line: 161
    end_line: 246
apis:
  - protocol: file
    path: minecraft:reset-pending-degilding
    description: { zh: 重置服务端待确认的褪金状态。 , en: Resets the server-side pending degilding state. }
  - protocol: file
    path: minecraft:quick-move-degilding-table
    description: { zh: 在容器和玩家库存之间快速转移物品。 , en: Quickly transfers items between the container and player inventory. }
deps:
  - kind: reference
    to: mcde-degilding.interface.handler.transaction
    label: { zh: 清除褪金确认进度, en: Clears degilding confirmation progress }
  - kind: reference
    to: mcde-degilding.gameplay.table.storage
    label: { zh: 检查容器可用性, en: Checks container usability }
---
