package com.rheannagallego.view;

import com.rheannagallego.algorithms.AlgorithmAnimation;
import com.rheannagallego.algorithms.RadixSortAnimation;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

//button that starts the sort animation within the algorithm animation classes
public class SortButton extends Button {

    AlgorithmAnimation algorithm;

    int[] arr;
    FieldBox fieldBox;
    ResetButton resetButton;

    public SortButton(FieldBox fieldBox){
        //initialize instance of fieldBox so button can access values in the EnterFields
        this.fieldBox = fieldBox;
        //Button is always enabled until a sort is run (will re-enable when the Reset button is pushed)
        this.setDisable(false);

        //add styling for the button from the css file
        this.setText("Sort values");
        this.getStyleClass().add("Button");

        //logic when button is pushed
        this.setOnAction(actionEvent -> {
            //get the values in the EnterFields as an int array
            arr = this.fieldBox.getEnterFieldValues();
            boolean invalidValues = false;
            //get the selected algorithm from the AlgorithmBox ListView
            algorithm = AlgorithmBox.getAlgorithm();

            //loop through values in the EnterFields, if any are invalid then set the boolean flag
            //invalidValues to true to indicate invalid entries
            for(int i : arr){
                if(algorithm instanceof RadixSortAnimation){
                    if(i < 10 || i > 50){
                        invalidValues = true;
                    }
                }else {
                    if (i <= 0 || i > 50) {
                        invalidValues = true;
                    }
                }
            }

            //if user has entered a value out of range and tries to sort, an alert will pop-up indicating the issue
            //and sort will not run
            if(invalidValues){
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Invalid values entered");
                a.show();
            }else {
                try {
                    //set the number of values for the algorithm to sort
                    algorithm.setSPSize(arr.length);
                    //disable button until the Reset button is pressed
                    this.setDisable(true);

                    //start the sort for the selected algorithm, will sort the values entered in the EnterFields
                    algorithm.startSort(arr);
                    //play the animation to visualize the algorithm sort process
                    algorithm.playAnimation();
                    //enable the Reset button so user can reset the values once animation is finished
                    this.resetButton.setDisable(false);
                } catch (NullPointerException e) {
                    //catch in case user tries to select the Sort button without selecting an algorithm
                    //from the AlgorithmBox ListView, will pop-up with an alert window
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setContentText("You must pick an algorithm to sort");
                    a.show();
                }
            }
        });
    }

    //set instance of Reset button so this class can enable/disable the button
    public void setResetButton(ResetButton resetButton){
        this.resetButton = resetButton;
    }
}
