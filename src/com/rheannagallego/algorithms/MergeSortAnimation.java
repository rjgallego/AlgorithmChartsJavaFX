package com.rheannagallego.algorithms;

import com.rheannagallego.view.MainWindow;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class MergeSortAnimation extends AlgorithmAnimation {
    private int[][] stackPanes;

    @Override
    public void startSort(int[] arr) {
        sort(arr, 0, arr.length);
    }

    private void sort(int[] arr, int start, int end){
        stackPanes = new int[arr.length][2];
        if(end - start < 2){
            return;
        }
        int mid = (end + start) / 2;
        //sort left
        sort(arr, start, mid);
        //sort right
        sort(arr, mid, end);
        merge(arr, start, mid, end);
    }

    private void merge(int[] arr, int start, int mid, int end){
        if(arr[mid - 1] < arr[mid]){
            return;
        }
        int i = start, toIdx = start, j = mid, tempIndex = 0;
        int[] tempArray = new int[end - start];
        while(i < mid && j < end){
            if(arr[i] < arr[j]){
                addTransition(toIdx++, i);
                tempArray[tempIndex++] = arr[i++];
            }else{
                addTransition(toIdx++, j);
                tempArray[tempIndex++] = arr[j++];
            }
        }
        while(i < mid){
            addTransition(toIdx++, i);
            tempArray[tempIndex++] = arr[i++];
        }
        while(j < end){
            addTransition(toIdx++, j);
            tempArray[tempIndex++] = arr[j++];
        }
        System.arraycopy(tempArray, 0, arr, start, tempArray.length);
    }

    @Override
    public void playAnimation() {
        initializeSPOrder();
        for(int i = 0; i < transitions.size(); i++) {
            animateRecursive(i);
        }
        playSequentialTransition();
    }

    @Override
    void initializeSPOrder(){
        for(int i = 0; i < stackPanes.length; i++){
            //initialize bar locations - {barIdx, barLoc}
            stackPanes[i] = new int[]{i, i};
        }
    }

    private int getBarLoc(int loc){
        List<int[]> matches = new ArrayList<>();
        int[] max;
        for(int[] arr : stackPanes){
            //if the location of that bar is the same as bar to move
            if(arr[1] == loc){
                matches.add(arr);
            }
        }
        if(matches.size() == 1){
            return matches.get(0)[0];
        }else{
            max = matches.get(0);
            for(int[] arr : matches){
                if(getBarValue(arr[0]) > getBarValue(max[0]))
                    max = arr;
            }
        }
        return max[0];
    }

    private int getBarValue(int idx){
        StackPane sp = (StackPane) MainWindow.chartPane.getChildren().get(idx);
        Text txt = (Text) sp.getChildren().get(1);
        return Integer.parseInt(txt.getText());
    }

    private void animateRecursive(int transitionIdx){

        int toLoc, fromLoc, variance;
        Integer[] currentTransition = transitions.get(transitionIdx);

        toLoc = currentTransition[0];
        fromLoc = currentTransition[1];
        variance = fromLoc - toLoc;

        if(variance != 0) {
            int idx = getBarLoc(fromLoc);
            StackPane movedBar = (StackPane) MainWindow.chartPane.getChildren().get(idx);

            addTranslateTransition(-variance, movedBar, 1);
            stackPanes[idx][1] = toLoc;
        }
    }
}
