package com.rheannagallego.algorithms;

import com.rheannagallego.view.MainWindow;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* Superclass for all algorithm animations to consolidate common functionality */

public abstract class AlgorithmAnimation {

    //list used to track the movements of the bars throughout the algorithm
    //contains a 2-dim array, first value is the index where the bar should move
    //the second value is the current index of the bar
    List<Integer[]> transitions = new ArrayList<>();

    SequentialTransition sq = new SequentialTransition();

    //array used to keep track of the order of the bars in the animation as they start sorting
    int[] spOrder;

    int spSize;

    //swaps two values in an array
    final void swap(int[] arr, int i, int j){
        int tmp = arr[j];
        arr[j] = arr[i];
        arr[i] = tmp;
    }

    //add a transition indicating the movement of a bar from location j to location i
    final void addTransition(int i, int j) {
        transitions.add(new Integer[]{i, j});
    }

    //initialize the order of the StackPanes as they were entered in the fields
    void initializeSPOrder(){
        spOrder = new int[spSize];
        for(int i = 0; i < spOrder.length; i++){
            spOrder[i] = i;
        }
    }

    //define the transition for each bar movement stored in the transitions list
    final void animateIterative(boolean isVariance, int transitionIdx){
        int toLoc, fromLoc, variance;
        Integer[] currentTransition = transitions.get(transitionIdx);

        //bar moves from index fromLoc to index toLoc
        toLoc = currentTransition[0];
        fromLoc = currentTransition[1];

        //define variances between where the bar is now and where it is moving to
        //used as a factor for how many spaces of 25px the bar should move
        if(isVariance) variance = fromLoc - toLoc;
        else variance = 1;

        //get the specified bars being swapped from the Chart Pane window
        //Use their original index as specified in spOrder, their order may change but index is always the same
        //i.e. if bar moved from location 0 to location 3, need to get the 3rd entry in spOrder to get the correct bar
        StackPane firstBar = (StackPane) MainWindow.chartPane.getChildren().get(spOrder[toLoc]);
        StackPane secondBar = (StackPane) MainWindow.chartPane.getChildren().get(spOrder[fromLoc]);

        addTranslateTransition(variance, firstBar, 1);
        addTranslateTransition(variance, secondBar, -1);

        //swap the indices of the bars being swapped
        //will track which bar in the Chart Pane is at which location
        swap(spOrder, toLoc, fromLoc);
    }

    //defines the translate transition to be added to the sequence
    final void addTranslateTransition(int variance, StackPane bar, int direction){
        TranslateTransition tt = new TranslateTransition(Duration.millis(1000), bar);
        tt.setByX(25 * variance * direction);
        tt.setAutoReverse(false);
        sq.getChildren().add(tt);
    }

    public final void setSPSize(int spSize){
        this.spSize = spSize;
    }

    void playSequentialTransition(){
        sq.play();
    }

    //move bars back to their original positions so that user can run multiple sorts without restarting
    public void resetBars(int[] arr){
        int currentIndex, variance;
        SequentialTransition resetTransition = new SequentialTransition();

        //sort the original array and add to a list so we can get the index of the bar in sorted order
        int[] sortedArray = Arrays.stream(arr).sorted().toArray();
        List<Integer> sortedArrList = new ArrayList<>();
        for(int i : sortedArray) sortedArrList.add(i);

        for(int i = 0; i < arr.length; i++){
            //getting each bar in the chart pane
            StackPane currentPane = (StackPane) MainWindow.chartPane.getChildren().get(i);

            currentIndex = sortedArrList.indexOf(arr[i]);

            //find the difference between where the bar is as sorted and where it was at the start
            variance = i - currentIndex;

            //if bar has been reset, replace value with -1 so ArrayList will get correct bar in case of duplicate values
            sortedArrList.set(currentIndex, -1);

            //add translate transition to move bar to it's original location and add to the sequential transition
            TranslateTransition tt = new TranslateTransition(Duration.millis(1000), currentPane);
            tt.setByX(25 * variance);
            tt.setAutoReverse(false);
            resetTransition.getChildren().add(tt);
        }
        resetTransition.play();
    }

    //abstract methods to be implemented by each animation class
    public abstract void startSort(int[] arr);
    public abstract void playAnimation();
}
