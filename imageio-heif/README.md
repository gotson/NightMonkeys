# ImageIO HEIF/AVIF plugin

## Requirements

`libheif` version 1.16+ must be installed on the target system.

## Known installations methods

- Homebrew (Mac & Linux): `brew install libheif`
- apt (Linux): `apt-get install libheif-dev`

## Features

- Decode HEIF and AVIF images

## Limitations

- HEIC and AVIF animations are not supported. A single image is returned.
- Not implemented yet:
  - Color profiles
  - EXIF and XMP
  - Thumbnails
