/*
 * Copyright (c) 2008, Harald Kuhr
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * * Neither the name of the copyright holder nor the names of its
 *   contributors may be used to endorse or promote products derived from
 *   this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.github.gotson.nightmonkeys.common.imageio;

import com.twelvemonkeys.imageio.stream.URLImageInputStreamSpi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InOrder;
import org.mockito.stubbing.Answer;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.event.IIOReadProgressListener;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.spi.IIORegistry;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.stream.ImageInputStream;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ImagingOpException;
import java.awt.image.RenderedImage;
import java.awt.image.SampleModel;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static java.lang.Math.min;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

/**
 * ImageReaderAbstractTest
 *
 * @author <a href="mailto:harald.kuhr@gmail.com">Harald Kuhr</a>
 * @author last modified by $Author: haraldk$
 * @version $Id: ImageReaderAbstractTest.java,v 1.0 Apr 1, 2008 10:36:46 PM haraldk Exp$
 */
@TestInstance(PER_CLASS)
public abstract class ImageReaderAbstractTest<T extends ImageReader> {
    // TODO: Should we really test if the provider is installed?
    //       - Pro: Tests the META-INF/services config
    //       - Con: Not all providers should be installed at runtime...
    // TODO: Create own subclass for testing the Spis?

    static {
        IIORegistry.getDefaultInstance().registerServiceProvider(new URLImageInputStreamSpi());
        ImageIO.setUseCache(false);
    }

    @SuppressWarnings("unchecked")
    private final Class<T> readerClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    protected final ImageReaderSpi provider = createProvider();

    protected abstract ImageReaderSpi createProvider();

    protected final T createReader() throws IOException {
        return readerClass.cast(provider.createReaderInstance(null));
    }

    protected abstract List<TestData> getTestData();

    protected abstract List<String> getFormatNames();

    protected abstract List<String> getSuffixes();

    protected abstract List<String> getMIMETypes();

    protected static void failBecause(String message, Throwable exception) {
        throw new AssertionError(message, exception);
    }

    protected void assertProviderInstalledForName(final String pFormat, final Class<? extends ImageReader> pReaderClass) {
        assertProviderInstalled0(pFormat.toUpperCase(), pReaderClass, ImageIO.getImageReadersByFormatName(pFormat.toUpperCase()));
        assertProviderInstalled0(pFormat.toLowerCase(), pReaderClass, ImageIO.getImageReadersByFormatName(pFormat.toLowerCase()));
    }

    protected void assertProviderInstalledForMIMEType(final String pType, final Class<? extends ImageReader> pReaderClass) {
        assertProviderInstalled0(pType, pReaderClass, ImageIO.getImageReadersByMIMEType(pType));
    }

    protected void assertProviderInstalledForSuffix(final String pType, final Class<? extends ImageReader> pReaderClass) {
        assertProviderInstalled0(pType, pReaderClass, ImageIO.getImageReadersBySuffix(pType));
    }

    private void assertProviderInstalled0(final String pFormat, final Class<? extends ImageReader> pReaderClass, final Iterator<ImageReader> pReaders) {
        boolean found = false;
        while (pReaders.hasNext()) {
            ImageReader reader = pReaders.next();
            if (reader.getClass() == pReaderClass && isOurProvider(reader.getOriginatingProvider())) {
                found = true;
            }
        }

        Assertions.assertTrue(found, String.format("%s not provided by %s for '%s'", pReaderClass.getSimpleName(), provider.getClass().getSimpleName(), pFormat));
    }

    private boolean isOurProvider(final ImageReaderSpi spi) {
        return provider.getClass().isInstance(spi);
    }

    @ParameterizedTest
    @MethodSource("getFormatNames")
    public void testProviderInstalledForNames(String name) {
        assertProviderInstalledForName(name, readerClass);
    }

    @ParameterizedTest
    @MethodSource("getSuffixes")
    public void testProviderInstalledForSuffixes(String suffix) {
        assertProviderInstalledForSuffix(suffix, readerClass);
    }

    @ParameterizedTest
    @MethodSource("getMIMETypes")
    public void testProviderInstalledForMIMETypes(String type) {
        assertProviderInstalledForMIMEType(type, readerClass);
    }

    @ParameterizedTest
    @MethodSource("getTestData")
    public void testProviderCanRead(TestData data) throws IOException {
        ImageInputStream stream = data.getInputStream();
        Assertions.assertNotNull(stream);
        Assertions.assertTrue(provider.canDecodeInput(stream), "Provider is expected to be able to decode data: " + data);
    }

    @Test
    public void testProviderCanReadNull() {
        boolean canRead = false;

        try {
            canRead = provider.canDecodeInput(null);
        } catch (IllegalArgumentException ignore) {
        } catch (RuntimeException e) {
            failBecause("RuntimeException other than IllegalArgumentException thrown", e);
        } catch (IOException e) {
            failBecause("Could not test data for read", e);
        }

        Assertions.assertFalse(canRead, "ImageReader can read null input");
    }

    @Test
    public void testSetInput() throws IOException {
        // Should just pass with no exceptions
        ImageReader reader = createReader();
        Assertions.assertNotNull(reader);

        for (TestData data : getTestData()) {
            reader.setInput(data.getInputStream());
        }

        reader.dispose();
    }

    @Test
    public void testSetInputNull() throws IOException {
        // Should just pass with no exceptions
        ImageReader reader = createReader();
        Assertions.assertNotNull(reader);
        reader.setInput(null);
        reader.dispose();
    }

    @ParameterizedTest
    @MethodSource("getTestData")
    public void testRead(TestData data) throws IOException {
        ImageReader reader = createReader();

        reader.setInput(data.getInputStream());

        for (int i = 0; i < data.getImageCount(); i++) {
            BufferedImage image = null;

            try {
                image = reader.read(i);
                File tempActual = File.createTempFile("junit-testRead-", ".png");
                System.out.println("tempActual.getAbsolutePath(): " + tempActual.getAbsolutePath());
                ImageIO.write(image, "PNG", tempActual);
            } catch (Exception e) {
                e.printStackTrace();
                failBecause(String.format("Image %s index %s could not be read: %s", data.getInput(), i, e), e);
            }

            Assertions.assertNotNull(image, String.format("Image %s index %s was null!", data.getInput(), i));

            Assertions.assertEquals(data.getDimension(i).width, image.getWidth(),
                String.format("Image %s index %s has wrong width: %s", data.getInput(), i, image.getWidth()));
            Assertions.assertEquals(data.getDimension(i).height, image.getHeight(),
                String.format("Image %s index %s has wrong height: %s", data.getInput(), i, image.getHeight()));
        }

        reader.dispose();
    }

    @Test
    public void testReadIndexNegative() throws IOException {
        ImageReader reader = createReader();
        TestData data = getTestData().get(0);
        reader.setInput(data.getInputStream());

        BufferedImage image = null;
        try {
            image = reader.read(-1);
            Assertions.fail("Read image with illegal index");
        } catch (IndexOutOfBoundsException ignore) {
        } catch (IOException e) {
            failBecause("Image could not be read", e);
        }
        Assertions.assertNull(image);

        reader.dispose();
    }

    @Test
    public void testReadIndexOutOfBounds() throws IOException {
        ImageReader reader = createReader();
        TestData data = getTestData().get(0);
        reader.setInput(data.getInputStream());

        BufferedImage image = null;
        try {
            image = reader.read(Integer.MAX_VALUE); // TODO: This might actually not throw exception...
            Assertions.fail("Read image with index out of bounds");
        } catch (IndexOutOfBoundsException ignore) {
        } catch (IOException e) {
            failBecause("Image could not be read", e);
        }
        Assertions.assertNull(image);

        reader.dispose();
    }

    @Test
    public void testReRead() throws IOException {
        ImageReader reader = createReader();
        TestData data = getTestData().get(0);
        reader.setInput(data.getInputStream(), false); // Explicitly allow repositioning, even though it's the default

        BufferedImage first = reader.read(0);
        Assertions.assertNotNull(first);

        BufferedImage second = reader.read(0);
        Assertions.assertNotNull(second);

        // TODO: These images should be exactly the same, but there's no equals for images
        Assertions.assertEquals(first.getType(), second.getType());
        Assertions.assertEquals(first.getWidth(), second.getWidth());
        Assertions.assertEquals(first.getHeight(), second.getHeight());

        reader.dispose();
    }

    @Test
    public void testReadNoInput() throws IOException {
        ImageReader reader = createReader();
        // Do not set input

        assertThrows(IllegalStateException.class, () -> reader.read(0));
    }

    @Test
    public void testReadIndexNegativeWithParam() throws IOException {
        ImageReader reader = createReader();
        TestData data = getTestData().get(0);
        reader.setInput(data.getInputStream());

        try {
            assertThrows(IndexOutOfBoundsException.class, () -> reader.read(-1, reader.getDefaultReadParam()));
        } finally {
            reader.dispose();
        }
    }

    @Test
    public void testReadIndexOutOfBoundsWithParam() throws IOException {
        ImageReader reader = createReader();
        TestData data = getTestData().get(0);
        reader.setInput(data.getInputStream());

        try {
            assertThrows(IndexOutOfBoundsException.class, () -> reader.read(Short.MAX_VALUE, reader.getDefaultReadParam()));
        } finally {
            reader.dispose();
        }
    }

    @Test
    public void testReadNoInputWithParam() throws IOException {
        ImageReader reader = createReader();
        // Do not set input

        try {
            assertThrows(IllegalStateException.class, () -> reader.read(0, reader.getDefaultReadParam()));
        } finally {
            reader.dispose();
        }
    }

    @Test
    public void testReadWithNewParam() throws IOException {
        ImageReader reader = createReader();
        TestData data = getTestData().get(0);
        reader.setInput(data.getInputStream());

        BufferedImage image = null;
        try {
            image = reader.read(0, new ImageReadParam());
        } catch (IOException e) {
            failBecause("Image could not be read", e);
        }

        Assertions.assertNotNull(image, "Image was null!");
        Assertions.assertEquals(data.getDimension(0).width, image.getWidth(), "Read image has wrong width: " + image.getWidth());
        Assertions.assertEquals(data.getDimension(0).height, image.getHeight(), "Read image has wrong height: " + image.getHeight());

        reader.dispose();
    }

    @Test
    public void testReadWithDefaultParam() throws IOException {
        ImageReader reader = createReader();
        TestData data = getTestData().get(0);
        reader.setInput(data.getInputStream());

        BufferedImage image = null;
        try {
            image = reader.read(0, reader.getDefaultReadParam());
        } catch (IOException e) {
            failBecause("Image could not be read", e);
        }

        Assertions.assertNotNull(image, "Image was null!");
        Assertions.assertEquals(data.getDimension(0).width, image.getWidth(), "Read image has wrong width: " + image.getWidth());
        Assertions.assertEquals(data.getDimension(0).height, image.getHeight(), "Read image has wrong height: " + image.getHeight());

        reader.dispose();
    }

    @Test
    public void testReadWithNullParam() throws IOException {
        ImageReader reader = createReader();
        TestData data = getTestData().get(0);
        reader.setInput(data.getInputStream());

        BufferedImage image = null;
        try {
            image = reader.read(0, null);
        } catch (IOException e) {
            failBecause("Image could not be read", e);
        }

        Assertions.assertNotNull(image, "Image was null!");
        Assertions.assertEquals(data.getDimension(0).width, image.getWidth(), "Read image has wrong width: " + image.getWidth());
        Assertions.assertEquals(data.getDimension(0).height, image.getHeight(), "Read image has wrong height: " + image.getHeight());

        reader.dispose();
    }

    @Test
    public void testReadWithSizeParam() throws IOException {
        ImageReader reader = createReader();
        TestData data = getTestData().get(0);
        reader.setInput(data.getInputStream());
        ImageReadParam param = reader.getDefaultReadParam();

        if (param.canSetSourceRenderSize()) {
            param.setSourceRenderSize(new Dimension(10, 10));

            BufferedImage image = null;
            try {
                image = reader.read(0, param);
            } catch (IOException e) {
                failBecause("Image could not be read", e);
            }

            Assertions.assertNotNull(image, "Image was null!");
            Assertions.assertEquals(10, image.getWidth(), "Read image has wrong width: " + image.getWidth());
            Assertions.assertEquals(10, image.getHeight(), "Read image has wrong height: " + image.getHeight());
        }

        reader.dispose();
    }

    @Test
    public void testReadWithSubsampleParamDimensions() throws IOException {
        ImageReader reader = createReader();
        TestData data = getTestData().get(0);
        reader.setInput(data.getInputStream());
        ImageReadParam param = reader.getDefaultReadParam();

        param.setSourceSubsampling(5, 5, 0, 0);

        BufferedImage image = null;
        try {
            image = reader.read(0, param);
        } catch (IOException e) {
            failBecause("Image could not be read", e);
        }

        Assertions.assertNotNull(image, "Image was null!");
        Assertions.assertEquals((data.getDimension(0).width + 4) / 5, image.getWidth(), "Read image has wrong width: ");
        Assertions.assertEquals((data.getDimension(0).height + 4) / 5, image.getHeight(), "Read image has wrong height: ");

        reader.dispose();
    }

    @Test
    public void testReadWithSubsampleParamPixels() throws IOException {
        ImageReader reader = createReader();
        TestData data = getTestData().get(0);
        reader.setInput(data.getInputStream());

        ImageReadParam param = reader.getDefaultReadParam();

        BufferedImage image = null;
        BufferedImage subsampled = null;
        try {
            image = reader.read(0, param);

            param.setSourceSubsampling(2, 2, 0, 0);
            subsampled = reader.read(0, param);
        } catch (IOException e) {
            failBecause("Image could not be read", e);
        }

        assertSubsampledImageDataEquals("Subsampled image data does not match expected", image, subsampled, param);

        reader.dispose();
    }

    // TODO: Subsample all test data
    // TODO: Subsample with varying ratios and offsets

    @SuppressWarnings("SameParameterValue")
    protected final void assertSubsampledImageDataEquals(String message, BufferedImage expected, BufferedImage actual, ImageReadParam param) throws IOException {
        Assertions.assertNotNull(expected, "Expected image was null");
        Assertions.assertNotNull(actual, "Actual image was null!");

        if (expected == actual) {
            return;
        }

        int xOff = param.getSubsamplingXOffset();
        int yOff = param.getSubsamplingYOffset();
        int xSub = param.getSourceXSubsampling();
        int ySub = param.getSourceYSubsampling();

        Assertions.assertEquals((expected.getWidth() - xOff + xSub - 1) / xSub, actual.getWidth(), "Subsampled image has wrong width: ");
        Assertions.assertEquals((expected.getHeight() - yOff + ySub - 1) / ySub, actual.getHeight(), "Subsampled image has wrong height: ");
        Assertions.assertEquals(expected.getType(), actual.getType(), "Subsampled has different type");

        for (int y = 0; y < actual.getHeight(); y++) {
            for (int x = 0; x < actual.getWidth(); x++) {
                int expectedRGB = expected.getRGB(xOff + x * xSub, yOff + y * ySub);
                int actualRGB = actual.getRGB(x, y);

                try {
                    Assertions.assertEquals((expectedRGB >>> 24) & 0xff, (actualRGB >>> 24) & 0xff, 5);
                    Assertions.assertEquals((expectedRGB >> 16) & 0xff, (actualRGB >> 16) & 0xff, 5);
                    Assertions.assertEquals((expectedRGB >> 8) & 0xff, (actualRGB >> 8) & 0xff, 5);
                    Assertions.assertEquals(expectedRGB & 0xff, actualRGB & 0xff, 5);
                } catch (AssertionError e) {
                    File tempExpected = File.createTempFile("junit-expected-subsample-", ".png");
                    System.err.println("tempExpected.getAbsolutePath(): " + tempExpected.getAbsolutePath());
                    ImageIO.write(expected, "PNG", tempExpected);
                    File tempActual = File.createTempFile("junit-actual-subsample-", ".png");
                    System.err.println("tempActual.getAbsolutePath(): " + tempActual.getAbsolutePath());
                    ImageIO.write(actual, "PNG", tempActual);

                    Assertions.assertEquals(String.format("#%08x", expectedRGB), String.format("#%08x", actualRGB), String.format("%s ARGB at (%d, %d)", message, x, y));
                }
            }
        }
    }

    public static void assertImageDataEquals(String message, BufferedImage expected, BufferedImage actual) {
        Assertions.assertNotNull(expected, "Expected image was null");
        Assertions.assertNotNull(actual, "Actual image was null!");

        if (expected == actual) {
            return;
        }

        for (int y = 0; y < expected.getHeight(); y++) {
            for (int x = 0; x < expected.getWidth(); x++) {
                int expectedRGB = expected.getRGB(x, y);
                int actualRGB = actual.getRGB(x, y);

                Assertions.assertEquals((expectedRGB >> 24) & 0xff, (actualRGB >> 24) & 0xff, 5, String.format("%s alpha at (%d, %d)", message, x, y));
                Assertions.assertEquals((expectedRGB >> 16) & 0xff, (actualRGB >> 16) & 0xff, 5, String.format("%s red at (%d, %d)", message, x, y));
                Assertions.assertEquals((expectedRGB >> 8) & 0xff, (actualRGB >> 8) & 0xff, 5, String.format("%s green at (%d, %d)", message, x, y));
                Assertions.assertEquals(expectedRGB & 0xff, actualRGB & 0xff, 5, String.format("%s blue at (%d, %d)", message, x, y));
            }
        }
    }

    @Test
    public void testReadWithSourceRegionParam() throws IOException {
        ImageReader reader = createReader();
        TestData data = getTestData().get(0);
        reader.setInput(data.getInputStream());
        ImageReadParam param = reader.getDefaultReadParam();

        param.setSourceRegion(new Rectangle(0, 0, 10, 10));

        BufferedImage image = null;
        try {
            image = reader.read(0, param);
        } catch (IOException e) {
            failBecause("Image could not be read", e);
        }

        Assertions.assertNotNull(image, "Image was null!");
        Assertions.assertEquals(10, image.getWidth(), "Read image has wrong width: " + image.getWidth());
        Assertions.assertEquals(10, image.getHeight(), "Read image has wrong height: " + image.getHeight());

        reader.dispose();
    }

    @Test
    public void testReadWithSourceRegionParamEqualImage() throws IOException {
        // Default invocation
        assertReadWithSourceRegionParamEqualImage(new Rectangle(3, 3, 9, 9), getTestData().get(0), 0);
    }

    protected void assertReadWithSourceRegionParamEqualImage(final Rectangle r, final TestData data, final int imageIndex) throws IOException {
        ImageReader reader = createReader();
        try (ImageInputStream inputStream = data.getInputStream()) {
            reader.setInput(inputStream);
            ImageReadParam param = reader.getDefaultReadParam();

            // Read full image and get sub image for comparison
            BufferedImage original = reader.read(imageIndex, param);
            final BufferedImage roi = original.getSubimage(r.x, r.y, r.width, r.height);

            param.setSourceRegion(r);

            final BufferedImage image = reader.read(imageIndex, param);

            Assertions.assertNotNull(image, "Image was null!");
            Assertions.assertEquals(r.width, image.getWidth(), "Read image has wrong width: " + image.getWidth());
            Assertions.assertEquals(r.height, image.getHeight(), "Read image has wrong height: " + image.getHeight());

            try {
                assertImageDataEquals("Images differ", roi, image);
            } catch (AssertionError e) {
                File tempRoi = File.createTempFile("junit-subimage-roi-", ".png");
                System.err.println("tempRoi.getAbsolutePath(): " + tempRoi.getAbsolutePath());
                ImageIO.write(roi, "PNG", tempRoi);

                File tempExpected = File.createTempFile("junit-subimage-expected-", ".png");
                System.err.println("tempExpected.getAbsolutePath(): " + tempExpected.getAbsolutePath());
                Graphics2D graphics = original.createGraphics();
                try {
                    graphics.setColor(Color.RED);
                    graphics.draw(r);
                } finally {
                    graphics.dispose();
                }
                ImageIO.write(original, "PNG", tempExpected);

                File tempActual = File.createTempFile("junit-subimage-actual-", ".png");
                System.err.println("tempActual.getAbsolutePath(): " + tempActual.getAbsolutePath());
                ImageIO.write(image, "PNG", tempActual);

                throw e;
            }
        } finally {
            reader.dispose();
        }
    }

    @Test
    public void testReadWithSizeAndSourceRegionParam() throws IOException {
        // TODO: Is this test correct???
        ImageReader reader = createReader();
        TestData data = getTestData().get(0);
        reader.setInput(data.getInputStream());
        ImageReadParam param = reader.getDefaultReadParam();

        if (param.canSetSourceRenderSize()) {
            Dimension size = data.getDimension(0);
            size = new Dimension(size.width * 2, size.height * 2);

            param.setSourceRenderSize(size);
            param.setSourceRegion(new Rectangle(0, 0, 10, 10));

            BufferedImage image = null;
            try {
                image = reader.read(0, param);
            } catch (IOException e) {
                failBecause("Image could not be read", e);
            }

            Assertions.assertNotNull(image, "Image was null!");
            Assertions.assertEquals(10, image.getWidth(), "Read image has wrong width: " + image.getWidth());
            Assertions.assertEquals(10, image.getHeight(), "Read image has wrong height: " + image.getHeight());
        }

        reader.dispose();
    }

    @Test
    public void testReadWithSubsampleAndSourceRegionParam() throws IOException {
        // NOTE: The "standard" (com.sun.imageio.plugin.*) ImageReaders pass
        // this test, so the test should be correct...
        ImageReader reader = createReader();
        TestData data = getTestData().get(0);
        reader.setInput(data.getInputStream());
        ImageReadParam param = reader.getDefaultReadParam();

        param.setSourceSubsampling(2, 2, 0, 0);
        param.setSourceRegion(new Rectangle(0, 0, 10, 10));

        BufferedImage image = null;
        try {
            image = reader.read(0, param);
        } catch (IOException e) {
            failBecause("Image could not be read", e);
        }
        Assertions.assertNotNull(image, "Image was null!");
        Assertions.assertEquals(5, image.getWidth(), "Read image has wrong width: " + image.getWidth());
        Assertions.assertEquals(5, image.getHeight(), "Read image has wrong height: " + image.getHeight());

        reader.dispose();
    }

    @Test
    public void testReadAsRenderedImageIndexNegative() throws IOException {
        ImageReader reader = createReader();
        TestData data = getTestData().get(0);
        reader.setInput(data.getInputStream());

        RenderedImage image = null;
        try {
            image = reader.readAsRenderedImage(-1, reader.getDefaultReadParam());
            Assertions.fail("Read image with illegal index");
        } catch (IndexOutOfBoundsException expected) {
            // Ignore
        } catch (IOException e) {
            failBecause("Image could not be read", e);
        }

        Assertions.assertNull(image);

        reader.dispose();
    }

    @Test
    public void testReadAsRenderedImageIndexOutOfBounds() throws IOException {
        ImageReader reader = createReader();
        TestData data = getTestData().get(0);
        reader.setInput(data.getInputStream());

        RenderedImage image = null;
        try {
            image = reader.readAsRenderedImage(reader.getNumImages(true), reader.getDefaultReadParam());
            Assertions.fail("Read image with index out of bounds");
        } catch (IndexOutOfBoundsException expected) {
            // Ignore
        } catch (IIOException e) {
            // Allow this to bubble up, due to a bug in the Sun JPEGImageReader
            throw e;
        } catch (IOException e) {
            failBecause("Image could not be read", e);
        }

        Assertions.assertNull(image);

        reader.dispose();
    }

    @Test
    public void testReadAsRenderedImageNoInput() throws IOException {
        ImageReader reader = createReader();
        // Do not set input

        RenderedImage image = null;
        try {
            image = reader.readAsRenderedImage(0, reader.getDefaultReadParam());
            Assertions.fail("Read image with no input");
        } catch (IllegalStateException expected) {
            // Ignore
        } catch (IOException e) {
            failBecause("Image could not be read", e);
        }

        Assertions.assertNull(image);

        reader.dispose();
    }

    @Test
    public void testReadAsRenderedImage() throws IOException {
        ImageReader reader = createReader();
        TestData data = getTestData().get(0);
        reader.setInput(data.getInputStream());

        RenderedImage image = null;
        try {
            image = reader.readAsRenderedImage(0, null);
        } catch (IOException e) {
            failBecause("Image could not be read", e);
        }

        Assertions.assertNotNull(image, "Image was null!");
        Assertions.assertEquals(data.getDimension(0).width, image.getWidth(), "Read image has wrong width: " + image.getWidth());
        Assertions.assertEquals(data.getDimension(0).height, image.getHeight(), "Read image has wrong height: " + image.getHeight());

        reader.dispose();
    }

    @Test
    public void testReadAsRenderedImageWithDefaultParam() throws IOException {
        ImageReader reader = createReader();
        TestData data = getTestData().get(0);
        reader.setInput(data.getInputStream());

        RenderedImage image = null;
        try {
            image = reader.readAsRenderedImage(0, reader.getDefaultReadParam());
        } catch (IOException e) {
            failBecause("Image could not be read", e);
        }

        Assertions.assertNotNull(image, "Image was null!");
        Assertions.assertEquals(data.getDimension(0).width, image.getWidth(), "Read image has wrong width: " + image.getWidth());
        Assertions.assertEquals(data.getDimension(0).height, image.getHeight(), "Read image has wrong height: " + image.getHeight());

        reader.dispose();
    }

    @Test
    public void testGetDefaultReadParam() throws IOException {
        ImageReader reader = createReader();
        ImageReadParam param = reader.getDefaultReadParam();
        Assertions.assertNotNull(param);
        reader.dispose();
    }

    @Test
    public void testGetFormatName() throws IOException {
        ImageReader reader = createReader();
        TestData data = getTestData().get(0);
        reader.setInput(data.getInputStream());
        String name = null;
        try {
            name = reader.getFormatName();
        } catch (IOException e) {
            Assertions.fail(e.getMessage());
        }
        Assertions.assertNotNull(name);
        reader.dispose();
    }

    @Test
    public void testGetMinIndex() throws IOException {
        ImageReader reader = createReader();
        TestData data = getTestData().get(0);
        reader.setInput(data.getInputStream());
        int num = 0;

        try {
            num = reader.getMinIndex();
        } catch (IllegalStateException ignore) {
        }
        Assertions.assertEquals(0, num);
        reader.dispose();
    }

    @Test
    public void testGetMinIndexNoInput() throws IOException {
        ImageReader reader = createReader();
        int num = 0;

        try {
            num = reader.getMinIndex();
        } catch (IllegalStateException ignore) {
        }
        Assertions.assertEquals(0, num);
        reader.dispose();
    }

    @ParameterizedTest
    @MethodSource("getTestData")
    public void testGetNumImages(TestData data) throws IOException {
        ImageReader reader = createReader();
        reader.setInput(data.getInputStream());
        int num = -1;
        try {
            num = reader.getNumImages(false);
        } catch (IOException e) {
            Assertions.fail(e.getMessage());
        }
        Assertions.assertTrue(num == -1 || num > 0);

        try {
            num = reader.getNumImages(true);
        } catch (IOException e) {
            Assertions.fail(e.getMessage());
        }

        Assertions.assertTrue(num > 0);
        Assertions.assertTrue(data.getImageCount() <= num);
        if (data.getImageCount() != num) {
            System.err.println("WARNING: Image count not equal to test data count");
        }
        reader.dispose();
    }

    @Test
    public void testGetNumImagesNoInput() throws IOException {
        ImageReader reader = createReader();
        int num = -1;

        try {
            num = reader.getNumImages(false);
        } catch (IllegalStateException ignore) {
        } catch (IOException e) {
            Assertions.fail(e.getMessage());
        }
        Assertions.assertEquals(-1, num);

        try {
            num = reader.getNumImages(true);
            Assertions.fail("Should throw IllegalStateException");
        } catch (IllegalStateException ignore) {
        } catch (IOException e) {
            Assertions.fail(e.getMessage());
        }
        Assertions.assertEquals(-1, num);
        reader.dispose();
    }

    @Test
    public void testGetWidth() throws IOException {
        ImageReader reader = createReader();
        TestData data = getTestData().get(0);
        reader.setInput(data.getInputStream());

        int width = 0;
        try {
            width = reader.getWidth(0);
        } catch (IOException e) {
            Assertions.fail("Could not read image width: " + e);
        }
        Assertions.assertEquals(data.getDimension(0).width, width, "Wrong width reported");
        reader.dispose();
    }

    @Test
    public void testGetWidthIndexOutOfBounds() throws IOException {
        ImageReader reader = createReader();
        TestData data = getTestData().get(0);
        reader.setInput(data.getInputStream());

        //int width = 0;
        try {
            /*width = */
            reader.getWidth(-1);
            // NOTE: Some readers (like the com.sun.imageio stuff) ignores
            // index in getWidth/getHeight for formats with only one image...
            //assertEquals("Wrong width reported", data.getDimension().width, width);
        } catch (IndexOutOfBoundsException ignore) {
        } catch (IOException e) {
            Assertions.fail("Could not read image aspect ratio: " + e);
        }
        reader.dispose();
    }

    @Test
    public void testGetWidthNoInput() throws IOException {
        ImageReader reader = createReader();

        int width = 0;
        try {
            width = reader.getWidth(0);
            Assertions.fail("Width read without input");
        } catch (IllegalStateException ignore) {
        } catch (IOException e) {
            Assertions.fail("Could not read image width: " + e);
        }
        Assertions.assertEquals(0, width, "Wrong width reported");
        reader.dispose();
    }

    @Test
    public void testGetHeight() throws IOException {
        ImageReader reader = createReader();
        TestData data = getTestData().get(0);
        reader.setInput(data.getInputStream());

        int height = 0;
        try {
            height = reader.getHeight(0);
        } catch (IOException e) {
            Assertions.fail("Could not read image height: " + e);
        }
        Assertions.assertEquals(data.getDimension(0).height, height, "Wrong height reported");
        reader.dispose();
    }

    @Test
    public void testGetHeightNoInput() throws IOException {
        ImageReader reader = createReader();

        int height = 0;
        try {
            height = reader.getHeight(0);
            Assertions.fail("height read without input");
        } catch (IllegalStateException ignore) {
        } catch (IOException e) {
            Assertions.fail("Could not read image height: " + e);
        }
        Assertions.assertEquals(0, height, "Wrong height reported");
        reader.dispose();
    }

    @Test
    public void testGetHeightIndexOutOfBounds() throws IOException {
        ImageReader reader = createReader();
        TestData data = getTestData().get(0);
        reader.setInput(data.getInputStream());

        //int height = 0;
        try {
            /*height =*/
            reader.getHeight(-1);
            // NOTE: Some readers (like the com.sun.imageio stuff) ignores
            // index in getWidth/getHeight for formats with only one image...
            //assertEquals("Wrong height reported", data.getDimension().height, height);
        } catch (IndexOutOfBoundsException ignore) {
        } catch (IOException e) {
            Assertions.fail("Could not read image height: " + e);
        }
        reader.dispose();
    }

    @Test
    public void testGetAspectRatio() throws IOException {
        ImageReader reader = createReader();
        TestData data = getTestData().get(0);
        reader.setInput(data.getInputStream());

        float aspectRatio = 0f;
        try {
            aspectRatio = reader.getAspectRatio(0);
        } catch (IOException e) {
            Assertions.fail("Could not read image aspect ratio" + e);
        }
        Dimension d = data.getDimension(0);
        Assertions.assertEquals(d.getWidth() / d.getHeight(), aspectRatio, 0.001, "Wrong aspect aspect ratio");
        reader.dispose();
    }

    @Test
    public void testGetAspectRatioNoInput() throws IOException {
        ImageReader reader = createReader();

        float aspectRatio = 0f;
        try {
            aspectRatio = reader.getAspectRatio(0);
            Assertions.fail("aspect read without input");
        } catch (IllegalStateException ignore) {
        } catch (IOException e) {
            Assertions.fail("Could not read image aspect ratio" + e);
        }
        Assertions.assertEquals(0f, aspectRatio, 0f, "Wrong aspect aspect ratio");
        reader.dispose();
    }

    @Test
    public void testGetAspectRatioIndexOutOfBounds() throws IOException {
        ImageReader reader = createReader();
        TestData data = getTestData().get(0);
        reader.setInput(data.getInputStream());

        //float aspectRatio = 0f;
        try {
            // NOTE: Some readers (like the com.sun.imageio stuff) ignores
            // index in getWidth/getHeight for formats with only one image...
            /*aspectRatio =*/
            reader.getAspectRatio(-1);
            //assertEquals("Wrong aspect ratio", data.getDimension().width / (float) data.getDimension().height, aspectRatio, 0f);
        } catch (IndexOutOfBoundsException ignore) {
        } catch (IOException e) {
            Assertions.fail("Could not read image aspect ratio" + e);
        }
        reader.dispose();
    }

    @Test
    public void testDisposeBeforeRead() throws IOException {
        ImageReader reader = createReader();
        reader.dispose(); // Just pass with no exceptions
    }

    @Test
    public void testDisposeAfterRead() throws IOException {
        ImageReader reader = createReader();
        TestData data = getTestData().get(0);
        reader.setInput(data.getInputStream());
        reader.dispose(); // Just pass with no exceptions
    }

    @Test
    public void testAddIIOReadProgressListener() throws IOException {
        ImageReader reader = createReader();
        reader.addIIOReadProgressListener(mock(IIOReadProgressListener.class));
        reader.dispose();
    }

    @Test
    public void testAddIIOReadProgressListenerNull() throws IOException {
        ImageReader reader = createReader();
        reader.addIIOReadProgressListener(null);
        reader.dispose();
    }

    @Test
    public void testAddIIOReadProgressListenerCallbacks() throws IOException {
        ImageReader reader = createReader();
        TestData data = getTestData().get(0);
        reader.setInput(data.getInputStream());

        IIOReadProgressListener listener = mock(IIOReadProgressListener.class);
        reader.addIIOReadProgressListener(listener);

        try {
            reader.read(0);
        } catch (IOException e) {
            Assertions.fail("Could not read image");
        }

        // At least imageStarted and imageComplete, plus any number of imageProgress
        InOrder ordered = inOrder(listener);
        ordered.verify(listener).imageStarted(reader, 0);
        ordered.verify(listener, atLeastOnce()).imageProgress(eq(reader), anyFloat());
        ordered.verify(listener).imageComplete(reader);
        reader.dispose();
    }

    @Test
    public void testMultipleAddIIOReadProgressListenerCallbacks() throws IOException {
        ImageReader reader = createReader();
        TestData data = getTestData().get(0);
        reader.setInput(data.getInputStream());

        IIOReadProgressListener listener = mock(IIOReadProgressListener.class);
        IIOReadProgressListener listenerToo = mock(IIOReadProgressListener.class);
        IIOReadProgressListener listenerThree = mock(IIOReadProgressListener.class);

        reader.addIIOReadProgressListener(listener);
        reader.addIIOReadProgressListener(listenerToo);
        reader.addIIOReadProgressListener(listenerThree);

        try {
            reader.read(0);
        } catch (IOException e) {
            Assertions.fail("Could not read image");
        }

        // At least imageStarted and imageComplete, plus any number of imageProgress
        InOrder ordered = inOrder(listener, listenerToo, listenerThree);

        ordered.verify(listener).imageStarted(reader, 0);
        ordered.verify(listenerToo).imageStarted(reader, 0);
        ordered.verify(listenerThree).imageStarted(reader, 0);

        ordered.verify(listener, atLeastOnce()).imageProgress(eq(reader), anyFloat());
        ordered.verify(listenerToo, atLeastOnce()).imageProgress(eq(reader), anyFloat());
        ordered.verify(listenerThree, atLeastOnce()).imageProgress(eq(reader), anyFloat());

        ordered.verify(listener).imageComplete(reader);
        ordered.verify(listenerToo).imageComplete(reader);
        ordered.verify(listenerThree).imageComplete(reader);
        reader.dispose();
    }

    @Test
    public void testRemoveIIOReadProgressListenerNull() throws IOException {
        ImageReader reader = createReader();
        reader.removeIIOReadProgressListener(null);
        reader.dispose();
    }

    @Test
    public void testRemoveIIOReadProgressListenerNone() throws IOException {
        ImageReader reader = createReader();
        reader.removeIIOReadProgressListener(mock(IIOReadProgressListener.class));
        reader.dispose();
    }

    @Test
    public void testRemoveIIOReadProgressListener() throws IOException {
        ImageReader reader = createReader();
        TestData data = getTestData().get(0);
        reader.setInput(data.getInputStream());

        IIOReadProgressListener listener = mock(IIOReadProgressListener.class);
        reader.addIIOReadProgressListener(listener);
        reader.removeIIOReadProgressListener(listener);

        try {
            reader.read(0);
        } catch (IOException e) {
            Assertions.fail("Could not read image");
        }

        // Should not have called any methods...
        verifyNoInteractions(listener);
        reader.dispose();
    }

    @Test
    public void testRemoveIIOReadProgressListenerMultiple() throws IOException {
        ImageReader reader = createReader();
        TestData data = getTestData().get(0);
        reader.setInput(data.getInputStream());

        IIOReadProgressListener listener = mock(IIOReadProgressListener.class, "Listener1");
        reader.addIIOReadProgressListener(listener);


        IIOReadProgressListener listenerToo = mock(IIOReadProgressListener.class, "Listener2");
        reader.addIIOReadProgressListener(listenerToo);

        reader.removeIIOReadProgressListener(listener);

        try {
            reader.read(0);
        } catch (IOException e) {
            Assertions.fail("Could not read image");
        }

        // Should not have called any methods on listener1...
        verifyNoInteractions(listener);

        InOrder ordered = inOrder(listenerToo);
        ordered.verify(listenerToo).imageStarted(reader, 0);
        ordered.verify(listenerToo, atLeastOnce()).imageProgress(eq(reader), anyFloat());
        ordered.verify(listenerToo).imageComplete(reader);
        reader.dispose();
    }

    @Test
    public void testRemoveAllIIOReadProgressListeners() throws IOException {
        ImageReader reader = createReader();
        TestData data = getTestData().get(0);
        reader.setInput(data.getInputStream());

        IIOReadProgressListener listener = mock(IIOReadProgressListener.class);
        reader.addIIOReadProgressListener(listener);

        reader.removeAllIIOReadProgressListeners();

        try {
            reader.read(0);
        } catch (IOException e) {
            Assertions.fail("Could not read image");
        }

        // Should not have called any methods...
        verifyNoInteractions(listener);
        reader.dispose();
    }

    @Test
    public void testRemoveAllIIOReadProgressListenersMultiple() throws IOException {
        ImageReader reader = createReader();
        TestData data = getTestData().get(0);
        reader.setInput(data.getInputStream());

        IIOReadProgressListener listener = mock(IIOReadProgressListener.class);
        reader.addIIOReadProgressListener(listener);

        IIOReadProgressListener listenerToo = mock(IIOReadProgressListener.class);
        reader.addIIOReadProgressListener(listenerToo);

        reader.removeAllIIOReadProgressListeners();

        try {
            reader.read(0);
        } catch (IOException e) {
            Assertions.fail("Could not read image");
        }

        // Should not have called any methods...
        verifyNoInteractions(listener);
        verifyNoInteractions(listenerToo);
        reader.dispose();
    }

    @Test
    public void testAbort() throws IOException {
        final ImageReader reader = createReader();
        TestData data = getTestData().get(0);
        reader.setInput(data.getInputStream());

        IIOReadProgressListener listener = mock(IIOReadProgressListener.class, "Progress1");
        reader.addIIOReadProgressListener(listener);

        IIOReadProgressListener listenerToo = mock(IIOReadProgressListener.class, "Progress2");
        reader.addIIOReadProgressListener(listenerToo);

        // Create a listener that just makes the reader abort immediately...
        IIOReadProgressListener abortingListener = mock(IIOReadProgressListener.class, "Aborter");
        Answer<Void> abort = invocation -> {
            reader.abort();
            return null;
        };
        doAnswer(abort).when(abortingListener).imageStarted(any(ImageReader.class), anyInt());
        doAnswer(abort).when(abortingListener).imageProgress(any(ImageReader.class), anyFloat());

        reader.addIIOReadProgressListener(abortingListener);

        try {
            reader.read(0);
        } catch (IOException e) {
            failBecause("Image could not be read", e);
        }

        verify(listener).readAborted(reader);
        verify(listenerToo).readAborted(reader);
        reader.dispose();
    }

    @ParameterizedTest
    @MethodSource("getTestData")
    public void testGetTypeSpecifiers(TestData data) throws IOException {
        final ImageReader reader = createReader();
        reader.setInput(data.getInputStream());

        ImageTypeSpecifier rawType = reader.getRawImageType(0);
        Assertions.assertNotNull(rawType);

        Iterator<ImageTypeSpecifier> types = reader.getImageTypes(0);

        Assertions.assertNotNull(types);
        Assertions.assertTrue(types.hasNext());

        // TODO: This might fail even though the specifiers are obviously equal, if the
        // color spaces they use are not the SAME instance, as ColorSpace uses identity equals
        // and Interleaved ImageTypeSpecifiers are only equal if color spaces are equal...
        boolean rawFound = false;
        while (types.hasNext()) {
            ImageTypeSpecifier type = types.next();
            if (type.equals(rawType)) {
                rawFound = true;
                break;
            }
        }

        Assertions.assertTrue(rawFound, "ImageTypeSpecifier from getRawImageType should be in the iterator from getImageTypes");

        reader.dispose();
    }

    @ParameterizedTest
    @MethodSource("getTestData")
    public void testSetDestination(TestData data) throws IOException {
        ImageReader reader = createReader();
        reader.setInput(data.getInputStream());

        ImageReadParam param = reader.getDefaultReadParam();
        Iterator<ImageTypeSpecifier> types = reader.getImageTypes(0);
        while (types.hasNext()) {
            ImageTypeSpecifier type = types.next();

            BufferedImage destination = type.createBufferedImage(50, 50);
            param.setDestination(destination);

            BufferedImage result = null;
            try {
                result = reader.read(0, param);
            } catch (Exception e) {
                failBecause("Could not read " + data.getInput() + " with explicit destination " + destination, e);
            }

            Assertions.assertSame(destination, result);
        }
        reader.dispose();
    }

    @ParameterizedTest
    @MethodSource("getTestData")
    public void testSetDestinationRaw(TestData data) throws IOException {
        ImageReader reader = createReader();
        reader.setInput(data.getInputStream());

        ImageReadParam param = reader.getDefaultReadParam();

        ImageTypeSpecifier type = reader.getRawImageType(0);

        if (type != null) {
            BufferedImage destination = type.createBufferedImage(reader.getWidth(0), reader.getHeight(0));
            param.setDestination(destination);

            BufferedImage result = null;
            try {
                result = reader.read(0, param);
            } catch (Exception e) {
                failBecause("Image could not be read", e);
            }

            Assertions.assertSame(destination, result);
        } else {
            System.err.println("WARNING: Test skipped due to reader.getRawImageType(0) returning null");
        }
        reader.dispose();
    }

    @Test
    public void testSetDestinationIllegal() throws IOException {
        final ImageReader reader = createReader();
        TestData data = getTestData().get(0);
        reader.setInput(data.getInputStream());

        List<ImageTypeSpecifier> illegalTypes = createIllegalTypes(reader.getImageTypes(0));

        ImageReadParam param = reader.getDefaultReadParam();
        for (ImageTypeSpecifier illegalType : illegalTypes) {
            BufferedImage destination = illegalType.createBufferedImage(50, 50);
            param.setDestination(destination);

            try {
                BufferedImage result = reader.read(0, param);

                // NOTE: We allow the reader to read, as it's inconvenient to test all possible cases.
                // However, it may NOT fail with any other exception in that case.
                // TODO: Special case for BufferedImage type 2/3 and 6/7
                System.err.println("WARNING: Reader does not throw exception with non-declared destination: " + destination);

                // Test that the destination is really taken into account
                Assertions.assertSame(destination, result);
            } catch (IIOException expected) {
                // TODO: This is thrown by ImageReader.getDestination. But are we happy with that?
                // The problem is that the checkReadParamBandSettings throws IllegalArgumentException, which seems more appropriate...
                String message = expected.getMessage().toLowerCase();
                if (!(message.contains("destination") || message.contains("band size") || // For JDK classes
                      ((destination.getType() == BufferedImage.TYPE_BYTE_BINARY ||
                        destination.getType() == BufferedImage.TYPE_BYTE_INDEXED) &&
                       message.contains("indexcolormodel")))) {
                    failBecause(
                        "Wrong message: " + message + " for type " + destination.getType(), expected
                    );
                }
            } catch (IllegalArgumentException expected) {
                String message = expected.getMessage().toLowerCase();
                Assertions.assertTrue(message.contains("dest"), "Wrong message: " + message);
            }
        }
        reader.dispose();
    }

    @Test
    public void testSetDestinationTypeIllegal() throws IOException {
        final ImageReader reader = createReader();
        TestData data = getTestData().get(0);
        reader.setInput(data.getInputStream());

        List<ImageTypeSpecifier> illegalTypes = createIllegalTypes(reader.getImageTypes(0));

        ImageReadParam param = reader.getDefaultReadParam();
        for (ImageTypeSpecifier illegalType : illegalTypes) {
            param.setDestinationType(illegalType);

            try {
                reader.read(0, param);
                Assertions.fail("Expected to throw exception with illegal type specifier");
            } catch (IIOException | IllegalArgumentException expected) {
                // TODO: This is thrown by ImageReader.getDestination. But are we happy with that?
                String message = expected.getMessage().toLowerCase();
                if (!(message.contains("destination") && message.contains("type")
                      || message.contains("num source & dest bands differ"))) {
                    // Allow this to bubble up, due to a bug in the Sun PNGImageReader
                    throw expected;
                }
            }
        }
        reader.dispose();
    }

    private List<ImageTypeSpecifier> createIllegalTypes(Iterator<ImageTypeSpecifier> pValidTypes) {
        List<ImageTypeSpecifier> allTypes = new ArrayList<>();
        for (int i = BufferedImage.TYPE_INT_RGB; i < BufferedImage.TYPE_BYTE_INDEXED; i++) {
            allTypes.add(ImageTypeSpecifier.createFromBufferedImageType(i));
        }

        List<ImageTypeSpecifier> illegalTypes = new ArrayList<>(allTypes);
        while (pValidTypes.hasNext()) {
            ImageTypeSpecifier valid = pValidTypes.next();
            boolean removed = illegalTypes.remove(valid);

            // TODO: 4BYTE_ABGR (6) and 4BYTE_ABGR_PRE (7) is essentially the same type...
            // #$@*%$! ImageTypeSpecifier.equals is not well-defined
            if (!removed) {
                illegalTypes.removeIf(illegalType -> illegalType.getBufferedImageType() == valid.getBufferedImageType());
            }
        }

        return illegalTypes;
    }

    // TODO: Test dest offset + destination set?
    // TODO: Test that destination offset is used for image data, not just image dimensions...
    @Test
    public void testSetDestinationOffset() throws IOException {
        final ImageReader reader = createReader();
        TestData data = getTestData().get(0);
        reader.setInput(data.getInputStream());

        ImageReadParam param = reader.getDefaultReadParam();
        Point point = new Point(37, 42);
        param.setDestinationOffset(point);

        BufferedImage image = reader.read(0, param);

        Assertions.assertNotNull(image);
        Assertions.assertEquals(reader.getWidth(0) + point.x, image.getWidth());
        Assertions.assertEquals(reader.getHeight(0) + point.y, image.getHeight());
        reader.dispose();
    }

    @Test
    public void testSetDestinationOffsetNull() throws IOException {
        final ImageReader reader = createReader();
        TestData data = getTestData().get(0);
        reader.setInput(data.getInputStream());

        ImageReadParam param = reader.getDefaultReadParam();
        try {
            param.setDestinationOffset(null);
            Assertions.fail("Null offset not allowed");
        } catch (IllegalArgumentException e) {
            Assertions.assertTrue(e.getMessage().toLowerCase().contains("offset"));
        }
        reader.dispose();
    }

    @Test
    public void testSetDestinationType() throws IOException {
        final ImageReader reader = createReader();
        TestData data = getTestData().get(0);
        reader.setInput(data.getInputStream());

        ImageReadParam param = reader.getDefaultReadParam();

        Iterator<ImageTypeSpecifier> types = reader.getImageTypes(0);
        while (types.hasNext()) {
            ImageTypeSpecifier type = types.next();
            param.setDestinationType(type);

            BufferedImage result = null;
            try {
                result = reader.read(0, param);
            } catch (Exception e) {
                failBecause("Could not read " + data.getInput() + " with explicit destination type " + type, e);
            }

            Assertions.assertNotNull(result);
            Assertions.assertEquals(type.getColorModel(), result.getColorModel());

            // The following logically tests
            // assertEquals(type.getSampleModel(), result.getSampleModel());
            // but SampleModel does not have a proper equals method.
            SampleModel expectedModel = type.getSampleModel();
            SampleModel resultModel = result.getSampleModel();

            Assertions.assertEquals(expectedModel.getDataType(), resultModel.getDataType());
            Assertions.assertEquals(expectedModel.getNumBands(), resultModel.getNumBands());
            Assertions.assertEquals(expectedModel.getNumDataElements(), resultModel.getNumDataElements());
            Assertions.assertArrayEquals(expectedModel.getSampleSize(), resultModel.getSampleSize());
            Assertions.assertEquals(expectedModel.getTransferType(), resultModel.getTransferType());
            for (int i = 0; i < expectedModel.getNumBands(); i++) {
                Assertions.assertEquals(expectedModel.getSampleSize(i), resultModel.getSampleSize(i));
            }
        }
        reader.dispose();
    }

    @Test
    public void testNotBadCaching() throws IOException {
        T reader = createReader();
        TestData data = getTestData().get(0);
        reader.setInput(data.getInputStream());

        BufferedImage one = reader.read(0);
        BufferedImage two = reader.read(0);

        // Test for same BufferedImage instance
        Assertions.assertNotSame(one, two, "Multiple reads return same (mutable) image");

        // Test for same backing storage (array)
        one.setRGB(0, 0, Color.BLACK.getRGB());
        two.setRGB(0, 0, Color.WHITE.getRGB());
        Assertions.assertTrue(one.getRGB(0, 0) != two.getRGB(0, 0));

        reader.dispose();
    }

    @ParameterizedTest
    @MethodSource("getTestData")
    public void testReadThumbnails(TestData testData) throws IOException {
        T reader = createReader();

        if (reader.readerSupportsThumbnails()) {
            try (ImageInputStream inputStream = testData.getInputStream()) {
                reader.setInput(inputStream);

                int numImages = reader.getNumImages(true);

                for (int i = 0; i < numImages; i++) {
                    int numThumbnails = reader.getNumThumbnails(i);

                    for (int t = 0; t < numThumbnails; t++) {
                        BufferedImage thumbnail = reader.readThumbnail(i, t);

                        File tempActual = File.createTempFile("junit-testReadThumbnails-" + i + "." + t + "-", ".png");
                        System.out.println("tempActual.getAbsolutePath(): " + tempActual.getAbsolutePath());
                        ImageIO.write(thumbnail, "PNG", tempActual);

                        Assertions.assertNotNull(thumbnail);
                    }
                }
            }
        }

        reader.dispose();
    }

    @ParameterizedTest
    @MethodSource("getTestData")
    public void testThumbnailProgress(TestData testData) throws IOException {
        T reader = createReader();

        IIOReadProgressListener listener = mock(IIOReadProgressListener.class);
        reader.addIIOReadProgressListener(listener);

        if (reader.readerSupportsThumbnails()) {
            try (ImageInputStream inputStream = testData.getInputStream()) {

                reader.setInput(inputStream);

                int numThumbnails = reader.getNumThumbnails(0);
                for (int i = 0; i < numThumbnails; i++) {
                    reset(listener);

                    reader.readThumbnail(0, i);

                    InOrder order = inOrder(listener);
                    order.verify(listener).thumbnailStarted(reader, 0, i);
                    order.verify(listener, atLeastOnce()).thumbnailProgress(reader, 100f);
                    order.verify(listener).thumbnailComplete(reader);
                }
            }
        }

        reader.dispose();
    }

    @ParameterizedTest
    @MethodSource("getTestData")
    public void testNotBadCachingThumbnails(TestData data) throws IOException {
        T reader = createReader();

        if (reader.readerSupportsThumbnails()) {
            reader.setInput(data.getInputStream());

            int images = reader.getNumImages(true);
            for (int i = 0; i < images; i++) {
                int thumbnails = reader.getNumThumbnails(0);

                for (int j = 0; j < thumbnails; j++) {
                    BufferedImage one = reader.readThumbnail(i, j);
                    BufferedImage two = reader.readThumbnail(i, j);

                    Assertions.assertNotSame(one, two, "Multiple reads return same (mutable) image");

                    Graphics2D g = one.createGraphics();
                    try {
                        g.setColor(Color.WHITE);
                        g.setXORMode(Color.BLACK);
                        g.fillRect(0, 0, one.getWidth(), one.getHeight());
                    } finally {
                        g.dispose();
                    }

                    Assertions.assertTrue(one.getRGB(0, 0) != two.getRGB(0, 0));
                }

                if (thumbnails > 0) {
                    // We've tested thumbnails, let's get out of here
                    return;
                }
            }

            Assertions.fail("No thumbnails tested for reader that supports thumbnails.");
        }
        reader.dispose();
    }

    protected List<TestData> getTestDataForAffineTransformOpCompatibility() {
        // Allow subclasses to filter out test data that can't be converted to a compatible image without data loss
        return getTestData();
    }

    @ParameterizedTest
    @MethodSource("getTestDataForAffineTransformOpCompatibility")
    public void testAffineTransformOpCompatibility(TestData testData) throws IOException {
        // Test that the output of normal images are compatible with AffineTransformOp. Is unlikely to work on all test data
        ImageReader reader = createReader();

        try (ImageInputStream input = testData.getInputStream()) {
            reader.setInput(input);

            ImageReadParam param = reader.getDefaultReadParam();
            param.setSourceRegion(new Rectangle(min(reader.getWidth(0), 64), min(reader.getHeight(0), 64)));

            BufferedImage originalImage = reader.read(0, param);

            AffineTransform transform = AffineTransform.getTranslateInstance(10, 10);
            AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);

            try {
                BufferedImage resultImage = op.filter(originalImage, null); // The exception happens here
                Assertions.assertNotNull(resultImage);
            } catch (ImagingOpException e) {
                Assertions.fail(e.getMessage() + ".\n\t"
                                + originalImage + "\n\t"
                                + testData);
            }
        }

        reader.dispose();
    }

    @Disabled("TODO: Implement")
    @Test
    public void testSetDestinationBands() {
        throw new UnsupportedOperationException("Method testSetDestinationBands not implemented"); // TODO: Implement
    }

    @Disabled("TODO: Implement")
    @Test
    public void testSetSourceBands() {
        throw new UnsupportedOperationException("Method testSetDestinationBands not implemented"); // TODO: Implement
    }

    @Test
    public void testProviderAndMetadataFormatNamesMatch() throws IOException {
        ImageReader reader = createReader();
        reader.setInput(getTestData().get(0).getInputStream());

        IIOMetadata imageMetadata = reader.getImageMetadata(0);
        if (imageMetadata != null) {
            Assertions.assertEquals(provider.getNativeImageMetadataFormatName(), imageMetadata.getNativeMetadataFormatName());
        }

        IIOMetadata streamMetadata = reader.getStreamMetadata();
        if (streamMetadata != null) {
            Assertions.assertEquals(provider.getNativeStreamMetadataFormatName(), streamMetadata.getNativeMetadataFormatName());
        }
        reader.dispose();
    }

    protected URL getClassLoaderResource(final String pName) {
        return getClass().getResource(pName);
    }

    /**
     * Slightly fuzzy RGB equals method. Variable tolerance.
     */
    public static void assertRGBEquals(String message, int expectedRGB, int actualRGB, int tolerance) {
        try {
            Assertions.assertEquals((expectedRGB >>> 24) & 0xff, (actualRGB >>> 24) & 0xff, 0);
            Assertions.assertEquals((expectedRGB >> 16) & 0xff, (actualRGB >> 16) & 0xff, tolerance);
            Assertions.assertEquals((expectedRGB >> 8) & 0xff, (actualRGB >> 8) & 0xff, tolerance);
            Assertions.assertEquals((expectedRGB) & 0xff, (actualRGB) & 0xff, tolerance);
        } catch (AssertionError e) {
            Assertions.assertEquals(String.format("#%08x", expectedRGB), String.format("#%08x", actualRGB), message);
        }
    }

    static final protected class TestData {
        private final Object input;
        private final List<Dimension> sizes;
        private final List<BufferedImage> images;

        public TestData(final Object pInput, final Dimension... pSizes) {
            this(pInput, Arrays.asList(pSizes), null);
        }

        public TestData(final Object pInput, final BufferedImage... pImages) {
            this(pInput, null, Arrays.asList(pImages));
        }

        public TestData(final Object pInput, final List<Dimension> pSizes, final List<BufferedImage> pImages) {
            if (pInput == null) {
                throw new IllegalArgumentException("input == null");
            }

            sizes = new ArrayList<>();
            images = new ArrayList<>();

            List<Dimension> sizes = pSizes;
            if (sizes == null) {
                sizes = new ArrayList<>();
                if (pImages != null) {
                    for (BufferedImage image : pImages) {
                        sizes.add(new Dimension(image.getWidth(), image.getHeight()));
                    }
                } else {
                    throw new IllegalArgumentException("Need either size or image");
                }
            } else if (pImages != null) {
                if (pImages.size() != pSizes.size()) {
                    throw new IllegalArgumentException("Size parameter and image size differs");
                }
                for (int i = 0; i < sizes.size(); i++) {
                    if (!new Dimension(pImages.get(i).getWidth(), pImages.get(i).getHeight()).equals(sizes.get(i))) {
                        throw new IllegalArgumentException("Size parameter and image size differs");
                    }

                }
            }

            this.sizes.addAll(sizes);
            if (pImages != null) {
                images.addAll(pImages);
            }

            input = pInput;
        }

        public Object getInput() {
            return input;
        }

        public ImageInputStream getInputStream() {
            try {
                ImageInputStream stream = ImageIO.createImageInputStream(input);
                Assertions.assertNotNull(stream, "Could not create ImageInputStream for input: " + input);

                return stream;
            } catch (IOException e) {
                failBecause("Could not create ImageInputStream for input: " + input, e);
            }

            return null;
        }

        public int getImageCount() {
            return sizes.size();
        }

        public Dimension getDimension(final int pIndex) {
            return sizes.get(pIndex);
        }

        @SuppressWarnings("unused")
        public BufferedImage getImage(final int pIndex) {
            return images.get(pIndex);
        }

        @Override
        public String toString() {
            return String.format("%s: %s", getClass().getSimpleName(), input);
        }
    }
}
