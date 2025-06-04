package com.photoshop;

import java.util.Stack;

import com.photoshop.utils.AppUtils;
import com.photoshop.utils.Constants;
import com.photoshop.utils.ImageUtils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class PrimaryController {

    private Stage stage;
    private Image originalImage;
    private double originalFitWidth;
    private double originalFitHeight;
    private Stack<Image> undoStack = new Stack<>();
    private Stack<Image> redoStack = new Stack<>();

    @FXML
    private MenuItem aboutButton;

    @FXML
    private MenuItem blurButton;

    @FXML
    private MenuItem closeFileButton;

    @FXML
    private MenuItem copyButton;

    @FXML
    private ImageView imageView;

    @FXML
    private MenuItem cutButton;

    @FXML
    private MenuItem deleteButton;

    @FXML
    private MenuItem fullscreenButton;

    @FXML
    private MenuItem grayScaleButton;

    @FXML
    private MenuItem hideToolbarButton;

    @FXML
    private MenuItem edgeDetectButton;

    @FXML
    private MenuItem embossButton;

    @FXML
    private MenuItem invertButton;

    @FXML
    private MenuItem pixelButton;

    @FXML
    private MenuItem colorOverlayButton;

    @FXML
    private MenuItem bulgeButton;

    @FXML
    private MenuItem newFileLabel;

    @FXML
    private MenuItem openFileButton;

    @FXML
    private MenuItem pasteButton;

    @FXML
    private MenuItem prefButton;

    @FXML
    private MenuItem quitButton;

    @FXML
    private MenuItem redoButton;

    @FXML
    private MenuItem sepiaButton;

    @FXML
    private MenuItem resizeButton;

    @FXML
    private MenuItem revertFileButton;

    @FXML
    private MenuItem saveFileAsButton;

    @FXML
    private MenuItem saveFileButton;

    @FXML
    private MenuItem rotateImage;

    @FXML
    private MenuItem selectAllButton;

    @FXML
    private MenuItem horizontalFlip;

    @FXML
    private MenuItem sharpenButton;

    @FXML
    private MenuItem undoButton;

    @FXML
    private MenuItem unselectAllButton;

    @FXML
    private MenuItem vignetteButton;

    @FXML
    private MenuBar toolbar;

    @FXML
    private MenuItem brightenImage;

    @FXML
    private Font x1;

    @FXML
    private Color x2;

    @FXML
    private Font x3;

    @FXML
    private Color x4;

    @FXML
    private MenuItem zoomButton;

    @FXML
    private Label quickActionLabel;

    private void saveState() {
        Image current = imageView.getImage();
        if (current != null) {
            undoStack.push(current);
            redoStack.clear(); //clear redo history on new action
        }
    }

    @FXML
    void quitAppButton(ActionEvent event) {
        AppUtils.quitApp();
    }

    @FXML
    void aboutButton(ActionEvent event) {
        AppUtils.createAlert(String.format("About %s", Constants.APP_NAME), "Botoshop is a replacement for photoshop, in javafx", "Created for ICS3U1 project, June 2025", AlertType.INFORMATION);
    }

    @FXML
    void onOpenImage(ActionEvent event) {
        // saves image to original image, opens image in imgview
        AppUtils.openImage(imageView, stage);
        originalImage = imageView.getImage();
        originalFitWidth = imageView.getFitWidth();
        originalFitHeight = imageView.getFitHeight();
    }

    @FXML
    public void onSaveImage(ActionEvent event) {
        AppUtils.saveImage(imageView);
    }

    @FXML
    void onHorizontalFlip(ActionEvent event) {
        saveState();
        ImageUtils.onHorizontalFlip(imageView);
    }

    @FXML
    void onGaussianBlur(ActionEvent event) {
        saveState();
        ImageUtils.applyGaussianBlur(imageView, Constants.BLUR_RADIUS, Constants.BLUR_SIGMA);
    }

    @FXML
    void onRotateImage(ActionEvent event) {
        saveState();
        ImageUtils.onRotate(imageView, AppUtils.prompt("Rotate Image", "Enter the rotation angle in degrees:", "Degrees:", "45"));
    }

    @FXML
    void onGrayscaleImage(ActionEvent event) {
        saveState();
        ImageUtils.applyGrayscale(imageView);
    }

    @FXML
    void onSepiaImage(ActionEvent event) {
        saveState();
        ImageUtils.applySepia(imageView);
    }

    @FXML
    void onInvertImage(ActionEvent event) {
        saveState();
        ImageUtils.invertColor(imageView);
    }

    @FXML
    void onBrightenImage(ActionEvent event) {
        saveState();
        ImageUtils.applyBrightness(imageView, AppUtils.prompt("Brighten Image", "Enter the brightness amount:", "Brightness:", "1.0"));
    }

    @FXML
    void revertToOriginal(ActionEvent event) {
        saveState();
        imageView.setImage(originalImage);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(originalFitWidth);
        imageView.setFitHeight(originalFitHeight);
    }

    @FXML
    void onPixelImage(ActionEvent event) {
        saveState();
        ImageUtils.applyPixelation(imageView, Constants.PIXEL_BLOCK_SIZE);
    }

    @FXML
    void onColorOverlay(ActionEvent event) {
        saveState();
        ImageUtils.applyColorOverlay(imageView, AppUtils.colorChooserPrompt());
    }

    @FXML
    void onBulgeImage(ActionEvent event) {
        saveState();
        ImageUtils.applyBulge(imageView);
    }

    @FXML
    void onVignetteImage(ActionEvent event) {
        saveState();
        ImageUtils.applyVignette(imageView);
    }

    @FXML
    void onEdgeDetectImage(ActionEvent event) {
        saveState();
        ImageUtils.applyEdgeDetection(imageView);
    }

    @FXML
    void onEmbossImage(ActionEvent event) {
        saveState();
        ImageUtils.applyEmboss(imageView);
    }

    @FXML
    void onResizeImage(ActionEvent event) {
        saveState();
        ImageUtils.resizeImage(imageView, AppUtils.prompt("Resize", "Resize Image", "Enter the scale you would like to resize the image to:", "1.0"));
    }

    @FXML
    void onUndo(ActionEvent event) {
        if (!undoStack.isEmpty()) {
            redoStack.push(imageView.getImage());
            if (imageView.getFitHeight() != originalFitHeight && imageView.getFitWidth() != originalFitWidth) {
                imageView.setFitHeight(originalFitHeight);
                imageView.setFitWidth(originalFitWidth);
            }
            imageView.setImage(undoStack.pop());
        }
    }
    
    @FXML
    void onRedo(ActionEvent event) {
        if (!redoStack.isEmpty()) {
            undoStack.push(imageView.getImage());
            imageView.setImage(redoStack.pop());
        }
    }

    @FXML
    void onFullscreen(ActionEvent event) {
        if (stage != null) {
            stage.setFullScreen(true);
        }
    }

    @FXML
    void onHideToolbar(ActionEvent event) {
        toolbar.setVisible(false);
        toolbar.setManaged(false);
    }
    

    void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case Z:
                if (event.isControlDown()) {
                    onUndo(null);
                }
                if (event.isControlDown() && event.isShiftDown()) {
                    onRedo(null);
                }
                break;
            case F11:
                onFullscreen(null);
                break;
            default:
                break;
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}