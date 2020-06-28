package com.rheannagallego.view;

import com.rheannagallego.algorithms.AlgorithmAnimation;
import javafx.scene.control.Button;

public class ResetButton extends Button {

    AlgorithmAnimation algorithm;
    FieldBox fieldBox;
    SortButton sortButton;
    int[] arr;

    //button to reset the bars in the ChartPane to their original location so they can be sorted multiple times
    public ResetButton(FieldBox fieldBox){
        //local value of FieldBox so it can access the values entered in the fields
        this.fieldBox = fieldBox;

        //styling for hte button based from the css file
        this.setText("Reset Values");
        this.getStyleClass().add("button");

        //initially disable, user cannot reset values until they have been sorted
        this.setDisable(true);

        //when user presses button, run the resetBars method from the algorithm animation classes
        //then disable the button and re-enable the sort button
        this.setOnAction(actionEvent -> {
            arr = this.fieldBox.getEnterFieldValues();
            this.algorithm = AlgorithmBox.getAlgorithm();
            algorithm.resetBars(arr);
            this.setDisable(true);
            this.sortButton.setDisable(false);
        });
    }

    //set instance of Sort button so this class can enable/disable the button
    public void setSortButton(SortButton sortButton){
        this.sortButton = sortButton;
    }
}
