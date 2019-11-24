package com.example.baitmastertracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class TrackingActivity extends AppCompatActivity {

    User user;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    Map<FragmentType, Fragment> fragmentMap;
    FragmentType currentFragment;
    enum FragmentType{IMAGE, MAP, SETTING}

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);

        TabLayout tab = findViewById(R.id.tab);
        tab.addOnTabSelectedListener(tabListener);

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

    private void initializeFragmentMap(){
        fragmentMap = new HashMap<>();

        // put stuff
    }

    private void loadUser(){
        System.out.println(mAuth.getUid());
        mDatabase.child("Peeps").child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(User.class);
                System.out.println(mAuth.getUid());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private TabLayout.OnTabSelectedListener tabListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            //do something
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };

    public void logOut(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        finish();
    }
}
