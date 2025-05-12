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

                Color blurredColor = new Color(clamp(redSum), clamp(greenSum), clamp(blueSum), reader.getColor(x, y).getOpacity());
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
                double gray = color.getRed() * Constants.GRAYSCALE_VALUES[0] + color.getGreen() * Constants.GRAYSCALE_VALUES[1] + color.getBlue() * Constants.GRAYSCALE_VALUES[2];
                writer.setColor(x, y, new Color(gray, gray, gray, color.getOpacity()));
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
        double factor = brightness;
    
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

    public static void applyPixelation(ImageView imageView, int blockSize) {
        Image sourceImage = imageView.getImage();
        int width = (int) sourceImage.getWidth();
        int height = (int) sourceImage.getHeight();
    
        WritableImage pixelatedImage = new WritableImage(width, height);
        PixelReader reader = sourceImage.getPixelReader();
        PixelWriter writer = pixelatedImage.getPixelWriter();
    
        for (int y = 0; y < height; y += blockSize) {
            for (int x = 0; x < width; x += blockSize) {
                Color color = reader.getColor(x, y);
    
                for (int dy = 0; dy < blockSize; dy++) {
                    for (int dx = 0; dx < blockSize; dx++) {
                        int px = x + dx;
                        int py = y + dy;
    
                        if (px < width && py < height) {
                            writer.setColor(px, py, color);
                        }
                    }
                }
            }
        }
    
        imageView.setImage(pixelatedImage);
    }

    public static void applyColorOverlay(ImageView imageView, Color overlayColor) {
        Image sourceImage = imageView.getImage();
        int width = (int) sourceImage.getWidth();
        int height = (int) sourceImage.getHeight();
    
        WritableImage coloredImage = new WritableImage(width, height);
        PixelReader reader = sourceImage.getPixelReader();
        PixelWriter writer = coloredImage.getPixelWriter();
    
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color original = reader.getColor(x, y);
    
                double r = (original.getRed() + overlayColor.getRed()) / 2.0;
                double g = (original.getGreen() + overlayColor.getGreen()) / 2.0;
                double b = (original.getBlue() + overlayColor.getBlue()) / 2.0;
                double a = original.getOpacity();
    
                writer.setColor(x, y, new Color(r, g, b, a));
            }
        }
        imageView.setImage(coloredImage);
    }


    public static void applyBulge(ImageView imageView) {
        Image sourceImage = imageView.getImage();
        int width = (int) sourceImage.getWidth();
        int height = (int) sourceImage.getHeight();
    
        WritableImage distortedImage = new WritableImage(width, height);
        PixelReader reader = sourceImage.getPixelReader();
        PixelWriter writer = distortedImage.getPixelWriter();
    
        double cx = width / 2.0;
        double cy = height / 2.0;
    
        double p = Constants.BULGE_DISTORTION_POWER;
        double s = Constants.BULGE_SCALE_FACTOR;
    
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double dx = x - cx;
                double dy = y - cy;
    
                double r = Math.sqrt(dx * dx + dy * dy);
                double theta = Math.atan2(dy, dx);
    
                double rPrime = Math.pow(r, p) / s;
    
                double srcX = cx + rPrime * Math.cos(theta);
                double srcY = cy + rPrime * Math.sin(theta);
    
                if (srcX >= 0 && srcX < width && srcY >= 0 && srcY < height) {
                    Color color = reader.getColor((int) srcX, (int) srcY);
                    writer.setColor(x, y, color);
                } else {
                    writer.setColor(x, y, Color.TRANSPARENT);
                }
            }
        }

        imageView.setImage(distortedImage);
    }

    public static void applyVignette(ImageView imageView) {
        Image sourceImage = imageView.getImage();
        int width = (int) sourceImage.getWidth();
        int height = (int) sourceImage.getHeight();
    
        WritableImage vignettedImage = new WritableImage(width, height);
        PixelReader reader = sourceImage.getPixelReader();
        PixelWriter writer = vignettedImage.getPixelWriter();
    
        double cx = width / 2.0;
        double cy = height / 2.0;
    
        double maxDist = Math.sqrt(cx * cx + cy * cy);
        // don't go fully black
        double minFactor = Constants.VIGNETTE_MIN_SCALE;
    
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double dx = x - cx;
                double dy = y - cy;
                double dist = Math.sqrt(dx * dx + dy * dy);
    
                double factor = 1.0 - (dist / maxDist);

                factor = Math.max(minFactor, Math.min(1.0, factor));
    
                Color original = reader.getColor(x, y);
    
                double hue = original.getHue();
                double sat = original.getSaturation();
                double bright = original.getBrightness();
    
                writer.setColor(x, y, Color.hsb(hue, sat, bright * factor, original.getOpacity()));
            }
        }
    
        imageView.setImage(vignettedImage);
    }

    private static void applyKernelToImage(ImageView imageView, double[][] kernel) {
        Image sourceImage = imageView.getImage();
        int width = (int) sourceImage.getWidth();
        int height = (int) sourceImage.getHeight();
    
        WritableImage resultImage = new WritableImage(width, height);
        PixelReader reader = sourceImage.getPixelReader();
        PixelWriter writer = resultImage.getPixelWriter();
    
        int kernelSize = kernel.length;
        int offset = kernelSize / 2;
    
        for (int x = offset; x < width - offset; x++) {
            for (int y = offset; y < height - offset; y++) {
                double r = 0;
                double g = 0;
                double b = 0;
    
                for (int ky = 0; ky < kernelSize; ky++) {
                    for (int kx = 0; kx < kernelSize; kx++) {
                        int pixelX = x + kx - offset;
                        int pixelY = y + ky - offset;
                        Color color = reader.getColor(pixelX, pixelY);
                        double kernelVal = kernel[ky][kx];
    
                        r += color.getRed() * kernelVal;
                        g += color.getGreen() * kernelVal;
                        b += color.getBlue() * kernelVal;
                    }
                }
    
                writer.setColor(x, y, new Color(clamp(r), clamp(g), clamp(b), reader.getColor(x, y).getOpacity()));
            }
        }
    
        imageView.setImage(resultImage);
    }

    public static void applyEdgeDetection(ImageView imageView) {
        applyKernelToImage(imageView, Constants.EDGE_DETECTION_KERNEL);
    }

    public static void applyEmboss(ImageView imageView) {
        applyKernelToImage(imageView, Constants.EMBOSS_KERNEL);
    }

    public static void resizeImage(ImageView imageView, double scale) {
        Image image = imageView.getImage();
        int newWidth = (int) (image.getWidth() * scale);
        int newHeight = (int) (image.getHeight() * scale);
    
        WritableImage resizedImage = new WritableImage(newWidth, newHeight);
        PixelReader reader = image.getPixelReader();
        PixelWriter writer = resizedImage.getPixelWriter();
    
        for (int y = 0; y < newHeight; y++) {
            for (int x = 0; x < newWidth; x++) {
                int srcX = (int) (x / scale);
                int srcY = (int) (y / scale);
                writer.setColor(x, y, reader.getColor(srcX, srcY));
            }
        }
        
        imageView.setImage(resizedImage);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(0);
        imageView.setFitHeight(0);
    }
}