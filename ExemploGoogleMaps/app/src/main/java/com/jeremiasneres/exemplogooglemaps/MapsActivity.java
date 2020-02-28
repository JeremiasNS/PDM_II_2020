package com.jeremiasneres.exemplogooglemaps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends
        AppCompatActivity implements
        OnMapReadyCallback, GoogleMap.OnCameraIdleListener {

    private static final String TAG = MapsActivity.class.getSimpleName();
    private GoogleMap mMap;
    private static final float ZOOM_DELTA = 2.0f;
    private static final float DEFAULT_MIN_ZOOM = 2.0f;
    private static final float DEFAULT_MAX_ZOOM = 22.0f;

    private static final LatLngBounds ADELAIDE = new LatLngBounds(
            new LatLng(-35.0, 138.58), new LatLng(-34.9, 138.61));

    private static final CameraPosition ADELAIDE_CAMERA = new CameraPosition
            .Builder().target(new LatLng(-34.92873, -138.59995))
            .zoom(20.0f).bearing(0).tilt(0).build();

    private float nMinZoom, nMaxZoom;
    private TextView mCameraTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        mMap = null;
        resetMinMaxZoom();
        mCameraTextView = findViewById(R.id.camera_text);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    private void resetMinMaxZoom() {
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnCameraIdleListener(this);

        /*// Add a marker in Sydney and move the camera
        LatLng homeJeremiasNeres = new LatLng(-10.340801, -48.288268);
        mMap.addMarker(new MarkerOptions().position(homeJeremiasNeres).title("Esta é a localização da casa do Jeremias!"));
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(homeJeremiasNeres));
        mMap.setMinZoomPreference(15.0f);*/

    }

    public void onClampToJeremias(View view) {
    }

    public void onClampToPacific(View view) {
    }

    public void onLanLngClampToReset(View view) {
    }

    public void onClampToMinZoom(View view) {
    }

    public void onClampToMaxZoom(View view) {
    }

    @Override
    public void onCameraIdle() {
        //Quando o movimento da câmera no mapa parar, sera chamado
        mCameraTextView.setText(mMap.getCameraPosition().toString());
    }

    private boolean checkReady() {
        if (mMap == null) {
            Toast.makeText(getApplicationContext(), "O mapa não está pronto ainda", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


}
