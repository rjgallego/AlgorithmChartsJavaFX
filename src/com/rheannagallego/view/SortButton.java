package com.rheannagallego.view;

import com.rheannagallego.algorithms.AlgorithmAnimation;
import com.rheannagallego.algorithms.QuickSortAnimation;
import javafx.scene.control.Button;
import java.util.List;

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
                if(algorithm != null) {
                    algorithm.sort(arr, 0, arr.length);
                    algorithm.playAnimation();
                }
            }
        );
    }
}
