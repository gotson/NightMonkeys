# ImageIO WebP plugin

## Requirements

`libwebp` must be installed on the target system.

## Known installations methods

- Homebrew (Mac & Linux): `brew install webp`
- Scoop (Windows): `scoop install libwebp`
- Chocolatey (Windows): `choco install webp`

## Limitations

- Source subsampling doesn't exactly work, it uses the native scaling of `libwebp`, which doesn't have extensive control
  for subsampling X and Y. The following will not produce expected results:

```java
ImageReadParam param=reader.getDefaultReadParam();
    param.setSourceSubsampling(2,2,0,0);
```

- The Demux API is not yet implemented, so animations, ICC profiles, EXIF and XMP are not supported.

- Source region will snap to even values. This is a limitation from `libwebp`. The following will not produce expected
  results:

```java
ImageReadParam param=reader.getDefaultReadParam();
    param.setSourceRegion(new Rectangle(3,3,9,9));
```