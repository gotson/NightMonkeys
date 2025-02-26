package com.github.gotson.nightmonkeys.webp;

@FunctionalInterface
public interface ProgressCallback {

    /**
     * Called when progress is made.
     *
     * @param progress the progress value between 0 and 1.
     * @return true to continue encoding, false to abort.
     */
    boolean onProgress(float progress);
}
