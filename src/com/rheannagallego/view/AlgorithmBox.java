package com.rheannagallego.view;

import com.rheannagallego.algorithms.*;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;

//VBox that shows the list of available algorithms to choose from in a ListView

public class AlgorithmBox extends VBox {
    private static ListView<String> algorithmList = new ListView<>();
    private static String selectedIndices = "";

    public AlgorithmBox(FieldBox fieldBox){

        //adding style classes from css file to the VBox and the ListView
        this.getStyleClass().add("vbox");
        algorithmList.getStyleClass().add("list-view");

        initializeAlgorithms();
        this.getChildren().add(algorithmList);

        //logic for when a user selects an algorithm from the list
        //if user chooses Radix Sort, then it changes the label stating available values to enter and turns values
        //in the EnterField outside of that range (10-50) red
        //other algorithms show red if label is not between 1-50
        //Also saves the selected algorithm name as a string to selectedIndices variable
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

    //add the names of the algorithms to the ListView to display
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

    //returns an instance of an algorithm class based on which one the user selects in the ListView
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

    //returns name of selected algorithm as a String
    public static String getSelectedIndices(){
        return selectedIndices;
    }
}
