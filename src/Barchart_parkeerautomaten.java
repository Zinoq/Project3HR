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


public class Barchart_parkeerautomaten {

    public Barchart_parkeerautomaten(){}

    public Scene getSceneparkeerautomaten(Database Database){
        //layout scene
        HBox hbox = new HBox();
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);
        BorderPane borderpane = new BorderPane();

//        Button button2006 = new Button("2006");
//        GridPane.setConstraints(button2006, 0, 0);
//        Button button2007 = new Button("2007");
//        GridPane.setConstraints(button2007, 1, 0);
//        Button button2008 = new Button("2008");
//        GridPane.setConstraints(button2008, 2, 0);
//        Button button2009 = new Button("2009");
//        GridPane.setConstraints(button2009, 3, 0);
//        Button button2011 = new Button("2011");
//        GridPane.setConstraints(button2011, 4, 0);
//        grid.setAlignment(Pos.CENTER);
//        grid.getChildren().addAll(button2006, button2007, button2008, button2009, button2011);

        // chart
        double Noord = Double.parseDouble(Database.execute("SELECT COUNT(*) FROM standard.parkeerautomaten WHERE Deelgem=\"Noord\";", "Count(*)").get(0)); //get omdat het in een lijst staat
        double Centrum = Double.parseDouble(Database.execute("SELECT COUNT(*) FROM standard.parkeerautomaten WHERE Deelgem=\"Centrum\";", "Count(*)").get(0));
        double Charlois = Double.parseDouble(Database.execute("SELECT COUNT(*) FROM standard.parkeerautomaten WHERE Deelgem=\"Charlois\";", "Count(*)").get(0));
        double Delfshaven = Double.parseDouble(Database.execute("SELECT COUNT(*) FROM standard.parkeerautomaten WHERE Deelgem=\"Delfshaven\";", "Count(*)").get(0));
        double Feijenoord = Double.parseDouble(Database.execute("SELECT COUNT(*) FROM standard.parkeerautomaten WHERE Deelgem=\"Feijenoord\";", "Count(*)").get(0));
        double IJsselmonde =  Double.parseDouble(Database.execute("SELECT COUNT(*) FROM standard.parkeerautomaten WHERE Deelgem=\"Ijsselmonde\";", "Count(*)").get(0));
        double Kralingen = Double.parseDouble(Database.execute("SELECT COUNT(*) FROM standard.parkeerautomaten WHERE Deelgem=\"Kralingen/Crooswijk\";", "Count(*)").get(0));
        double PrinsAlexander = Double.parseDouble(Database.execute("SELECT COUNT(*) FROM standard.parkeerautomaten WHERE Deelgem=\"Prins Alexander\";", "Count(*)").get(0));

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> barch = new BarChart<String, Number>(xAxis, yAxis);
        xAxis.setLabel("Regio");
        yAxis.setLabel("Hoeveelheid Parkeerautomaten");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Hoeveelheid Parkeerautomaten");
        series1.getData().add(new XYChart.Data("Noord", Noord));
        series1.getData().add(new XYChart.Data("Centrum", Centrum));
        series1.getData().add(new XYChart.Data("Charlois", Charlois));
        series1.getData().add(new XYChart.Data("Delfshaven", Delfshaven));
        series1.getData().add(new XYChart.Data("Feijenoord", Feijenoord));
        series1.getData().add(new XYChart.Data("IJsselmonde", IJsselmonde));
        series1.getData().add(new XYChart.Data("Kralingen", Kralingen));
        series1.getData().add(new XYChart.Data("Prins Alexander", PrinsAlexander));

        borderpane.setBottom(grid);
        borderpane.setTop(hbox);

        Scene scene = new Scene(barch, 1280, 720);
        barch.getData().addAll(series1);

        return scene; // return scene so we can use it in main
    }
}