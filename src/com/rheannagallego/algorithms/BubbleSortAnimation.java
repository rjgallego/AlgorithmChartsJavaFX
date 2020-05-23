package com.rheannagallego.algorithms;

import com.rheannagallego.view.MainWindow;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class BubbleSortAnimation extends AlgorithmAnimation{

    @Override
    public void startSort(int[] arr) {
        for(int i = arr.length - 1; i > 0; i--){
            for(int j = 0; j < i; j++){
                if(arr[j] > arr[j+1]){
                    swap(arr, j, j+1);
                    addTransition(j, j+1);
                }
            }
        }
    }

    @Override
    public void playAnimation() {
        initializeSPOrder();

        for(int i = 0; i < transitions.size(); i++) {
            animateIterative(false, i);
        }

        playSequentialTransition();
    }

}
