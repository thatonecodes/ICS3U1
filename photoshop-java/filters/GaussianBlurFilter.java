package filters;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class GaussianBlurFilter {

    public static BufferedImage apply(BufferedImage image) {
        float[] matrix = {
            0.0625f,
            0.125f,
            0.0625f,
            0.125f,
            0.25f,
            0.125f,
            0.0625f,
            0.125f,
            0.0625f,
        };
        Kernel kernel = new Kernel(3, 3, matrix);
        ConvolveOp convolve = new ConvolveOp(
            kernel,
            ConvolveOp.EDGE_NO_OP,
            null
        );
        return convolve.filter(image, null);
    }
}
