package com.rheannagallego.algorithms;

public class InsertionSort {
    static int[] arr = {20, 35, -15, 7, -22, 1, 21};
    static int newElement;

    public static void sort(int[] arr) {
        for(int i = 1; i < arr.length; i++){
            newElement = arr[i];
            for(int j = i-1; j >= 0; j--){
                if(arr[j] > newElement){
                    arr[j+1] = arr[j];
                    arr[j] = newElement;
                    printArr();
                }
            }

        }
    }

//    public static void swap(int i, int j){
//        int tmp = arr[j];
//        arr[j] = arr[i];
//        arr[i] = tmp;
//    }

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
