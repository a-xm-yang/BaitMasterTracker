package com.example.baitmastertracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private ConstraintLayout root;
    private MapView mapView;
    private GoogleMap gMap;
    private User user;

    List<LocationTime> alreadyAdded = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = (ConstraintLayout) inflater.inflate(R.layout.fragment_map, container, false);
        mapView = root.findViewById(R.id.mapView);
        mapView.onCreate(null);
        mapView.getMapAsync(this);
        return root;
    }

    public void setUser(User user) {
        this.user = user;
        showLocations();
    }

    public void showLocations() {

        if (gMap == null){
            return;
        }

        for (LocationTime lt : user.getAllLocationTime()) {

            if (alreadyAdded.contains(lt)){
                continue;
            }

            alreadyAdded.add(lt);

            LatLng loc = new LatLng(lt.getLatitude(), lt.getLongitude());
            gMap.addMarker(new MarkerOptions().position(loc).title(lt.getTime()));
            gMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;

        if(user!= null){
            showLocations();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
