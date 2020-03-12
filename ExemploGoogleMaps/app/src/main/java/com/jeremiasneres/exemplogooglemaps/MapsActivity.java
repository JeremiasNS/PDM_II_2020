package com.jeremiasneres.exemplogooglemaps;

import androidx.appcompat.app.AppCompatActivity;

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

    private static final LatLngBounds IFTO_PALMAS = new LatLngBounds(
            new LatLng(-10.197874, -48.311135), new LatLng(-10.191523, -48.310659));

    private static final CameraPosition ADELAIDE_CAMERA = new CameraPosition
            .Builder().target(new LatLng(-34.92873, -138.59995))
            .zoom(20.0f).bearing(0).tilt(0).build();

    private static final CameraPosition IFTO_PALMAS_CAMERA = new CameraPosition
            .Builder().target(new LatLng(-34.92873, -138.59995))
            .zoom(20.0f).bearing(0).tilt(0).build();

    private float mMinZoom, nMaxZoom;
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
        mMinZoom = DEFAULT_MIN_ZOOM;
        nMaxZoom = DEFAULT_MAX_ZOOM;
    }



    public void onClampToAdelaide(View view) {
        if (!checkReady()) {
            return;
        }
        mMap.setLatLngBoundsForCameraTarget(ADELAIDE);
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(ADELAIDE_CAMERA));
    }

    public void onClampToIFTO(View view) {
        if (!checkReady()) {
            return;
        }
        mMap.setLatLngBoundsForCameraTarget(IFTO_PALMAS);
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(IFTO_PALMAS_CAMERA));
    }

    public void onLanLngClampToReset(View view) {
        if (!checkReady()) {
            return;
        }
        mMap.setLatLngBoundsForCameraTarget(null);
        toast("Limites de lat e lng resetados");
    }

    public void onSetMinZoomClamp(View view) {
        if (!checkReady()) {
            return;
        }
        mMinZoom = mMinZoom + ZOOM_DELTA;
        mMap.setMinZoomPreference(mMinZoom);
        toast("Min Zoom configurado!");
    }

    public void onSetMaxZoomClamp(View view) {
        if (!checkReady()) {
            return;
        }
        nMaxZoom = nMaxZoom - ZOOM_DELTA;
        mMap.setMaxZoomPreference(nMaxZoom);
        toast("Max Zoom Configurado!");
    }

    public void onSetMinMaxZoomClamp(View view) {
        if (!checkReady()) {
            return;
        }
        resetMinMaxZoom();
        mMap.resetMinMaxZoomPreference();
        toast("Zoom Min Max Configurado!");
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

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

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    public void onCameraIdle() {
        //Quando o movimento da câmera no mapa parar, sera chamado
        mCameraTextView.setText(mMap.getCameraPosition().toString());
    }

    public void onClampToPacific(View view) {
    }

    private boolean checkReady() {
        if (mMap == null) {
            Toast.makeText(getApplicationContext(), "Mapa ainda não disponivel", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void toast(String msg) {
        Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_SHORT).show();
    }


}
