package com.rheannagallego.algorithms;

import com.rheannagallego.view.MainWindow;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import java.util.ArrayList;
import java.util.List;

public class MergeSortAnimation extends AlgorithmAnimation {
    private int[][] stackPanes;

    //algorithm to sort values input by user in the EnterFields
    //recursive algorithm, splits array in half until only 1 element in each split array, then merge those split arrays
    //together. Sorts from left to right.
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

        //merge the left and right array in the right order to sort
        merge(arr, start, mid, end);
    }

    private void merge(int[] arr, int start, int mid, int end){
        //if the elements on the left side are less than elements on right side,
        //then array is already sorted and nothing left to do
        if(arr[mid - 1] < arr[mid]){
            return;
        }
        int i = start, toIdx = start, j = mid, tempIndex = 0;

        //temp array to store sorted values, copied into original array at the end
        int[] tempArray = new int[end - start];

        //run through left array and right array comparing elements
        //copy values to temp array from smallest to largest
        while(i < mid && j < end){
            if(arr[i] < arr[j]){
                addTransition(toIdx++, i);
                tempArray[tempIndex++] = arr[i++];
            }else{
                addTransition(toIdx++, j);
                tempArray[tempIndex++] = arr[j++];
            }
        }
        //if there are leftover values when left array or right array are already sorted
        //then copy them directly into the temp array at the end
        while(i < mid){
            addTransition(toIdx++, i);
            tempArray[tempIndex++] = arr[i++];
        }
        while(j < end){
            addTransition(toIdx++, j);
            tempArray[tempIndex++] = arr[j++];
        }

        //copy the temp array back into the original array
        System.arraycopy(tempArray, 0, arr, start, tempArray.length);
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

    //initialize the order of the stack panes in the Chart Pane
    //using custom method as two methods can technically exist in the same location at the same time
    //need to use a 2 dimensional array to store the bar location and it's original index in the Chart Pane
    @Override
    void initializeSPOrder(){
        for(int i = 0; i < stackPanes.length; i++){
            //initialize bar locations - {barIdx, barLoc}
            stackPanes[i] = new int[]{i, i};
        }
    }

    //get the bar's original index in the Chart Pane from it's current location in the window
    private int getBarLoc(int loc){
        List<int[]> matches = new ArrayList<>();
        int[] max;

        //getting all of the bars at a current location (in case there are multiple in the same location in
        //in the window)
        for(int[] arr : stackPanes){
            //if the location of that bar is the same as bar to move
            if(arr[1] == loc){
                matches.add(arr);
            }
        }
        //if there is only 1 bar at a location, then return that bar
        if(matches.size() == 1){
            return matches.get(0)[0];
        }else{
            //if there are multiple bars in the same location, then get the bar with the largest value and return it
            max = matches.get(0);
            for(int[] arr : matches){
                if(getBarValue(arr[0]) > getBarValue(max[0]))
                    max = arr;
            }
        }
        return max[0];
    }

    //get the actual value of a bar based on it's index in the Chart Pane
    private int getBarValue(int idx){
        StackPane sp = (StackPane) MainWindow.chartPane.getChildren().get(idx);
        Text txt = (Text) sp.getChildren().get(1);
        return Integer.parseInt(txt.getText());
    }

    //custom method to create Translate Transition for the animation
    private void animateRecursive(int transitionIdx){

        int toLoc, fromLoc, variance;
        Integer[] currentTransition = transitions.get(transitionIdx);

        toLoc = currentTransition[0];
        fromLoc = currentTransition[1];
        variance = fromLoc - toLoc;

        //get the correct bar, specify the distance it should move horizontally
        //add a Translate Transition for that movement, then update that bar's location
        //in the stackPanes array
        if(variance != 0) {
            int idx = getBarLoc(fromLoc);
            StackPane movedBar = (StackPane) MainWindow.chartPane.getChildren().get(idx);

            addTranslateTransition(-variance, movedBar, 1);
            stackPanes[idx][1] = toLoc;
        }
    }

}
