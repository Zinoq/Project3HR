import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.stage.Stage;

public class Barchart extends Application {
    Database Database2 = new Database();
    String Stadsdriehoek = "Stadsdriehoek/C.S. Kwartier";
    String OudeWesten = "Oude Westen";
    String Dijkzigt = "Cool/Nieuwe, Werk/Dijkzigt";
    String Stadscentrum = "Stadscentrum";
    String Rotterdam = "Rotterdam";
    String css = this.getClass().getResource("/BarChartStyle.css").toExternalForm();

    public Scene scene;

    @Override public void start(Stage stage) {

        stage.setTitle("Tevredenheid bevolking per regio");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = new BarChart<String, Number>(xAxis,yAxis);
        xAxis.setLabel("Wijken");
        yAxis.setLabel("Percentage");
        bc.setTitle("Tevredenheid bevolking per regio");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("2006");
        series1.getData().add(new XYChart.Data(Stadsdriehoek, Double.parseDouble(Database2.execute("SELECT year_2006 FROM tevredenheid_met_het_wonen_in_de_buurt WHERE wijk='Stadsdriehoek/C.S. Kwartier';", "year_2006").get(0))));
        series1.getData().add(new XYChart.Data(OudeWesten, Double.parseDouble(Database2.execute("SELECT year_2006 FROM tevredenheid_met_het_wonen_in_de_buurt WHERE wijk='Oude Westen';", "year_2006").get(1))));
        series1.getData().add(new XYChart.Data(Dijkzigt, Double.parseDouble(Database2.execute("SELECT year_2006 FROM tevredenheid_met_het_wonen_in_de_buurt WHERE wijk='Cool/Nieuwe Werk/Dijkzigt';", "year_2006").get(2))));
        series1.getData().add(new XYChart.Data(Stadscentrum, Double.parseDouble(Database2.execute("SELECT year_2006 FROM tevredenheid_met_het_wonen_in_de_buurt WHERE wijk='Stadscentrum';", "year_2006").get(3))));
        series1.getData().add(new XYChart.Data(Rotterdam, Double.parseDouble(Database2.execute("SELECT year_2006 FROM tevredenheid_met_het_wonen_in_de_buurt WHERE wijk='Rotterdam';", "year_2006").get(4))));

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("2007");
        series2.getData().add(new XYChart.Data(Stadsdriehoek, Double.parseDouble(Database2.execute("SELECT year_2007 FROM tevredenheid_met_het_wonen_in_de_buurt WHERE wijk='Stadsdriehoek/C.S. Kwartier';", "year_2007").get(5))));
        series2.getData().add(new XYChart.Data(OudeWesten, Double.parseDouble(Database2.execute("SELECT year_2007 FROM tevredenheid_met_het_wonen_in_de_buurt WHERE wijk='Oude Westen';", "year_2007").get(6))));
        series2.getData().add(new XYChart.Data(Dijkzigt, Double.parseDouble(Database2.execute("SELECT year_2007 FROM tevredenheid_met_het_wonen_in_de_buurt WHERE wijk='Cool/Nieuwe Werk/Dijkzigt';", "year_2007").get(7))));
        series2.getData().add(new XYChart.Data(Stadscentrum, Double.parseDouble(Database2.execute("SELECT year_2007 FROM tevredenheid_met_het_wonen_in_de_buurt WHERE wijk='Stadscentrum';", "year_2007").get(8))));
        series2.getData().add(new XYChart.Data(Rotterdam, Double.parseDouble(Database2.execute("SELECT year_2007 FROM tevredenheid_met_het_wonen_in_de_buurt WHERE wijk='Rotterdam';", "year_2007").get(9))));

        XYChart.Series series3 = new XYChart.Series();
        series3.setName("2008");
        series3.getData().add(new XYChart.Data(Stadsdriehoek, Double.parseDouble(Database2.execute("SELECT year_2008 FROM tevredenheid_met_het_wonen_in_de_buurt WHERE wijk='Stadsdriehoek/C.S. Kwartier';", "year_2008").get(10))));
        series3.getData().add(new XYChart.Data(OudeWesten, Double.parseDouble(Database2.execute("SELECT year_2008 FROM tevredenheid_met_het_wonen_in_de_buurt WHERE wijk='Oude Westen';", "year_2008").get(11))));
        series3.getData().add(new XYChart.Data(Dijkzigt, Double.parseDouble(Database2.execute("SELECT year_2008 FROM tevredenheid_met_het_wonen_in_de_buurt WHERE wijk='Cool/Nieuwe Werk/Dijkzigt';", "year_2008").get(12))));
        series3.getData().add(new XYChart.Data(Stadscentrum, Double.parseDouble(Database2.execute("SELECT year_2008 FROM tevredenheid_met_het_wonen_in_de_buurt WHERE wijk='Stadscentrum';", "year_2008").get(13))));
        series3.getData().add(new XYChart.Data(Rotterdam, Double.parseDouble(Database2.execute("SELECT year_2008 FROM tevredenheid_met_het_wonen_in_de_buurt WHERE wijk='Rotterdam';", "year_2008").get(14))));

        XYChart.Series series4 = new XYChart.Series();
        series4.setName("2009");
        series4.getData().add(new XYChart.Data(Stadsdriehoek, Double.parseDouble(Database2.execute("SELECT year_2009 FROM tevredenheid_met_het_wonen_in_de_buurt WHERE wijk='Stadsdriehoek/C.S. Kwartier';", "year_2009").get(15))));
        series4.getData().add(new XYChart.Data(OudeWesten, Double.parseDouble(Database2.execute("SELECT year_2009 FROM tevredenheid_met_het_wonen_in_de_buurt WHERE wijk='Oude Westen';", "year_2009").get(16))));
        series4.getData().add(new XYChart.Data(Dijkzigt, Double.parseDouble(Database2.execute("SELECT year_2009 FROM tevredenheid_met_het_wonen_in_de_buurt WHERE wijk='Cool/Nieuwe Werk/Dijkzigt';", "year_2009").get(17))));
        series4.getData().add(new XYChart.Data(Stadscentrum, Double.parseDouble(Database2.execute("SELECT year_2009 FROM tevredenheid_met_het_wonen_in_de_buurt WHERE wijk='Stadscentrum';", "year_2009").get(18))));
        series4.getData().add(new XYChart.Data(Rotterdam, Double.parseDouble(Database2.execute("SELECT year_2009 FROM tevredenheid_met_het_wonen_in_de_buurt WHERE wijk='Rotterdam';", "year_2009").get(19))));

        XYChart.Series series5 = new XYChart.Series();
        series5.setName("2011");
        series5.getData().add(new XYChart.Data(Stadsdriehoek, Double.parseDouble(Database2.execute("SELECT year_2011 FROM tevredenheid_met_het_wonen_in_de_buurt WHERE wijk='Stadsdriehoek/C.S. Kwartier';", "year_2011").get(20))));
        series5.getData().add(new XYChart.Data(OudeWesten, Double.parseDouble(Database2.execute("SELECT year_2011 FROM tevredenheid_met_het_wonen_in_de_buurt WHERE wijk='Oude Westen';", "year_2011").get(21))));
        series5.getData().add(new XYChart.Data(Dijkzigt, Double.parseDouble(Database2.execute("SELECT year_2011 FROM tevredenheid_met_het_wonen_in_de_buurt WHERE wijk='Cool/Nieuwe Werk/Dijkzigt';", "year_2011").get(22))));
        series5.getData().add(new XYChart.Data(Stadscentrum, Double.parseDouble(Database2.execute("SELECT year_2011 FROM tevredenheid_met_het_wonen_in_de_buurt WHERE wijk='Stadscentrum';", "year_2011").get(23))));
        series5.getData().add(new XYChart.Data(Rotterdam, Double.parseDouble(Database2.execute("SELECT year_2011 FROM tevredenheid_met_het_wonen_in_de_buurt WHERE wijk='Rotterdam';", "year_2011").get(24))));


        scene  = new Scene(bc,1200,800);
        scene.getStylesheets().add(css);
        bc.getData().addAll(series1, series2, series3, series4, series5);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
