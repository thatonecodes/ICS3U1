package com.photoshop.utils;

import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

/**
 * ImageUtils
 */
public class ImageUtils {

    // public static BufferedImage applyGaussianBlur(BufferedImage image) {
    //     float[] matrix = {
    //         0.0625f,
    //         0.125f,
    //         0.0625f,
    //         0.125f,
    //         0.25f,
    //         0.125f,
    //         0.0625f,
    //         0.125f,
    //         0.0625f,
    //     };
    //     Kernel kernel = new Kernel(3, 3, matrix);
    //     ConvolveOp convolve = new ConvolveOp(
    //         kernel,
    //         ConvolveOp.EDGE_NO_OP,
    //         null
    //     );
    //     return convolve.filter(image, null);
    // }

    public static void main(String[] args) {
        //run as testing
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
}
