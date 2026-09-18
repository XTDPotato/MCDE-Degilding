---
uid: e3a71c5d
id: mcde-degilding.bootstrap.common
parent: mcde-degilding.bootstrap
revision: 970658398c9f9ef06cd53a71c5542f4416e9e79e
updated_at: 2026-09-18T15:33:02Z
fingerprint: 1cc4ebfcba7d55ad14722d5ddf3c3234f66247e98ff47f62b7c0b9f65e1d54f2
state: active
name: { zh: 服务端启动入口, en: Common initialization entry point }
description: { zh: 提供模组命名空间 ID，并在 Fabric 初始化阶段按顺序注册运行时内容。, en: Provides the mod namespace ID and registers runtime content in the Fabric initialization phase. }
source:
  - path: src/main/java/io/github/backupcup/mcdedegilding/McdeDegilding.java
    line: 11
    end_line: 25
  - path: src/1.21.1/java/io/github/backupcup/mcdedegilding/McdeDegilding.java
    line: 11
    end_line: 25
apis:
  - protocol: file
    path: fabric:main-initializer
    description: { zh: Fabric 服务端初始化入口。 , en: Fabric common initialization entry point. }
deps:
  - kind: call
    to: mcde-degilding.bootstrap.registries.blocks
    label: { zh: 注册方块, en: Register blocks }
  - kind: call
    to: mcde-degilding.bootstrap.registries.block-entities
    label: { zh: 注册方块实体, en: Register block entities }
  - kind: call
    to: mcde-degilding.bootstrap.registries.screen-handlers
    label: { zh: 注册界面处理器, en: Register screen handlers }
---
