---
uid: 4d7bce95
id: mcde-degilding.bootstrap.registries.screen-handlers
parent: mcde-degilding.bootstrap.registries
revision: 970658398c9f9ef06cd53a71c5542f4416e9e79e
updated_at: 2026-09-18T15:33:02Z
fingerprint: 1eb3781c03d677d88a9b4588ec00075e194343336c62f3c8542f0210046b0a68
state: active
name: { zh: 界面处理器注册, en: Screen handler registration }
description: { zh: 创建并注册褪金台的同步 ScreenHandlerType，作为服务端容器和客户端界面的连接点。, en: Creates and registers the synchronized ScreenHandlerType that connects the server container and client screen. }
source:
  - path: src/main/java/io/github/backupcup/mcdedegilding/registry/ModScreenHandlers.java
    line: 10
    end_line: 20
  - path: src/1.21.1/java/io/github/backupcup/mcdedegilding/registry/ModScreenHandlers.java
    line: 10
    end_line: 20
apis:
  - protocol: file
    path: registry:screen-handler-mcde-degilding-degilding-table
    description: { zh: 注册褪金台 ScreenHandlerType。 , en: Registers the Degilding Table ScreenHandlerType. }
deps:
  - kind: reference
    to: mcde-degilding.interface.handler.slots
    label: { zh: 构造服务端容器, en: Constructs server container }
---
