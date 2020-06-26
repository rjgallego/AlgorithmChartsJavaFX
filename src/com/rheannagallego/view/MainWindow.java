package com.rheannagallego.view;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MainWindow extends BorderPane {
    public static ChartPane chartPane = new ChartPane();
    private FieldBox fieldBox = new FieldBox();
    private ButtonBox buttonBox = new ButtonBox(fieldBox);
    private AlgorithmBox algorithmBox = new AlgorithmBox(fieldBox);

    public MainWindow(){
        this.getStyleClass().add("pane");

        this.setTop(fieldBox);
        this.setCenter(chartPane);
        this.setBottom(buttonBox);
        this.setLeft(algorithmBox);
    }

    public FieldBox getFieldBox() {
        return this.fieldBox;
    }

    public ChartPane getChartPane(){ return MainWindow.chartPane; }
}
