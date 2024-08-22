package com.github.gotson.nightmonkeys.jxl;

import java.awt.color.ICC_Profile;

public record BasicInfo(
    int width,
    int height,
    boolean hasAlpha,
    boolean hasAnimation,
    int colorChannels,
    ICC_Profile iccProfile,
    int frameCount) {
}
