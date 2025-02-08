package utils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageUtils {

    public static BufferedImage loadImage(File file) throws IOException {
        return ImageIO.read(file);
    }

    public static void saveImage(BufferedImage image, File file, String format)
        throws IOException {
        if (image == null) {
            throw new IOException("Image is null, cannot save.");
        }

        // Convert image to TYPE_INT_ARGB (PNG) or TYPE_INT_RGB (JPEG)
        BufferedImage formattedImage;
        if (format.equalsIgnoreCase("png")) {
            formattedImage = new BufferedImage(
                image.getWidth(),
                image.getHeight(),
                BufferedImage.TYPE_INT_ARGB
            );
        } else {
            formattedImage = new BufferedImage(
                image.getWidth(),
                image.getHeight(),
                BufferedImage.TYPE_INT_RGB
            );
        }

        Graphics2D g = formattedImage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();

        boolean success = ImageIO.write(formattedImage, format, file);
        if (!success) {
            throw new IOException(
                "Failed to write the image in format: " + format
            );
        }

        System.out.println(
            "Image saved successfully as " +
            format.toUpperCase() +
            " at " +
            file.getAbsolutePath()
        );
    }
}
