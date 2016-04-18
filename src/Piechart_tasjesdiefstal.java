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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.chart.*;
import javafx.scene.Group;

public class Piechart_tasjesdiefstal {

    public Piechart_tasjesdiefstal() {

    }

    public Scene getSceneDiefstal(Database Database) {
        //layout scene
        HBox hbox = new HBox();
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);
        BorderPane borderpane = new BorderPane();



        Button button2006 = new Button("2006");
        GridPane.setConstraints(button2006, 0, 0);
        Button button2007 = new Button("2007");
        GridPane.setConstraints(button2007, 1, 0);
        Button button2008 = new Button("2008");
        GridPane.setConstraints(button2008, 2, 0);
        Button button2009 = new Button("2009");
        GridPane.setConstraints(button2009, 3, 0);
        Button button2011 = new Button("2011");
        GridPane.setConstraints(button2011, 4, 0);
        grid.setAlignment(Pos.CENTER);
        grid.getChildren().addAll(button2006, button2007, button2008, button2009, button2011);

        StringBuilder Query1 = new StringBuilder("SELECT SUM(year_2006) FROM slachtofferschap_tasjesroof_met_geweld;");
        StringBuilder Query2 = new StringBuilder("SELECT SUM(year_2006) FROM slachtofferschap_tasjesroof_zonder_geweld;");

        button2006.setOnAction(e -> {
            Query1.replace(7, 21, "SUM(year_2006)");
            Query2.replace(7, 21, "SUM(year_2006)");
        });

        button2007.setOnAction(e -> {
            Query1.replace(7, 21, "SUM(year_2007)");
            Query2.replace(7, 21, "SUM(year_2007)");
            double MetGeweld = Double.parseDouble(Database.newExecute(Query1.toString()).get(0));
            double ZonderGeweld = Double.parseDouble(Database.newExecute(Query2.toString()).get(1));
        });

        button2008.setOnAction(e -> {
            Query1.replace(7, 21, "SUM(year_2008)");
            Query2.replace(7, 21, "SUM(year_2008)");
        });

        button2009.setOnAction(e -> {
            Query1.replace(7, 21, "SUM(year_2009)");
            Query2.replace(7, 21, "SUM(year_2009)");
        });

        button2011.setOnAction(e -> {
            Query1.replace(7, 21, "SUM(year_2011)");
            Query2.replace(7, 21, "SUM(year_2011)");
        });

        double MetGeweld = Double.parseDouble(Database.newExecute(Query1.toString()).get(0));
        double ZonderGeweld = Double.parseDouble(Database.newExecute(Query2.toString()).get(1));
        Scene sceneDiefstal = new Scene(borderpane);

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Met geweld", MetGeweld),
                        new PieChart.Data("Zonder geweld", ZonderGeweld));
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Overvallen met of zonder geweld");
        final ObservableList<Node> children = ((BorderPane) sceneDiefstal.getRoot()).getChildren();

        children.add(chart);

        final Label caption = new Label("");
        caption.setTextFill(Color.BLACK);
        caption.setStyle("-fx-font: 16 Roboto;");
        children.add(caption);
        hbox.getChildren().addAll(children);

        borderpane.setBottom(grid);
        borderpane.setTop(hbox);


        for (final PieChart.Data data : chart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED,
                    new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent e) {
                            caption.setTranslateX(e.getSceneX());
                            caption.setTranslateY(e.getSceneY());
                            caption.setText(String.valueOf(data.getPieValue()) + "");
                            caption.setVisible(true);
                        }
                    });
        }
        return sceneDiefstal;
    }
}

//    public static void main(String[] args) {
//        Database Database = new Database();
//        StringBuilder Query1 = new StringBuilder("SELECT SUM(year_2006) FROM slachtofferschap_tasjesroof_met_geweld;");
//        StringBuilder Query2 = new StringBuilder("SELECT SUM(year_2006) FROM slachtofferschap_tasjesroof_zonder_geweld;");
//        double MetGeweld = Double.parseDouble(Database.newExecute(Query1.toString()).get(0));
//        double ZonderGeweld = Double.parseDouble(Database.newExecute(Query2.toString()).get(1));
//        System.out.println(MetGeweld);
//        System.out.println(ZonderGeweld);
//
//    }
//}