import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.*;
import javafx.scene.Group;
import java.util.ArrayList;

public class Piechart extends Application {
    String Title;
    int Width;
    int Height;
    String Result1;
    String Result2;
    Database Database;

      public Piechart(String title, int Width, int Height) {
          this.Title = title;
          this.Width = Width;
          this.Height = Height;
      }

        public String getResult1(String Query1, String Selector1) {
            Result1 = Database.execute(Query1, Selector1);
            System.out.println(Result1);
            return Result1;
        }

        public String getResult2(String Query2, String Selector2) {
            Result2 = Database.execute(Query2, Selector2);
            System.out.println(Result2);
            return Result2;
        }

        @Override public void start(Stage stage) {
            Scene scene = new Scene(new Group());
            stage.setTitle(this.Title);
            stage.setWidth(this.Width);
            stage.setHeight(this.Height);

            ObservableList<PieChart.Data> pieChartData =
                    FXCollections.observableArrayList(
                            new PieChart.Data("Grapefruit", 13),
                            new PieChart.Data("Oranges", 25),
                            new PieChart.Data("Plums", 10),
                            new PieChart.Data("Pears", 22),
                            new PieChart.Data("Apples", 30));
            final PieChart chart = new PieChart(pieChartData);
            chart.setTitle(this.Title);

            ((Group) scene.getRoot()).getChildren().add(chart);
            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            Piechart chart1 = new Piechart("Test", 500, 500);
            Database Database = new Database();
            chart1.getResult1("SELECT Inventarisnr FROM standard.parkeerautomaten WHERE Inventarisnr=100.0", "Inventarisnr");
            chart1.getResult2("SELECT Deelgem FROM standard.parkeerautomaten WHERE Inventarisnr=100.0", "Deelgem");


//            launch(args);
        }
    }
