package com.photoshop;

import java.util.Stack;

import com.photoshop.utils.AppUtils;
import com.photoshop.utils.Constants;
import com.photoshop.utils.ImageUtils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class PrimaryController {

    private Stage stage;
    private Image originalImage;
    private Stack<String> userActionStack;

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
    private SeparatorMenuItem insert;

    @FXML
    private MenuItem insertAudio;

    @FXML
    private MenuItem insertCircle;

    @FXML
    private MenuItem insertCode;

    @FXML
    private MenuItem insertImage;

    @FXML
    private MenuItem insertLink;

    @FXML
    private MenuItem insertRectangle;

    @FXML
    private MenuItem insertVideo;

    @FXML
    private MenuItem invertButton;

    @FXML
    private Label metadataLabel;

    @FXML
    private AnchorPane metadataPane;

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
        AppUtils.openImage(originalImage, imageView, stage);
    }

    @FXML
    public void onSaveImage(ActionEvent event) {
        AppUtils.saveImage(imageView);
    }

    @FXML
    void onHorizontalFlip(ActionEvent event) {
        ImageUtils.onHorizontalFlip(imageView);
    }

    @FXML
    void onGaussianBlur(ActionEvent event) {
        ImageUtils.applyGaussianBlur(imageView, Constants.BLUR_RADIUS, Constants.BLUR_SIGMA);
    }

    @FXML
    void onRotateImage(ActionEvent event) {
        ImageUtils.onRotate(imageView, AppUtils.prompt("Rotate Image", "Enter the rotation angle in degrees:", "Degrees:", "45"));
    }

    @FXML
    void onGrayscaleImage(ActionEvent event) {
        ImageUtils.applyGrayscale(imageView);
    }

    @FXML
    void onSepiaImage(ActionEvent event) {
        ImageUtils.applySepia(imageView);
    }

    @FXML
    void onInvertImage(ActionEvent event) {
        ImageUtils.invertColor(imageView);
    }

    @FXML
    void onBrightenImage(ActionEvent event) {
        ImageUtils.applyBrightness(imageView, AppUtils.prompt("Brighten Image", "Enter the brightness amount:", "Brightness:", "25"));
    }

    @FXML
    void revertToOriginal(ActionEvent event) {
        imageView.setImage(originalImage);
    }
}