package cl.j.verduleria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class mapa extends AppCompatActivity implements OnMapReadyCallback{
    Button volvere;
    private Marker marcadore;
    double lat = 0.0;
    double lon = 0.0;
    //GoogleMap mMap = googleMap;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        volvere = findViewById(R.id.bvolver);

        volvere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(),
                        MainActivity.class
                );
                startActivity(intent);
            }
        });

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        GoogleMap mMap = googleMap;

        //llamado de valores con el intent
        double lon1 = getIntent().getDoubleExtra("lo1", 0.0);
        double lat1 = getIntent().getDoubleExtra("la1", 0.0);

        double lon2 = getIntent().getDoubleExtra("lo2", 0.0);
        double lat2 = getIntent().getDoubleExtra("la2", 0.0);

        double lon3 = getIntent().getDoubleExtra("lo3", 0.0);
        double lat3 = getIntent().getDoubleExtra("la3", 0.0);

        //Se asignan los valores a sus marcadores correspondientes
        LatLng punto1 = new LatLng(lon1, lat1);
        mMap.addMarker(new MarkerOptions().position(punto1)
                .title("Primer Marcador")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        LatLng punto2 = new LatLng(lon2, lat2);
        mMap.addMarker(new MarkerOptions().position(punto2)
                .title("Segundo Marcador")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));

        LatLng punto3 = new LatLng(lon3, lat3);
        mMap.addMarker(new MarkerOptions().position(punto3)
                .title("Tercer Marcador")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));

        miubicacion();
    }
    //Procedemos a programar los diversos metodos
    private void agregarMarcador(double lat, double lon) {
        LatLng coordenadas = new LatLng(lat, lon);
        CameraUpdate miubicacion = CameraUpdateFactory.newLatLngZoom(coordenadas, 16);
        mMap.addMarker(new MarkerOptions().position(coordenadas)
                .title("Posicion Actual")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        mMap.animateCamera(miubicacion);
    }

    private void actualizarubicacion(Location location) {
        lat = location.getLatitude();
        lon = location.getLongitude();
        agregarMarcador(lat, lon);
    }

    LocationListener locListener = new LocationListener() {
        @Override
        public void onLocationChanged(@NonNull Location location) {
            actualizarubicacion(location);
        }
    };

    private void miubicacion() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,15000,0,locListener);
        actualizarubicacion(location);
    }
}




