/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix.linear;

import matrix.Matrix;
import javafx.concurrent.Task;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author ccaballer96
 */
public class L_UDLRmatrix extends Matrix{
    private int pos;
    
    public L_UDLRmatrix(int r, int c, GridPane grid, String txt){
        super(r, c, grid, txt);
    }

    @Override
    public void fillMatrix() {
        try {
            Task<Void> task;
            task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    for (int i = 0; i < COL; i++) {
                        for (int j = 0; j < ROW; j++) {
                            cell = (Pane) getNodeByRowColumnIndex(j, i);
                            pos = i * (COL - 1) + j;
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
    }

    private void fillCell() {
        cell.setStyle("-fx-background-color: #7bc043");
    }
    
    @Override
    public Node getNodeByRowColumnIndex(int row, int col){
        return super.getNodeByRowColumnIndex(row, col);
    }
}
