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
                double redSum = 0, greenSum = 0, blueSum = 0;

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
                // must reversely map destination pixel (x, y) to source to avoid white dots
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
}