import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.*;
import com.lynden.gmapsfx.javascript.object.Animation;
import com.lynden.gmapsfx.shapes.Circle;
import com.lynden.gmapsfx.shapes.CircleOptions;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Map implements MapComponentInitializedListener {
    private GoogleMapView mapView;
    private GoogleMap map;
    Database Database = new Database();
    private ArrayList<Object> MarkerList = new ArrayList<>();

    private Timeline timeline;
    private List<String> list = Arrays.asList("Maandag", "Dinsdag", "Woensdag", "Donderdag", "Vrijdag", "Zaterdag", "Zondag");
    private int i = 0;
    private int j = 0;
    private Label daglabel;

    private String dag;
    private String plaats;
    private int radius;
    private Marker marker;
    private Circle rcircle;

    private Scene scene;

    public Button removeAllbut;
    public Button showAllbut;
    public Button removeBut;
    public Button showBut;

    public Map() {
    }

    public Scene getMapScene() {
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
        choiceday.getItems().addAll("Zondag" ,"Maandag", "Dinsdag", "Woensdag", "Donderdag", "Vrijdag", "Zaterdag");
        GridPane.setConstraints(choiceday, 1, 0);
        choiceday.setValue("Maandag");
        choiceday.setOnAction(event -> {
            dag = choiceday.getValue();
            System.out.println(dag);
        });

        Label marklabel = new Label("Welke markt zoekt u: "); // label markt
        GridPane.setConstraints(marklabel, 0, 1);

        ChoiceBox<String> choicemarkt = new ChoiceBox<>(); // choisebox markt
        choicemarkt.getItems().addAll("Binnenrotte Centrummarkt", "Boekenmarkt Wijdekerkstraat", "Biologische markt Eendrachtsplein", "Afrikaanderplein Rotterdam Zuid", "Ommoord", "IJsselmonde", "Visserijplein Rotterdam-West", "Alexanderpolder", "Schiebroek", "Overschie", "Zondagsmarkt");
        GridPane.setConstraints(choicemarkt, 1, 1);
        choicemarkt.setValue("Binnenrotte Centrummarkt");
        choicemarkt.setOnAction(event -> {
            plaats = choicemarkt.getValue();
            System.out.println(plaats);
        });

        Label radlabel = new Label("Radius in meter: "); // radius label radius
        GridPane.setConstraints(radlabel, 0, 2);

        TextField radinput = new TextField();// radius input
        radinput.setPromptText("Voer radius in");
        GridPane.setConstraints(radinput, 1, 2);

        showBut = new Button("Toon markt"); // load map button
        GridPane.setConstraints(showBut, 1, 3);
        showBut.setOnAction(event -> {
            removeBut.setVisible(true);
            showBut.setVisible(false);
            isInt(radinput);
            addCircle();
            addMarker();
        });

        removeBut = new Button("Verwijder markt"); // load map button
        GridPane.setConstraints(removeBut, 1, 3);
        removeBut.setVisible(false);
        removeBut.setOnAction(event -> {
            removeBut.setVisible(false);
            showBut.setVisible(true);
            removeMarker();
            removeCircle();
        });

        showAllbut = new Button("Toon alle markten"); // load map button
        GridPane.setConstraints(showAllbut, 1, 5);
        showAllbut.setOnAction(event -> {
            addAllMarkers();
            showAllbut.setVisible(false);
            removeAllbut.setVisible(true);
        });

        removeAllbut = new Button("Verwijder alle markten");
        removeAllbut.setVisible(false);
        GridPane.setConstraints(removeAllbut, 1, 5);
        removeAllbut.setOnAction(event -> {
            removeAllMarkers();
            removeAllbut.setVisible(false);
            showAllbut.setVisible(true);
        });

        Button startbut = new Button("Start animatie");
        GridPane.setConstraints(startbut, 1, 8);
        startbut.setOnAction(event -> {
            StartAnimation();
            daglabel.setVisible(true);
            System.out.println("start");
        });

        Button stopbut = new Button("Stop animatie");
        GridPane.setConstraints(stopbut, 1, 9);
        stopbut.setOnAction(event -> {
            StopAnimation();
            daglabel.setVisible(false);
            System.out.println("stop");
        });
        daglabel = new Label("");
        GridPane.setConstraints(daglabel, 1,10);

        grid.getChildren().addAll(daylabel, choiceday, marklabel, choicemarkt, radlabel, radinput, showBut,removeBut, startbut, stopbut, removeAllbut, showAllbut, daglabel);
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

        return scene;
    }

    private int isInt(TextField input) {
        try {
            radius = Integer.parseInt(input.getText());
            System.out.println("radius = " + radius);
            return radius;
        } catch (NumberFormatException e) {
            System.out.println("error: input isn't an int");
            return 0;
        }
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
    }

    //Add a marker to the map.
    public void addMarker() {
        MarkerOptions markerOptions = new MarkerOptions()
                .position(new LatLong (Double.parseDouble(Database.execute("SELECT Latitude FROM standard.markten WHERE Latitude IS NOT NULL and Plaats= \"" + plaats + "\" and Dag= \"" + dag + "\"","Latitude").get(0)), (Double.parseDouble(Database.execute("SELECT Longitude FROM standard.markten WHERE Longitude IS NOT NULL and Plaats= \"" + plaats + "\" and Dag= \"" + dag + "\"","Longitude").get(0)))))
                .visible(true);

        marker = new Marker(markerOptions);
        map.addMarker(marker);
        }

    //Add a marker to the map.
    public void addAllMarkers() {
        for (int i = 0; i < 11; i++) {
            MarkerOptions markerOptions = new MarkerOptions()
                    .position(new LatLong(Database.getMarkerLat().get(i), Database.getMarkerLong().get(i)))
                    .visible(true);

            Marker marker = new Marker(markerOptions);
            MarkerList.add(marker);
            System.out.println(MarkerList.get(i));
            map.addMarker(marker);
        }
    }

    public void removeAllMarkers(){
        for (int i = 1; i < 11; i++) {
            MarkerOptions markerOptions = new MarkerOptions()
                    .position(new LatLong(Database.getMarkerLat().get(i), Database.getMarkerLong().get(i)))
                    .visible(true);

            Marker marker = new Marker(markerOptions);
            map.removeMarker(marker);
            }
    }

    public void removeMarker(){
        map.removeMarker(marker);
        System.out.println(MarkerList);
    }

    public void addCircle() {
        //Add a radius circle to a marker
        CircleOptions rcircleoptions = new CircleOptions()
                .center(new LatLong (Double.parseDouble(Database.execute("SELECT Latitude FROM standard.markten WHERE Latitude IS NOT NULL and Plaats= \"" + plaats + "\" and Dag= \"" + dag + "\"","Latitude").get(0)), (Double.parseDouble(Database.execute("SELECT Longitude FROM standard.markten WHERE Longitude IS NOT NULL and Plaats= \"" + plaats + "\" and Dag= \"" + dag + "\"","Longitude").get(0)))))
                .radius(radius)
                .strokeColor("red")
                .strokeWeight(2)
                .fillColor("red")
                .fillOpacity(0.3);
        rcircle = new Circle(rcircleoptions);
        map.addMapShape(rcircle);
    }

    public void removeCircle(){
        map.removeMapShape(rcircle);
    }

    public void addParkeerMarkers() {
        for (int p = 0; p < 500; p++) {
            LatLong startpoint = new LatLong(51.922597, 4.484583);
            double distance = startpoint.distanceFrom(new LatLong(Database.getParkeerLat().get(p), Database.getParkeerLong().get(p)));
            if (distance < radius) {
                map.addMarker(new Marker(new MarkerOptions().position(new LatLong(Database.getParkeerLat().get(p), Database.getParkeerLong().get(p)))));
            }
        }
    }

    public void StartAnimation() {
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                System.out.println(i);

                                if (i == 7) {
                                    i = 0;
                                }
                                daglabel.setText(list.get(i));
                                if (j >= 1 && !list.get(i).equals("Dinsdag")){
                                    map.removeMarker(marker);
                                }
                                if(!list.get(i).equals("Maandag")) {
                                        System.out.println(Database.execute("SELECT Latitude FROM standard.markten WHERE Latitude IS NOT NULL and Dag= \"" + list.get(i) + "\"", "Latitude").get(0));
                                        System.out.println(Database.execute("SELECT Longitude FROM standard.markten WHERE Longitude IS NOT NULL and Dag= \"" + list.get(i) + "\"", "Longitude").get(0));
                                        MarkerOptions markerOptions = new MarkerOptions()
                                                .position(new LatLong(Double.parseDouble(Database.execute("SELECT Latitude FROM standard.markten WHERE Latitude IS NOT NULL and Dag= \"" + list.get(i) + "\"", "Latitude").get(0)), (Double.parseDouble(Database.execute("SELECT Longitude FROM standard.markten WHERE Longitude IS NOT NULL and Dag= \"" + list.get(i) + "\"", "Longitude").get(0)))))
                                                .visible(true);

                                        marker = new Marker(markerOptions);
                                        map.addMarker(marker);
                                        System.out.println(marker);
                                                                    }
                                System.out.println("i na" + i);
                                System.out.println("j na" + j);
                                i++;
                                j++;

                            }


                        }
                ),
                new KeyFrame(Duration.seconds(2))
        );
        timeline.setCycleCount(javafx.animation.Animation.INDEFINITE);
        timeline.play();
    }

    public void StopAnimation() {
        timeline.stop();
    }
}


