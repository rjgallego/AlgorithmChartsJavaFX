package com.rheannagallego.view;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class FieldBox extends HBox {
    private Label label = new Label("Enter Values Between 0-50:");

    public FieldBox(){
        this.label.setStyle("-fx-font-weight: bold");
        this.getStyleClass().add("hbox");
        this.getChildren().add(label);

        initializeTextFields();
    }

    private void initializeTextFields(){
        for(int i = 0; i < 15; i++){
            this.getChildren().add(new EnterField(this));
        }
        getTextField(1).setEditable(true);
    }

    public int[] getEnterFieldValues(){
        int[] arr = new int[getArrSize()];
        EnterField currentField;

        for(int i = 1; i < this.getChildren().size(); i++){
            currentField = getTextField(i);
            if(currentField.getText().equals("")) break;
            arr[i-1] = Integer.parseInt(currentField.getText());
        }

//        for(Node cell : this.getChildren()){
//            if(cell instanceof EnterField){
//                if(((EnterField) cell).getText().equals("")) break;
//
//                arr[i] = Integer.parseInt(((EnterField) cell).getText());
//                i++;
//            }
//        }
        return arr;
    }

    public EnterField getTextField(int loc){
        if(this.getChildren().get(loc) instanceof EnterField)
            return (EnterField) this.getChildren().get(loc);
        else
            return null;
    }

    private int getArrSize(){
        int size = 0;
        EnterField currentField;

        for(int i = 1; i < this.getChildren().size(); i++){
            currentField = getTextField(i);
            if(currentField.getText().equals("")) return size;
            size++;
        }
//        for(Node cell : this.getChildren()){
//            if(cell instanceof EnterField){
//                if(((EnterField) cell).getText().equals("")) return size;
//                size++;
//            }
//        }
        return size;
    }
}
