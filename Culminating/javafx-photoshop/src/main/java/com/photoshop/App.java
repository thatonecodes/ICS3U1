package com.photoshop;

import java.io.IOException;

import com.photoshop.utils.Constants;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = loader.load();

        PrimaryController controller = loader.getController();
        controller.setStage(stage);

        scene = new Scene(root, 750, 750);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                controller.handleKeyPress(event);
            }
        });
        stage.setScene(scene);

        stage.setTitle(Constants.APP_NAME);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("assets/icon.png")));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}