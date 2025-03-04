package com.github.gotson.nightmonkeys.heif.imageio.plugins;

import com.github.gotson.nightmonkeys.heif.Heif;
import com.github.gotson.nightmonkeys.heif.HeifException;

import javax.imageio.ImageWriteParam;
import java.util.Locale;

public final class HeifWriteParam extends ImageWriteParam {
    private boolean lossless;

    public HeifWriteParam(final Locale locale) {
        super(locale);

        try {
            compressionTypes = Heif.getAvailableEncoders()
                .stream()
                .map(it -> it.format().name())
                .distinct()
                .toArray(String[]::new);
        } catch (HeifException e) {
            System.err.println("Could not get available encoders");
        }

        canWriteCompressed = true;
        lossless = true;
    }

    public boolean isLossless() {
        return lossless;
    }

    public void setLossless(boolean lossless) {
        this.lossless = lossless;
    }

    @Override
    public boolean isCompressionLossless() {
        return lossless;
    }
}
