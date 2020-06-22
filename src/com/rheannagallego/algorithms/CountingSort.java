package com.rheannagallego.algorithms;

public class CountingSort {
    public static void sort(int[] arr) {
        int[] countingArray = new int[50];
        int index = 0;
        for(int i : arr){
            countingArray[i-1]++;
        }
        for(int i = 0; i < countingArray.length; i++){
            int currentCount = countingArray[i];
            if(currentCount > 0){
                for(int j = 0; j < currentCount; j++){
                    arr[index++] = i+1;
                    printArray(arr);
                }
            }
        }
        printArray(arr);
    }

    public static void main(String[] args) {
        int[] arr = {2, 5, 9, 8, 2, 8, 7, 10, 4, 3, 50};
        sort(arr);
    }

    private static void printArray(int[] arr){
        for(int i : arr){
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
