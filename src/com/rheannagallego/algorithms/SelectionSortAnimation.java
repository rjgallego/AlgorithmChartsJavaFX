package com.rheannagallego.algorithms;

public class SelectionSortAnimation extends AlgorithmAnimation{

    //algorithm to sort values input by user in the EnterFields
    //loops through array from right to left, for each value loop through the array again from left to right until
    //it reaches the current index on the left (current iteration of outer loop)
    //if a value is found that's larger than the current index on the left, then swap the bars
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
            //add transition to list to indicate bars being swapped & their to / from locations
            addTransition(largestIdx, i);
        }
    }

    //play the animation once the input array is sorted
    @Override
    public void playAnimation() {
        initializeSPOrder();

        //go through transitions stored in the sort method and add a Translate Transition for each entry
        for(int i = 0; i < transitions.size(); i++) {
            animateIterative(true, i);
        }
       playSequentialTransition();
    }

}
