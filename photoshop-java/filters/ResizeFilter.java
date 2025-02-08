package filters;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class ResizeFilter {

    public static BufferedImage apply(
        BufferedImage image,
        int newWidth,
        int newHeight
    ) {
        //make sure min is 1
        newWidth = Math.max(newWidth, 1);
        newHeight = Math.max(newHeight, 1);

        BufferedImage resized = new BufferedImage(
            newWidth,
            newHeight,
            image.getType()
        );
        Graphics2D g = resized.createGraphics();
        g.setRenderingHint(
            RenderingHints.KEY_INTERPOLATION,
            RenderingHints.VALUE_INTERPOLATION_BILINEAR
        );
        g.drawImage(image, 0, 0, newWidth, newHeight, null);
        g.dispose();
        return resized;
    }
}
