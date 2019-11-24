package com.example.baitmastertracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ImagesActivity extends AppCompatActivity {

    StorageReference storageRef;
    StorageReference pathRef;
    StorageReference gsRef;
    StorageReference httpsRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);

        // Create a storage reference from our app
        storageRef = FirebaseStorage.getInstance().getReference();

        // Create a reference with an initial file path and name
       // StorageReference pathReference = storageRef.child("6rceBPsyKfRp0SqzgUo654M6wMo2").child("2019.11.23 AD at 16:09:27 EST.jpg");

        StorageReference pathReference = storageRef.child("stars.jpg");

        ImageView image = findViewById(R.id.imageView);
        Glide.with(this).load(pathReference).into(image);

    }




}
