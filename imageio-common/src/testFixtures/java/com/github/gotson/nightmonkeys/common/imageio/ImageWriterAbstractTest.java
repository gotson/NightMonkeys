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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InOrder;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.event.IIOWriteProgressListener;
import javax.imageio.spi.IIORegistry;
import javax.imageio.spi.ImageWriterSpi;
import javax.imageio.stream.ImageOutputStream;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.anyFloat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;

/**
 * ImageReaderAbstractTestCase class description.
 *
 * @author <a href="mailto:harald.kuhr@gmail.com">Harald Kuhr</a>
 * @author last modified by $Author: haku $
 * @version $Id: ImageReaderAbstractTestCase.java,v 1.0 18.nov.2004 17:38:33 haku Exp $
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class ImageWriterAbstractTest<T extends ImageWriter> {

    // TODO: Move static block + getClassLoaderResource to common superclass for reader/writer test cases or delegate.

    static {
        IIORegistry.getDefaultInstance().registerServiceProvider(new URLImageInputStreamSpi());
        ImageIO.setUseCache(false);
    }

    @SuppressWarnings("unchecked")
    private final Class<T> writerClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    protected final ImageWriterSpi provider = createProvider();

    protected abstract ImageWriterSpi createProvider();

    protected final T createWriter() throws IOException {
        return writerClass.cast(provider.createWriterInstance());
    }

    private List<? extends RenderedImage> testData;

    private List<? extends RenderedImage> getTestDataInternal() {
        if (testData == null) {
            testData = getTestData();
        }
        return testData;
    }

    protected abstract List<? extends RenderedImage> getTestData();

    private List<? extends ImageWriteParam> testParams;

    private List<? extends ImageWriteParam> getTestWriteParamsInternal() {
        if (testParams == null) {
            testParams = getTestWriteParams();
        }
        return testParams;
    }

    protected abstract List<? extends ImageWriteParam> getTestWriteParams();

    protected static BufferedImage drawSomething(final BufferedImage image) {
        Graphics2D g = image.createGraphics();
        try {
            int width = image.getWidth();
            int height = image.getHeight();

            g.clearRect(0, 0, width, height);
            g.setPaint(new LinearGradientPaint(0, 0, width, 0, new float[] {0.2f, 1}, new Color[] {new Color(0x0, true), Color.BLUE}));
            g.fillRect(0, 0, width, height);
            g.setPaint(new LinearGradientPaint(0, 0, 0, height, new float[] {0.2f, 1}, new Color[] {new Color(0x0, true), Color.RED}));
            g.fillRect(0, 0, width, height);
            g.setPaint(new LinearGradientPaint(0, 0, 0, height, new float[] {0, 1}, new Color[] {new Color(0x00ffffff, true), Color.WHITE}));
            g.fill(new Polygon(new int[] {0, width, width}, new int[] {0, height, 0}, 3));
        } finally {
            g.dispose();
        }

        return image;
    }

    protected final RenderedImage getTestData(final int index) {
        return getTestDataInternal().get(index);
    }

    protected final String testFileExtension = getTestFileExtension();

    protected abstract String getTestFileExtension();

    protected URL getClassLoaderResource(final String name) {
        return getClass().getResource(name);
    }

    @Test
    public void testSetOutput() throws IOException {
        // Should just pass with no exceptions
        ImageWriter writer = createWriter();
        assertNotNull(writer);
        writer.setOutput(ImageIO.createImageOutputStream(new ByteArrayOutputStream()));
    }

    @Test
    public void testSetOutputNull() throws IOException {
        // Should just pass with no exceptions
        ImageWriter writer = createWriter();
        assertNotNull(writer);
        writer.setOutput(null);
    }

    @ParameterizedTest
    @MethodSource("getTestDataInternal")
    public void testWrite(RenderedImage testData) throws IOException {
        testWrite(testData, null, true, true);
    }

    @ParameterizedTest
    @MethodSource("getTestWriteParamsInternal")
    public void testWriteWithParam(ImageWriteParam testParam) throws IOException {
        testWrite(getTestData(0), testParam, true, false);
    }

    public void testWrite(RenderedImage testData, ImageWriteParam param, boolean writeTestImageToTemp, boolean writeCheckImageToTemp) throws IOException {
        ImageWriter writer = createWriter();

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        BufferedImage testImage = drawSomething((BufferedImage) testData);

        try (ImageOutputStream stream = ImageIO.createImageOutputStream(buffer)) {
            writer.setOutput(stream);
            writer.write(null, new IIOImage(testImage, null, null), param);
        } catch (IOException e) {
            throw new AssertionError(e.getMessage(), e);
        }

        assertTrue(buffer.size() > 0, "No image data written");

        if (writeTestImageToTemp) {
            String attributes = "";
            if (param != null) {
                attributes += param.getCompressionType() + "-" + param.getCompressionQuality() + "-";
                if (param.isCompressionLossless()) {
                    attributes += "lossless-";
                } else {
                    attributes += "lossy-";
                }
            }
            File tempActual = File.createTempFile("junit-testWrite-" + attributes, "." + testFileExtension);
            System.out.println("tempActual.getAbsolutePath(): " + tempActual.getAbsolutePath());
            Files.write(tempActual.toPath(), buffer.toByteArray());
        }

        if (writeCheckImageToTemp) {
            File tempPng = File.createTempFile("junit-testWrite-", ".png");
            ImageIO.write(testImage, "PNG", tempPng);
        }
    }

    @Test
    public void testWriteNull() throws IOException {
        ImageWriter writer = createWriter();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        writer.setOutput(ImageIO.createImageOutputStream(buffer));

        try {
            writer.write((RenderedImage) null);
        } catch (IllegalArgumentException ignore) {
        } catch (IOException e) {
            throw new AssertionError(e.getMessage(), e);
        }

        assertEquals(0, buffer.size(), "Image data written");
    }

    @Test
    public void testWriteNoOutput() throws IOException {
        ImageWriter writer = createWriter();

        assertThrows(IllegalStateException.class, () -> {
            writer.write(getTestData(0));
        }, "Expected IllegalStateException when no output is set on writer");
    }

    @Test
    public void testGetDefaultWriteParam() throws IOException {
        ImageWriter writer = createWriter();
        ImageWriteParam param = writer.getDefaultWriteParam();
        assertNotNull(param, "Default ImageWriteParam is null");
    }

    // TODO: Test writing with params
    // TODO: Source region and subsampling at least

    @Test
    public void testAddIIOWriteProgressListener() throws IOException {
        ImageWriter writer = createWriter();
        writer.addIIOWriteProgressListener(mock(IIOWriteProgressListener.class));
    }

    @Test
    public void testAddIIOWriteProgressListenerNull() throws IOException {
        ImageWriter writer = createWriter();
        writer.addIIOWriteProgressListener(null);
    }

    @Test
    public void testAddIIOWriteProgressListenerCallbacks() throws IOException {
        ImageWriter writer = createWriter();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        writer.setOutput(ImageIO.createImageOutputStream(buffer));

        IIOWriteProgressListener listener = mock(IIOWriteProgressListener.class);
        writer.addIIOWriteProgressListener(listener);

        try {
            writer.write(getTestData(0));
        } catch (IOException e) {
            fail("Could not write image");
        }

        // At least imageStarted and imageComplete, plus any number of imageProgress
        InOrder ordered = inOrder(listener);
        ordered.verify(listener).imageStarted(writer, 0);
        ordered.verify(listener, atLeastOnce()).imageProgress(eq(writer), anyFloat());
        ordered.verify(listener).imageComplete(writer);
    }

    @Test
    public void testMultipleAddIIOWriteProgressListenerCallbacks() throws IOException {
        ImageWriter writer = createWriter();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        writer.setOutput(ImageIO.createImageOutputStream(buffer));

        IIOWriteProgressListener listener = mock(IIOWriteProgressListener.class);
        IIOWriteProgressListener listenerToo = mock(IIOWriteProgressListener.class);
        IIOWriteProgressListener listenerThree = mock(IIOWriteProgressListener.class);

        writer.addIIOWriteProgressListener(listener);
        writer.addIIOWriteProgressListener(listenerToo);
        writer.addIIOWriteProgressListener(listenerThree);

        try {
            writer.write(getTestData(0));
        } catch (IOException e) {
            fail("Could not write image");
        }

        // At least imageStarted and imageComplete, plus any number of imageProgress
        InOrder ordered = inOrder(listener, listenerToo, listenerThree);

        ordered.verify(listener).imageStarted(writer, 0);
        ordered.verify(listenerToo).imageStarted(writer, 0);
        ordered.verify(listenerThree).imageStarted(writer, 0);

        ordered.verify(listener, atLeastOnce()).imageProgress(eq(writer), anyFloat());
        ordered.verify(listenerToo, atLeastOnce()).imageProgress(eq(writer), anyFloat());
        ordered.verify(listenerThree, atLeastOnce()).imageProgress(eq(writer), anyFloat());

        ordered.verify(listener).imageComplete(writer);
        ordered.verify(listenerToo).imageComplete(writer);
        ordered.verify(listenerThree).imageComplete(writer);
    }

    @Test
    public void testRemoveIIOWriteProgressListenerNull() throws IOException {
        ImageWriter writer = createWriter();
        writer.removeIIOWriteProgressListener(null);
    }

    @Test
    public void testRemoveIIOWriteProgressListenerNone() throws IOException {
        ImageWriter writer = createWriter();
        writer.removeIIOWriteProgressListener(mock(IIOWriteProgressListener.class));
    }

    @Test
    public void testRemoveIIOWriteProgressListener() throws IOException {
        ImageWriter writer = createWriter();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        writer.setOutput(ImageIO.createImageOutputStream(buffer));

        IIOWriteProgressListener listener = mock(IIOWriteProgressListener.class);
        writer.addIIOWriteProgressListener(listener);
        writer.removeIIOWriteProgressListener(listener);

        try {
            writer.write(getTestData(0));
        } catch (IOException e) {
            fail("Could not write image");
        }

        // Should not have called any methods...
        verifyNoInteractions(listener);
    }

    @Test
    public void testRemoveIIOWriteProgressListenerMultiple() throws IOException {
        ImageWriter writer = createWriter();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        writer.setOutput(ImageIO.createImageOutputStream(buffer));

        IIOWriteProgressListener listener = mock(IIOWriteProgressListener.class);
        writer.addIIOWriteProgressListener(listener);

        IIOWriteProgressListener listenerToo = mock(IIOWriteProgressListener.class);
        writer.addIIOWriteProgressListener(listenerToo);

        writer.removeIIOWriteProgressListener(listener);

        try {
            writer.write(getTestData(0));
        } catch (IOException e) {
            fail("Could not write image");
        }

        // Should not have called any methods...
        verifyNoInteractions(listener);

        // At least imageStarted and imageComplete, plus any number of imageProgress
        InOrder ordered = inOrder(listenerToo);
        ordered.verify(listenerToo).imageStarted(writer, 0);
        ordered.verify(listenerToo, atLeastOnce()).imageProgress(eq(writer), anyFloat());
        ordered.verify(listenerToo).imageComplete(writer);

    }

    @Test
    public void testRemoveAllIIOWriteProgressListeners() throws IOException {
        ImageWriter writer = createWriter();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        writer.setOutput(ImageIO.createImageOutputStream(buffer));


        IIOWriteProgressListener listener = mock(IIOWriteProgressListener.class);
        writer.addIIOWriteProgressListener(listener);

        writer.removeAllIIOWriteProgressListeners();

        try {
            writer.write(getTestData(0));
        } catch (IOException e) {
            fail("Could not write image");
        }

        // Should not have called any methods...
        verifyNoInteractions(listener);
    }

    @Test
    public void testRemoveAllIIOWriteProgressListenersMultiple() throws IOException {
        ImageWriter writer = createWriter();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        writer.setOutput(ImageIO.createImageOutputStream(buffer));


        IIOWriteProgressListener listener = mock(IIOWriteProgressListener.class);
        writer.addIIOWriteProgressListener(listener);

        IIOWriteProgressListener listenerToo = mock(IIOWriteProgressListener.class);
        writer.addIIOWriteProgressListener(listenerToo);

        writer.removeAllIIOWriteProgressListeners();

        try {
            writer.write(getTestData(0));
        } catch (IOException e) {
            fail("Could not write image");
        }

        // Should not have called any methods...
        verifyNoInteractions(listener);
        verifyNoInteractions(listenerToo);
    }
}