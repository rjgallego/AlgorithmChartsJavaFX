package com.rheannagallego.algorithms;

import com.rheannagallego.view.MainWindow;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class CountingSortAnimation extends AlgorithmAnimation {
    @Override
    public void startSort(int[] arr) {
        int[] countingArray = new int[50];
        int index = 0;
        for(int i : arr){
            countingArray[i-1]++;
        }
        for(int i = 0; i < countingArray.length; i++){
            int currentCount = countingArray[i];
            if(currentCount > 0){
                for(int j = 0; j < currentCount; j++){
                    arr[index] = i+1;
                    addTransition(index++, i+1);
//                    System.out.println("Move bar value " + (i+1) + " to location " + (index-1));
                }
            }
        }
    }

    @Override
    public void playAnimation() {
        initializeSPOrder();
        for(int i = 0; i < transitions.size(); i++) {
            animateRecursive(i);
        }
        playSequentialTransition();
    }

    private int getBarIndex(int value){
        for(int index = 0; index < spOrder.length ; index++){
            if(spOrder[index] != -1) {
                StackPane bar = (StackPane) MainWindow.chartPane.getChildren().get(index);
                if (Integer.parseInt(((Text) bar.getChildren().get(1)).getText()) == value) {
                    spOrder[index] = -1;
                    return index;
                }
            }
        }
        return -1;
    }

    public void animateRecursive(int transitionIdx){
        int toLoc, fromLoc, variance;
        Integer[] currentTransition = transitions.get(transitionIdx);
        toLoc = currentTransition[0];
        fromLoc = getBarIndex(currentTransition[1]);
        variance = toLoc - fromLoc;

        StackPane movedBar = (StackPane) MainWindow.chartPane.getChildren().get(fromLoc);
//        ((Rectangle)movedBar.getChildren().get(0)).setFill(Color.NAVY);
        addTranslateTransition(variance, movedBar, 1);
    }
}
