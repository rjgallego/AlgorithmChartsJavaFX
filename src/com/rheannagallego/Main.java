package com.rheannagallego;

import com.rheannagallego.view.MainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private MainWindow mainWindow;

    @Override
    public void start(Stage primaryStage) throws Exception{
        mainWindow = new MainWindow();

        Scene scene = new Scene(mainWindow, 900, 600);
        scene.getStylesheets().add(this.getClass().getResource("Resources/styles.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Hello World");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
