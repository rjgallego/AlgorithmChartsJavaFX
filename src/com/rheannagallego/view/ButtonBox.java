package com.rheannagallego.view;

import javafx.scene.layout.HBox;

public class ButtonBox extends HBox {
    ResetButton resetButton;
    SortButton sortButton;

    public ButtonBox(FieldBox fieldBox){
        this.getStyleClass().add("buttonbox12   16");
        sortButton = new SortButton(fieldBox);
        resetButton = new ResetButton(fieldBox);

        resetButton.setSortButton(sortButton);
        sortButton.setResetButton(resetButton);

        this.getChildren().add(sortButton);
        this.getChildren().add(resetButton);
    }

}
