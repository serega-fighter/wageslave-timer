package com.serega.timer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.InputStream;

public class UIApplication extends Application {

    // Guaranteed to be non-null and initialized
    public static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        UIApplication.stage = stage;

        FXMLLoader loader = new FXMLLoader(UIApplication.class.getClassLoader().getResource("PrimaryView.fxml"));

        Parent root = loader.load();

        Scene scene = new Scene(root, Color.TRANSPARENT);
        scene.getStylesheets().add("style.css");
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setAlwaysOnTop(true);
        stage.setMaximized(false);
        stage.setOnCloseRequest(event -> System.exit(0));
        stage.setTitle("Hourglass");

        InputStream iconStream = UIApplication.class.getClassLoader().getResourceAsStream("hourglass.png");
        if (iconStream == null) {
            throw new RuntimeException("Icon not found");
        }
        stage.getIcons().add(new Image(iconStream));

        stage.show();
    }
}
