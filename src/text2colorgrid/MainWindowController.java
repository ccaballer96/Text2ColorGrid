/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text2colorgrid;

import matrix.linear.L_UDLRmatrix;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import matrix.Matrix;
import matrix.diagonal.D_UDLRmatrix;

/**
 * FXML Controller class
 *
 * @author ccaballer96
 */
public class MainWindowController implements Initializable {

    @FXML
    private Button goBtn;
    @FXML
    private TextArea textArea;
    @FXML
    private TextField timeField;

    private Stage mainWindow, matrixView;
    private String text, patternType;
    private double time;
    private VBox root;
    private HBox hbox;
    private Button backBtn;
    private GridPane grid;
    private Separator sep;
    private Matrix matrix;
    private int col, row;
    @FXML
    private ToggleGroup colorProperties;
    @FXML
    private ToggleGroup pattern;
    @FXML
    private RadioMenuItem l_udlrMenu;

    public void initData(Stage stage) {
        mainWindow = stage;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        goBtn.disableProperty().bind(textArea.textProperty().isEmpty().and(timeField.textProperty().isEmpty()));
    }

    @FXML
    private void runMatrix(ActionEvent event) {
        try {
            matrixView = new Stage();
            root = new VBox();
            root.setPadding(new Insets(10, 10, 10, 10));
            root.setSpacing(10);
            hbox = new HBox();
            hbox.setAlignment(Pos.CENTER_RIGHT);
            backBtn = new Button("Back");
            hbox.getChildren().add(backBtn);
            grid = new GridPane();
            sep = new Separator();
            buildMatrix();
            grid.setGridLinesVisible(true);
            root.getChildren().addAll(grid, sep, hbox);
            Scene scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight());
            matrixView.setScene(scene);
            matrixView.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void buildMatrix() {
        text = textArea.getText();
        time = Double.parseDouble(timeField.getText());
        double sqrt = Math.sqrt(text.length());
        row = (int) Math.round(sqrt);
        col = (int) Math.ceil(sqrt);
        patternType = pattern.getSelectedToggle().toString().substring(17, 23).toUpperCase();
        System.out.println(patternType);
        selectMatrix();
    }

    private void selectMatrix() {
        switch (patternType) {
            case "L_UDLR": {
                matrix = new L_UDLRmatrix(row, col, grid, text);
                matrix.fillMatrix();
            }
            case "D_UDLR": {
                matrix = new D_UDLRmatrix(row, col, grid, text);
                matrix.fillMatrix();
            }
        }
        //System.out.println(pattern.getSelectedToggle().toString().substring(17, 21));
    }
}
