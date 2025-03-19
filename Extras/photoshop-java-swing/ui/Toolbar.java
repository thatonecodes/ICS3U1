package ui;

import filters.GaussianBlurFilter;
import filters.GrayscaleFilter;
import filters.InvertFilter;
import filters.ResizeFilter;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import model.ImageModel;

public class Toolbar extends JPanel {

    public Toolbar(ImageModel model, ImagePanel panel) {
        JButton invertButton = new JButton("Invert");
        invertButton.addActionListener(_ -> {
            BufferedImage inverted = InvertFilter.apply(model.getImage());
            model.setImage(inverted);
            panel.repaint();
        });

        JButton blurButton = new JButton("Blur");
        blurButton.addActionListener(_ -> {
            BufferedImage blurred = GaussianBlurFilter.apply(model.getImage());
            model.setImage(blurred);
            panel.repaint();
        });

        JButton grayScaleButton = new JButton("GrayScale");
        grayScaleButton.addActionListener(_ -> {
            BufferedImage grayScaled = GrayscaleFilter.apply(model.getImage());
            model.setImage(grayScaled);
            panel.repaint();
        });

        JSlider resizeSlider = new JSlider(10, 200, 100); // Min: 10%, Max: 200%, Default: 100%
        resizeSlider.setMajorTickSpacing(50);
        resizeSlider.setMinorTickSpacing(10);
        resizeSlider.setPaintTicks(true);
        resizeSlider.setPaintLabels(true);

        resizeSlider.addChangeListener(
            new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    BufferedImage original = model.getImage();
                    if (original != null) {
                        int scalePercent = resizeSlider.getValue();

                        scalePercent = Math.max(scalePercent, 1);

                        int newWidth = (int) (original.getWidth() *
                            (scalePercent / 100.0));
                        int newHeight = (int) (original.getHeight() *
                            (scalePercent / 100.0));

                        BufferedImage resized = ResizeFilter.apply(
                            original,
                            newWidth,
                            newHeight
                        );
                        model.setImage(resized);
                        panel.repaint();
                    }
                }
            }
        );

        add(invertButton);
        add(blurButton);
        add(grayScaleButton);
        add(new JLabel("Resize:"));
        add(resizeSlider);
    }
}
