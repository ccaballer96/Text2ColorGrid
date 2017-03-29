/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text2colorgrid;

import java.util.concurrent.Semaphore;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author ccaballer96
 */
public class Text2ColorGrid extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Stage mainWindow = primaryStage;
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
            Parent root = (Parent) myLoader.load();
            myLoader.<MainWindowController>getController().initData(mainWindow);
            Scene scene = new Scene(root);
            mainWindow.setScene(scene);
            mainWindow.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
