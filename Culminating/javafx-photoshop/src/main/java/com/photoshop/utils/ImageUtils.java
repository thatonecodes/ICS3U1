package com.photoshop.utils;

import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
/**
 * ImageUtils, class provides utility functions to modify images.
 */
public class ImageUtils {

    //TODO: fix blur function
    // private static double[][] createGaussianKernel(int radius, double sigma) {
    //      int size = 2 * radius + 1;
    //      double[][] kernel = new double[size][size];
    //      double sum = 0;

    //     for (int y = -radius; y <= radius; y++) {
    //         for (int x = -radius; x <= radius; x++) {
    //             kernel[y + radius][x + radius] = Math.exp(-(x * x + y * y) / (2 * sigma * sigma)) / (2 * Math.PI * sigma * sigma);
    //             sum += kernel[y + radius][x + radius];
    //         }
    //     }

    //     // Normalize the kernel
    //     for (int y = 0; y < size; y++) {
    //         for (int x = 0; x < size; x++) {
    //             kernel[y][x] /= sum;
    //         }
    //     }

    //      return kernel;
    // }

    // public static void applyGaussianBlur(ImageView imageView, int radius, int sigma) {
    //     //g(x, y) = (1 / (2 * pi * sigma^2)) * exp(-(x^2 + y^2) / (2 * sigma^2))
    //     int width = (int) imageView.getImage().getWidth();
    //     int height = (int) imageView.getImage().getHeight();

    //     WritableImage writableImage = new WritableImage(width, height);
    //     PixelReader reader = imageView.getImage().getPixelReader();
    //     PixelWriter writer = writableImage.getPixelWriter();
        
    //     double[][] kernel = createGaussianKernel(radius, sigma);

    //     for (int y = 0; y < height; y++) {
    //         for (int x = 0; x < width; x++) {
    //             double redSum = 0, greenSum = 0, blueSum = 0, weightSum = 0;

    //             for (int ky = -radius; ky <= radius; ky++) {
    //                 for (int kx = -radius; kx <= radius; kx++) {
    //                     int neighborX = x + kx;
    //                     int neighborY = y + ky;

    //                     neighborX = Math.min(Math.max(neighborX, 0), width - 1);
    //                     neighborY = Math.min(Math.max(neighborY, 0), height - 1);

    //                     int rgb = source.getRGB(neighborX, neighborY);
    //                     double weight = kernel[ky + radius][kx + radius];

    //                     redSum += ((rgb >> 16) & 0xFF) * weight;
    //                     greenSum += ((rgb >> 8) & 0xFF) * weight;
    //                     blueSum += (rgb & 0xFF) * weight;
    //                     weightSum += weight;
    //                 }
    //             }

    //             int red = (int) Math.round(redSum / weightSum);
    //             int green = (int) Math.round(greenSum / weightSum);
    //             int blue = (int) Math.round(blueSum / weightSum);

    //             // bit mainupation, shift 16 bits left on green according to match with 3x3 matrix
    //             int blurredRGB = (red << 16) | (green << 8) | blue | (0xFF << 24); 
    //             blurredImage.setRGB(x, y, blurredRGB);
    //         }
    //     }
    //     imageView.setImage(writableImage);
    // }

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
        // TODO: fix exception with this rotate function
        int width = (int) imageView.getImage().getWidth();
        int height = (int) imageView.getImage().getHeight();
        double cx = width / 2.0; 
        double cy = height / 2.0;
        double theta = Math.toRadians(degree);
        double sinTheta = Math.sin(theta);
        double cosTheta = Math.cos(theta);

        WritableImage writableImage = new WritableImage(width, height);
        PixelReader reader = imageView.getImage().getPixelReader();
        PixelWriter writer = writableImage.getPixelWriter();
        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                double dx = i - cx;
                double dy = i - cy;

                int newX = (int) Math.round(dx * cosTheta - dy * sinTheta + cx);
                int newY = (int) Math.round(dx * sinTheta - dy * cosTheta + cy);
                if (newX >= 0 && newX < width && newY >= 0 && newY < height) {
                    writer.setColor(newX, newY, reader.getColor(i, j));
                } else {
                    writer.setColor(i, j, Color.TRANSPARENT);
                }
            }
        }
        imageView.setImage(writableImage);
    }
}