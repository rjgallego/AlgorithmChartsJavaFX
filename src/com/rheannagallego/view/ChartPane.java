package com.rheannagallego.view;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

//main window at center of border pane to display the bar chart and sort the values
public class ChartPane extends HBox {

    //adding styling to the HBox from css file
    public ChartPane(){
        this.getStyleClass().add("hbox");
    }

    //creates a StackPane to display the values entered by the user in the EnterField as a vertical bar
    public void updateBar(int height, int index){
        //if user is adding a new value, logic to add a new bar
        //otherwise add a new bar to the end
        if(this.getChildren().size() <= index){
            Text text = new Text(Integer.toString(height));
            Rectangle rect = new Rectangle(20, height * 10);
            rect.setFill(Color.CORNFLOWERBLUE);

            StackPane stackPane = new StackPane();
            stackPane.setAlignment(Pos.BOTTOM_CENTER);
            stackPane.getChildren().addAll(rect, text);

            this.getChildren().add(stackPane);
        }else {
            StackPane currentPane = (StackPane) this.getChildren().get(index);
            ((Rectangle) currentPane.getChildren().get(0)).setHeight(height * 10);
            ((Text) currentPane.getChildren().get(1)).setText(Integer.toString(height));
        }
    }

}
