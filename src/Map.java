import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.*;
import com.lynden.gmapsfx.shapes.Circle;
import com.lynden.gmapsfx.shapes.CircleOptions;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Map implements MapComponentInitializedListener {
    private GoogleMapView mapView;
    private GoogleMap map;
    Database Database = new Database();

    private Timeline timeline;
    private List<String> list = Arrays.asList("Maandag", "Dinsdag", "Woensdag", "Donderdag", "Vrijdag", "Zaterdag", "Zondag");
    private ArrayList<Object> markerlist = new ArrayList<>();
    private int i = 0;
    private int j = 0;
    private Label daglabel;

    private String plaats;
    private int radius;
    private Boolean animation = false;
    private Boolean clicked = false;
    private Marker marker;
    private Circle rcircle;

    private Scene scene;

    private Button removeAllbut;
    private Button showAllbut;
    private Button removeBut;
    private Button showBut;

    // Constructor
    public Map() {
    }

    public Scene getMapScene() {
        // Set the scene

        // Create the JavaFX component and set this as a listener so we know when
        //the map has been initialized, at which point we can then begin manipulating it.
        mapView = new GoogleMapView();
        mapView.addMapInializedListener(this);

        // initialize the stackpane and boxes
        StackPane root = new StackPane();
        HBox hbox = new HBox(30);
        VBox labelbox = new VBox(15);
        VBox mapbox = new VBox(15);

        // initialize the grid
        GridPane grid = new GridPane(); // markten map layout
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);

        Label daylabel = new Label("Markten in Rotterdam "); // label days
        GridPane.setConstraints(daylabel, 0, 0);
        daylabel.setFont(new Font("Serif", 15));

        Label marklabel = new Label("Welke markt zoekt u: "); // label markt
        GridPane.setConstraints(marklabel, 0, 1);

        ChoiceBox<String> choicemarkt = new ChoiceBox<>(); // choicebox markt
        choicemarkt.getItems().addAll("Binnenrotte Centrummarkt", "Boekenmarkt Wijdekerkstraat", "Biologische markt Eendrachtsplein", "Afrikaanderplein Rotterdam Zuid", "Ommoord", "IJsselmonde", "Visserijplein Rotterdam-West", "Alexanderpolder", "Schiebroek", "Overschie", "Zondagsmarkt");
        GridPane.setConstraints(choicemarkt, 1, 1);
        choicemarkt.setValue("Binnenrotte Centrummarkt");
        choicemarkt.setOnAction(event -> {
            clicked = true;
            plaats = choicemarkt.getValue();
            System.out.println(plaats);
        });

        Label radlabel = new Label("Radius in meter: "); // radius label radius
        GridPane.setConstraints(radlabel, 0, 2);

        TextField radinput = new TextField();// radius input
        radinput.setPromptText("Voer radius in");
        GridPane.setConstraints(radinput, 1, 2);

        showBut = new Button("Toon markt"); // show markt button
        GridPane.setConstraints(showBut, 1, 3);
        showBut.setOnAction(event -> {
            if (clicked) {
                removeBut.setVisible(true);
                showBut.setVisible(false);
                isInt(radinput);
                addCircle();
                addMarker();
            }
        });

        removeBut = new Button("Verwijder markt"); // remove markt button
        GridPane.setConstraints(removeBut, 1, 3);
        removeBut.setVisible(false);
        removeBut.setOnAction(event -> {
            removeBut.setVisible(false);
            showBut.setVisible(true);
            removeMarker();
            removeCircle();
        });

        showAllbut = new Button("Toon alle markten"); // show all button
        GridPane.setConstraints(showAllbut, 1, 5);
        showAllbut.setOnAction(event -> {
            addAllMarkers();
            showAllbut.setVisible(false);
            removeAllbut.setVisible(true);
        });

        // TODO: Alle markers verwijderen

        removeAllbut = new Button("Verwijder alle markten"); // remove all button
        removeAllbut.setVisible(false);
        GridPane.setConstraints(removeAllbut, 1, 5);
        removeAllbut.setOnAction(event -> {
            removeAllMarkers();
            removeAllbut.setVisible(false);
            showAllbut.setVisible(true);
        });

        Label animationlabel = new Label("Markt per dag als animatie"); // infolabel animation
        GridPane.setConstraints(animationlabel, 0, 10);

        Button startbut = new Button("Start animatie"); // start button animation
        GridPane.setConstraints(startbut, 1, 10);
        startbut.setOnAction(event -> {
            animation = true;
            StartAnimation();
            daglabel.setVisible(true);
            System.out.println("start");
        });

        Button stopbut = new Button("Stop animatie"); // stop button animation
        GridPane.setConstraints(stopbut, 1, 11);
        stopbut.setOnAction(event -> {
            if (animation) {
                StopAnimation();
                daglabel.setVisible(false);
                System.out.println("stop");
                removeMarker();
                animation = false;
            }
        });
        daglabel = new Label(""); // animated label
        GridPane.setConstraints(daglabel, 1,12);

        // add all buttons and labels to the grid
        grid.getChildren().addAll(daylabel, marklabel, choicemarkt, radlabel, radinput, showBut, removeBut, animationlabel, startbut, stopbut, showAllbut, removeAllbut, daglabel);
        labelbox.getChildren().addAll(grid);

        // A grid within a vertical box, within a horizontal box, within a stackpane. (same for te map)
        // set the grow of the boxes when resizing the screen
        mapbox.setVgrow(mapView, Priority.ALWAYS);
        mapbox.getChildren().addAll(mapView);

        hbox.setHgrow(mapbox, Priority.ALWAYS);
        hbox.getChildren().addAll(labelbox, mapbox);
        root.getChildren().add(hbox);
        scene = new Scene(root, 1280, 720);

        // return the scene
        return scene;
    }

    // check if radius is an int, if so save in a variable
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

        // center the map in Rotterdam
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

    //Add one marker to the map.
    public void addMarker() {
        MarkerOptions markerOptions = new MarkerOptions()
                .position(new LatLong (Double.parseDouble(Database.execute("SELECT Latitude FROM standard.markten WHERE Latitude IS NOT NULL and Plaats= \"" + plaats + "\"","Latitude").get(0)), (Double.parseDouble(Database.execute("SELECT Longitude FROM standard.markten WHERE Longitude IS NOT NULL and Plaats= \"" + plaats + "\"","Longitude").get(0)))));
        marker = new Marker(markerOptions);
        map.addMarker(marker);
        }

    //Add all markers to the map.
    public void addAllMarkers() {
        for (int i = 0; i < 11; i++) {
            MarkerOptions markerOptions = new MarkerOptions()
                    .position(new LatLong(Database.getMarkerLat().get(i), Database.getMarkerLong().get(i)))
                    .visible(true);
            Marker marker = new Marker(markerOptions);
            markerlist.add(marker);
            map.addMarker(marker);
        }
    }

    // remove all markers from the map
    public void removeAllMarkers(){
        for (Object i : markerlist) {
//            map.removeMarker(i);
            }
    }

    // remove one marker from the map
    public void removeMarker(){
        map.removeMarker(marker);
    }

    //Add a radius circle to a marker
    public void addCircle() {
        CircleOptions rcircleoptions = new CircleOptions()
                // center the circle arount the marker
                .center(new LatLong (Double.parseDouble(Database.execute("SELECT Latitude FROM standard.markten WHERE Latitude IS NOT NULL and Plaats= \"" + plaats + "\"", "Latitude").get(0)), (Double.parseDouble(Database.execute("SELECT Longitude FROM standard.markten WHERE Longitude IS NOT NULL and Plaats= \"" + plaats + "\"","Longitude").get(0)))))
                .radius(radius)
                .strokeColor("red")
                .strokeWeight(2)
                .fillColor("red")
                .fillOpacity(0.3);
        rcircle = new Circle(rcircleoptions);
        map.addMapShape(rcircle);
    }

    // remove the circle from the map
    public void removeCircle(){
        map.removeMapShape(rcircle);
    }

    // start the marker animation
    public void StartAnimation() {
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                // reset the i to 0 if all items of the list are done
                                if (i == 7) {
                                    i = 0;
                                }
                                // set the text of the label
                                daglabel.setText(list.get(i));

                                // remove the previous marker if there
                                if (j > 0 && !list.get(i).equals("Dinsdag")){
                                    removeMarker();
                                    // update the map
                                    int currentZoom = map.getZoom();
                                    map.setZoom( currentZoom - 1 );
                                    map.setZoom( currentZoom );
                                }

                                // add marker if day is not monday
                                if(!list.get(i).equals("Maandag")) {
                                        MarkerOptions markerOptions = new MarkerOptions()
                                                .position(new LatLong(Double.parseDouble(Database.execute("SELECT Latitude FROM standard.markten WHERE Latitude IS NOT NULL and Dag= \"" + list.get(i) + "\"", "Latitude").get(0)), (Double.parseDouble(Database.execute("SELECT Longitude FROM standard.markten WHERE Longitude IS NOT NULL and Dag= \"" + list.get(i) + "\"", "Longitude").get(0)))));
                                        marker = new Marker(markerOptions);
                                        map.addMarker(marker);                                                                   }
                                i++;
                                j++;
                            }
                        }
                ),
                new KeyFrame(Duration.seconds(2))
        );
        // play the animation indefinite
        timeline.setCycleCount(javafx.animation.Animation.INDEFINITE);
        timeline.play();
    }

    // stop the marker animation
    public void StopAnimation() {
        timeline.stop();
    }
}