import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventHandler;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;
import com.lynden.gmapsfx.shapes.Circle;
import com.lynden.gmapsfx.shapes.CircleOptions;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import jdk.nashorn.api.scripting.JSObject;

public class Map extends Application implements MapComponentInitializedListener{
        GoogleMapView mapView;
        GoogleMap map;
        Database Database;
        Scene scene;

        public Map(){
            //Create the JavaFX component and set this as a listener so we know when
            //the map has been initialized, at which point we can then begin manipulating it.
            mapView = new GoogleMapView();
            mapView.addMapInializedListener(this);
            Database = new Database();
            scene = new Scene(mapView,1280,720);
        }

        @Override
        public void start(Stage stage) throws Exception {
            stage.setTitle("Map");
            stage.setScene(scene);
            stage.show();
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
                infoOptions.content(Database.execute("SELECT Plaats FROM standard.markten WHERE Plaats IS NOT NULL", "Plaats").get(i))
                        .position(new LatLong(Database.getMarkerLat().get(i), Database.getMarkerLong().get(i)));
                InfoWindow window = new InfoWindow(infoOptions);

                map.addUIEventHandler(marker, UIEventType.click, (netscape.javascript.JSObject obj) -> {
                    for (int j = 0; j < 11; j++) {
                        window.open(map, new Marker(markerOptions.position(new LatLong(Database.getMarkerLat().get(j), Database.getMarkerLong().get(j)))));
                    }});

                CircleOptions rcircleoptions = new CircleOptions()
                        .center(new LatLong(Database.getMarkerLat().get(i), Database.getMarkerLong().get(i)))
                                .radius(1000)
                                .strokeColor("red")
                                .strokeWeight(2)
                                .fillColor("red")
                                .fillOpacity(0.3);
                Circle rcircle = new Circle(rcircleoptions);
                map.addMapShape(rcircle);
                }
            }

        public static void main(String[] args) {
            launch(args);
        }
    }

