package com.rheannagallego.algorithms;

import com.rheannagallego.view.MainWindow;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class RadixSortAnimation extends AlgorithmAnimation{
    //array to track the location of each bar as it moves during the animation
    private int[][] stackPanes;

    //algorithm to sort values input by user in the EnterFields
    //sorts the values in order based on the 1's place, then resorts again based on the 10's place
    //values entered for this algorithm must have 2 digits (between 10-50).
    @Override
    public void startSort(int[] arr) {
        stackPanes = new int[arr.length][2];

        //initialize the stack panes in their current order as entered in the Enter Fields
        //Must use a 2 dimensional array for this algorithm as two bars can be in the same location at the same time
        //array will track the bar (value) and it's current location
        for(int i = 0; i < arr.length; i++){
            stackPanes[i] = new int[]{arr[i], i};
        }

        //use the counting sort algorithm to track the number of values between 0-9 that are in the 1's place and
        // the 10's place
        int n = arr.length;
        for (int j = 1; j <= 2; j++) {
            int[] countingArray = new int[10];
            for (int value : arr) {
                countingArray[getDigit(value, j)]++;
            }

            //run through the counting array again and indicate the number of instances of each value
            //exist up to that value
            //i.e. if the array is [0 0 1 0 1 2 0 1 0 0], the change it to [0 0 1 1 2 4 4 5 5 5]
            for (int i = 1; i < countingArray.length; i++) {
                countingArray[i] += countingArray[i-1];
            }

            //loop through the counting array backwards (to make the algorithm stable), get the 1's or 10's digit,
            //place the correct value with that digit from the original array to a temp array and add a transition
            //for the moved bar
            int[] tmp = new int[arr.length];
            for (int k = n - 1; k >= 0; k--) {
                tmp[--countingArray[getDigit(arr[k], j)]] = arr[k];
                addTransition(countingArray[getDigit(arr[k], j)], arr[k]);
            }
            //copy temp array back into the original array to continue the sort
            System.arraycopy(tmp, 0, arr, 0, arr.length);
        }
    }

    //gets the digit at the 1's or 10's value, depending on the iteration
    private int getDigit(int number, int index){
        return (int) (number / Math.pow(10, index-1)) % 10;
    }

    //creates translate transition for each transition in the list
    //uses custom method to create transitions as bars are not being swapped
    @Override
    public void playAnimation() {
        initializeSPOrder();
        for(int i = 0; i < transitions.size(); i++) {
            animateRecursive(i);
        }
        playSequentialTransition();
    }

    //get the original index of the bar in the Chart Pane based on the value of that bar
    private int getBarIndex(int value){
        for(int index = 0; index < spOrder.length ; index++){
            StackPane bar = (StackPane) MainWindow.chartPane.getChildren().get(index);
            if (Integer.parseInt(((Text) bar.getChildren().get(1)).getText()) == value) {
                return index;
            }

        }
        return -1;
    }

    //add a translate transition for each transition stored in the array
    public void animateRecursive(int transitionIdx){
        int toLoc, variance;
        int fromLoc = 0;
        Integer[] currentTransition = transitions.get(transitionIdx);
        toLoc = currentTransition[0];
        //get the from location from the stack panes and update with the that bar's new location
        for(int[] i : stackPanes){
            if(i[0] == currentTransition[1]){
                fromLoc = i[1];
                i[1] = toLoc;
                break;
            }
        }
        variance = toLoc - fromLoc;

        StackPane movedBar = (StackPane) MainWindow.chartPane.getChildren().get(getBarIndex(currentTransition[1]));
        addTranslateTransition(variance, movedBar, 1);
    }


}
