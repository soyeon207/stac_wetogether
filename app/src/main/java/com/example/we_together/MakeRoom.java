package com.example.we_together;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class
MakeRoom extends AppCompatActivity implements View.OnClickListener{

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    EditText edit_roomname;
    Button button;
    FirebaseAuth firebaseAuth;
    String room;
    String invitecode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_room);

        button = findViewById(R.id.btn);
        edit_roomname = findViewById(R.id.roomname);
        button.setOnClickListener(this);
        edit_roomname.setOnClickListener(this);

        firebaseAuth = firebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn:

                room = edit_roomname.getText().toString();
                invitecode = firebaseAuth.getCurrentUser().getUid().substring(0,6);

                SharedPreferences preferences = getSharedPreferences("SAVE",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("invitecode",invitecode);
                editor.putString("room",room);
                editor.commit();

                databaseReference.child("room").child(invitecode).child("name").push().setValue(room);
                databaseReference.child("room").child(invitecode).child("user").push().setValue(preferences.getString("name",""));

                Map<String, Object> childUpdates = new HashMap<>();
                Map<String, Object> postValues = null;

                FirebaseJoin post = new FirebaseJoin(invitecode,room);
                postValues = post.toMap();

                childUpdates.put("/users/" + preferences.getString("code","")+"/room", postValues);
                databaseReference.updateChildren(childUpdates);


                startActivity(new Intent(MakeRoom.this,placeActivity.class));
                finish();
                break;
        }
    }
}
