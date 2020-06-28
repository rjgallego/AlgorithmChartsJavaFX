package com.rheannagallego.view;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

//box to hold the EnterField (text fields) where users can enter values to be sorted

public class FieldBox extends HBox {
    //label to indicate the range that can be entered
    private static Label label = new Label("Enter Values 1-50: ");

    public FieldBox(){
        //adding styling to the hbox and label
        label.setStyle("-fx-font-weight: bold");
        this.getStyleClass().add("hbox");
        this.getChildren().add(label);

        initializeTextFields();
    }

    //initializes 15 empty text fields within the FieldBox
    //set only the first field to editable, rest are read-only by default
    private void initializeTextFields(){
        for(int i = 0; i < 15; i++){
            this.getChildren().add(new EnterField(this));
        }
        getTextField(1).setEditable(true);
    }

    //get the values that user has entered into the EnterFields, and return values as an int array
    public int[] getEnterFieldValues(){
        //initialize the output array with the size equal to the amount of values entered by user (between 1-15)
        int[] arr = new int[getArrSize()];
        EnterField currentField;

        //loop through the EnterFields within this box until it reaches the end or finds an empty field
        //add value to the output array
        //starting at 1 as FieldBox child at index 1 is the label
        for(int i = 1; i < this.getChildren().size(); i++){
            currentField = getTextField(i);
            if(currentField.getText().equals("")) break;
            arr[i-1] = Integer.parseInt(currentField.getText());
        }

        return arr;
    }

    //method to retrieve the text within the EnterField at the specified index
    public EnterField getTextField(int loc){
        if(this.getChildren().get(loc) instanceof EnterField)
            return (EnterField) this.getChildren().get(loc);
        else
            return null;
    }

    //looks through the EnterFields until it finds an empty fields, number of non-empty fields found is the size of the
    //array
    private int getArrSize(){
        int size = 0;
        EnterField currentField;

        for(int i = 1; i < this.getChildren().size(); i++){
            currentField = getTextField(i);
            if(currentField.getText().equals("")) return size;
            size++;
        }
        return size;
    }

    //set the text for the label (changes for Radix Sort)
    public void setLabel(String s){
        label.setText(s);
    }
}
