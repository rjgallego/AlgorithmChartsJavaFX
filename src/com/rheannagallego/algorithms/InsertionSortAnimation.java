package com.rheannagallego.algorithms;

public class InsertionSortAnimation extends AlgorithmAnimation {

    //algorithm to sort values input by user in the EnterFields
    //iterate through array from left to right, if a value on left hand side is > than an element on the right side,
    //then swap them
    @Override
    public void startSort(int[] arr) {
        sort(arr);
    }

    private void sort(int[] arr) {
        int newElement;
        for(int i = 1; i < arr.length; i++){
            newElement = arr[i];
            for(int j = i-1; j >= 0; j--){
                if(arr[j] > newElement){
                    arr[j+1] = arr[j];
                    arr[j] = newElement;

                    //add transition to list to indicate bars being swapped & their to / from locations
                    addTransition(j, j+1);
                }
            }
        }
    }

    //play the animation once the input array is sorted
    @Override
    public void playAnimation() {
        initializeSPOrder();

        //go through transitions stored in the sort method and add a Translate Transition for each entry
        for(int i = 0; i < transitions.size(); i++) {
            animateIterative(false, i);
        }
        playSequentialTransition();

    }

}
