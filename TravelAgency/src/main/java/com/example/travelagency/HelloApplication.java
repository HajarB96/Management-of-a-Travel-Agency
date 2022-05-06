package com.example.travelagency;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
     private static Stage stg;
    @Override
    public void start(Stage primaryStage) throws Exception {
        stg = primaryStage;
        primaryStage.setResizable(false);
        Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("login.fxml"));
        primaryStage.setTitle("Travel Agency");
        primaryStage.setScene(new Scene(root, 520, 400));
        primaryStage.show();
    }

    public void changeScene(String fxml,int x,int y) throws IOException {
        Parent pane = (Parent)FXMLLoader.load(this.getClass().getResource(fxml));
        stg.setResizable(false);
        stg.setMinWidth(x);
        stg.setMinHeight(y);
        stg.centerOnScreen();
        stg.getScene().setRoot(pane);

    }

    public static void main(String[] args) {
        launch();
    }

}