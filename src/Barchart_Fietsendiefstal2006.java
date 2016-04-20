import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;


public class Barchart_Fietsendiefstal2006 {

    public Barchart_Fietsendiefstal2006() {}

    public Scene getSceneBarchartFietsendiefstal2006(Database Database) {
        //chart
        String Query_ = "SELECT wijk, year_2006 FROM slachtofferschap fietsendiefstal GROUP BY wijk;";

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> barc = new BarChart<String, Number>(xAxis, yAxis);
        xAxis.setLabel("Wijken");
        yAxis.setLabel("Hoeveelheid");
        barc.setTitle("Fietsendiefstal per wijk in 2006");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("2006");
        series1.getData().add(new XYChart.Data("Stadsdriehoek/C.S. Kwartier", Double.parseDouble(Database.execute("SELECT wijk, year_2006 FROM slachtofferschap_fietsendiefstal WHERE wijk='Stadsdriehoek/C.S. Kwartier';", "year_2006").get(0))));
        series1.getData().add(new XYChart.Data("Oude Westen", Double.parseDouble(Database.execute("SELECT wijk, year_2006 FROM slachtofferschap_fietsendiefstal WHERE wijk='Oude Westen';","year_2006").get(0))));
        series1.getData().add(new XYChart.Data("Cool/Nieuwe Werk/Dijkzigt", Double.parseDouble(Database.execute("SELECT wijk, year_2006 FROM slachtofferschap_fietsendiefstal WHERE wijk='Cool/Nieuwe Werk/Dijkzigt';", "year_2006").get(0))));
        series1.getData().add(new XYChart.Data("Stadscentrum", Double.parseDouble(Database.execute("SELECT wijk, year_2006 FROM slachtofferschap_fietsendiefstal WHERE wijk='Stadscentrum'", "year_2006").get(0))));
        series1.getData().add(new XYChart.Data("Rotterdam", Double.parseDouble(Database.execute("SELECT wijk, year_2006 FROM slachtofferschap_fietsendiefstal WHERE wijk='Rotterdam';", "year_2006").get(0))));


        Scene scene = new Scene(barc, 1280, 720);
        barc.getData().addAll(series1);

        return scene;
    }
}
