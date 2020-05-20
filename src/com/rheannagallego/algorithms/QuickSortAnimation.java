package com.rheannagallego.algorithms;

import com.rheannagallego.view.MainWindow;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class QuickSortAnimation extends AlgorithmAnimation {

    @Override
    public void startSort(int[] arr) {
        sort(arr, 0, arr.length);
    }

    public void sort(int[] arr, int start, int end) {
        if (end - start < 2) {
            return;
        }
        int partitionIdx = partition(arr, start, end);

        sort(arr, start, partitionIdx);
        sort(arr, partitionIdx + 1, end);
    }

    private int partition(int[] arr, int start, int end) {

        int pivot = arr[start];
        int i = start;
        int j = end;
        while (i < j) {
            while (i < j && arr[--j] >= pivot ) { continue; }
            if (i < j) {
                arr[i] = arr[j];
                addTransition(i, j, arr[j]);
            }
            while (i < j && arr[++i] <= pivot) { continue; }
            if (i < j) {
                arr[j] = arr[i];
                addTransition(j, i, arr[i]);
            }
        }
        arr[i] = pivot;
        addTransition(i, start, arr[i]);
        return i;
    }

    @Override
    public void playAnimation(){

        SequentialTransition sq = new SequentialTransition();

        for(int i = 0; i < transitions.size(); i++){

            Integer[] currentTransition = transitions.get(i);
            int toLoc = currentTransition[0];
            int fromLoc = currentTransition[1];
            int value = currentTransition[2];
            StackPane movedPane = new StackPane();
            StackPane tmpPane;

            for(Node cell : MainWindow.chartPane.getChildren()){
                if(cell instanceof StackPane) {
                    tmpPane = (StackPane) cell;
                    if(((Text) tmpPane.getChildren().get(1)).getText().equals(Integer.toString(value))){
                        movedPane = tmpPane;
                    }
                }

            }

            TranslateTransition tt = new TranslateTransition(Duration.millis(1000), movedPane);

            tt.setByX(25 * (toLoc - fromLoc));
            tt.setAutoReverse(false);

            sq.getChildren().add(tt);
        }
        sq.play();
    }

}
