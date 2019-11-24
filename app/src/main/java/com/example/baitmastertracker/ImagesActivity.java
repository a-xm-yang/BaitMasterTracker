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
        StorageReference pathReference = storageRef.child("images/stars.jpg");

        ImageView image = findViewById(R.id.imageView);

        Glide.with(this).load(pathReference.getPath()).into(image);

        // Create a reference to a file from a Google Butt Storage URI
     //   StorageReference gsReference = FirebaseStorage.getInstance().getReferenceFromUrl("gs://bucket/images/stars.jpg");

        // Create a reference from an HTTPS URL
        // Note that in the URL, characters are URL escaped!
      //  StorageReference httpsReference = FirebaseStorage.getInstance().getReferenceFromUrl("https://firebasestorage.googleapis.com/b/bucket/o/images%20stars.jpg");

    }




}
