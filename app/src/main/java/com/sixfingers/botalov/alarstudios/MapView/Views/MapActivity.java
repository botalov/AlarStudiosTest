package com.sixfingers.botalov.alarstudios.MapView.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sixfingers.botalov.alarstudios.ListActivity.Models.Data;
import com.sixfingers.botalov.alarstudios.R;

public class MapActivity extends AppCompatActivity implements IMapView, OnMapReadyCallback {
    private Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        initViews();
    }

    private void initViews(){
        TextView idTextView = findViewById(R.id.id_text_view);
        TextView nameTextView = findViewById(R.id.name_text_view);
        TextView countryTextView = findViewById(R.id.country_text_view);
        TextView latTextView = findViewById(R.id.lat_text_view);
        TextView lonTextView = findViewById(R.id.lon_text_view);

        Bundle args = getIntent().getExtras();
        if(args != null){
            data = (Data) args.getSerializable(DATA_ARG);

            if(data != null) {
                idTextView.setText(data.getId());
                nameTextView.setText(data.getName());
                countryTextView.setText(data.getCountry());
                latTextView.setText(String.valueOf(data.getLat()));
                lonTextView.setText(String.valueOf(data.getLon()));

                SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_fragment);
                mapFragment.getMapAsync(this);
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        GoogleMap map = googleMap;
        LatLng sydney = new LatLng(data.getLat(), data.getLon());
        map.addMarker(new MarkerOptions().position(sydney).title(data.getName()));
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
