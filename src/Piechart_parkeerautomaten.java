import com.sun.webkit.dom.HTMLElementImpl;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.chart.*;
import javafx.scene.Group;


public class Piechart_parkeerautomaten {

    public Piechart_parkeerautomaten(){}

    public Scene getSceneparkeerautomaten(){
        //layout scene
        HBox hbox = new HBox();
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);
        BorderPane borderpane = new BorderPane();

        // chart
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

        Scene scene = new Scene(borderpane);
        scene.getStylesheets().add(css);

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
        final ObservableList<Node> children = ((BorderPane) scene.getRoot()).getChildren();

        children.add(chart);

        final Label caption = new Label("");
        caption.setTextFill(Color.BLACK);
        caption.setStyle("-fx-font: 16 Roboto;");
        children.add(caption);
        hbox.getChildren().addAll(children);

        // buttons
        Button test1 = new Button("test1");
        GridPane.setConstraints(test1, 0, 0);
        Button test2 = new Button("test2");
        GridPane.setConstraints(test2, 1, 0);
        Button test3 = new Button("test3");
        GridPane.setConstraints(test3, 2, 0);
        Button test4 = new Button("test4");
        GridPane.setConstraints(test4, 3, 0);
        grid.setAlignment(Pos.CENTER);
        grid.getChildren().addAll(test1, test2, test3, test4);

        borderpane.setBottom(grid);
        borderpane.setTop(hbox);


        for (final PieChart.Data data : chart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED,
                    new EventHandler<MouseEvent>() {
                        @Override public void handle(MouseEvent e) {
                            caption.setTranslateX(e.getSceneX());
                            caption.setTranslateY(e.getSceneY());
                            caption.setText(String.valueOf(data.getPieValue()) + "");
                            caption.setVisible(true);
                        }
                    });
        }
        return scene;
    }
}
