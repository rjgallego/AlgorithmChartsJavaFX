package com.rheannagallego.view;

import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

public class EnterField extends TextField {
    //local instance of the field box so EnterFields can be added to it
    private FieldBox fieldBox;

    public EnterField(FieldBox fieldBox){
        this.fieldBox = fieldBox;

        //set each field initially to read only
        this.setEditable(false);
        this.getStyleClass().add("text-field");

        //add the listeners for when a user changes the value in a box
        addChangeListener();
        //add the listener for when a user clicks in a box
        addFocusedListener();
    }

    //if clicks away from a field, and the value in that field has been changed, then update that bar in the chart
    private void addFocusedListener(){
        this.focusedProperty().addListener((observableValue, s, t1) -> {
            if(!t1) {
                updateChartBar();
            }
        });
    }

    //if user adds a value to an empty field, then set the next field to editable
    //otherwise if user removes a value (empty string) then set the next field to read-only
    //this will force users to add values in sequential order so there are no empty fields between non-empty fields
    private void addChangeListener(){
        this.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if(!newValue.equals("")){
                setEditableField(true);
            }else{
                setEditableField(false);
            }
        });
    }

    //set the next field to editable or not editable based on what is entered in the parameter
    private void setEditableField(boolean isEditable){
        int index = fieldBox.getChildren().indexOf(this);

        //making sure it is not the last field, otherwise index+1 will throw IndexOutOfBounds exception
        if(index < 15) {
            EnterField nextField = fieldBox.getTextField(index + 1);
            nextField.setEditable(isEditable);
        }
    }

    //method to update the value of a bar based on user input into the EnterField
    private void updateChartBar(){
        String text = this.getText();
        try {
            //if user entered a value in the box
            if (!text.equals("")) {
                int textValue = Integer.parseInt(text);

                //if user has selected Radix Sort from the list of algorithms
                if (AlgorithmBox.getSelectedIndices().equals("Radix Sort")) {
                    //ensure user has entered a value between 10-50, then update the height of the bar in the
                    //Chart Pane based on what the user entered
                    if (textValue >= 10 && textValue <= 50) {
                        //set text to black in case it was previously red
                        this.setStyle("-fx-text-fill: black");
                        this.setTooltip(null);
                        MainWindow.chartPane.updateBar(Integer.parseInt(this.getText()), fieldBox.getChildren().indexOf(this) - 1);
                    } else {
                        //if user entered a value outside of 10-50, turn the text in that field red and
                        //add a tooltip to indicate the issue if the user hovers over it
                        Tooltip tooltip = new Tooltip("Value must be\nbetween 10-50");
                        this.setStyle("-fx-text-fill: red");
                        this.setTooltip(tooltip);
                    }
                } else {
                    //for all other algorithms besides Radix Sort
                    //ensure user has entered a value between 1-50, then update the height of the bar in the
                    //Chart Pane based on what the user entered
                    if (textValue >= 0 && textValue <= 50) {
                        this.setStyle("-fx-text-fill: black");
                        this.setTooltip(null);
                        MainWindow.chartPane.updateBar(Integer.parseInt(this.getText()), fieldBox.getChildren().indexOf(this) - 1);
                    } else {
                        //if user entered a value outside of 1-50, turn the text in that field red and
                        //add a tooltip to indicate the issue if the user hovers over it
                        Tooltip tooltip = new Tooltip("Value must be\nbetween 0-50");
                        this.setStyle("-fx-text-fill: red");
                        this.setTooltip(tooltip);
                    }
                }
            }
            //catch statement in case user entered any text that isn't an integer (letters, symbols, etc.)
            //will turn the text in the field red and add a tooltip to indicate the issue if user hovers over it
        }catch(NumberFormatException e){
            Tooltip tooltip = new Tooltip("Value must be\nan integer");
            this.setStyle("-fx-text-fill: red");
            this.setTooltip(tooltip);
        }
    }

}
