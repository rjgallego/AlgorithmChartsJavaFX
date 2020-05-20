package com.rheannagallego.algorithms;

import com.rheannagallego.view.MainWindow;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class SelectionSortAnimation extends AlgorithmAnimation{

    @Override
    public void startSort(int[] arr) {
        spSize = arr.length;
        int largestIdx;

        for(int i = arr.length - 1; i > 0; i--){
            largestIdx = i;
            for(int j = 0; j < i; j++){
                if(arr[j] > arr[largestIdx]){
                    largestIdx = j;
                }
            }
            swap(arr, i, largestIdx);
            addTransition(i, largestIdx, arr[i]);
        }
    }

    @Override
    public void playAnimation() {
        SequentialTransition sq = new SequentialTransition();
        int toLoc, fromLoc, variance;
        initializeSPOrder();

        for(int i = 0; i < transitions.size(); i++) {

            Integer[] currentTransition = transitions.get(i);
            toLoc = currentTransition[0];
            fromLoc = currentTransition[1];
            variance = toLoc - fromLoc;

            StackPane firstBar = (StackPane) MainWindow.chartPane.getChildren().get(spOrder[toLoc]);
            StackPane secondBar = (StackPane) MainWindow.chartPane.getChildren().get(spOrder[fromLoc]);

            TranslateTransition tt = new TranslateTransition(Duration.millis(1000), firstBar);
            tt.setByX(-25 * variance);
            tt.setAutoReverse(false);
            sq.getChildren().add(tt);

            TranslateTransition tt2 = new TranslateTransition(Duration.millis(1000), secondBar);
            tt2.setByX(25 * variance);
            tt2.setAutoReverse(false);
            sq.getChildren().add(tt2);

            swap(spOrder, toLoc, fromLoc);
        }
        sq.play();
    }

}
