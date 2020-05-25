package com.rheannagallego.view;

import javafx.scene.layout.HBox;

public class ButtonBox extends HBox {

    public ButtonBox(FieldBox fieldBox){
        this.getChildren().add(new SortButton(fieldBox));
        this.getChildren().add(new ResetButton(fieldBox));
    }

}
