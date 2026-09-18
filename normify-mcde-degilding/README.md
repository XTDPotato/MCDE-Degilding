# MCDE Degilding Architecture

This directory is a Normify-compatible static architecture snapshot for the
current repository revision `970658398c9f9ef06cd53a71c5542f4416e9e79e`.

The installed `normify-gen` skill targets DeepSeek Harness `normify_*` tools,
which are not connected in this Codex workspace. The module Markdown files
therefore follow the Normify data contract directly, while `outline.md`,
`tree.json`, `api-index.json`, and `architecture.html` are generated static
views.

- Read [outline.md](outline.md) for the concise module tree.
- Open [architecture.html](architecture.html) for a browsable local overview.
- Use [modules](modules) as the source-of-truth architecture database.
- Read [receipt.json](receipt.json) for snapshot metadata and validation.

On a DeepSeek Harness environment, import this directory and use the native
`normify_validate`, `normify_build`, and `normify_render` tools for subsequent
incremental refreshes.
