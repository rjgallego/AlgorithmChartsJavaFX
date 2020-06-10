package com.rheannagallego.algorithms;

public class MergeSort {
    public static void sort(int[] arr, int start, int end){
        if(end - start < 2){
            return;
        }
        int mid = (end + start) / 2;
        //sort left
        sort(arr, start, mid);
        //sort right
        sort(arr, mid, end);
        merge(arr, start, mid, end);
    }

    public static void merge(int[] arr, int start, int mid, int end){
        if(arr[mid - 1] < arr[mid]){
            return;
        }
        int i = start;
        int j = mid;
        int[] tempArray = new int[end - start];
        int tempIndex = 0, iVal = arr[i], jVal=arr[j];
        while(i < mid && j < end){
           if(arr[i] < arr[j]){
                tempArray[tempIndex++] = arr[i++];
            }else{
                tempArray[tempIndex++] = arr[j++];
            }
        }

        while(i < mid) tempArray[tempIndex++] = arr[i++];
        while(j < end) tempArray[tempIndex++] = arr[j++];

        System.arraycopy(tempArray, 0, arr, start, tempArray.length);
        printArray(arr);
    }

    public static void main(String[] args) {
        int[] arr = {20, 34, 5, 10, 40, 8, 2, 12, 19, 30};
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        sort(arr, 0, arr.length);
    }

    static void swap(int[] arr, int i, int j){
        int tmp = arr[j];
        arr[j] = arr[i];
        arr[i] = tmp;
    }

    static void printArray(int[] arr){
        for(int i : arr){
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
