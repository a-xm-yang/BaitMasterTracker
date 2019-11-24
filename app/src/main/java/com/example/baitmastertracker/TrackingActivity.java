package com.example.baitmastertracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrackingActivity extends AppCompatActivity{

    User user;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    Map<FragmentType, Fragment> fragmentMap;
    FragmentType currentFragment;

    Toolbar toolbar;

    enum FragmentType {IMAGE, MAP, SETTING}

    GoogleMap map;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);

        TabLayout tab = findViewById(R.id.tab);
        tab.addOnTabSelectedListener(tabListener);

        toolbar = findViewById(R.id.toolbar);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        initializeFragmentMap();
        loadUser();
    }

    @Override
    protected void onDestroy() {
        fragmentMap = null;
        mAuth = null;
        mDatabase = null;
        super.onDestroy();
    }

    private void initializeFragmentMap() {
        fragmentMap = new HashMap<>();

        fragmentMap.put(FragmentType.SETTING, new SettingFragment());
        fragmentMap.put(FragmentType.MAP, new MapFragment());
        fragmentMap.put(FragmentType.IMAGE, new ImagesFragment());

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        for (FragmentType type : fragmentMap.keySet()) {
            ft.add(R.id.fragmentContainer, fragmentMap.get(type), type.name());
            ft.hide(fragmentMap.get((type)));
        }
        ft.show(fragmentMap.get(FragmentType.MAP));
        ft.commit();
        currentFragment = FragmentType.MAP;
    }

    public void switchFragment(FragmentType type) {

        getSupportFragmentManager().beginTransaction()
                .hide(fragmentMap.get(currentFragment))
                .show(fragmentMap.get(type))
                .commit();
        currentFragment = type;

        switch (type) {
            case MAP:
                toolbar.setTitle("LOCATION TRACKING");
                break;
            case SETTING:
                toolbar.setTitle("ACCOUNT");
                break;
            case IMAGE:
                toolbar.setTitle("VIEW PICTURES");
                break;
        }
    }

    private void loadUser() {
        System.out.println(mAuth.getUid());
        mDatabase.child("Peeps").child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(User.class);
                ((MapFragment)fragmentMap.get(FragmentType.MAP)).setUser(user);
                ((ImagesFragment)fragmentMap.get(FragmentType.IMAGE)).populateFaces(user.getAllImageUrl());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private TabLayout.OnTabSelectedListener tabListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            int num = tab.getPosition();

            switch(num){
                case 0:
                    switchFragment(FragmentType.MAP);
                    break;
                case 1:
                    switchFragment(FragmentType.IMAGE);
                    break;
                case 2:
                    switchFragment(FragmentType.SETTING);
                    break;
            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };

    public void logOut() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        finish();
    }
}
