package com.rheannagallego.algorithms;

public class SelectionSort {
    static int[] arr = {20, 35, -15, 7, -22, 1, 55};
    static int largestIdx;

    public static void sort(int[] arr, int start, int end) {
        for(int i = arr.length - 1; i > 0; i--){
            largestIdx = i;
            for(int j = 0; j < i; j++){
                if(arr[j] > arr[largestIdx]){
                    largestIdx = j;
                }
            }
            swap(i, largestIdx);
        }
    }

    public static void swap(int i, int j){
        int tmp = arr[j];
        arr[j] = arr[i];
        arr[i] = tmp;
    }

    public static void printArr(){
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        sort(arr, 0, 0);
    }
}
