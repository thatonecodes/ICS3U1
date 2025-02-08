package filters;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class GrayscaleFilter {

    public static BufferedImage apply(BufferedImage image) {
        BufferedImage grayImage = new BufferedImage(
            image.getWidth(),
            image.getHeight(),
            BufferedImage.TYPE_INT_ARGB
        );

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Color originalColor = new Color(image.getRGB(x, y));

                // Calculate grayscale value (luminosity formula)
                int gray = (int) (0.3 * originalColor.getRed() +
                    0.59 * originalColor.getGreen() +
                    0.11 * originalColor.getBlue());

                Color grayColor = new Color(
                    gray,
                    gray,
                    gray,
                    originalColor.getAlpha()
                ); // Preserve alpha (transparency)
                grayImage.setRGB(x, y, grayColor.getRGB());
            }
        }
        return grayImage;
    }
}
