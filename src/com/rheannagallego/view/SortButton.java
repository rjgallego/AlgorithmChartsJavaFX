package com.rheannagallego.view;

import com.rheannagallego.algorithms.AlgorithmAnimation;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class SortButton extends Button {

    AlgorithmAnimation algorithm;

    int[] arr;
    FieldBox fieldBox;
    ResetButton resetButton;

    public SortButton(FieldBox fieldBox){
        this.fieldBox = fieldBox;
        this.setDisable(false);

        this.setText("Sort values");
        this.getStyleClass().add("Button");

        this.setOnAction(actionEvent -> {
            arr = this.fieldBox.getEnterFieldValues();

            try {
                algorithm = AlgorithmBox.getAlgorithm();
                algorithm.setSPSize(arr.length);
                this.setDisable(true);

                algorithm.startSort(arr);
                algorithm.playAnimation();
                this.resetButton.setDisable(false);
            }catch(NullPointerException e){
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("You must pick an algorithm to sort");
                a.show();
            }
        });
    }

    public void setResetButton(ResetButton resetButton){
        this.resetButton = resetButton;
    }
}
