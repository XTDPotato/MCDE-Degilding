# MCDE Degilding

[简体中文](README.zh-CN.md) | English

> Vibe Coding project powered by GPT6 Astra.

Minecraft 1.20.1 Fabric add-on for MC Dungeons: Enchanting (MCDE).

The Degilding Table lets players insert an item with MCDE gilding and remove
one selected gilded enchantment. Each removal consumes 16 iron ingots in
Survival mode. Other enchantments and runic slots remain unchanged. The
selection list uses MCDE's standard Dungeon-style enchantment icons.

The add-on includes Simplified Chinese translations for both the Degilding
Table and MCDE's own blocks, UI text, gilding tooltip, and enchantment
descriptions.

## Building From Source

This project targets Minecraft 1.20.1 with Fabric Loom and requires Java 17.
The MCDE dependency is not redistributed in this repository. For a local
build, place `mcde-1.6.4-1.20.jar` in `libs/`, then run:

```text
./gradlew build
```

The output is written to `build/libs/mcde-degilding-1.0.0.jar`.

## Required Installation

Place all of the following in the same Fabric 1.20.1 instance's `mods` folder:

- Fabric Loader 0.16.5 or newer
- Fabric API 0.92.2+1.20.1 or compatible
- MC Dungeons: Enchanting `1.6.4-1.20`
- `mcde-degilding-1.0.0.jar`

This release does not run in NeoForge 1.21.1. It is an add-on, so it does not
bundle MCDE's own enchantments, items, or blocks; the original MCDE JAR must
also be installed.

## Project Credits

- Development style: Vibe Coding
- AI collaborator: GPT6 Astra
- License: MIT
