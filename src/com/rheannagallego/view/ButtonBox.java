package com.rheannagallego.view;

import javafx.scene.layout.HBox;

//HBox that holds the 2 buttons to sort and reset values
public class ButtonBox extends HBox {
    ResetButton resetButton;
    SortButton sortButton;


    public ButtonBox(FieldBox fieldBox) {
        //adding style class from css file to the vbox
        this.getStyleClass().add("buttonbox");

        //initializing buttons
        sortButton = new SortButton(fieldBox);
        resetButton = new ResetButton(fieldBox);

        //adding the button styling from the css file
        this.sortButton.getStyleClass().add("button");
        this.resetButton.getStyleClass().add("button");

        //initializing the instance variable of the reset button to the desclared sort button
        //and vice versa so both button objects are able to access each other
        resetButton.setSortButton(sortButton);
        sortButton.setResetButton(resetButton);

        //adding buttons to the box to display
        this.getChildren().add(sortButton);
        this.getChildren().add(resetButton);
    }

}
