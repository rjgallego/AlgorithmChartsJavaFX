package com.rheannagallego.algorithms;

public class ShellSortAnimation extends AlgorithmAnimation {

    //algorithm to sort values input by user in the EnterFields
    //specify a gap (for this algorithm, it is half the length of the array), then for each iteration
    //use insertion sort to sort values separated by gap. With each iteration divide gap by 2 until it is 1 and run
    //a normal insertion sort

    @Override
    public void startSort(int[] arr) {
        sort(arr);
    }

    private void sort(int[] arr) {
        int j, newElement;
        int gap = arr.length / 2;

        while(gap > 0) {
            for (int i = gap; i < arr.length; i++) {
                newElement = arr[i];
                for(j = i - gap; j >= 0; j = j - gap) {
                    if (newElement < arr[j]) {
                        arr[j + gap] = arr[j];
                        arr[j] = newElement;

                        //add transition to list to indicate bars being swapped & their to / from locations
                        addTransition(j + gap, j);
                    }
                }
            }
            gap = gap / 2;
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
