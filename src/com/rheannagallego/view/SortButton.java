package com.rheannagallego.view;

import com.rheannagallego.algorithms.AlgorithmAnimation;
import javafx.scene.control.Button;

public class SortButton extends Button {

    AlgorithmAnimation algorithm;

    int[] arr;
    FieldBox fieldBox;

    public SortButton(FieldBox fieldBox){
        this.fieldBox = fieldBox;

        this.setText("Sort values");
        this.getStyleClass().add("Button");

        this.setOnAction(actionEvent -> {
                arr = this.fieldBox.getEnterFieldValues();

                algorithm = AlgorithmBox.getAlgorithm();
                algorithm.setSPSize(arr.length);
                if(algorithm != null) {
                    algorithm.startSort(arr);
                    algorithm.playAnimation();
                }
            }
        );
    }
}
