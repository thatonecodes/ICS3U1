package com.photoshop.utils;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 * This class contains useful application utilities to be used in App.java.
 */
public class AppUtils {
    
    public static String getVersionString() {
        return Constants.VERSION_NUMBER;
    }

    public static void quitApp() {
        System.out.println("User chose to exit app, quitting with exit code 0.");
        System.exit(0);
    }

    public static void createAlert(String title, String headerText, String contentText, AlertType typeOfAlert) {
        if (headerText == null) {
            headerText = "";
        }
        Alert alert = new Alert(typeOfAlert);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        alert.showAndWait();
    }

    public static void openImage(ImageView canvasImage, Stage desiredStage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", Constants.VALID_EXTENSION_STRINGS));

        try {
            File file = fileChooser.showOpenDialog(desiredStage);
            if (file != null) {
                Image image = new Image(file.toURI().toString());
                canvasImage.setImage(image);
            }
        } catch (Exception e) {
            createAlert("Error", "Image Load Failed", "There was a problem opening your image!", AlertType.ERROR);
        }
    }

    public static void saveImage(ImageView imageView){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Image");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG files", "*.png"));
        File file = fileChooser.showSaveDialog(imageView.getScene().getWindow());

        if (file != null) {
            WritableImage writableImage = imageView.snapshot(null, null);
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null), "png", file);
            } catch (IOException e) {
                createAlert("Error", "Image Save Failed", "There was a problem saving your image", AlertType.ERROR);
            }
        }
    }

    public static double prompt(String title, String headerText, String contentText, String defaultVal) {
        TextInputDialog dialog = new TextInputDialog(defaultVal); // default value
        dialog.setTitle(title);
        dialog.setHeaderText(headerText);
        dialog.setContentText(contentText);

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            try {
                return Double.parseDouble(result.get());
            } catch (NumberFormatException e) {
                AppUtils.createAlert("Invalid Input", "Invalid Number format!", "", AlertType.ERROR);
            }
        }
        return 0.0;
    }

    public static Color colorChooserPrompt() {
        Dialog<Color> dialog = new Dialog<>();
        dialog.setTitle("Choose a Color");

        ColorPicker colorPicker = new ColorPicker();

        VBox content = new VBox(50, colorPicker);
        content.setStyle("-fx-padding: 20;");
        dialog.getDialogPane().setContent(content);

        ButtonType okButton = new ButtonType("OK", ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(okButton, cancelButton);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == okButton) {
                return colorPicker.getValue();
            }
            return null;
        });

        Optional<Color> result = dialog.showAndWait();
        return result.orElse(null);
    }
}