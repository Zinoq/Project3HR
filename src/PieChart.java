import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.*;
import javafx.scene.Group;

public class Piechart extends Application {
    Database Database = new Database();
    double Noord = Double.parseDouble(Database.execute("SELECT COUNT(*) FROM standard.parkeerautomaten WHERE Deelgem=\"Noord\";", "Count(*)").get(0));
    double Centrum = Double.parseDouble(Database.execute("SELECT COUNT(*) FROM standard.parkeerautomaten WHERE Deelgem=\"Centrum\";", "Count(*)").get(1));
    double Charlois = Double.parseDouble(Database.execute("SELECT COUNT(*) FROM standard.parkeerautomaten WHERE Deelgem=\"Charlois\";", "Count(*)").get(2));
    double Delfshaven = Double.parseDouble(Database.execute("SELECT COUNT(*) FROM standard.parkeerautomaten WHERE Deelgem=\"Delshaven\";", "Count(*)").get(3));
    double Feijenoord = Double.parseDouble(Database.execute("SELECT COUNT(*) FROM standard.parkeerautomaten WHERE Deelgem=\"Feijenoord\";", "Count(*)").get(4));
    double IJsselmonde =  Double.parseDouble(Database.execute("SELECT COUNT(*) FROM standard.parkeerautomaten WHERE Deelgem=\"Ijsselmonde\";", "Count(*)").get(5));
    double Kralingen = Double.parseDouble(Database.execute("SELECT COUNT(*) FROM standard.parkeerautomaten WHERE Deelgem=\"Kralingen/Crooswijk\";", "Count(*)").get(6));
    double PrinsAlexander = Double.parseDouble(Database.execute("SELECT COUNT(*) FROM standard.parkeerautomaten WHERE Deelgem=\"Prins Alexander\";", "Count(*)").get(7));


        @Override public void start(Stage stage) {
            Scene scene = new Scene(new Group());
            stage.setTitle("Test");
            stage.setWidth(800);
            stage.setHeight(800);

            ObservableList<PieChart.Data> pieChartData =
                    FXCollections.observableArrayList(
                            new PieChart.Data("Noord", Noord),
                            new PieChart.Data("Centrum",Centrum),
                            new PieChart.Data("Charlois", Charlois),
                            new PieChart.Data("Delfshaven", Delfshaven),
                            new PieChart.Data("Feijenoord", Feijenoord),
                            new PieChart.Data("IJsselmonde",IJsselmonde),
                            new PieChart.Data("Kralingen/Crooswijk", Kralingen),
                            new PieChart.Data("Prins Alexander", PrinsAlexander));
            final PieChart chart = new PieChart(pieChartData);
            chart.setTitle("Aantal parkeerautomaten per deelgemeente");

            ((Group) scene.getRoot()).getChildren().add(chart);
            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }
