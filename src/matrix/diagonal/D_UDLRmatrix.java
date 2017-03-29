/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix.diagonal;

import java.util.ArrayList;
import java.util.Collections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import matrix.Matrix;

/**
 *
 * @author ccaballer96
 */
public class D_UDLRmatrix extends Matrix {

    private int pos;
    private String colorHex;
    private ArrayList<Character> charList;

    public D_UDLRmatrix(int r, int c, int colorOp, double time, GridPane grid, String txt, boolean rev) {
        super(r, c, colorOp, time, grid, txt, rev);
    }

    @Override
    public void fillMatrix() {
        try {
            Task<Void> task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    int aux;
                    diffChars();
                    if (!reverse) {
                        for (int i = 0; i < ROW; i++) {
                            aux = i;
                            for (int j = 0; (j < COL) && (aux >= 0); j++, aux--) {
                                cell = (Pane) getNodeByRowColumnIndex(aux, j, grid);
                                pos = aux * (ROW - 1) + j;
                                fillCell();
                            }

                            if (COL == ROW) {
                                Thread.sleep((long) time / ((COL * 2) - 1));
                            } else {
                                Thread.sleep((long) time / (ROW * 2));
                            }
                        }
                        for (int j = 1; j < COL; j++) {
                            aux = j;
                            for (int i = ROW - 1; (aux < COL) && i >= 0; i--, aux++) {
                                cell = (Pane) getNodeByRowColumnIndex(i, aux, grid);
                                pos = i * (ROW - 1) + aux;
                                fillCell();
                            }
                            if (COL == ROW) {
                                Thread.sleep((long) time / ((COL * 2) - 1));
                            } else {
                                Thread.sleep((long) time / (ROW * 2));
                            }
                        }
                    }
                    else{
                        for(int j = COL-1; j >= 1; j--){
                            aux = j;
                            for (int i = ROW - 1; (aux < COL) && i >= 0; i--, aux++) {
                                cell = (Pane) getNodeByRowColumnIndex(i, aux, grid);
                                pos = i * (ROW - 1) + aux;
                                fillCell();
                            }
                            if (COL == ROW) {
                                Thread.sleep((long) time / ((COL * 2) - 1));
                            } else {
                                Thread.sleep((long) time / (ROW * 2));
                            }
                        }
                        for (int i = ROW-1; i >= 0; i--) {
                            aux = i;
                            for (int j = 0; (j < COL) && (aux >= 0); j++, aux--) {
                                cell = (Pane) getNodeByRowColumnIndex(aux, j, grid);
                                pos = aux * (ROW - 1) + j;
                                fillCell();
                            }

                            if (COL == ROW) {
                                Thread.sleep((long) time / ((COL * 2) - 1));
                            } else {
                                Thread.sleep((long) time / (ROW * 2));
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

    private void fillCell() throws Exception {
        char letter = text.charAt(pos);
        int index = charList.indexOf(letter);
        int color = (16777215 * index) / (numDiffChars() - 1);
        colorHex = Integer.toHexString(color);
        //System.out.println(colorHex);
        //System.err.println(color);
        while (colorHex.length() < 6) {
            colorHex = "0" + colorHex;
        }
        cell.setStyle("-fx-background-color: #" + colorHex);
    }

    private Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) throws Exception {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
            if (gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }

        return result;
    }

    private void diffChars() throws Exception {
        charList = new ArrayList<>();
        for (int i = 0; i < text.length(); i++) {
            if (!charList.contains(text.charAt(i))) {
                charList.add(text.charAt(i));
            }
        }
        Collections.sort(charList);
    }

    private int numDiffChars() throws Exception {
        return charList.size();
    }

}
