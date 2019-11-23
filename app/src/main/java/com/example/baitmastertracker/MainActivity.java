package com.example.baitmastertracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MainActivity extends AppCompatActivity {

    ImageView image;

    private FirebaseAuth mAuth;
    private String email;
    private String password;
    private DatabaseReference mDatabase;
    private User user;
    private StorageReference mStorageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mStorageRef = FirebaseStorage.getInstance().getReference();
    }

    public void login(View view) {
        EditText usernameField = findViewById(R.id.username);
        EditText passwordField = findViewById(R.id.password);
        email = usernameField.getText().toString();
        password = passwordField.getText().toString();

        showLoading();
        login();
    }

    private void showLoading(){
        ProgressBar bar = findViewById(R.id.loginProgressBar);
        bar.setVisibility(View.VISIBLE);
    }

    private void finishLoading(){
        ProgressBar bar = findViewById(R.id.loginProgressBar);
        bar.setVisibility(View.GONE);
    }

    public void displayErrorMessage(String message) {
        EditText passwordField = findViewById(R.id.password);
        passwordField.setError(message);
        finishLoading();
    }

    private void login(){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        finishLoading();
                        if (task.isSuccessful()) {
                            System.out.println(mAuth.getUid());
                            mDatabase.child("Peeps").child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    user = dataSnapshot.getValue(User.class);
                                    System.out.println(mAuth.getUid());

                                    StorageReference riversRef = mStorageRef.child(mAuth.getUid());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                    displayErrorMessage("Connection Error");
                                }
                            });
                        } else{
                            displayErrorMessage("Incorrect Username or Password");
                        }
                    }
                });
    }
}
