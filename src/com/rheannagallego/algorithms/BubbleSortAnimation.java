package com.rheannagallego.algorithms;

import com.rheannagallego.view.MainWindow;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class BubbleSortAnimation extends AlgorithmAnimation{

    int[] spOrder;
    int spSize;

    @Override
    public void sort(int[] arr, int start, int end) {
        spSize = arr.length;
        for(int i = arr.length - 1; i > 0; i--){
            for(int j = 0; j < i; j++){
                if(arr[j] > arr[j+1]){
                    swap(arr, j, j+1);
                    addTransition(j, j+1, arr[j]);
                }
            }
        }
    }

    @Override
    public void playAnimation() {
        SequentialTransition sq = new SequentialTransition();
        initializeSPOrder();

//        for(int i = 0; i < transitions.size(); i++){
//            System.out.println("Swap bar " + transitions.get(i)[0] + " with bar " + transitions.get(i)[1]);
//        }

        for(int i = 0; i < transitions.size(); i++) {

            Integer[] currentTransition = transitions.get(i);
            int toLoc = currentTransition[0];
            int fromLoc = currentTransition[1];

            StackPane firstBar = (StackPane) MainWindow.chartPane.getChildren().get(spOrder[toLoc]);
            StackPane secondBar = (StackPane) MainWindow.chartPane.getChildren().get(spOrder[fromLoc]);

            TranslateTransition tt = new TranslateTransition(Duration.millis(1000), firstBar);
            tt.setByX(25);
            tt.setAutoReverse(false);
            sq.getChildren().add(tt);

            TranslateTransition tt2 = new TranslateTransition(Duration.millis(1000), secondBar);
            tt2.setByX(-25);
            tt2.setAutoReverse(false);
            sq.getChildren().add(tt2);

            swap(spOrder, toLoc, fromLoc);

//            System.out.print("{ ");
//            for(int j = 0; j < spOrder.length; j++){
//                System.out.print(spOrder[j] + " ");
//            }
//            System.out.print("}");
//            System.out.println();
        }

        sq.play();

    }

    private void initializeSPOrder(){
        spOrder = new int[spSize];
        for(int i = 0; i < spOrder.length; i++){
            spOrder[i] = i;
        }
    }

}
