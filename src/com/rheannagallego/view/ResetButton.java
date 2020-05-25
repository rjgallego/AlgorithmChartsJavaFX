package com.rheannagallego.view;

import com.rheannagallego.algorithms.AlgorithmAnimation;
import javafx.scene.control.Button;

public class ResetButton extends Button {

    AlgorithmAnimation algorithm;
    FieldBox fieldBox;
    int[] arr;

    public ResetButton(FieldBox fieldBox){
        this.fieldBox = fieldBox;
        this.setText("Reset Values");
        this.getStyleClass().add("button");

        this.setOnAction(actionEvent -> {
            arr = this.fieldBox.getEnterFieldValues();
            this.algorithm = AlgorithmBox.getAlgorithm();
            algorithm.resetBars(arr);
        });
    }
}
