package com.rheannagallego.algorithms;

import com.rheannagallego.view.MainWindow;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class CountingSortAnimation extends AlgorithmAnimation {

    //algorithm to sort values input by user in the EnterFields
    //uses counting array to store how many of each entered integer's are in the input array
    //then goes through counting array and adds that value back into original array the specified number of times
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

                    //add transition to list to indicate bar being moved and it's to/from location
                    addTransition(index++, i+1);
                }
            }
        }
    }

    //creates translate transition for each transition in the list
    //uses custom method to create transitions as bars are not being swapped
    @Override
    public void playAnimation() {
        initializeSPOrder();
        for(int i = 0; i < transitions.size(); i++) {
            animateRecursive(i);
        }
        playSequentialTransition();
    }

    //get the index (original location) of a specified bar in the Chart Pane based on it's value
    private int getBarIndex(int value){
        for(int index = 0; index < spOrder.length ; index++){
            if(spOrder[index] != -1) {
                StackPane bar = (StackPane) MainWindow.chartPane.getChildren().get(index);
                if (Integer.parseInt(((Text) bar.getChildren().get(1)).getText()) == value) {
                    //if bar is found, set that value in spOrder to -1 so no duplicate values are not picked twice
                    spOrder[index] = -1;
                    return index;
                }
            }
        }
        return -1;
    }

    //creates Translate Transition for each transition, moves the indicated bar from one location to it's sorted position
    //according to the algorithm
    public void animateRecursive(int transitionIdx){
        int toLoc, fromLoc, variance;
        Integer[] currentTransition = transitions.get(transitionIdx);
        toLoc = currentTransition[0];
        fromLoc = getBarIndex(currentTransition[1]);
        variance = toLoc - fromLoc;

        StackPane movedBar = (StackPane) MainWindow.chartPane.getChildren().get(fromLoc);
        addTranslateTransition(variance, movedBar, 1);
    }
}
