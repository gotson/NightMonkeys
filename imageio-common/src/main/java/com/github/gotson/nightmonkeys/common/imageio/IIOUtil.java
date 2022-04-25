package com.github.gotson.nightmonkeys.common.imageio;

import javax.imageio.stream.ImageInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public final class IIOUtil {

    private IIOUtil() {}

    public static byte[] byteArrayFromStream(ImageInputStream stream) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        final byte[] buf = new byte[8192];
        int n;
        while (0 < (n = stream.read(buf))) {
            bos.write(buf, 0, n);
        }
        return bos.toByteArray();
    }
}
