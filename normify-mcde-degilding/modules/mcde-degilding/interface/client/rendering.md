---
uid: 0c6e3fb4
id: mcde-degilding.interface.client.rendering
parent: mcde-degilding.interface.client
revision: 970658398c9f9ef06cd53a71c5542f4416e9e79e
updated_at: 2026-09-18T15:33:02Z
fingerprint: 76b3c3eaee952c8bec17384a409cca68f3b2c799e292ac591ec50600d2144f12
state: active
name: { zh: 界面绘制与提示, en: Screen rendering and tooltips }
description: { zh: 绘制背景、装备和材料图标、半透明镀金项目、MCDE 图标回退，以及缺铁或待确认状态的提示文本。, en: Draws the background, equipment and material icons, translucent gilding entries, MCDE icon fallback, and tooltips for insufficient iron or pending confirmation. }
source:
  - path: src/main/java/io/github/backupcup/mcdedegilding/screen/DegildingScreen.java
    line: 22
    end_line: 151
  - path: src/1.21.1/java/io/github/backupcup/mcdedegilding/screen/DegildingScreen.java
    line: 22
    end_line: 153
apis:
  - protocol: file
    path: minecraft:degilding-screen-draw
    description: { zh: 绘制褪金台的主布局、物品和镀金项目。 , en: Draws the Degilding Table main layout, items, and gilding entries. }
  - protocol: file
    path: minecraft:degilding-screen-tooltip
    description: { zh: 为镀金项目显示铁锭需求、二次确认或附魔名称提示。 , en: Shows iron-requirement, second-confirmation, or enchantment-name tooltips for gilding entries. }
deps:
  - kind: reference
    to: mcde-degilding.interface.handler.slots
    label: { zh: 读取输入物品与镀金列表, en: Reads input item and gilding list }
  - kind: reference
    to: mcde-degilding.interface.handler.transaction
    label: { zh: 显示材料成本与确认状态, en: Displays material cost and confirmation state }
  - kind: reference
    to: mcde-degilding.content.assets.gui
    label: { zh: 使用界面贴图, en: Uses GUI texture }
---
