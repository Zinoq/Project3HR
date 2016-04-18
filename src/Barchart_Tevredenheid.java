import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Barchart_Tevredenheid {

    public Barchart_Tevredenheid() {}

    public Scene getSceneBarchart(Database Database) {

        //chart

        String Stadsdriehoek = "Stadsdriehoek/C.S. Kwartier";
        String OudeWesten = "Oude Westen";
        String Dijkzigt = "Cool/Nieuwe, Werk/Dijkzigt";
        String Stadscentrum = "Stadscentrum";
        String Rotterdam = "Rotterdam";

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> bc = new BarChart<String, Number>(xAxis, yAxis);
        xAxis.setLabel("Wijken");
        yAxis.setLabel("Percentage");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("2006");
        series1.getData().add(new XYChart.Data(Stadsdriehoek, Double.parseDouble(Database.execute("SELECT year_2006 FROM tevredenheid_met_het_wonen_in_de_buurt WHERE wijk='Stadsdriehoek/C.S. Kwartier';", "year_2006").get(0))));
        series1.getData().add(new XYChart.Data(OudeWesten, Double.parseDouble(Database.execute("SELECT year_2006 FROM tevredenheid_met_het_wonen_in_de_buurt WHERE wijk='Oude Westen';", "year_2006").get(0))));
        series1.getData().add(new XYChart.Data(Dijkzigt, Double.parseDouble(Database.execute("SELECT year_2006 FROM tevredenheid_met_het_wonen_in_de_buurt WHERE wijk='Cool/Nieuwe Werk/Dijkzigt';", "year_2006").get(0))));
        series1.getData().add(new XYChart.Data(Stadscentrum, Double.parseDouble(Database.execute("SELECT year_2006 FROM tevredenheid_met_het_wonen_in_de_buurt WHERE wijk='Stadscentrum';", "year_2006").get(0))));
        series1.getData().add(new XYChart.Data(Rotterdam, Double.parseDouble(Database.execute("SELECT year_2006 FROM tevredenheid_met_het_wonen_in_de_buurt WHERE wijk='Rotterdam';", "year_2006").get(0))));

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("2007");
        series2.getData().add(new XYChart.Data(Stadsdriehoek, Double.parseDouble(Database.execute("SELECT year_2007 FROM tevredenheid_met_het_wonen_in_de_buurt WHERE wijk='Stadsdriehoek/C.S. Kwartier';", "year_2007").get(0))));
        series2.getData().add(new XYChart.Data(OudeWesten, Double.parseDouble(Database.execute("SELECT year_2007 FROM tevredenheid_met_het_wonen_in_de_buurt WHERE wijk='Oude Westen';", "year_2007").get(0))));
        series2.getData().add(new XYChart.Data(Dijkzigt, Double.parseDouble(Database.execute("SELECT year_2007 FROM tevredenheid_met_het_wonen_in_de_buurt WHERE wijk='Cool/Nieuwe Werk/Dijkzigt';", "year_2007").get(0))));
        series2.getData().add(new XYChart.Data(Stadscentrum, Double.parseDouble(Database.execute("SELECT year_2007 FROM tevredenheid_met_het_wonen_in_de_buurt WHERE wijk='Stadscentrum';", "year_2007").get(0))));
        series2.getData().add(new XYChart.Data(Rotterdam, Double.parseDouble(Database.execute("SELECT year_2007 FROM tevredenheid_met_het_wonen_in_de_buurt WHERE wijk='Rotterdam';", "year_2007").get(0))));

        XYChart.Series series3 = new XYChart.Series();
        series3.setName("2008");
        series3.getData().add(new XYChart.Data(Stadsdriehoek, Double.parseDouble(Database.execute("SELECT year_2008 FROM tevredenheid_met_het_wonen_in_de_buurt WHERE wijk='Stadsdriehoek/C.S. Kwartier';", "year_2008").get(0))));
        series3.getData().add(new XYChart.Data(OudeWesten, Double.parseDouble(Database.execute("SELECT year_2008 FROM tevredenheid_met_het_wonen_in_de_buurt WHERE wijk='Oude Westen';", "year_2008").get(0))));
        series3.getData().add(new XYChart.Data(Dijkzigt, Double.parseDouble(Database.execute("SELECT year_2008 FROM tevredenheid_met_het_wonen_in_de_buurt WHERE wijk='Cool/Nieuwe Werk/Dijkzigt';", "year_2008").get(0))));
        series3.getData().add(new XYChart.Data(Stadscentrum, Double.parseDouble(Database.execute("SELECT year_2008 FROM tevredenheid_met_het_wonen_in_de_buurt WHERE wijk='Stadscentrum';", "year_2008").get(0))));
        series3.getData().add(new XYChart.Data(Rotterdam, Double.parseDouble(Database.execute("SELECT year_2008 FROM tevredenheid_met_het_wonen_in_de_buurt WHERE wijk='Rotterdam';", "year_2008").get(0))));

        XYChart.Series series4 = new XYChart.Series();
        series4.setName("2009");
        series4.getData().add(new XYChart.Data(Stadsdriehoek, Double.parseDouble(Database.execute("SELECT year_2009 FROM tevredenheid_met_het_wonen_in_de_buurt WHERE wijk='Stadsdriehoek/C.S. Kwartier';", "year_2009").get(0))));
        series4.getData().add(new XYChart.Data(OudeWesten, Double.parseDouble(Database.execute("SELECT year_2009 FROM tevredenheid_met_het_wonen_in_de_buurt WHERE wijk='Oude Westen';", "year_2009").get(0))));
        series4.getData().add(new XYChart.Data(Dijkzigt, Double.parseDouble(Database.execute("SELECT year_2009 FROM tevredenheid_met_het_wonen_in_de_buurt WHERE wijk='Cool/Nieuwe Werk/Dijkzigt';", "year_2009").get(0))));
        series4.getData().add(new XYChart.Data(Stadscentrum, Double.parseDouble(Database.execute("SELECT year_2009 FROM tevredenheid_met_het_wonen_in_de_buurt WHERE wijk='Stadscentrum';", "year_2009").get(0))));
        series4.getData().add(new XYChart.Data(Rotterdam, Double.parseDouble(Database.execute("SELECT year_2009 FROM tevredenheid_met_het_wonen_in_de_buurt WHERE wijk='Rotterdam';", "year_2009").get(0))));

        XYChart.Series series5 = new XYChart.Series();
        series5.setName("2011");
        series5.getData().add(new XYChart.Data(Stadsdriehoek, Double.parseDouble(Database.execute("SELECT year_2011 FROM tevredenheid_met_het_wonen_in_de_buurt WHERE wijk='Stadsdriehoek/C.S. Kwartier';", "year_2011").get(0))));
        series5.getData().add(new XYChart.Data(OudeWesten, Double.parseDouble(Database.execute("SELECT year_2011 FROM tevredenheid_met_het_wonen_in_de_buurt WHERE wijk='Oude Westen';", "year_2011").get(0))));
        series5.getData().add(new XYChart.Data(Dijkzigt, Double.parseDouble(Database.execute("SELECT year_2011 FROM tevredenheid_met_het_wonen_in_de_buurt WHERE wijk='Cool/Nieuwe Werk/Dijkzigt';", "year_2011").get(0))));
        series5.getData().add(new XYChart.Data(Stadscentrum, Double.parseDouble(Database.execute("SELECT year_2011 FROM tevredenheid_met_het_wonen_in_de_buurt WHERE wijk='Stadscentrum';", "year_2011").get(0))));
        series5.getData().add(new XYChart.Data(Rotterdam, Double.parseDouble(Database.execute("SELECT year_2011 FROM tevredenheid_met_het_wonen_in_de_buurt WHERE wijk='Rotterdam';", "year_2011").get(0))));


        Scene scene = new Scene(bc, 1280, 720);
        bc.getData().addAll(series1, series2, series3, series4, series5);

        return scene;
    }
}