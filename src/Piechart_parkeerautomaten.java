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

    public Scene getSceneparkeerautomaten(Database Database){
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

        // chart
        double Noord = Double.parseDouble(Database.execute("SELECT COUNT(*) FROM standard.parkeerautomaten WHERE Deelgem=\"Noord\";", "Count(*)").get(0)); //get omdat het in een lijst staat
        double Centrum = Double.parseDouble(Database.execute("SELECT COUNT(*) FROM standard.parkeerautomaten WHERE Deelgem=\"Centrum\";", "Count(*)").get(0));
        double Charlois = Double.parseDouble(Database.execute("SELECT COUNT(*) FROM standard.parkeerautomaten WHERE Deelgem=\"Charlois\";", "Count(*)").get(0));
        double Delfshaven = Double.parseDouble(Database.execute("SELECT COUNT(*) FROM standard.parkeerautomaten WHERE Deelgem=\"Delfshaven\";", "Count(*)").get(0));
        double Feijenoord = Double.parseDouble(Database.execute("SELECT COUNT(*) FROM standard.parkeerautomaten WHERE Deelgem=\"Feijenoord\";", "Count(*)").get(0));
        double IJsselmonde =  Double.parseDouble(Database.execute("SELECT COUNT(*) FROM standard.parkeerautomaten WHERE Deelgem=\"Ijsselmonde\";", "Count(*)").get(0));
        double Kralingen = Double.parseDouble(Database.execute("SELECT COUNT(*) FROM standard.parkeerautomaten WHERE Deelgem=\"Kralingen/Crooswijk\";", "Count(*)").get(0));
        double PrinsAlexander = Double.parseDouble(Database.execute("SELECT COUNT(*) FROM standard.parkeerautomaten WHERE Deelgem=\"Prins Alexander\";", "Count(*)").get(0));
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
