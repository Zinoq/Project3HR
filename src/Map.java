import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Andre on 7-4-2016.
 */
public class Map extends Application implements MapComponentInitializedListener{

        GoogleMapView mapView;
        GoogleMap map;

        public void start(Stage stage) throws Exception {

            //Create the JavaFX component and set this as a listener so we know when
            //the map has been initialized, at which point we can then begin manipulating it.
            mapView = new GoogleMapView();
            mapView.addMapInializedListener(this);

            Scene scene = new Scene(mapView);

            stage.setTitle("Map");
            stage.setScene(scene);
            stage.show();
        }


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

            //Add a marker to the map
            MarkerOptions markerOptions = new MarkerOptions();

            markerOptions.position( new LatLong(51.917377, 4.483920) )
                    .visible(Boolean.TRUE)
                    .title("School");

            Marker marker = new Marker( markerOptions );

            map.addMarker(marker);

        }

        public static void main(String[] args) {
            launch(args);
        }
    }

