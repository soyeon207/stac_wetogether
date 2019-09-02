package com.example.we_together;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Splash extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        firebaseAuth = firebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){


            Intent intent = new Intent(Splash.this,MakeRoom.class);
            startActivity(intent);
            finish();
        }

    }
}
