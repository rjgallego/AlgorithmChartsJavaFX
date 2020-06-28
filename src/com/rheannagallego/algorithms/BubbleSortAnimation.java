package com.rheannagallego.algorithms;

public class BubbleSortAnimation extends AlgorithmAnimation{

    //algorithm to sort values input by user in the EnterFields
    //loops through array comparing each value to to it's right neighbor
    //if right neighbor > left, then swap the values, continue until sorted
    @Override
    public void startSort(int[] arr) {
        for(int i = arr.length - 1; i > 0; i--){
            for(int j = 0; j < i; j++){
                if(arr[j] > arr[j+1]){
                    swap(arr, j, j+1);

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
