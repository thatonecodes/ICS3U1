package com.photoshop.utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
/**
 * ImageUtils, class provides utility functions to modify images.
 */
public class ImageUtils {

    private static double[][] createGaussianKernel(int radius, double sigma) {
        int size = 2 * radius + 1;
        double[][] kernel = new double[size][size];
        double sum = 0;

        for (int y = -radius; y <= radius; y++) {
            for (int x = -radius; x <= radius; x++) { 
                // gaussian blur formula
                double value = Math.exp(-(x * x + y * y) / (2 * sigma * sigma)) /
                               (2 * Math.PI * sigma * sigma);
                kernel[y + radius][x + radius] = value;
                sum += value;
            }
        }

        // normalization of the kernel
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                kernel[y][x] /= sum;
            }
        }

        return kernel;
    }

    public static void applyGaussianBlur(ImageView imageView, int radius, double sigma) {
        Image sourceImage = imageView.getImage();
        int width = (int) sourceImage.getWidth();
        int height = (int) sourceImage.getHeight();

        WritableImage blurredImage = new WritableImage(width, height);
        PixelReader reader = sourceImage.getPixelReader();
        PixelWriter writer = blurredImage.getPixelWriter();

        double[][] kernel = createGaussianKernel(radius, sigma);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double redSum = 0;
                double greenSum = 0;
                double blueSum = 0;

                for (int ky = -radius; ky <= radius; ky++) {
                    for (int kx = -radius; kx <= radius; kx++) {
                        int px = Math.min(Math.max(x + kx, 0), width - 1);
                        int py = Math.min(Math.max(y + ky, 0), height - 1);

                        Color color = reader.getColor(px, py);
                        double weight = kernel[ky + radius][kx + radius];

                        redSum += color.getRed() * weight;
                        greenSum += color.getGreen() * weight;
                        blueSum += color.getBlue() * weight;
                    }
                }

                Color blurredColor = new Color(clamp(redSum), clamp(greenSum), clamp(blueSum), 1.0);
                writer.setColor(x, y, blurredColor);
            }
        }

        imageView.setImage(blurredImage);
    }

    private static double clamp(double value) {
        return Math.min(1.0, Math.max(0.0, value));
    }

    public static void onHorizontalFlip(ImageView imageView) {
        int width = (int) imageView.getImage().getWidth();
        int height = (int) imageView.getImage().getHeight();

        WritableImage writableImage = new WritableImage(width, height);
        PixelReader reader = imageView.getImage().getPixelReader();
        PixelWriter writer = writableImage.getPixelWriter();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                writer.setColor(width - i - 1, j, reader.getColor(i, j));
            }
        }
        imageView.setImage(writableImage);
    }

    public static void onRotate(ImageView imageView, double degree) {
        Image sourceImage = imageView.getImage();
        int width = (int) sourceImage.getWidth();
        int height = (int) sourceImage.getHeight();

        double cx = width / 2.0;
        double cy = height / 2.0;
        double theta = Math.toRadians(degree);
        double sinTheta = Math.sin(theta);
        double cosTheta = Math.cos(theta);

        WritableImage rotatedImage = new WritableImage(width, height);
        PixelReader reader = sourceImage.getPixelReader();
        PixelWriter writer = rotatedImage.getPixelWriter();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // must reversely map destination pixel (y, x) to source to avoid white dots
                double dx = x - cx;
                double dy = y - cy;

                double srcX =  dx * cosTheta + dy * sinTheta + cx;
                double srcY = -dx * sinTheta + dy * cosTheta + cy;

                int srcXi = (int) Math.floor(srcX);
                int srcYi = (int) Math.floor(srcY);

                if (srcXi >= 0 && srcXi < width && srcYi >= 0 && srcYi < height) {
                    writer.setColor(x, y, reader.getColor(srcXi, srcYi));
                } else {
                    writer.setColor(x, y, Color.TRANSPARENT);
                }
            }
        }
        imageView.setImage(rotatedImage);
    }

    public static void applyGrayscale(ImageView imageView) {
        Image sourceImage = imageView.getImage();
        int width = (int) sourceImage.getWidth();
        int height = (int) sourceImage.getHeight();

        WritableImage grayscaledImage = new WritableImage(width, height);
        PixelReader reader = sourceImage.getPixelReader();
        PixelWriter writer = grayscaledImage.getPixelWriter();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color color = reader.getColor(x, y);
                writer.setColor(x, y, new Color(color.getRed() * Constants.GRAYSCALE_VALUES[0], color.getGreen() * Constants.GRAYSCALE_VALUES[1], color.getBlue() * Constants.GRAYSCALE_VALUES[2], color.getOpacity()));
            }
        }

        imageView.setImage(grayscaledImage);
    }

    public static void applySepia(ImageView imageView) {
        Image sourceImage = imageView.getImage();
        int width = (int) sourceImage.getWidth();
        int height = (int) sourceImage.getHeight();
    
        WritableImage sepiaImage = new WritableImage(width, height);
        PixelReader reader = sourceImage.getPixelReader();
        PixelWriter writer = sepiaImage.getPixelWriter();
    
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color color = reader.getColor(x, y);
                double r = color.getRed();
                double g = color.getGreen();
                double b = color.getBlue();
    
                double newR = Constants.SEPIA_VALUES[0][0] * r + Constants.SEPIA_VALUES[0][1] * g + Constants.SEPIA_VALUES[0][2] * b;
                double newG = Constants.SEPIA_VALUES[1][0] * r + Constants.SEPIA_VALUES[1][1] * g + Constants.SEPIA_VALUES[1][2] * b;
                double newB = Constants.SEPIA_VALUES[2][0] * r + Constants.SEPIA_VALUES[2][1] * g + Constants.SEPIA_VALUES[2][2] * b;
    
                newR = clamp(newR);
                newG = clamp(newG);
                newB = clamp(newB);
    
                writer.setColor(x, y, new Color(newR, newG, newB, color.getOpacity()));
            }
        }
    
        imageView.setImage(sepiaImage);
    }

    public static void invertColor(ImageView imageView) {
        Image sourceImage = imageView.getImage();
        int width = (int) sourceImage.getWidth();
        int height = (int) sourceImage.getHeight();

        WritableImage invertedImage = new WritableImage(width, height);
        PixelReader reader = sourceImage.getPixelReader();
        PixelWriter writer = invertedImage.getPixelWriter();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color color = reader.getColor(x, y);
                writer.setColor(x, y, new Color(1 - color.getRed(), 1 - color.getGreen(), 1 - color.getBlue(), color.getOpacity()));
            }
        }

        imageView.setImage(invertedImage);
    }

    public static void applyBrightness(ImageView imageView, double brightness) {
        // clamp brightness between 1 and 100
        brightness = Math.max(1, Math.min(brightness, 100));
        double factor = brightness / 100.0;
    
        Image sourceImage = imageView.getImage();
        int width = (int) sourceImage.getWidth();
        int height = (int) sourceImage.getHeight();
    
        WritableImage brightenedImage = new WritableImage(width, height);
        PixelReader reader = sourceImage.getPixelReader();
        PixelWriter writer = brightenedImage.getPixelWriter();
    
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color color = reader.getColor(x, y);
    
                double r = clamp(color.getRed() * factor);
                double g = clamp(color.getGreen() * factor);
                double b = clamp(color.getBlue() * factor);
    
                writer.setColor(x, y, new Color(r, g, b, color.getOpacity()));
            }
        }
    
        imageView.setImage(brightenedImage);
    }
}