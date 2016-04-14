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


public class Piechart_tasjesdiefstal extends Application {
    Database Database = new Database();
    String Query1 = "SELECT SUM(year_2006) FROM slachtofferschap_tasjesroof_met_geweld;";
    String Query2 = "SELECT SUM(year_2006) FROM slachtofferschap_tasjesroof_zonder_geweld;";
    double MetGeweld = Double.parseDouble(Database.execute(Query1, "SUM(year_2006)").get(0));
    double ZonderGeweld = Double.parseDouble(Database.execute(Query2, "SUM(year_2006)").get(1));
    String css = this.getClass().getResource("/PiechartStyle.css").toExternalForm();



    @Override public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        scene.getStylesheets().add(css);
        stage.setTitle("Overvallen");
        stage.setWidth(800);
        stage.setHeight(800);

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Met geweld", MetGeweld),
                        new PieChart.Data("Zonder geweld",ZonderGeweld));
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Overvallen met of zonder geweld");
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
                            caption.setText(String.valueOf(data.getPieValue()) + "");
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
