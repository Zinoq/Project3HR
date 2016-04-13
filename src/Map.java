import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

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
            MarkerOptions markerOptions = new MarkerOptions();

            for (int i = 0; i < 500; i++) {
                map.addMarker(new Marker(markerOptions.position(new LatLong(Database.getMarkerLat().get(i), Database.getMarkerLong().get(i)))
                        .visible(Boolean.TRUE)
                        .title("test")));
            }
        }


        public static void main(String[] args) {
            launch(args);
        }
    }

