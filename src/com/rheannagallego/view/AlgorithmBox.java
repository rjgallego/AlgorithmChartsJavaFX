package com.rheannagallego.view;

import com.rheannagallego.algorithms.*;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;

public class AlgorithmBox extends VBox {
    private static ListView<String> algorithmList = new ListView<>();
    private static String selectedIndices = "";

    public AlgorithmBox(FieldBox fieldBox){

        this.getStyleClass().add("vbox");
        algorithmList.getStyleClass().add("list-view");

        initializeAlgorithms();
        this.getChildren().add(algorithmList);

        algorithmList.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            selectedIndices = newValue;
            if(selectedIndices.equals("Radix Sort")){
                for(Node node : fieldBox.getChildren()){
                    if(node instanceof EnterField) {
                        EnterField currentField = (EnterField) node;
                        if (!currentField.getText().isEmpty()) {
                            int value = Integer.parseInt(currentField.getText());
                            if (value < 10) {
                                Tooltip tooltip = new Tooltip("Value must be\nbetween 10-50");
                                currentField.setStyle("-fx-text-fill: red");
                                currentField.setTooltip(tooltip);
                            }
                        }
                    }
                }
                fieldBox.setLabel("Enter Values 10-50: ");
            }
            else{
                for(Node node : fieldBox.getChildren()){
                    if(node instanceof EnterField) {
                        node.setStyle("-fx-text-fill: black");
                    }
                }
                fieldBox.setLabel("Enter Values 1-50: ");
            }


        });
    }

    private void initializeAlgorithms(){

        algorithmList.getItems().add("Bubble Sort");
        algorithmList.getItems().add("Selection Sort");
        algorithmList.getItems().add("Insertion Sort");
        algorithmList.getItems().add("Shell Sort");
        algorithmList.getItems().add("Merge Sort");
        algorithmList.getItems().add("Quick Sort");
        algorithmList.getItems().add("Counting Sort");
        algorithmList.getItems().add("Radix Sort");
    }

    public static AlgorithmAnimation getAlgorithm(){

       switch(selectedIndices){
           case "Bubble Sort":
               return new BubbleSortAnimation();
           case "Selection Sort":
               return new SelectionSortAnimation();
           case "Insertion Sort":
               return new InsertionSortAnimation();
           case "Shell Sort":
               return new ShellSortAnimation();
           case "Merge Sort":
               return new MergeSortAnimation();
           case "Quick Sort":
               return new QuickSortAnimation();
           case "Counting Sort":
               return new CountingSortAnimation();
           case "Radix Sort":
                return new RadixSortAnimation();
           default:
               return null;
       }
    }

    public static String getSelectedIndices(){
        return selectedIndices;
    }
}
