/**
 * Created by jacob on 11-4-2016.
 */

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {
    Stage window1, window2, window3, window4, window5, mapwindow;
    Database Database = new Database();

    Piechart_tasjesdiefstal TasjesDiefstalChart = new Piechart_tasjesdiefstal();
    Barchart_Tevredenheid BarchartTevredenheid = new Barchart_Tevredenheid();
    Linechart_Fietsendiefstal LinechartFietsendiefstal = new Linechart_Fietsendiefstal();
    Piechart_parkeerautomaten Piechartparkeerautomaten = new Piechart_parkeerautomaten();
    Map map = new Map();

    Scene menuscene, grafscene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        window1 = primaryStage;

        window3 = new Stage();
        window4 = new Stage();
        window5 = new Stage();

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

        Button mapbut = new Button("Markten Kaart"); // market/parkeerautomaten button
        mapbut.setOnAction(e ->{
            mapwindow = new Stage();
            mapwindow.setTitle("Map");
            mapwindow.setScene(map.getMapScene());
            mapwindow.showAndWait();
        }); // gaat naar het scherm met de map

        Button quit = new Button("Afsluiten"); // quit button
        quit.setOnAction(e -> window1.close());

        mainmenu.getChildren().addAll(grafbut, mapbut, quit);
        mainmenu.setAlignment(Pos.CENTER);
        menuscene = new Scene(mainmenu, 1280, 720);

        // grafieken screen
        VBox grafVbox = new VBox(15); //layout

        Button grafiek1 = new Button("grafiek1"); // Piechart_tasjesdiefstal
        grafiek1.setOnAction(e -> {
            window2 = new Stage();
            window2.setTitle("Tasjes Diefstal met en zonder geweld");
            window2.setScene(TasjesDiefstalChart.getSceneDiefstal(Database));
            window2.showAndWait();
        });

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


        File f = new File("mainStyle.css");
        menuscene.getStylesheets().clear();
        grafscene.getStylesheets().clear();
        menuscene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        grafscene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));


        primaryStage.setTitle("Test project 3");
        primaryStage.setScene(menuscene);
        primaryStage.show(); //displays everything
    }
}
