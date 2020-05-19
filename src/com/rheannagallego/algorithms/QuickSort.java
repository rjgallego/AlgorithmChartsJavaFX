package com.rheannagallego.algorithms;

public class QuickSort {

    public void quicksort(int[] arr, int start, int end) {
        if (end - start < 2) {
            return;
        }
        int partitionIdx = partition(arr, start, end);

        quicksort(arr, start, partitionIdx);
        quicksort(arr, partitionIdx + 1, end);
    }

    private int partition(int[] arr, int start, int end) {
        int pivot = arr[start];
        int i = start;
        int j = end;
        while (i < j) {
            while (i < j && arr[--j] >= pivot ) { continue; }
            if (i < j) {
                arr[i] = arr[j];
            }
            while (i < j && arr[++i] <= pivot) { continue; }
            if (i < j) {
                arr[j] = arr[i];
            }
        }
        arr[i] = pivot;
        return i;
    }
}
