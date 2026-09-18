---
uid: f6b218c4
id: mcde-degilding.bootstrap.client
parent: mcde-degilding.bootstrap
revision: 970658398c9f9ef06cd53a71c5542f4416e9e79e
updated_at: 2026-09-18T15:33:02Z
fingerprint: 808b4035d4d1e2fe49e41c49f8da5daaeac8492a5834185e938b1fcb2ae5d352
state: active
name: { zh: 客户端启动入口, en: Client initialization entry point }
description: { zh: 将褪金台的同步界面处理器绑定到客户端 DegildingScreen 实现。, en: Binds the Degilding Table's synchronized handler to the client DegildingScreen implementation. }
source:
  - path: src/main/java/io/github/backupcup/mcdedegilding/McdeDegildingClient.java
    line: 8
    end_line: 13
  - path: src/1.21.1/java/io/github/backupcup/mcdedegilding/McdeDegildingClient.java
    line: 8
    end_line: 13
apis:
  - protocol: file
    path: fabric:client-initializer
    description: { zh: Fabric 客户端初始化入口。 , en: Fabric client initialization entry point. }
deps:
  - kind: reference
    to: mcde-degilding.bootstrap.registries.screen-handlers
    label: { zh: 使用已注册的处理器类型, en: Uses the registered handler type }
  - kind: call
    to: mcde-degilding.interface.client.rendering
    label: { zh: 绑定界面实现, en: Bind screen implementation }
---
