package com.github.gotson.nightmonkeys.webp.imageio.plugins;

import com.twelvemonkeys.imageio.AbstractMetadata;

/**
 * This class represents the metadata for a WebP stream.
 * Meaning the metadata that is associated with the whole container.
 */
public class WebpStreamMetadata extends AbstractMetadata {

	private int canvasWidth;
	private int canvasHeight;
	private int canvasBackgroundColor;
	private int loopCount;
	private int totalDuration;

	/**
	 * Returns the width of the canvas.
	 *
	 * @return the width of the canvas
	 */
	public int getCanvasWidth() {
		return canvasWidth;
	}

	/**
	 * Sets the width of the canvas.
	 *
	 * @param canvasWidth the new width of the canvas
	 */
	public void setCanvasWidth(int canvasWidth) {
		this.canvasWidth = canvasWidth;
	}

	/**
	 * Returns the height of the canvas.
	 *
	 * @return the height of the canvas
	 */
	public int getCanvasHeight() {
		return canvasHeight;
	}

	/**
	 * Sets the height of the canvas.
	 *
	 * @param canvasHeight the new height of the canvas
	 */
	public void setCanvasHeight(int canvasHeight) {
		this.canvasHeight = canvasHeight;
	}

	/**
	 * Returns the loop count.
	 *
	 * @return the loop count
	 */
	public int getLoopCount() {
		return loopCount;
	}

	/**
	 * Sets the loop count.
	 * 0 means infinite loop.
	 *
	 * @param loopCount the new loop count
	 */
	public void setLoopCount(int loopCount) {
		this.loopCount = loopCount;
	}

	/**
	 * Returns the background color of the canvas as an argb integer.
	 *
	 * @return the background color of the canvas
	 */
	public int getCanvasBackgroundColor() {
		return canvasBackgroundColor;
	}

	/**
	 * Sets the background color of the canvas.
	 * The color is represented as argb.
	 *
	 * @param canvasBackgroundColor the new background color of the canvas
	 */
	public void setCanvasBackgroundColor(int canvasBackgroundColor) {
		this.canvasBackgroundColor = canvasBackgroundColor;
	}

	/**
	 * Returns the total duration (in ms) of the animation.
	 *
	 * @return the total duration of the animation
	 */
	public int getTotalDuration() {
		return totalDuration;
	}

	/**
	 * Sets the total duration (in ms) of the animation.
	 *
	 * @param totalDuration the new total duration of the animation
	 */
	public void setTotalDuration(int totalDuration) {
		this.totalDuration = totalDuration;
	}
}