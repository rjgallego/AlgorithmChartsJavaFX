package com.rheannagallego.algorithms;

import com.rheannagallego.view.MainWindow;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class SelectionSortAnimation extends AlgorithmAnimation{

    @Override
    public void startSort(int[] arr) {
        int largestIdx;

        for(int i = arr.length - 1; i > 0; i--){
            largestIdx = i;
            for(int j = 0; j < i; j++){
                if(arr[j] > arr[largestIdx]){
                    largestIdx = j;
                }
            }
            swap(arr, i, largestIdx);
            addTransition(largestIdx, i);
        }
    }

    @Override
    public void playAnimation() {
        initializeSPOrder();

        for(int i = 0; i < transitions.size(); i++) {
            animateIterative(true, i);
        }
       playSequentialTransition();
    }

    @Override
    public void resetTransitions() {
        for(int i = transitions.size() - 1; i >= 0; i--) {
            animateIterative(true, i);
        }
        playSequentialTransition();
        initializeSPOrder();
    }

}
