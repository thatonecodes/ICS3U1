package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.*;
import model.ImageModel;

public class ImagePanel extends JPanel {

    private ImageModel model;

    public ImagePanel(ImageModel model) {
        this.model = model;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage image = model.getImage();
        if (image != null) {
            g.drawImage(image, 0, 0, this);
        }
    }
}
