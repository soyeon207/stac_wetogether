package com.example.we_together;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View.OnClickListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class join extends AppCompatActivity implements OnClickListener{

    private DatabaseReference mPostReference; //Firebse에서 데이터를 읽거나 쓰기 위해서 필요한 인스턴스

    EditText edit_id,edit_pwd,edit_name;
    Button join_btn;
    String id,name,pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        edit_id = findViewById(R.id.id);
        edit_pwd = findViewById(R.id.password);
        edit_name = findViewById(R.id.name);
        join_btn = findViewById(R.id.join_btn);

        join_btn.setOnClickListener(this); //join_btn을 클릭하면 밑에 있는 onclick 함수가 실행되게 된다

        FirebaseIdCall();
    }

    public void postFirebaseDatabase(boolean add){
        mPostReference = FirebaseDatabase.getInstance().getReference(); // Firebase의 정보를 불러옴
        Map<String,Object> childUpdates = new HashMap<>();
        Map<String,Object> postValues=null;

        if(add){
            FirebaseJoin join = new FirebaseJoin(pwd,name,id);
            postValues = join.toMap();
        }

        childUpdates.put("/users/"+id+"/info",postValues);
        mPostReference.updateChildren(childUpdates);
    }

    public void FirebaseIdCall() { //아이디 중복 처리를 하기 위해서 파이어베이스에서 아이디를 불러오는 함수
        FirebaseDatabase.getInstance().getReference().child("users").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                // 앱에 들어갔을 때 모든 아이디를 불러옴 & 값이 추가되면 그 값을 불러옴
                Log.e("MainActivity", "ChildEventListener - onChildAdded : " + dataSnapshot.getKey());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                // firebase 값이 바뀌면 그 값을 불러옴
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                // firebase 값이 삭제되면 그 값을 불러옴
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });
    }

    @Override
    public void onClick(View v){

        switch (v.getId()){
            case R.id.join_btn:
                id = edit_id.getText().toString();
                name = edit_name.getText().toString();
                pwd = edit_pwd.getText().toString();

                postFirebaseDatabase(true);
                break;

        }
    }
}

