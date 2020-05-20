package com.rheannagallego.view;

import com.rheannagallego.algorithms.*;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class AlgorithmBox extends VBox {

    private static ListView<String> algorithmList = new ListView<>();

    public AlgorithmBox(){
        this.getStyleClass().add("vbox");
        algorithmList.getStyleClass().add("list-view");

        initializeAlgorithms();
        this.getChildren().add(algorithmList);
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
       String selectedIndices = algorithmList.getSelectionModel().getSelectedItem();
       switch(selectedIndices){
           case "Bubble Sort":
               return new BubbleSortAnimation();
           case "Selection Sort":
               return new SelectionSortAnimation();
           case "Insertion Sort":
               break;
           case "Shell Sort":
               break;
           case "Merge Sort":
               break;
           case "Quick Sort":
               return new QuickSortAnimation();
           case "Counting Sort":
               break;
           case "Radix Sort":
               break;
           default:
               return null;
       }
       return null;
    }
}
