/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix;

import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author ccaballer96
 */
public abstract class Matrix {
    
    protected final int ROW, COL, DIM_CELL, DIM_MATRIX;
    protected boolean reverse;
    protected int pos, colorOp;
    protected double time;
    protected String text;
    protected GridPane grid;
    protected Pane cell;
    
    public Matrix(int r, int c, int colorOp, double time, GridPane grid, String txt, boolean rev){
        ROW = r; COL = c; this.grid = grid;
        text = txt; reverse = rev;
        this.colorOp = colorOp;
        this.time = time;
        DIM_MATRIX = COL * ROW;
        if(50*ROW > 300) DIM_CELL = 300 / Math.max(ROW, COL);
        else DIM_CELL = 50;
        for(int i = 0; i < ROW; i++){
            for(int j = 0; j < COL; j++){
                cell = new Pane();
                cell.setPrefSize(DIM_CELL, DIM_CELL);
                grid.add(cell, j, i);
            }
        }
    }
    
    public int getDimension(){
        return DIM_MATRIX;
    }
    
    public GridPane getMatrix(){
        return grid;
    }
    
    protected Node getNodeByRowColumnIndex(int row, int col){
        Node result = null;
        ObservableList<Node> childrens = grid.getChildren();
        for (Node node : childrens) {
            if (grid.getRowIndex(node) == row && grid.getColumnIndex(node) == col) {
                result = node;
                break;
            }
        }
        return result;
    }
    
    public abstract void fillMatrix();
    
    /*private void fillCell() {
        cell.setStyle("-fx-background-color: #7bc043");
    }*/
    
    /*public void fillMatrix() {
        try {
            Task<Void> task;
            task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    for (int i = 0; i < ROW; i++) {
                        for (int j = 0; j < COL; j++) {
                            cell = (Pane) getNodeByRowColumnIndex(i, j);
                            pos = i * (ROW - 1) + j;
                            fillCell();
                            Thread.sleep(3000 / DIM_MATRIX);
                        }
                    }
                    return null;
                }
            };
            Thread th = new Thread(task);
            th.setDaemon(true);
            th.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
