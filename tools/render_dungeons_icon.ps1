Add-Type -AssemblyName System.Drawing

$root = Split-Path -Parent $PSScriptRoot
$targetPath = Join-Path $root 'src\main\resources\assets\mcde_degilding\icon.png'
$icon = [System.Drawing.Bitmap]::new(64, 64, [System.Drawing.Imaging.PixelFormat]::Format32bppArgb)
$graphics = [System.Drawing.Graphics]::FromImage($icon)
$graphics.SmoothingMode = [System.Drawing.Drawing2D.SmoothingMode]::None
$graphics.InterpolationMode = [System.Drawing.Drawing2D.InterpolationMode]::NearestNeighbor

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

function Draw-Diamond {
    param(
        [System.Drawing.Graphics]$Graphics,
        [int]$CenterX,
        [int]$CenterY,
        [int]$Radius,
        [string]$Outline,
        [string]$Fill
    )

    for ($offset = 0; $offset -le $Radius; $offset++) {
        $halfWidth = $Radius - $offset
        Fill-Rect $Graphics ($CenterX - $halfWidth) ($CenterY - $Radius + $offset) ($halfWidth * 2 + 1) 1 $Outline
        Fill-Rect $Graphics ($CenterX - $halfWidth) ($CenterY + $Radius - $offset) ($halfWidth * 2 + 1) 1 $Outline
    }
    for ($offset = 2; $offset -le ($Radius - 2); $offset++) {
        $halfWidth = $Radius - $offset - 1
        Fill-Rect $Graphics ($CenterX - $halfWidth) ($CenterY - $Radius + $offset) ($halfWidth * 2 + 1) 1 $Fill
        Fill-Rect $Graphics ($CenterX - $halfWidth) ($CenterY + $Radius - $offset) ($halfWidth * 2 + 1) 1 $Fill
    }
}

Fill-Rect $graphics 0 0 64 64 '#171420'
Fill-Rect $graphics 3 3 58 58 '#241A31'
Fill-Rect $graphics 5 5 54 54 '#120F19'
Fill-Rect $graphics 7 7 50 2 '#BE70EF'
Fill-Rect $graphics 7 55 50 2 '#6D388F'
Fill-Rect $graphics 7 7 2 50 '#A85DDB'
Fill-Rect $graphics 55 7 2 50 '#553071'

Draw-Diamond $graphics 24 28 17 '#7F3EA7' '#321B47'
Draw-Diamond $graphics 39 34 17 '#B45BE7' '#4B2767'
Draw-Diamond $graphics 32 38 17 '#D47CFF' '#5B317A'

Fill-Rect $graphics 27 27 10 3 '#1A1025'
Fill-Rect $graphics 24 30 16 4 '#1A1025'
Fill-Rect $graphics 27 34 10 4 '#1A1025'
Fill-Rect $graphics 30 38 5 5 '#1A1025'
Fill-Rect $graphics 30 29 4 3 '#F2B74A'
Fill-Rect $graphics 27 32 4 3 '#F2B74A'
Fill-Rect $graphics 30 35 4 3 '#F2B74A'
Fill-Rect $graphics 33 38 3 2 '#FFF0A2'
Fill-Rect $graphics 20 42 8 3 '#F1B342'
Fill-Rect $graphics 22 45 4 3 '#B05C26'

$graphics.Dispose()
$icon.Save($targetPath, [System.Drawing.Imaging.ImageFormat]::Png)
$icon.Dispose()
