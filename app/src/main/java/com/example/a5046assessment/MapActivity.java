package com.example.a5046assessment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;

import com.example.a5046assessment.databinding.ActivityMapBinding;
import com.mapbox.geojson.Point;
import com.mapbox.maps.CameraOptions;
import com.mapbox.maps.MapView;
import com.mapbox.maps.Style;
import com.mapbox.maps.plugin.annotation.AnnotationPlugin;
import com.mapbox.maps.plugin.annotation.AnnotationPluginImplKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManagerKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;

import java.io.IOException;
import java.util.List;

public class MapActivity extends AppCompatActivity {
    private ActivityMapBinding binding;
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMapBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.startMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = binding.addressEditText.getText().toString();
                Geocoder geocoder = new Geocoder(MapActivity.this);
                List<Address> addressList;
                double longitude;
                double latitude;
                try {
                    addressList = geocoder.getFromLocationName(address, 1);
                    if (addressList != null) {
                        longitude = addressList.get(0).getLongitude();
                        latitude = addressList.get(0).getLatitude();

                        binding.longitudeTextView.setText("Lng: " + String.valueOf(longitude));
                        binding.latitudeTextView.setText("Lat: " + String.valueOf(latitude));

                        final Point point = Point.fromLngLat(longitude, latitude);
                        CameraOptions cameraPosition = new CameraOptions.Builder().zoom(13.0).center(point).build();
                        binding.mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                            @Override
                            public void onStyleLoaded(@NonNull Style style) {
                                AnnotationPlugin annotationAPI = AnnotationPluginImplKt.
                                        getAnnotations(binding.mapView);
                                PointAnnotationManager pointAnnotationManager = PointAnnotationManagerKt.
                                        createPointAnnotationManager(annotationAPI, binding.mapView);
                                PointAnnotationOptions pointAnnotationOptions = new PointAnnotationOptions()
                                        .withPoint(com.mapbox.geojson.Point.fromLngLat(longitude, latitude))
                                        .withIconImage(BitmapFactory.decodeResource(getResources(), R.drawable.red_marker));
                                pointAnnotationManager.create(pointAnnotationOptions);
                            }
                        });
                        binding.mapView.getMapboxMap().setCamera(cameraPosition);
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

                /*
                double longitude = Double.parseDouble(binding.longitudeEditText.getText().toString());
                double latitude = Double.parseDouble(binding.latitudeEditText.getText().toString());

                final Point point = Point.fromLngLat(longitude, latitude);
                CameraOptions cameraPosition = new CameraOptions.Builder().zoom(13.0).center(point).build();
                binding.mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {
                        AnnotationPlugin annotationAPI = AnnotationPluginImplKt.
                                getAnnotations(binding.mapView);
                        PointAnnotationManager pointAnnotationManager = PointAnnotationManagerKt.
                                createPointAnnotationManager(annotationAPI, binding.mapView);
                        PointAnnotationOptions pointAnnotationOptions = new PointAnnotationOptions()
                                .withPoint(com.mapbox.geojson.Point.fromLngLat(longitude, latitude))
                                .withIconImage(BitmapFactory.decodeResource(getResources(), R.drawable.red_marker));
                        pointAnnotationManager.create(pointAnnotationOptions);
                    }
                });
                binding.mapView.getMapboxMap().setCamera(cameraPosition);
                */
            }
        });
    }
}