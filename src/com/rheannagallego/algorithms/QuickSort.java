package com.rheannagallego.algorithms;

public class QuickSort {

    public static void quicksort(int[] arr, int start, int end) {
        if (end - start < 2) {
            return;
        }
        int partitionIdx = partition(arr, start, end);

        quicksort(arr, start, partitionIdx);
        quicksort(arr, partitionIdx + 1, end);
    }

    private static int partition(int[] arr, int start, int end) {
        int pivot = arr[start];
        int i = start;
        int j = end;
        while (i < j) {
            while (i < j && arr[--j] >= pivot ) { continue; }
            if (i < j) {
                arr[i] = arr[j];
                printArr(arr);
            }
            while (i < j && arr[++i] <= pivot) { continue; }
            if (i < j) {
                arr[j] = arr[i];
                printArr(arr);
            }
        }
        arr[i] = pivot;
        printArr(arr);
        return i;
    }

    public static void main(String[] args) {
        int[] arr = {20, 35, 5, 10, 40, 8, 2};
        quicksort(arr, 0, 7);
    }

    private static void printArr(int[] arr){
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
