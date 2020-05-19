package com.rheannagallego.algorithms;

public class BubbleSort {
    static int[] arr = {20, 35, -15, 7, 55, 1, -22};

    public static void sort(int[] arr, int start, int end) {
        for(int i = arr.length - 1; i > 0; i--){
            for(int j = 0; j < i; j++){
                if(arr[j] > arr[j+1]){
                    swap(j, j+1);
                }
            }
        }
    }

    public static void swap(int i, int j){
        int tmp = arr[j];
        arr[j] = arr[i];
        arr[i] = tmp;
    }


    public static void main(String[] args) {
        sort(arr, 0, 0);
    }
}
