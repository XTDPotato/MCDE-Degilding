# MCDE 褪金台

[English](README.md) | 简体中文

> Vibe Coding 项目，由 GPT6 Astra 协作完成。

这是一个适用于 Minecraft 1.20.1 与 1.21.1 Fabric 的 MC Dungeons:
Enchanting（MCDE）附属模组。

褪金台允许玩家放入带有 MCDE 镀金附魔的物品，并选择移除其中一个镀金附魔。在生存模式下，每次移除需要消耗 16 个铁锭。其他附魔和符文槽会保持不变。选择列表使用 MCDE 的地下城风格附魔图标。

模组同时包含简体中文翻译，覆盖褪金台、MCDE 方块、界面文本、镀金提示和附魔描述。

## 从源码构建

项目为每个支持的游戏版本提供独立 Fabric 构建目标。

仓库不再分发 MCDE 依赖。构建前请将以下两个 JAR 放入 `libs/`：

```text
mcde-1.6.4-1.20.jar
mcde-1.6.4-1.21.jar
```

按目标版本构建：

```text
./gradlew build -PtargetMinecraft=1.20.1
./gradlew build -PtargetMinecraft=1.21.1
```

| 游戏版本 | Java | MCDE 依赖 | Fabric API |
| --- | --- | --- | --- |
| 1.20.1 | 17 | `1.6.4-1.20` | `0.92.2+1.20.1` 或兼容版本 |
| 1.21.1 | 21 | `1.6.4-1.21` | `0.116.17+1.21.1` 或兼容版本 |

构建产物位于 `build/libs/`，文件名会包含对应的游戏版本。

## 安装要求

请将以下对应版本的文件放入同一个 Fabric 实例的 `mods` 文件夹：

- Fabric Loader 0.16.5 或更高版本
- 上表列出的 Fabric API 与 MCDE 版本
- Release 中与游戏版本对应的 MCDE 褪金台 JAR

本模组仅支持 Fabric，不适用于 NeoForge。它是附属模组，不包含 MCDE 自身的附魔、物品或方块，因此仍需要安装对应版本的原版 MCDE JAR。

## MCDE 前置模组

MCDE 褪金台是 BackupCup 的
[MC Dungeons: Enchanting（MCDE）](https://github.com/BackupCup/MCDE)
附属模组。使用前请下载并安装与游戏版本对应的 MCDE。

## 挖掘与掉落

褪金台可使用空手或任何工具挖掘，并会正常掉落自身。它被标记为可用斧头挖掘的方块。

- 空手或非斧头工具：约 5 秒
- 任意斧头：约 2 秒
- 破坏褪金台时，还会掉落其中保存的装备与铁锭

## 项目署名

- 开发方式：Vibe Coding
- AI 协作者：GPT6 Astra
- 许可证：MIT
