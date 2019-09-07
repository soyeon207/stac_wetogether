package com.example.we_together;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;

public class Room extends AppCompatActivity implements View.OnClickListener{

    Button make_btn, join_btn;
    FirebaseAuth firebaseAuth;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        make_btn = findViewById(R.id.make);
        join_btn = findViewById(R.id.join);
        textView = findViewById(R.id.text);

        make_btn.setOnClickListener(this);
        join_btn.setOnClickListener(this);

        firebaseAuth = firebaseAuth.getInstance();
        textView.setText(firebaseAuth.getCurrentUser().getUid());

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.join:
                startActivity(new Intent(Room.this,JoinRoom.class));

                break;
            case R.id.make:
                startActivity(new Intent(Room.this,MakeRoom.class));

                break;
        }

    }
}
