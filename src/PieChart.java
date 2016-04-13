import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.chart.*;
import javafx.scene.Group;

import java.io.File;

public class Piechart extends Application {
    Database Database = new Database();
    double Noord = Double.parseDouble(Database.execute("SELECT COUNT(*) FROM standard.parkeerautomaten WHERE Deelgem=\"Noord\";", "Count(*)").get(0)); //get omdat het in een lijst staat
    double Centrum = Double.parseDouble(Database.execute("SELECT COUNT(*) FROM standard.parkeerautomaten WHERE Deelgem=\"Centrum\";", "Count(*)").get(1));
    double Charlois = Double.parseDouble(Database.execute("SELECT COUNT(*) FROM standard.parkeerautomaten WHERE Deelgem=\"Charlois\";", "Count(*)").get(2));
    double Delfshaven = Double.parseDouble(Database.execute("SELECT COUNT(*) FROM standard.parkeerautomaten WHERE Deelgem=\"Delfshaven\";", "Count(*)").get(3));
    double Feijenoord = Double.parseDouble(Database.execute("SELECT COUNT(*) FROM standard.parkeerautomaten WHERE Deelgem=\"Feijenoord\";", "Count(*)").get(4));
    double IJsselmonde =  Double.parseDouble(Database.execute("SELECT COUNT(*) FROM standard.parkeerautomaten WHERE Deelgem=\"Ijsselmonde\";", "Count(*)").get(5));
    double Kralingen = Double.parseDouble(Database.execute("SELECT COUNT(*) FROM standard.parkeerautomaten WHERE Deelgem=\"Kralingen/Crooswijk\";", "Count(*)").get(6));
    double PrinsAlexander = Double.parseDouble(Database.execute("SELECT COUNT(*) FROM standard.parkeerautomaten WHERE Deelgem=\"Prins Alexander\";", "Count(*)").get(7));
    String css = this.getClass().getResource("/PiechartStyle.css").toExternalForm();



        @Override public void start(Stage stage) {
            Scene scene = new Scene(new Group());
            scene.getStylesheets().add(css);
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
            final ObservableList<Node> children = ((Group) scene.getRoot()).getChildren();

            children.add(chart);

            final Label caption = new Label("");
            caption.setTextFill(Color.BLACK);
            caption.setStyle("-fx-font: 16 Roboto;");
            children.add(caption);

            for (final PieChart.Data data : chart.getData()) {
                data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED,
                        new EventHandler<MouseEvent>() {
                            @Override public void handle(MouseEvent e) {
                                caption.setTranslateX(e.getSceneX());
                                caption.setTranslateY(e.getSceneY());
                                caption.setText(String.valueOf(data.getPieValue()) + "Stuks");
                                caption.setVisible(true);
                            }
                        });
            }

            stage.setScene(scene);
            stage.show();
        }
        public static void main(String[] args) {
            launch(args);
        }
    }
