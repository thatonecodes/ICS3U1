package com.photoshop.utils;

public final class Constants {

    private Constants(){
        throw new UnsupportedOperationException("Constants class cannot be instantiated");
    }

    public static final String VERSION_NUMBER = "1.0.0";
    public static final String APP_NAME = "Botoshop";
    public static final int BLUR_RADIUS = 3;
    public static final double BLUR_SIGMA = 1.5;
    public static final String[] VALID_EXTENSION_STRINGS = new String[] {"*.png", "*.jpg", "*.jpeg", "*.bmp", "*.gif"};
    public static final double[] GRAYSCALE_VALUES = new double[] {0.21, 0.71, 0.07};
    public static final double[][] SEPIA_VALUES = new double[][] {{0.393, 0.769, 0.189}, {0.349, 0.686, 0.168}, {0.272, 0.534, 0.131}};
    public static final int PIXEL_BLOCK_SIZE = 5;
    public static final double BULGE_DISTORTION_POWER = 1.6;
    public static final double BULGE_SCALE_FACTOR = 30.0;
    public static final double VIGNETTE_MIN_SCALE = 0.3;
    public static final double[][] EDGE_DETECTION_KERNEL = new double[][] {{ 1, 1, 1 },{ 1, -7, 1 },{ 1, 1, 1 }};
    public static final double[][] EMBOSS_KERNEL = new double[][] {{ -2, -1, 0 }, { -1, 1, 1 }, { 0, 1, 2 }};
    public static final double[][] SHARPEN_KERNEL = new double[][] {{ 0, -1, 0 }, { -1, 5, -1 }, { 0, -1, 0 }};
}
