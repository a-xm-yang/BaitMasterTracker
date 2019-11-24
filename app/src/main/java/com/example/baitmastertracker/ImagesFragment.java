package com.example.baitmastertracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class ImagesFragment extends Fragment {

    ConstraintLayout root;
    List<ImageView> faces = new ArrayList<>();
    StorageReference ref = FirebaseStorage.getInstance().getReference();
    FirebaseAuth mAuth;

    LinearLayout linear;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = (ConstraintLayout) inflater.inflate(R.layout.fragment_images, container, false);
        return root;
    }

    public void populateFaces(List<String> urls){


        ScrollView scroll = root.findViewById(R.id.scroll);
        scroll.removeAllViews();

        mAuth = FirebaseAuth.getInstance();
        linear = new LinearLayout(this.getActivity());
        linear.setOrientation(LinearLayout.VERTICAL);

        for (String s: urls) {
            StorageReference endpoint = ref.child(mAuth.getUid()).child(s);
            ImageView image = new ImageView(this.getActivity());
            faces.add(image);
            Glide.with(this).load(endpoint).into(image);
            linear.addView(image);
        }

        scroll.addView(linear);
    }
}
