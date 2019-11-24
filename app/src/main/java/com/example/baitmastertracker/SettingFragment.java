package com.example.baitmastertracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

public class SettingFragment extends Fragment {

    ConstraintLayout rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ConstraintLayout) inflater.inflate(R.layout.fragment_account, container, false);
        attachListener();
        return rootView;
    }

    private void attachListener(){
        Button b = rootView.findViewById(R.id.logOut);
        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ((TrackingActivity)getActivity()).logOut();
            }
        });
    }
}
