package com.rheannagallego.algorithms;

import com.rheannagallego.view.MainWindow;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class InsertionSortAnimation extends AlgorithmAnimation {
    @Override
    public void startSort(int[] arr) {
        sort(arr);
    }

    private void sort(int[] arr) {
        int newElement;
        for(int i = 1; i < arr.length; i++){
            newElement = arr[i];
            for(int j = i-1; j >= 0; j--){
                if(arr[j] > newElement){
                    arr[j+1] = arr[j];
                    arr[j] = newElement;
                    addTransition(j, j+1);
                }
            }
        }
    }

    @Override
    public void playAnimation() {
//        SequentialTransition sq = new SequentialTransition();
//        int toLoc, fromLoc, variance;
        initializeSPOrder();

        for(int i = 0; i < transitions.size(); i++) {
            animateIterative(false, i);
//
//            Integer[] currentTransition = transitions.get(i);
//            toLoc = currentTransition[0];
//            fromLoc = currentTransition[1];
//
//            StackPane firstBar = (StackPane) MainWindow.chartPane.getChildren().get(spOrder[toLoc]);
//            StackPane secondBar = (StackPane) MainWindow.chartPane.getChildren().get(spOrder[fromLoc]);
//
//            TranslateTransition tt = new TranslateTransition(Duration.millis(1000), firstBar);
//            tt.setByX(25);
//            tt.setAutoReverse(false);
//            sq.getChildren().add(tt);
//
//            TranslateTransition tt2 = new TranslateTransition(Duration.millis(1000), secondBar);
//            tt2.setByX(-25);
//            tt2.setAutoReverse(false);
//            sq.getChildren().add(tt2);
//
//            swap(spOrder, toLoc, fromLoc);
        }
//        sq.play();
        playSequentialTransition();

    }
}
