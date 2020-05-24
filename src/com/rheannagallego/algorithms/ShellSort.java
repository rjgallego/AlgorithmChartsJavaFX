package com.rheannagallego.algorithms;

public class ShellSort {
    static int[] arr = {20, 35, -15, 7, 55, 1, -22};
    static int gap = arr.length / 2;
    static int newElement;

    public static void sort(int[] arr) {
        int j;
        while(gap > 0) {
            for (int i = gap; i < arr.length; i++) {
                newElement = arr[i];
                for(j = i - gap; j >= 0; j = j - gap) {
                    if (newElement < arr[j]) {
                        arr[j + gap] = arr[j];
                        arr[j] = newElement;
                        printArr();
                    }
                }
            }
            gap = gap / 2;
        }
    }

    public static void printArr(){
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        sort(arr);
    }
}
