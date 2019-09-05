package com.example.we_together;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class JoinRoom extends AppCompatActivity {

    EditText edit_code;
    Button btn_code;
    String invite_code;
    FirebaseDatabase mdatabase = FirebaseDatabase.getInstance();
    DatabaseReference mdatabaseRef = mdatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_room);

        edit_code = findViewById(R.id.code);
        btn_code = findViewById(R.id.button);

        btn_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                invite_code = edit_code.getText().toString();

                mdatabaseRef.child("name").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                            if(snapshot.getKey().equals(invite_code)){
                                SharedPreferences preferences = getSharedPreferences("SAVE",MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("invitecode",snapshot.getKey());
                                editor.putString("room",snapshot.getValue().toString());
                                editor.commit();

                                startActivity(new Intent(JoinRoom.this, MainActivity2.class));
                                finish();
                            }
                        }



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                edit_code.setError("초대코드를 확인해주세요");
            }
        });
    }
}
