package com.rheannagallego.view;

import javafx.scene.layout.HBox;

public class ButtonBox extends HBox {

    public ButtonBox(FieldBox fieldBox){
//        this.getStyleClass().add("hbox");
        this.getChildren().add(new SortButton(fieldBox));
    }

}
