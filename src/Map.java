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

public class Map extends Application implements MapComponentInitializedListener{
        GoogleMapView mapView;
        GoogleMap map;
        Database Database;

        public Map(){
            //Create the JavaFX component and set this as a listener so we know when
            //the map has been initialized, at which point we can then begin manipulating it.
            mapView = new GoogleMapView();
            mapView.addMapInializedListener(this);
            Database = new Database();
        }

        @Override
        public void start(Stage stage) throws Exception {
            Scene scene = new Scene(mapView);;
            stage.setTitle("Map");
            stage.setScene(scene);
            stage.show();
        }

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

            for (int i = 0; i < Database.getMarkerLat().size(); i++) {
                markerOptions.position(new LatLong(Database.getMarkerLat().get(i), Database.getMarkerLong().get(i)))
                        .visible(true)
                        .title("test");
                Marker marker = new Marker(markerOptions);
                map.addMarker(marker);
            }
        }


        public static void main(String[] args) {
            launch(args);
        }
    }

//
//Database.execute("SELECT X_Coord, Y_Coord FROM standard.parkeerautomaten WHERE Inventarisnr=100.0", "X_Coord");
//        double X_Coord = Double.parseDouble(Database.getSpecific(1));
//        Database.execute("SELECT X_Coord, Y_Coord FROM standard.parkeerautomaten WHERE Inventarisnr=100.0", "Y_Coord");
//        double Y_Coord = Double.parseDouble(Database.getSpecific(1));
//        // ^ opvragen x & y coordinaten
//        createMarker(X_Coord, Y_Coord, true, "test");