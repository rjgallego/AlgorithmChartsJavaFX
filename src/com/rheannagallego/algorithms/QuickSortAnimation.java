package com.rheannagallego.algorithms;

import com.rheannagallego.view.MainWindow;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class QuickSortAnimation extends AlgorithmAnimation {
    List<Boolean> isPivot = new ArrayList<>();

    // algorithm to sort values input by user in the EnterFields
    // Recursive algorithm, starts with whole array and uses a pivot value (first value) and iterates through the array,
    // if value is < pivot, move it to the left of the pivot. If value on the left is > pivot,
    // move it to the right of the pivot. Call sort on again on array values left and on values right of the partition
    //until array is sorted
    @Override
    public void startSort(int[] arr) {
        initializeSPOrder();
        sort(arr, 0, arr.length);
    }

    //recursively sort, each time splitting array into left array and right array
    //splits based on where the pivot ends up in it's sorted location after calling the
    //partition method
    public void sort(int[] arr, int start, int end) {
        if (end - start < 2) {
            return;
        }
        int partitionIdx = partition(arr, start, end);

        sort(arr, start, partitionIdx);
        sort(arr, partitionIdx + 1, end);
    }

    private int partition(int[] arr, int start, int end) {

        int pivot = arr[start]; int startIdx = spOrder[start];
        int i = start;
        int j = end;
        while (i < j) {
            //if left side has not crossed right side
            //sort through left side until it hits a value smaller than the pivot
            //then swap with current index on left side and start sorting through left side
            while (i < j && arr[--j] >= pivot ) { continue; }
            if (i < j) {
                arr[i] = arr[j];
                //add transition to list to indicate bar being moved and it's to/from location
                addTransition(i, j, start, false);
                //indicate that this value is not the pivot value
                isPivot.add(false);
            }
            //if left side has not crossed right side
            //sort through right side until it hits a value that is greater than the pivot
            //then swap with current index on right side and start sorting through right side
            while (i < j && arr[++i] <= pivot) { continue; }
            if (i < j) {
                arr[j] = arr[i];
                //add transition to list to indicate bar being moved and it's to/from location
                addTransition(j, i, start, false);
                //indicate that this value is not the pivot value
                isPivot.add(false);
            }
        }

        //set the pivot at the current index of the left side, now in it's sorted location
        arr[i] = pivot;

        //add transition to list to indicate bar being moved and it's to/from location
        addTransition(i, startIdx, start, true);
        //indicate that this value is not the pivot value
        isPivot.add(true);

        return i;
    }

    //add a translate transition to the sequence
    //determine which bar is moving, and to which location, and specify the distance it should move horizontally
    private void addTransition(int to, int from, int start, boolean pivot){
        int variance;
        StackPane movedBar;

        //get the correct bar and change it's location in the spOrder array to track
        //it's location in the window
        if(pivot){
            variance = start - to;
            movedBar = (StackPane) MainWindow.chartPane.getChildren().get(from);
            spOrder[to] = from;
        }else{
            variance = from - to;
            movedBar = (StackPane) MainWindow.chartPane.getChildren().get(spOrder[from]);
            spOrder[to] = spOrder[from];
        }

        addTranslateTransition(-variance, movedBar, 1);
    }

    public void playAnimation() {
        playSequentialTransition();
    }
}
