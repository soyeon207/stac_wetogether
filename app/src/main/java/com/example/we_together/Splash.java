package com.example.we_together;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Splash extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        firebaseAuth = firebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){ // 만약 로그인을 했고

            SharedPreferences preferences = getSharedPreferences("SAVE",MODE_PRIVATE);
            String invitecode = preferences.getString("invitecode","");

            if(invitecode.isEmpty())  // 방을 만들지 않았다면
                startActivity(new Intent(Splash.this,Room.class));

            else //방도 들어가거나 만들었다면
                startActivity(new Intent(Splash.this, MainActivity.class));

            finish();
        }

        else { //로그인을 하지 않았따면
            startActivity(new Intent(Splash.this,Login.class)); // 로그인 화면으로 넘겨준다
            finish();
        }

    }
}
