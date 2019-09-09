package com.example.we_together;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Room extends AppCompatActivity implements View.OnClickListener{

    Button make_btn, join_btn;
    FirebaseAuth firebaseAuth;
    TextView textView;
    String user_code;

    public void find_name(){
        Log.e("실행","실행");
        FirebaseDatabase mdatabase = FirebaseDatabase.getInstance();
        DatabaseReference mdatabaseRef = mdatabase.getReference();

        mdatabaseRef.child("users").child(user_code).child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot message:dataSnapshot.getChildren()){

                    SharedPreferences preferences = getSharedPreferences("SAVE",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("name",message.getValue().toString());

                    Log.e("name",message.getValue().toString());

                    editor.commit();


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }

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
        user_code = firebaseAuth.getCurrentUser().getUid();
        textView.setText(user_code);

        find_name();

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
