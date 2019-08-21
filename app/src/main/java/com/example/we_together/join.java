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

    static ArrayList<String> IdList = new ArrayList<String>();

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

    //이미 있는 아이딘지 확인 하는 함수
    public boolean IsExistID() {
        boolean IsExist = IdList.contains(id);
        return IsExist;
    }

    //아이디 중복 처리를 하기 위해서 파이어베이스에서 아이디를 불러오는 함수
    public void FirebaseIdCall() {
        FirebaseDatabase.getInstance().getReference().child("users").addChildEventListener(new ChildEventListener() {

            @Override // 앱에 들어갔을 때 모든 아이디를 불러옴 & 값이 추가되면 그 값을 불러옴
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                IdList.add(dataSnapshot.getKey());
            }

            @Override // firebase 값이 바뀌면 그 값을 불러옴
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }

            @Override // firebase 값이 삭제되면 그 값을 불러옴
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) { }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });
    }

    //중복되는 아이디인지 확인 하고 중복되지 않으면 Firebase에 값을 넣는 함수
    public void IsExistCheck() {
        if(IsExistID())  //중복되는 아이디가 존재할 경우
            Toast.makeText(join.this,"이미 존재 하는 아이디 입니다. 다른 아이디로 설정해주세요",Toast.LENGTH_LONG).show();
        else  //중복되는 아이디가 존재하지 않을 경우
            postFirebaseDatabase(true);
        edit_id.requestFocus(); //중복되는 아이디가 존재하기 때문에 아이디를 다시 입력하라는 의미에서 아이디에 포커싱을 해줌.
    }

    //edittext가 빈칸일 경우 해당 edittext에 error를 표시해주고 포커싱을 해줌. 반복적으로 방송되기에 함수화 함.
    public void BlankChk(EditText a) {
        a.setError("빈칸입니다");
        a.requestFocus();
    }

    @Override //버튼이 클릭되었을 때 호출되는 함수
    public void onClick(View v){

        switch (v.getId()){
            case R.id.join_btn:
                id = edit_id.getText().toString();
                name = edit_name.getText().toString();
                pwd = edit_pwd.getText().toString();

                if(id.isEmpty()) BlankChk(edit_id);
                else if(pwd.isEmpty()) BlankChk(edit_pwd);
                else if(name.isEmpty()) BlankChk(edit_name);
                else IsExistCheck();

                break;

        }
    }
}

