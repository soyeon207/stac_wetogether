package com.example.we_together;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Splash extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        firebaseAuth = firebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){ // 만약 로그인을 했다면

            Intent intent = new Intent(Splash.this,MainActivity.class); //메인 화면으로 넘겨주고
            startActivity(intent);
            finish();
        }

        else { //로그인을 하지 않았따면
            startActivity(new Intent(Splash.this,Login.class)); // 로그인 화면으로 넘겨준다
            finish();
        }

    }
}
