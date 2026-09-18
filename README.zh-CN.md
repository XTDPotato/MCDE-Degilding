# MCDE 褪金台

[English](README.md) | 简体中文

> Vibe Coding 项目，由 GPT6 Astra 协作完成。

这是一个适用于 Minecraft 1.20.1 Fabric 的 MC Dungeons: Enchanting（MCDE）附属模组。

褪金台允许玩家放入带有 MCDE 镀金附魔的物品，并选择移除其中一个镀金附魔。在生存模式下，每次移除需要消耗 16 个铁锭。其他附魔和符文槽会保持不变。选择列表使用 MCDE 的地下城风格附魔图标。

模组同时包含简体中文翻译，覆盖褪金台、MCDE 方块、界面文本、镀金提示和附魔描述。

## 从源码构建

项目目标为 Minecraft 1.20.1，使用 Fabric Loom，并需要 Java 17。

仓库不再分发 MCDE 依赖。构建前请将 `mcde-1.6.4-1.20.jar` 放入 `libs/`，然后执行：

```text
./gradlew build
```

构建产物位于：

```text
build/libs/mcde-degilding-1.0.0.jar
```

## 安装要求

请将以下文件放入同一个 Fabric 1.20.1 实例的 `mods` 文件夹：

- Fabric Loader 0.16.5 或更高版本
- Fabric API 0.92.2+1.20.1 或兼容版本
- MC Dungeons: Enchanting `1.6.4-1.20`
- `mcde-degilding-1.0.0.jar`

本版本不适用于 NeoForge 1.21.1。它是附属模组，不包含 MCDE 自身的附魔、物品或方块，因此仍需要安装原版 MCDE JAR。

## 项目署名

- 开发方式：Vibe Coding
- AI 协作者：GPT6 Astra
- 许可证：MIT
