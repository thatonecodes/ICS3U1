package com.photoshop;

import com.photoshop.utils.AppUtils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class PrimaryController {

    @FXML
    private MenuItem aboutButton;

    @FXML
    private MenuItem blurButton;

    @FXML
    private MenuItem closeFileButton;

    @FXML
    private MenuItem copyButton;

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
    private MenuItem selectAllButton;

    @FXML
    private MenuItem sharpenButton;

    @FXML
    private MenuItem undoButton;

    @FXML
    private MenuItem unselectAllButton;

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
}