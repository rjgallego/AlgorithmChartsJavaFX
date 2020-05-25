package com.rheannagallego.algorithms;

import com.rheannagallego.view.MainWindow;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AlgorithmAnimation {
    List<Integer[]> transitions = new ArrayList();
    SequentialTransition sq = new SequentialTransition();
    int[] spOrder;
    int spSize;

    final void swap(int[] arr, int i, int j){
        int tmp = arr[j];
        arr[j] = arr[i];
        arr[i] = tmp;
    }

    final void addTransition(int i, int j) {
        transitions.add(new Integer[]{i, j});
    }

    final void initializeSPOrder(){
        spOrder = new int[spSize];
        for(int i = 0; i < spOrder.length; i++){
            spOrder[i] = i;
        }
    }

    final void animateIterative(boolean isVariance, int transitionIdx){
        int toLoc, fromLoc, variance;
        Integer[] currentTransition = transitions.get(transitionIdx);

        toLoc = currentTransition[0];
        fromLoc = currentTransition[1];
        if(isVariance) variance = fromLoc - toLoc;
        else variance = 1;

        StackPane firstBar = (StackPane) MainWindow.chartPane.getChildren().get(spOrder[toLoc]);
        StackPane secondBar = (StackPane) MainWindow.chartPane.getChildren().get(spOrder[fromLoc]);

        addTranslateTransition(variance, firstBar, 1);
        addTranslateTransition(variance, secondBar, -1);

        swap(spOrder, toLoc, fromLoc);
    }

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

    public void resetBars(int[] arr){
        SequentialTransition resetTransition = new SequentialTransition();

        int[] sortedArray = Arrays.stream(arr).sorted().toArray();
        List<Integer> sortedArrList = new ArrayList<>();
        for(int i : sortedArray) sortedArrList.add(i);

        for(int i = 0; i < arr.length; i++){
            StackPane currentPane = (StackPane) MainWindow.chartPane.getChildren().get(i);
            int variance = i - sortedArrList.indexOf(arr[i]);
            TranslateTransition tt = new TranslateTransition(Duration.millis(1000), currentPane);
            tt.setByX(25 * variance);
            tt.setAutoReverse(false);
            resetTransition.getChildren().add(tt);
        }
        resetTransition.play();
    }

    public abstract void startSort(int[] arr);
    public abstract void playAnimation();
    public abstract void resetTransitions();
}
