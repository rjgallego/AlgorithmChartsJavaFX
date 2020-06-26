package com.rheannagallego.algorithms;

public class RadixSort {
    public static void sort(int[] arr) {
        int n = arr.length;
        for (int j = 1; j <= 2; j++) {
            int[] countingArray = new int[10];
            for (int i = 0; i < arr.length; i++) {
                countingArray[getDigit(arr[i], j)]++;
            }
            for (int i = 1; i < countingArray.length; i++) {
                countingArray[i] += countingArray[i-1];
            }

            int[] tmp = new int[arr.length];
            for (int k = n - 1; k >= 0; k--) {
                tmp[--countingArray[getDigit(arr[k], j)]] = arr[k];
            }
            System.arraycopy(tmp, 0, arr, 0, arr.length);
            printArray(arr);
        }

    }

    public static void main(String[] args) {
        int[] arr = {47, 25, 45, 36, 13, 30, 17, 22, 15, 34, 19, 29};
        sort(arr);
    }

    private static int getDigit(int number, int index){
        return (int) (number / Math.pow(10, index-1)) % 10;
    }

    private static void printArray(int[] arr){
        for(int i : arr){
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
