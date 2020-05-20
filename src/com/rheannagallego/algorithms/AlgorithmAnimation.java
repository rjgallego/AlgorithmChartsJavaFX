package com.rheannagallego.algorithms;

import java.util.ArrayList;
import java.util.List;

public abstract class AlgorithmAnimation {
    List<Integer[]> transitions = new ArrayList();
    int[] spOrder;
    int spSize;

    public abstract void startSort(int[] arr);
    public abstract void playAnimation();

    final void swap(int[] arr, int i, int j){
        int tmp = arr[j];
        arr[j] = arr[i];
        arr[i] = tmp;
    }

    final void addTransition(int i, int j, int value) {
        transitions.add(new Integer[]{i, j, value});
    }

    final void initializeSPOrder(){
        spOrder = new int[spSize];
        for(int i = 0; i < spOrder.length; i++){
            spOrder[i] = i;
        }
    }
}
