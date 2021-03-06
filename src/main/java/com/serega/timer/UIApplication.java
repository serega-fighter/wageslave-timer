package com.serega.timer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
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

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setAlwaysOnTop(true);
        stage.setMaximized(false);
        stage.setOnCloseRequest(event -> System.exit(0));
        stage.setTitle("Wagelsave-Timer");
//        InputStream iconStream = UIApplication.class.getClassLoader().getResourceAsStream("icons/ulyp-logo-icon.png");
//        if (iconStream == null) {
//            throw new RuntimeException("Icon not found");
//        }
//        stage.getIcons().add(new Image(iconStream));

        stage.show();
    }
}
