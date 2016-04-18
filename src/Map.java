import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;
import com.lynden.gmapsfx.shapes.Circle;
import com.lynden.gmapsfx.shapes.CircleOptions;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;


public class Map implements MapComponentInitializedListener{
        GoogleMapView mapView;
        GoogleMap map;
        public Label daglabel;
        Scene scene;
        Boolean running = false;
        Database Database = new Database();
        public Map(){
        }

        public Scene getMapScene()  {
            //Create the JavaFX component and set this as a listener so we know when
            //the map has been initialized, at which point we can then begin manipulating it.
            StackPane root = new StackPane();
            HBox hbox = new HBox(30);

            VBox labelbox = new VBox(15);
            GridPane grid = new GridPane(); // markten map layout
            grid.setPadding(new Insets(10, 10, 10, 10));
            grid.setVgap(10);
            grid.setHgap(10);

            Label daylabel = new Label("Kies de dag: "); // label days
            GridPane.setConstraints(daylabel, 0, 0);

            ChoiceBox<String> choiceday = new ChoiceBox<>(); // choisebox days
            choiceday.getItems().addAll("Maandag", "Dinsdag", "Woensdag", "Donderdag", "Vrijdag", "Zaterdag");
            GridPane.setConstraints(choiceday, 1, 0);
            choiceday.setValue("Maandag");

            Label marklabel = new Label("Welke markt zoekt u: "); // label markt
            GridPane.setConstraints(marklabel, 0, 1);

            ChoiceBox<String> choicemarkt = new ChoiceBox<>(); // choisebox markt
            choicemarkt.getItems().addAll("Markt1", "Markt2", "Markt3", "Markt4", "Markt5");
            GridPane.setConstraints(choicemarkt, 1, 1);
            choicemarkt.setValue("Markt1");

            Label radlabel = new Label("Radius in meter: "); // radius label radius
            GridPane.setConstraints(radlabel, 0, 2);

            TextField radinput = new TextField();// radius input
            radinput.setPromptText("Enter your radius here");
            GridPane.setConstraints(radinput, 1, 2);

            Button radConfirm = new Button("ok"); // load map button
            GridPane.setConstraints(radConfirm, 1, 3);

            Button startbut = new Button("Start animation");
            GridPane.setConstraints(startbut, 1,6);
            startbut.setOnAction(event -> {System.out.println("Start");});

            Button stopbut = new Button("Stop animation");
            GridPane.setConstraints(stopbut, 1,7);
            stopbut.setOnAction(event -> {System.out.println("Stop");});

            daglabel = new Label("");
            GridPane.setConstraints(daglabel, 1,8);

            grid.getChildren().addAll(daylabel, choiceday, marklabel, choicemarkt, radlabel, radinput, radConfirm, startbut, stopbut, daglabel);
            labelbox.getChildren().addAll(grid);

            VBox mapbox = new VBox(15);
            mapView = new GoogleMapView();
            mapView.addMapInializedListener(this);
            mapbox.setVgrow(mapView, Priority.ALWAYS);
            mapbox.getChildren().addAll(mapView);

            hbox.setHgrow(mapbox, Priority.ALWAYS);
            hbox.getChildren().addAll(labelbox, mapbox);
            root.getChildren().add(hbox);
            scene = new Scene(root, 1280, 720);

//    private int isInt(TextField input) {
//        try {
//            int radius = Integer.parseInt(input.getText());
//            System.out.println("radius = " + radius);
//            return radius;
//        } catch (NumberFormatException e) {
//            System.out.println("error: input isn't an int");
//            return 0;
//        }
//    }
            return scene;
        }

        // initialize the map
        @Override
        public void mapInitialized() {
            //Set the initial properties of the map.
            MapOptions mapOptions = new MapOptions();

            mapOptions.center(new LatLong(51.924420, 4.477733))
                    .mapType(MapTypeIdEnum.ROADMAP)
                    .overviewMapControl(false)
                    .panControl(false)
                    .rotateControl(false)
                    .scaleControl(false)
                    .streetViewControl(false)
                    .zoomControl(false)
                    .zoom(12);

            map = mapView.createMap(mapOptions);

            //Add a marker to the map.
            for (int i = 0; i < 11; i++) {
                MarkerOptions markerOptions = new MarkerOptions()
                        .position(new LatLong(Database.getMarkerLat().get(i), Database.getMarkerLong().get(i)))
                        .animation(Animation.DROP);

                Marker marker = new Marker(markerOptions);
                map.addMarker(marker);

                //Add an info window to a marker
                InfoWindowOptions infoOptions = new InfoWindowOptions();
                infoOptions.content(Database.execute("SELECT Plaats FROM standard.markten WHERE Plaats IS NOT NULL", "Plaats").get(0))
                        .position(new LatLong(Database.getMarkerLat().get(i), Database.getMarkerLong().get(i)));
                InfoWindow window = new InfoWindow(infoOptions);

                //Make the marker clickable to show an info window
                map.addUIEventHandler(marker, UIEventType.click, (netscape.javascript.JSObject obj) -> {
                    for (int j = 0; j < 11; j++) {
                        window.open(map, new Marker(markerOptions.position(new LatLong(Database.getMarkerLat().get(j), Database.getMarkerLong().get(j)))));
                    }
                });
            }

            //Add a radius circle to a marker
            CircleOptions rcircleoptions = new CircleOptions()
                    .center(new LatLong(51.922597, 4.484583))
                    .radius(100)
                    .strokeColor("red")
                    .strokeWeight(2)
                    .fillColor("red")
                    .fillOpacity(0.3);
            Circle rcircle = new Circle(rcircleoptions);
            map.addMapShape(rcircle);

            for (int p = 0; p < 500; p++) {
                LatLong startpoint = new LatLong(51.922597, 4.484583);
                double distance = startpoint.distanceFrom(new LatLong(Database.getParkeerLat().get(p), Database.getParkeerLong().get(p)));
                if (distance < 50){
                    map.addMarker(new Marker(new MarkerOptions().position(new LatLong(Database.getParkeerLat().get(p), Database.getParkeerLong().get(p)))));
                }
            }
        }
    }

