package com.example.we_together;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class JoinRoom extends AppCompatActivity {

    EditText edit_code;
    Button btn_code;
    String invite_code,room_name,code_name;
    FirebaseDatabase mdatabase = FirebaseDatabase.getInstance();
    DatabaseReference mdatabaseRef = mdatabase.getReference();
    int a=0;

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
                mdatabaseRef.child("room").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                            if(snapshot.getKey().equals(invite_code)){ // 방 이름이 있다면

                                // 방이름을 저장해준다
                                mdatabaseRef.child("room").child(invite_code).child("name").addValueEventListener(new ValueEventListener() {

                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for(DataSnapshot s:dataSnapshot.getChildren()){
                                            room_name = s.getValue().toString();

                                            if(a==0)
                                                edit_code.setError("초대코드를 확인해주세요");

                                            else {
                                                SharedPreferences preferences = getSharedPreferences("SAVE",MODE_PRIVATE);
                                                SharedPreferences.Editor editor = preferences.edit();

                                                editor.putString("room",room_name);
                                                editor.putString("invitecode",code_name);
                                                editor.commit();

                                                startActivity(new Intent(JoinRoom.this, MainActivity.class));
                                                finish();
                                            }
                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) { }
                                });

                                a=1;

                                // 회원 정보 저장
                                code_name = snapshot.getKey();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) { }
                });

            }
        });
    }
}
