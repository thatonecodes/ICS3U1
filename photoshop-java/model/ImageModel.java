package model;

import java.awt.image.BufferedImage;
import java.util.Stack;

public class ImageModel {

    private BufferedImage currentImage;
    private Stack<BufferedImage> undoStack = new Stack<>();
    private Stack<BufferedImage> redoStack = new Stack<>();

    public void setImage(BufferedImage image) {
        undoStack.push(currentImage);
        currentImage = image;
    }

    public BufferedImage getImage() {
        return currentImage;
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(currentImage);
            currentImage = undoStack.pop();
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(currentImage);
            currentImage = redoStack.pop();
        }
    }
}
