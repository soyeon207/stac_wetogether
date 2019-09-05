package com.example.we_together;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener{
    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;
    FirebaseAuth firebaseAuth;
    Button logout_btn;

    TextView text_name,text_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        firebaseAuth = firebaseAuth.getInstance();
        logout_btn = findViewById(R.id.logout);

        text_name = findViewById(R.id.room_name);
        text_code = findViewById(R.id.room_code);

        SharedPreferences preferences = getSharedPreferences("SAVE",MODE_PRIVATE);
        text_code.setText("초대코드 " +preferences.getString("invitecode",""));
        text_name.setText("방 이름 " +preferences.getString("room",""));

        logout_btn.setOnClickListener(this);
        Log.e("uid",firebaseAuth.getCurrentUser().getUid());

    }

    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime)
        {
            super.onBackPressed();
        }
        else
        {
            backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(), "한 번 더 뒤로가기를 누르면 꺼집니다.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logout:

                SharedPreferences pref2 = getSharedPreferences("SAVE", MODE_PRIVATE);
                SharedPreferences.Editor editor2 = pref2.edit();
                editor2.clear();
                editor2.commit(); // SharedPreferences 의 데이터 모두 삭제

                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(this,Login.class));
                break;
        }
    }
}

