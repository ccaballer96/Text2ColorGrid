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
public class L_UDLRmatrix extends Matrix {

    public L_UDLRmatrix(int r, int c, int colorOp, double time, GridPane grid, String txt, boolean rev) {
        super(r, c, colorOp, time, grid, txt, rev);
    }

    @Override
    public void fillMatrix() {
        try {
            Task<Void> task;
            task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    if (!reverse) {
                        for (int i = 0; i < COL; i++) {
                            for (int j = 0; j < ROW; j++) {
                                cell = (Pane) getNodeByRowColumnIndex(j, i);
                                pos = i * (COL - 1) + j;
                                fillCell();
                                Thread.sleep((long) time / DIM_MATRIX);
                            }
                        }
                    }
                    else{
                        for(int i = COL - 1; i >= 0; i--){
                            for(int j = ROW -1; j >= 0; j--){
                                cell = (Pane) getNodeByRowColumnIndex(j, i);
                                pos = i * (COL - 1) + j;
                                fillCell();
                                Thread.sleep((long) time / DIM_MATRIX);
                            }
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
    public Node getNodeByRowColumnIndex(int row, int col) {
        return super.getNodeByRowColumnIndex(row, col);
    }
}
