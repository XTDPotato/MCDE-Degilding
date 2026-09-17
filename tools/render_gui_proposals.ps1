Add-Type -AssemblyName System.Drawing

$root = Split-Path -Parent $PSScriptRoot
$sourcePath = Join-Path $root '.mcde-1.20-repository\MCDE-1.6.4-1.20\src\main\resources\assets\mcde\textures\gui\gilding_foundry.png'
$outputDir = Join-Path $root 'art\gui-proposals'
$targetPath = Join-Path $root 'src\main\resources\assets\mcde_degilding\textures\gui\degilding_table.png'
New-Item -ItemType Directory -Path $outputDir -Force | Out-Null

function New-Canvas {
    param([System.Drawing.Bitmap]$Source)

    $canvas = [System.Drawing.Bitmap]::new(176, 166, [System.Drawing.Imaging.PixelFormat]::Format32bppArgb)
    $graphics = [System.Drawing.Graphics]::FromImage($canvas)
    $graphics.InterpolationMode = [System.Drawing.Drawing2D.InterpolationMode]::NearestNeighbor
    $graphics.PixelOffsetMode = [System.Drawing.Drawing2D.PixelOffsetMode]::Half
    $graphics.DrawImage(
        $Source,
        [System.Drawing.Rectangle]::new(0, 0, 168, 166),
        [System.Drawing.Rectangle]::new(2, 20, 168, 166),
        [System.Drawing.GraphicsUnit]::Pixel
    )
    $graphics.Dispose()
    return $canvas
}

function Fill-Rect {
    param(
        [System.Drawing.Graphics]$Graphics,
        [int]$X,
        [int]$Y,
        [int]$Width,
        [int]$Height,
        [string]$Color
    )

    $brush = [System.Drawing.SolidBrush]::new([System.Drawing.ColorTranslator]::FromHtml($Color))
    $Graphics.FillRectangle($brush, $X, $Y, $Width, $Height)
    $brush.Dispose()
}

function Draw-Frame {
    param(
        [System.Drawing.Graphics]$Graphics,
        [int]$X,
        [int]$Y,
        [int]$Width,
        [int]$Height,
        [string]$Outer,
        [string]$Highlight,
        [string]$Inner
    )

    Fill-Rect $Graphics $X $Y $Width $Height $Outer
    Fill-Rect $Graphics ($X + 1) ($Y + 1) ($Width - 2) ($Height - 2) $Highlight
    Fill-Rect $Graphics ($X + 3) ($Y + 3) ($Width - 6) ($Height - 6) $Inner
}

function Draw-Slot {
    param(
        [System.Drawing.Graphics]$Graphics,
        [int]$X,
        [int]$Y
    )

    Fill-Rect $Graphics $X $Y 20 20 '#B965DE'
    Fill-Rect $Graphics ($X + 1) ($Y + 1) 18 18 '#15111D'
}

function Draw-GildingGlyph {
    param(
        [System.Drawing.Graphics]$Graphics,
        [int]$X,
        [int]$Y,
        [int]$Style
    )

    $gold = if ($Style -eq 0) { '#FFD95E' } elseif ($Style -eq 1) { '#F59E36' } else { '#E8C64E' }
    $dark = '#6C321A'
    Fill-Rect $Graphics ($X + 3) $Y 7 2 $dark
    Fill-Rect $Graphics ($X + 2) ($Y + 2) 9 11 $dark
    Fill-Rect $Graphics ($X + 3) ($Y + 1) 7 11 $gold
    Fill-Rect $Graphics ($X + 5) ($Y + 2) 2 8 '#FFF5B0'
    Fill-Rect $Graphics ($X + 1) ($Y + 4) 11 4 $gold
    Fill-Rect $Graphics ($X + 4) ($Y + 13) 5 2 $dark
}

function Draw-List {
    param(
        [System.Drawing.Graphics]$Graphics,
        [int]$X,
        [int]$Y,
        [int]$Width,
        [string]$Panel,
        [string]$Row,
        [string]$Selected
    )

    Draw-Frame $Graphics $X $Y $Width 66 '#211A16' '#B17C3D' $Panel
    for ($rowIndex = 0; $rowIndex -lt 3; $rowIndex++) {
        $rowColor = if ($rowIndex -eq 1) { $Selected } else { $Row }
        Fill-Rect $Graphics ($X + 4) ($Y + 4 + $rowIndex * 19) ($Width - 13) 16 $rowColor
        Fill-Rect $Graphics ($X + 4) ($Y + 18 + $rowIndex * 19) ($Width - 13) 2 '#3C281D'
        Draw-GildingGlyph $Graphics ($X + 8) ($Y + 5 + $rowIndex * 19) $rowIndex
        Fill-Rect $Graphics ($X + 28) ($Y + 8 + $rowIndex * 19) 31 2 '#E5C166'
        Fill-Rect $Graphics ($X + 28) ($Y + 12 + $rowIndex * 19) 20 2 '#9C7440'
    }

    Fill-Rect $Graphics ($X + $Width - 7) ($Y + 4) 3 58 '#39271F'
    Fill-Rect $Graphics ($X + $Width - 7) ($Y + 23) 3 21 '#F1C657'
    Fill-Rect $Graphics ($X + $Width - 6) ($Y + 24) 1 19 '#FFF0A5'
}

function Draw-EmptyList {
    param(
        [System.Drawing.Graphics]$Graphics,
        [int]$X,
        [int]$Y,
        [int]$Width
    )

    Draw-Frame $Graphics $X $Y $Width 66 '#17131F' '#9C6936' '#211A2D'
    for ($rowIndex = 0; $rowIndex -lt 3; $rowIndex++) {
        $rowY = $Y + 3 + $rowIndex * 20
        Fill-Rect $Graphics ($X + 3) $rowY ($Width - 10) 18 '#4C385B'
        Fill-Rect $Graphics ($X + 3) ($rowY + 16) ($Width - 10) 2 '#2B202E'
    }
    Fill-Rect $Graphics ($X + $Width - 7) ($Y + 4) 3 58 '#38271F'
}

function Draw-Variant {
    param(
        [System.Drawing.Bitmap]$Canvas,
        [int]$Variant
    )

    $graphics = [System.Drawing.Graphics]::FromImage($Canvas)
    $graphics.InterpolationMode = [System.Drawing.Drawing2D.InterpolationMode]::NearestNeighbor
    $graphics.SmoothingMode = [System.Drawing.Drawing2D.SmoothingMode]::None

    switch ($Variant) {
        1 {
            Fill-Rect $graphics 0 0 168 78 '#292238'
            Fill-Rect $graphics 3 3 162 72 '#171421'
            Fill-Rect $graphics 6 6 156 66 '#211A2D'
            Draw-Slot -Graphics $graphics -X 18 -Y 12
            Draw-Slot -Graphics $graphics -X 18 -Y 40
            Draw-List -Graphics $graphics -X 40 -Y 7 -Width 117 -Panel '#211A2D' -Row '#4C385B' -Selected '#765093'
            Fill-Rect $graphics 3 74 162 4 '#744B97'
            Fill-Rect $graphics 4 75 160 1 '#E1A6FF'
        }
        2 {
            Fill-Rect $graphics 0 0 168 78 '#543A32'
            Fill-Rect $graphics 3 3 162 72 '#7D4B37'
            Fill-Rect $graphics 5 5 158 68 '#392924'
            Fill-Rect $graphics 8 8 151 62 '#51352A'
            Draw-Slot -Graphics $graphics -X 13 -Y 14
            Draw-Slot -Graphics $graphics -X 13 -Y 43
            Draw-List -Graphics $graphics -X 42 -Y 7 -Width 115 -Panel '#48332B' -Row '#6D4634' -Selected '#A67531'
            Fill-Rect $graphics 6 6 151 2 '#C98455'
            Fill-Rect $graphics 6 70 151 2 '#241B19'
            Fill-Rect $graphics 3 74 162 4 '#B36A46'
            Fill-Rect $graphics 4 75 160 1 '#FFD08A'
        }
        3 {
            Fill-Rect $graphics 0 0 168 78 '#373C3B'
            Fill-Rect $graphics 3 3 162 72 '#697167'
            Fill-Rect $graphics 5 5 158 68 '#2A302E'
            Fill-Rect $graphics 8 8 152 62 '#202725'
            Draw-Slot -Graphics $graphics -X 13 -Y 14
            Draw-Slot -Graphics $graphics -X 13 -Y 43
            Draw-List -Graphics $graphics -X 42 -Y 7 -Width 115 -Panel '#26312D' -Row '#3D5149' -Selected '#80723A'
            Fill-Rect $graphics 6 6 151 2 '#B4A75E'
            Fill-Rect $graphics 6 70 151 2 '#171B1A'
            Fill-Rect $graphics 3 74 162 4 '#8B8A55'
            Fill-Rect $graphics 4 75 160 1 '#D1C47B'
        }
    }

    $graphics.Dispose()
}

$source = [System.Drawing.Bitmap]::new($sourcePath)
try {
    foreach ($variant in 1..3) {
        $canvas = New-Canvas $source
        try {
            Draw-Variant $canvas $variant
            $preview = [System.Drawing.Bitmap]::new(704, 664, [System.Drawing.Imaging.PixelFormat]::Format32bppArgb)
            $previewGraphics = [System.Drawing.Graphics]::FromImage($preview)
            $previewGraphics.InterpolationMode = [System.Drawing.Drawing2D.InterpolationMode]::NearestNeighbor
            $previewGraphics.PixelOffsetMode = [System.Drawing.Drawing2D.PixelOffsetMode]::Half
            $previewGraphics.DrawImage($canvas, [System.Drawing.Rectangle]::new(0, 0, 704, 664))
            $previewGraphics.Dispose()
            $preview.Save(
                (Join-Path $outputDir ("degilding_gui_option_{0}.png" -f $variant)),
                [System.Drawing.Imaging.ImageFormat]::Png
            )
            $preview.Dispose()
        } finally {
            $canvas.Dispose()
        }
    }

    $finalTexture = [System.Drawing.Bitmap]::new($source)
    $finalGraphics = [System.Drawing.Graphics]::FromImage($finalTexture)
    $finalGraphics.InterpolationMode = [System.Drawing.Drawing2D.InterpolationMode]::NearestNeighbor
    $finalGraphics.SmoothingMode = [System.Drawing.Drawing2D.SmoothingMode]::None
    $finalGraphics.TranslateTransform(2, 20)
    Fill-Rect $finalGraphics 0 0 168 78 '#292238'
    Fill-Rect $finalGraphics 3 3 162 72 '#171421'
    Fill-Rect $finalGraphics 6 6 156 66 '#211A2D'
    Draw-Slot -Graphics $finalGraphics -X 18 -Y 12
    Draw-Slot -Graphics $finalGraphics -X 18 -Y 40
    Draw-EmptyList -Graphics $finalGraphics -X 42 -Y 7 -Width 115
    Fill-Rect $finalGraphics 3 74 162 4 '#744B97'
    Fill-Rect $finalGraphics 4 75 160 1 '#E1A6FF'
    $finalGraphics.Dispose()
    $finalTexture.Save($targetPath, [System.Drawing.Imaging.ImageFormat]::Png)
    $finalTexture.Dispose()
} finally {
    $source.Dispose()
}
