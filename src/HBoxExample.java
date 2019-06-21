/*
*
* https://www.tutorialspoint.com/javafx/layout_panes_hbox.htm
* https://docs.oracle.com/javase/8/javafx/layout-tutorial/builtin_layouts.htm#JFXLY102
 */

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;

public class HBoxExample extends Application {
    @Override
    public void start(Stage stage) {

        //creating a text field
        TextField textField = new TextField();

        //Creating the play button
        Button playButton = new Button("Play");

        //Creating the stop button
        Button stopButton = new Button("stop");

        //Instantiating the HBox class
        HBox hbox = new HBox();

        hbox.setStyle("-fx-background-color: #336699;");

        //Setting the space between the nodes of a HBox pane
        hbox.setSpacing(10);

        //Setting the margin to the nodes
        hbox.setMargin(textField, new Insets(20, 20, 20, 20));
        hbox.setMargin(playButton, new Insets(20, 20, 20, 20));
        hbox.setMargin(stopButton, new Insets(20, 20, 20, 20));

        //retrieving the observable list of the HBox
        ObservableList list = hbox.getChildren();

        //Adding all the nodes to the observable list (HBox)
        list.addAll(textField, playButton, stopButton);

        //Creating a scene object
        Scene scene = new Scene(hbox);

        //Setting title to the Stage
        stage.setTitle("Hbox Example");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();



    }

    public static FlowPane addFlowPane() {
        FlowPane flow = new FlowPane();
        flow.setPadding(new Insets(5, 0, 5, 0));
        flow.setVgap(4);
        flow.setHgap(4);
        flow.setPrefWrapLength(170); // preferred width allows for two columns
        flow.setStyle("-fx-background-color: DAE6F3;");

        ImageView pages;
            pages = new ImageView(
                    new Image(HBoxExample.class.getResourceAsStream(
                            "test.png")));
            flow.getChildren().add(pages);

        return flow;
    }

    public static void main(String args[]){

        launch(args);
    }
}