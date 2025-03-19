package ui;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import model.ImageModel;
import utils.ImageUtils;

public class MainFrame extends JFrame {

    private ImageModel model;
    private ImagePanel imagePanel;

    public MainFrame() {
        setTitle("Java Photoshop");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        model = new ImageModel();
        imagePanel = new ImagePanel(model);

        JScrollPane scrollPane = new JScrollPane(imagePanel); //allows scrolling
        add(scrollPane, BorderLayout.CENTER);

        Toolbar toolbar = new Toolbar(model, imagePanel);
        add(toolbar, BorderLayout.NORTH);

        // Add menus for File > Open/Save
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");

        JMenu editItem = new JMenu("Edit");
        JMenuItem undoItem = new JMenuItem("Undo");
        JMenuItem redoItem = new JMenuItem("Redo");

        openItem.addActionListener(_ -> openImage());
        saveItem.addActionListener(_ -> saveImage());

        redoItem.addActionListener(_ -> model.redo());
        undoItem.addActionListener(_ -> model.undo());

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        editItem.add(undoItem);
        editItem.add(redoItem);
        menuBar.add(fileMenu);
        menuBar.add(editItem);
        setJMenuBar(menuBar);
    }

    private void openImage() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                BufferedImage image = ImageUtils.loadImage(
                    chooser.getSelectedFile()
                );
                model.setImage(image);
                imagePanel.revalidate(); // Ensure layout updates
                imagePanel.repaint();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error loading image.");
            }
        }
    }

    private void saveImage() {
        BufferedImage image = model.getImage();
        if (image != null) {
            JFileChooser chooser = new JFileChooser();
            if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                try {
                    File selectedFile = chooser.getSelectedFile();
                    String filePath = selectedFile.getAbsolutePath();
                    if (!filePath.toLowerCase().endsWith(".png")) {
                        filePath += ".png";
                    }
                    ImageUtils.saveImage(image, new File(filePath), "png");
                    JOptionPane.showMessageDialog(
                        this,
                        "Image saved successfully!"
                    );
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error saving image.");
                    ex.printStackTrace();
                }
            }
        }
    }
}
