package com.photoshop;

import java.io.IOException;
import com.photoshop.utils.AppUtils;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.util.Duration;

public class PrimaryController {
    @FXML
    private SplitPane usageTab;  
    @FXML
    private Button toggleButton; 
    @FXML
    private Label timeSpentLabel;
    @FXML
    private Label versionLabel;
    @FXML
    private Label openCountLabel;

    private long startTime = System.currentTimeMillis(); 

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void toggleUsageBtn() {
        boolean isVisible = usageTab.isVisible();
        usageTab.setVisible(!isVisible);
        toggleButton.setText(isVisible ? "▼ Show" : "▲ Hide");
        AppUtils utils = new AppUtils();

        //increment the count
        utils.incrementAppOpenCount();
        openCountLabel.setText("App Open Count: " + utils.getAppOpenCount());
        if (!isVisible){
            Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> {
                    // time spent (in seconds)
                    long elapsedTime = (System.currentTimeMillis() - startTime) / 1000;
                    timeSpentLabel.setText("Time Spent: " + elapsedTime + "s");
                })
            );
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
            versionLabel.setText("Version: " + utils.getVersionString());
        }
    }
}
