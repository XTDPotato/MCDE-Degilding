# MCDE Degilding

[简体中文](README.zh-CN.md) | English

> Vibe Coding project powered by GPT6 Astra.

Fabric add-on for MC Dungeons: Enchanting (MCDE), supporting Minecraft 1.20.1
and 1.21.1.

The Degilding Table lets players insert an item with MCDE gilding and remove
one selected gilded enchantment. Each removal consumes 16 iron ingots in
Survival mode. Other enchantments and runic slots remain unchanged. The
selection list uses MCDE's standard Dungeon-style enchantment icons.

The add-on includes Simplified Chinese translations for both the Degilding
Table and MCDE's own blocks, UI text, gilding tooltip, and enchantment
descriptions.

## Building From Source

This project has separate Fabric targets for each supported game version. The
MCDE dependency is not redistributed in this repository. Place both required
MCDE JARs in `libs/`:

```text
mcde-1.6.4-1.20.jar
mcde-1.6.4-1.21.jar
```

Build the desired target:

```text
./gradlew build -PtargetMinecraft=1.20.1
./gradlew build -PtargetMinecraft=1.21.1
```

| Game version | Java | MCDE dependency | Fabric API |
| --- | --- | --- | --- |
| 1.20.1 | 17 | `1.6.4-1.20` | `0.92.2+1.20.1` or compatible |
| 1.21.1 | 21 | `1.6.4-1.21` | `0.116.17+1.21.1` or compatible |

The output is written to `build/libs/` with its game version in the file name.

## Required Installation

Place the matching files in the same Fabric instance's `mods` folder:

- Fabric Loader 0.16.5 or newer
- The Fabric API and MCDE version shown in the table above
- The matching MCDE Degilding JAR from the release

This is Fabric-only and does not run in NeoForge. It is an add-on, so it does
not bundle MCDE's own enchantments, items, or blocks; the matching original
MCDE JAR must also be installed.

## MCDE Dependency

MCDE Degilding is an add-on for
[MC Dungeons: Enchanting (MCDE)](https://github.com/BackupCup/MCDE) by
BackupCup. Download and install the matching MCDE release before using this
mod.

## Mining and Drops

The Degilding Table can be mined with an empty hand or any tool and always
drops itself outside of explosions. It is tagged as an axe-minable block.

- Empty hand or non-axe tool: about 5 seconds
- Any axe: about 2 seconds
- Breaking the table also drops its stored equipment and iron ingots

## Project Credits

- Development style: Vibe Coding
- AI collaborator: GPT6 Astra
- License: MIT
