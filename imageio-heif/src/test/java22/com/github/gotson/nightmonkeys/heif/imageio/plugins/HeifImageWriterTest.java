package com.github.gotson.nightmonkeys.heif.imageio.plugins;

import com.github.gotson.nightmonkeys.common.imageio.ImageWriterAbstractTest;
import com.twelvemonkeys.imageio.util.ImageTypeSpecifiers;

import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.spi.ImageWriterSpi;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HeifImageWriterTest extends ImageWriterAbstractTest<HeifImageWriter> {
    @Override
    protected ImageWriterSpi createProvider() {
        return new HeifImageWriterSpi();
    }

    @Override
    protected String getTestFileExtension() {
        return "heif";
    }

    @Override
    protected List<? extends RenderedImage> getTestData() {
        return List.of(
            new BufferedImage(1024, 1024, BufferedImage.TYPE_INT_ARGB),
            new BufferedImage(1024, 1024, BufferedImage.TYPE_INT_BGR),
            new BufferedImage(1024, 1024, BufferedImage.TYPE_INT_RGB),
            new BufferedImage(1024, 1024, BufferedImage.TYPE_INT_ARGB_PRE),
            // this image has different masks (RGBA) from the defaults (ARGB)
            ImageTypeSpecifiers.createPacked(ColorSpace.getInstance(ColorSpace.CS_sRGB), 0xff000000, 0x00ff0000, 0x0000ff00, 0x000000ff, DataBuffer.TYPE_INT, false)
                .createBufferedImage(512, 1024)
        );
    }

    @Override
    protected List<HeifWriteParam> getTestWriteParams() {
        List<HeifWriteParam> params = new ArrayList<>();
        ImageWriter writer;
        try {
            writer = createWriter();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (String compressionType : writer.getDefaultWriteParam().getCompressionTypes()) {
            for (int i = 0; i <= 10; i++) {
                HeifWriteParam param = (HeifWriteParam) writer.getDefaultWriteParam();
                param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                param.setCompressionType(compressionType);
                param.setLossless(i == 0);
                if (i > 0) param.setCompressionQuality(i * 0.1F);
                params.add(param);
            }
        }
        return params;
    }
}
