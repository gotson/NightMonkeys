# ImageIO WebP plugin

## Requirements

`libwebp` and `libwebpdemux` version 1.2.4+ must be installed on the target system.

## Known installations methods

- Homebrew (Mac & Linux): `brew install webp`
- apt (Linux): `apt-get install libwebp-dev`

## Features

- Decode WebP images (lossless, lossy, and alpha)
- Decode WebP animations. The reader returns the number of images in the animation, and can read individual frames.
- For WebP Containers containing ICC Profile, that profile will be applied on the decoded image.

## Limitations

- Source subsampling doesn't exactly work, it uses the native scaling of `libwebp`, which doesn't have extensive control
  for subsampling X and Y. The following will not produce expected results:

```java
ImageReadParam param=reader.getDefaultReadParam();
        param.setSourceSubsampling(2,2,0,0);
```

- The Demux API is partially implemented:
  - Supported: ICC profile, animation
  - Unsupported: EXIF and XMP

- Source region will snap to even values. This is a limitation from `libwebp`. The following will not produce expected
  results:

```java
ImageReadParam param=reader.getDefaultReadParam();
    param.setSourceRegion(new Rectangle(3,3,9,9));
```

## Implementation notes

The `panama` package bindings were generated using:
- jextract 22
- from the https://github.com/webmproject/libwebp repository, version 1.2.4
- based on:
  - the `decode.h` header file for `panama.webp`
  - the `demux.h` header file for `panama.webpdemux`