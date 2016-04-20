/**
 * Created by jacob on 11-4-2016.
 */

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;

import java.io.File;

public class Main extends Application {
    Stage window, grafiek1window, grafiek2window, grafiek3window, grafiek4window, grafiek5window, mapwindow;
    Database Database = new Database();

    Piechart_tasjesdiefstal TasjesDiefstalChart = new Piechart_tasjesdiefstal();
    Barchart_Tevredenheid BarchartTevredenheid = new Barchart_Tevredenheid();
    Linechart_Fietsendiefstal LinechartFietsendiefstal = new Linechart_Fietsendiefstal();
    Barchart_parkeerautomaten Piechartparkeerautomaten = new Barchart_parkeerautomaten();
    Barchart_Fietsendiefstal2006 BarchartFietsendiefstal = new Barchart_Fietsendiefstal2006();
    Map map = new Map();

    Scene menuscene, grafscene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;

        //main menu screen
        VBox mainmenu = new VBox(15); //layout van het menu

        Button grafbut = new Button("Grafieken"); // grafiek button
        grafbut.setOnAction(e -> window.setScene(grafscene)); // gaat naar het scherm met grafieken

        Button mapbut = new Button("Markten Kaart"); // google maps wordt pas geladen als er op de kaart knop geklikt wordt, dit voor veel betere performance bij opstarten
        mapbut.setOnAction(e ->{
            mapwindow = new Stage();
            mapwindow.getIcons().add(new Image("/path/to/stackoverflow.jpg"));
            mapwindow.setTitle("Map");
            mapwindow.setScene(map.getMapScene());
            mapwindow.showAndWait();
        }); // gaat naar het scherm met de map

        Button quit = new Button("Afsluiten"); // quit button
        quit.setOnAction(e -> window.close());

        mainmenu.getChildren().addAll(grafbut, mapbut, quit);
        mainmenu.setAlignment(Pos.CENTER);
        menuscene = new Scene(mainmenu, 1280, 720);

        // grafieken screen
        VBox grafVbox = new VBox(15); //layout

        Label grafiek1label = new Label("Tasjes diefstal met of zonder geweld");
        Button grafiek1 = new Button("Toon Grafiek"); // Piechart_tasjesdiefstal
        grafiek1.setOnAction(e -> {
            grafiek1window = new Stage();
            grafiek1window.setTitle("Tasjes Diefstal met en zonder geweld");
            grafiek1window.setScene(TasjesDiefstalChart.getSceneDiefstal(Database));
            grafiek1window.showAndWait();
        });

        Label grafiek2label = new Label("Tevredenheid van de bevolking per regio en jaar");
        Button grafiek2 = new Button("Toon Grafiek"); // Tevredenheid bevolking per regio
        grafiek2.setOnAction(e -> {
            grafiek2window = new Stage();
            grafiek2window.setTitle("Tevredenheid bevolking per regio");
            grafiek2window.setScene(BarchartTevredenheid.getSceneBarchart(Database));
            grafiek2window.showAndWait();
        });

        Label grafiek3label = new Label("Fietsendiefstallen per jaar");
        Button grafiek3 = new Button("Toon Grafiek"); // Linechart_Fietsendiefstal
        grafiek3.setOnAction(e -> {
            grafiek3window = new Stage();
            grafiek3window.setTitle("Fietsendiefstal");
            grafiek3window.setScene(LinechartFietsendiefstal.getSceneFietsendiefstal(Database));
            grafiek3window.showAndWait();
        });

        Label grafiek4label = new Label("Aantal parkeerautomaten per deelgemeente");
        Button grafiek4 = new Button("Toon Grafiek"); //
        grafiek4.setOnAction(e -> {
            grafiek4window = new Stage();
            grafiek4window.setTitle("Aantal parkeerautomaten");
            grafiek4window.setScene(Piechartparkeerautomaten.getSceneparkeerautomaten(Database));
            grafiek4window.showAndWait();
        });

        Label grafiek5label = new Label("Fietsendiefstallen per wijk in 2006");
        Button grafiek5 = new Button("Toon Grafiek"); //
        grafiek5.setOnAction(e -> {
            grafiek5window = new Stage();
            grafiek5window.setTitle("Aantal fietsendiefstallen per wijk in 2006");
            grafiek5window.setScene(BarchartFietsendiefstal.getSceneBarchartFietsendiefstal2006(Database));
            grafiek5window.showAndWait();
        });

        Button backbut1 = new Button("Terug naar menu"); // back to main menu button
        backbut1.setOnAction(e -> window.setScene(menuscene)); //action of the button

        grafVbox.getChildren().addAll(grafiek1label, grafiek1, grafiek2label, grafiek2, grafiek3label,  grafiek3, grafiek4label, grafiek4, grafiek5label, grafiek5, backbut1); // toevoegen aan het spel
        grafVbox.setAlignment(Pos.CENTER);
        grafscene = new Scene(grafVbox, 1280, 720);


        File f = new File("mainStyle.css");
        menuscene.getStylesheets().clear();
        grafscene.getStylesheets().clear();
        menuscene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/")); // voegt css toe aan beide menu schermen
        grafscene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));


        primaryStage.setTitle("Vision Project 3");
        primaryStage.setScene(menuscene);
        primaryStage.show(); //displays everything
    }
}
