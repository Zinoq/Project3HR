/**
 * Created by jacob on 11-4-2016.
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    Stage window;
    Scene scene1, scene2;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;

        //main menu layout
        VBox mainmenu = new VBox(15);
        Button grafbut = new Button("Grafieken");
        Button mapbut = new Button("markten map");
        mapbut.setOnAction(e -> window.setScene(scene2));
        Button quit = new Button("quit");
        quit.setOnAction(e -> window.close());

        mainmenu.getChildren().addAll(grafbut, mapbut, quit);
        mainmenu.setAlignment(Pos.CENTER);
        scene1 = new Scene(mainmenu, 1280, 720);

        // markten map layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);

        //label days
        Label daylabel = new Label("kies de dag: ");
        GridPane.setConstraints(daylabel, 0, 0);

        //choisebox days
        ChoiceBox<String> choiceday = new ChoiceBox<>();
        choiceday.getItems().addAll("Maandag", "Dinsdag", "Woensdag", "Donderdag", "Vrijdag", "Zaterdag");
        GridPane.setConstraints(choiceday, 1, 0);
        choiceday.setValue("Maandag");

        //label markt
        Label marklabel = new Label("Welke markt zoekt u: ");
        GridPane.setConstraints(marklabel, 0, 1);

        //choisebox markt
        ChoiceBox<String> choicemarkt = new ChoiceBox<>();
        choicemarkt.getItems().addAll("Markt1", "Markt2", "Markt3", "Markt4", "Markt5");
        GridPane.setConstraints(choicemarkt, 1, 1);
        choicemarkt.setValue("Markt1");

        //radius label radius
        Label radlabel = new Label("Radius in meter: ");
        GridPane.setConstraints(radlabel, 0, 2);

        //radius input
        TextField radinput = new TextField();
        radinput.setPromptText("1337");
        GridPane.setConstraints(radinput, 1, 2);

        Button radConfirm = new Button("ok");
        GridPane.setConstraints(radConfirm, 1, 3);
        radConfirm.setOnAction(e -> isInt(radinput)); //action of the button

        Button backbut = new Button("Terug naar menu");
        GridPane.setConstraints(backbut, 0, 5);
        backbut.setOnAction(e -> window.setScene(scene1)); //action of the button

        grid.getChildren().addAll(daylabel, choiceday, marklabel, choicemarkt, radlabel, radinput, radConfirm, backbut);
        scene2 = new Scene(grid, 1280, 720); //application window

        primaryStage.setTitle("Test project 3");
        primaryStage.setScene(scene1);
        primaryStage.show(); //displays everything
    }

    private int isInt(TextField input) {
        try {
            int radius = Integer.parseInt(input.getText());
            System.out.println("radius = " + radius);
            return radius;
        } catch (NumberFormatException e) {
            System.out.println("error: input isn't an int");
            return 0;
        }
    }
}
