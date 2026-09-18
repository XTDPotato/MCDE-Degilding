---
uid: b21f6d9a
id: mcde-degilding.content.metadata
parent: mcde-degilding.content
revision: 970658398c9f9ef06cd53a71c5542f4416e9e79e
updated_at: 2026-09-18T15:33:02Z
fingerprint: fb97e26585b4ccd8a27394cb7f0783dc60f9448ad197abcf70176136abe0931b
state: active
name: { zh: Fabric 元数据与 Mixin 配置, en: Fabric metadata and Mixin configuration }
description: { zh: 声明模组入口、依赖、图标和 MCDE 项目链接，并启用褪金台挖掘速度 Mixin。, en: Declares mod entry points, dependencies, icon, and MCDE project link, then enables the table mining-speed Mixin. }
source:
  - path: src/main/resources/fabric.mod.json
  - path: src/main/resources/mcde_degilding.mixins.json
apis:
  - protocol: file
    path: fabric:mod-metadata-mcde-degilding
    description: { zh: 声明 Fabric 模组元数据和入口点。 , en: Declares Fabric mod metadata and entry points. }
  - protocol: file
    path: mixin:configuration-mcde-degilding
    description: { zh: 启用褪金台挖掘速度注入。 , en: Enables the Degilding Table mining-speed injection. }
deps:
  - kind: reference
    to: mcde-degilding.gameplay.mining
    label: { zh: 加载挖掘速度 Mixin, en: Loads mining-speed Mixin }
  - kind: reference
    to: mcde-degilding.bootstrap.common
    label: { zh: 声明服务端入口, en: Declares common entry point }
  - kind: reference
    to: mcde-degilding.bootstrap.client
    label: { zh: 声明客户端入口, en: Declares client entry point }
---
