package filters;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class InvertFilter {

    public static BufferedImage apply(BufferedImage image) {
        BufferedImage result = new BufferedImage(
            image.getWidth(),
            image.getHeight(),
            BufferedImage.TYPE_INT_ARGB
        );
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int rgba = image.getRGB(x, y);
                Color color = new Color(rgba, true);
                Color inverted = new Color(
                    255 - color.getRed(),
                    255 - color.getGreen(),
                    255 - color.getBlue(),
                    color.getAlpha()
                );
                result.setRGB(x, y, inverted.getRGB());
            }
        }
        return result;
    }
}
