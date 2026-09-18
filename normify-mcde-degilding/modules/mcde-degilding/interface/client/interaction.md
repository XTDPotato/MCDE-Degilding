---
uid: 19a7fdec
id: mcde-degilding.interface.client.interaction
parent: mcde-degilding.interface.client
revision: 970658398c9f9ef06cd53a71c5542f4416e9e79e
updated_at: 2026-09-18T15:33:02Z
fingerprint: 76b3c3eaee952c8bec17384a409cca68f3b2c799e292ac591ec50600d2144f12
state: active
name: { zh: 列表交互与滚动, en: List interaction and scrolling }
description: { zh: 处理镀金条目点击、5 秒本地确认状态、鼠标滚轮、滚动条拖动，以及其他操作触发的确认取消。, en: Handles gilding-entry clicks, five-second local confirmation state, mouse-wheel input, scrollbar dragging, and confirmation cancellation after other actions. }
source:
  - path: src/main/java/io/github/backupcup/mcdedegilding/screen/DegildingScreen.java
    line: 153
    end_line: 389
  - path: src/1.21.1/java/io/github/backupcup/mcdedegilding/screen/DegildingScreen.java
    line: 156
    end_line: 389
apis:
  - protocol: file
    path: minecraft:degilding-screen-click-entry
    description: { zh: 向服务端发送所选镀金项目的容器按钮事件。 , en: Sends the selected gilding entry as a container button event to the server. }
  - protocol: file
    path: minecraft:degilding-screen-scroll-list
    description: { zh: 通过滚轮和可拖动滑块浏览超出可见范围的镀金项目。 , en: Browses gilding entries beyond the visible range through the mouse wheel and draggable thumb. }
  - protocol: file
    path: minecraft:degilding-screen-cancel-confirmation
    description: { zh: 在其他操作、物品变化或超时后重置客户端并通知服务端。 , en: Resets the client and notifies the server after other actions, item changes, or timeout. }
deps:
  - kind: call
    to: mcde-degilding.interface.handler.transaction
    label: { zh: 发起服务端褪金选择或确认, en: Initiates server-side selection or confirmation }
  - kind: call
    to: mcde-degilding.interface.handler.lifecycle
    label: { zh: 通知服务端重置确认, en: Notifies the server to reset confirmation }
---
