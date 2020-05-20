package com.rheannagallego.algorithms;

import com.rheannagallego.view.MainWindow;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class BubbleSortAnimation extends AlgorithmAnimation{

    @Override
    public void startSort(int[] arr) {
        spSize = arr.length;
        for(int i = arr.length - 1; i > 0; i--){
            for(int j = 0; j < i; j++){
                if(arr[j] > arr[j+1]){
                    swap(arr, j, j+1);
                    addTransition(j, j+1, arr[j]);
                }
            }
        }
    }

    @Override
    public void playAnimation() {
        SequentialTransition sq = new SequentialTransition();
        initializeSPOrder();

        for(int i = 0; i < transitions.size(); i++) {

            Integer[] currentTransition = transitions.get(i);
            int toLoc = currentTransition[0];
            int fromLoc = currentTransition[1];

            StackPane firstBar = (StackPane) MainWindow.chartPane.getChildren().get(spOrder[toLoc]);
            StackPane secondBar = (StackPane) MainWindow.chartPane.getChildren().get(spOrder[fromLoc]);

            TranslateTransition tt = new TranslateTransition(Duration.millis(1000), firstBar);
            tt.setByX(25);
            tt.setAutoReverse(false);
            sq.getChildren().add(tt);

            TranslateTransition tt2 = new TranslateTransition(Duration.millis(1000), secondBar);
            tt2.setByX(-25);
            tt2.setAutoReverse(false);
            sq.getChildren().add(tt2);

            swap(spOrder, toLoc, fromLoc);
        }

        sq.play();
    }

}
