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
    Database Database = new Database();
    Stage window1, window2, window3, window4, window5;

    Piechart_tasjesdiefstal TasjesDiefstalChart = new Piechart_tasjesdiefstal();
    Barchart_Tevredenheid BarchartTevredenheid = new Barchart_Tevredenheid();
    Linechart_Fietsendiefstal LinechartFietsendiefstal = new Linechart_Fietsendiefstal();
    Piechart_parkeerautomaten Piechartparkeerautomaten = new Piechart_parkeerautomaten();

    // we moeten in alle charts een constructor maken met eigenlijk alles daar in, zodat we hier een object kunnen maken en daarmee de scene kunnen aanroepen
    Scene menuscene, grafscene, mapscene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window1 = primaryStage;
        window2 = new Stage();
        window3 = new Stage();
        window4 = new Stage();
        window5 = new Stage();

        window2.setTitle("Tasjes Diefstal met en zonder geweld");
        window2.setScene(TasjesDiefstalChart.getSceneDiefstal(Database));
        window3.setTitle("Tevredenheid bevolking per regio");
        window3.setScene(BarchartTevredenheid.getSceneBarchart(Database));
        window4.setTitle("Fietsendiefstal");
        window4.setScene(LinechartFietsendiefstal.getSceneFietsendiefstal(Database));
        window5.setTitle("Aantal parkeerautomaten");
        window5.setScene(Piechartparkeerautomaten.getSceneparkeerautomaten(Database));


        //main menu screen
        VBox mainmenu = new VBox(15); //layout van het menu
        Button grafbut = new Button("Grafieken"); // grafiek button
        grafbut.setOnAction(e -> window1.setScene(grafscene)); // gaat naar het scherm met grafieken
        Button mapbut = new Button("markten map"); // market/parkeerautomaten button
        mapbut.setOnAction(e -> window1.setScene(mapscene)); // gaat naar het scherm met de map
        Button quit = new Button("quit"); // quit button
        quit.setOnAction(e -> window1.close());

        mainmenu.getChildren().addAll(grafbut, mapbut, quit);
        mainmenu.setAlignment(Pos.CENTER);
        menuscene = new Scene(mainmenu, 1280, 720);

        // grafieken screen
        VBox grafVbox = new VBox(15); //layout
        Button grafiek1 = new Button("grafiek1"); // Piechart_tasjesdiefstal
        grafiek1.setOnAction(e -> window2.showAndWait());
        Button grafiek2 = new Button("grafiek2"); // Tevredenheid bevolking per regio
        grafiek2.setOnAction(e -> window3.showAndWait());
        Button grafiek3 = new Button("grafiek3"); // Linechart_Fietsendiefstal
        grafiek3.setOnAction(e -> window4.showAndWait());
        Button grafiek4 = new Button("grafiek4"); //
        grafiek4.setOnAction(e -> window5.showAndWait());

        Button backbut1 = new Button("Terug naar menu"); // back to main menu button
        backbut1.setOnAction(e -> window1.setScene(menuscene)); //action of the button

        grafVbox.getChildren().addAll(grafiek1, grafiek2, grafiek3, grafiek4, backbut1); //, grafiek2, grafiek3, grafiek4
        grafVbox.setAlignment(Pos.CENTER);
        grafscene = new Scene(grafVbox, 1280, 720);

        // map screen
        GridPane grid = new GridPane(); // markten map layout
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);

        Label daylabel = new Label("kies de dag: "); // label days
        GridPane.setConstraints(daylabel, 0, 0);

        ChoiceBox<String> choiceday = new ChoiceBox<>(); // choisebox days
        choiceday.getItems().addAll("Maandag", "Dinsdag", "Woensdag", "Donderdag", "Vrijdag", "Zaterdag");
        GridPane.setConstraints(choiceday, 1, 0);
        choiceday.setValue("Maandag");

        Label marklabel = new Label("Welke markt zoekt u: "); // label markt
        GridPane.setConstraints(marklabel, 0, 1);

        ChoiceBox<String> choicemarkt = new ChoiceBox<>(); // choisebox markt
        choicemarkt.getItems().addAll("Markt1", "Markt2", "Markt3", "Markt4", "Markt5");
        GridPane.setConstraints(choicemarkt, 1, 1);
        choicemarkt.setValue("Markt1");

        Label radlabel = new Label("Radius in meter: "); // radius label radius
        GridPane.setConstraints(radlabel, 0, 2);

        TextField radinput = new TextField();// radius input
        radinput.setPromptText("1337");
        GridPane.setConstraints(radinput, 1, 2);

        Button radConfirm = new Button("ok"); // load map button
        GridPane.setConstraints(radConfirm, 1, 3);
        radConfirm.setOnAction(e -> isInt(radinput)); //action of the button

        Button backbut2 = new Button("Terug naar menu"); // back to main menu button
        GridPane.setConstraints(backbut2, 0, 5);
        backbut2.setOnAction(e -> window1.setScene(menuscene)); //action of the button

        grid.getChildren().addAll(daylabel, choiceday, marklabel, choicemarkt, radlabel, radinput, radConfirm, backbut2);
        mapscene = new Scene(grid, 1280, 720); //application window

        primaryStage.setTitle("Test project 3");
        primaryStage.setScene(menuscene);
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
