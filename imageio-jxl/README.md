# ImageIO Jpeg XL plugin

## Requirements

`libjxl` version 0.6.1+ must be installed on the target system. 

## Known installations methods

- Homebrew (Mac & Linux): `brew install jpeg-xl`
- apt (Linux): `apt-get install libjxl-dev`

## Features

- Decode Jpeg XL images

## Limitations

- Animations are not supported. A single image is returned.

## Implementation notes

The `panama` package bindings were generated using:
- jextract 22
- from the https://github.com/libjxl/libjxl repository, version 0.6.1
- based on the `decode.h` header file

The bindings were slightly adjusted for `JxlDecoderGetICCProfileSize` and `JxlDecoderGetColorAsICCProfile`, following a breaking change in [v0.9.0](https://github.com/libjxl/libjxl/releases/tag/v0.9.0) where a deprecated unused argument was removed.