# ImageIO WebP plugin

## Requirements

`libwebpdemux` must be installed on the target system.

## Known installations methods

- Homebrew (Mac & Linux): `brew install webp`
- Scoop (Windows): `scoop install libwebp`
- Chocolatey (Windows): `choco install webp`

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