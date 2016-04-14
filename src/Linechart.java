import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;


public class Linechart extends Application {
    Database db = new Database();
    String Query_ = "SELECT SUM(year_2006), SUM(year_2007), SUM(year_2008), SUM(year_2009), SUM(year_2011) FROM slachtofferschap_fietsendiefstal;";
    double data2006 = Double.parseDouble(db.execute(Query_, "SUM(year_2006)").get(0));
    double data2007 = Double.parseDouble(db.execute(Query_, "SUM(year_2007)").get(1));
    double data2008 = Double.parseDouble(db.execute(Query_, "SUM(year_2008)").get(2));
    double data2009 = Double.parseDouble(db.execute(Query_, "SUM(year_2009)").get(3));
    double data2011 = Double.parseDouble(db.execute(Query_, "SUM(year_2011)").get(4));

    @Override public void start(Stage stage) {
        stage.setTitle("Fietsendiefstal");
        final CategoryAxis xAxis = new CategoryAxis(); // x as
        final NumberAxis yAxis = new NumberAxis(); // y as
        xAxis.setLabel("Jaar");
        yAxis.setLabel("Hoeveelheid");
        //creating the chart
        final LineChart<String,Number> lineChart =
                new LineChart<String,Number>(xAxis,yAxis);

        lineChart.setTitle("Fietsendiefstal per jaar");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("Fietsendiefstal");
        //populating the series with data
        series.getData().add(new XYChart.Data("2006", data2006));
        series.getData().add(new XYChart.Data("2007", data2007));
        series.getData().add(new XYChart.Data("2008", data2008));
        series.getData().add(new XYChart.Data("2009", data2009));
        series.getData().add(new XYChart.Data("2011", data2011));


        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(series);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}