package com.example.we_together;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View.OnClickListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

    static ArrayList<String> arrayIndex = new ArrayList<String>();
    static ArrayList<String> arrayData = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        edit_id = findViewById(R.id.id);
        edit_pwd = findViewById(R.id.password);
        edit_name = findViewById(R.id.name);
        join_btn = findViewById(R.id.join_btn);

        join_btn.setOnClickListener(this); //join_btn을 클릭하면 밑에 있는 onclick 함수가 실행되게 된다

        getFirebaseId();
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

    public void getFirebaseId() {
        ValueEventListener postListner = new ValueEventListener() { //경로의 전체 내용에 대한 변경 사항을 읽고 수신 대기
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //이벤트 발생 시점을 기준으로 지정된 경로에 있는 내용의 정적 스냅샷을 읽을 수 있다
                //이 메소드는 리스너가 연결될 떄 한 번 호출된 후 하위를 포함한 데이터가 변경될 때마다 다시 호출됨.
                //데이터가 없으면 스냅샷은 exists() 호출 시 false를 반환하고, getValue() 호출 시 null을 반환
                arrayData.clear();
                arrayIndex.clear();
                Log.e("name","getFirebaseId");
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Log.e("id",postSnapshot.getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { //읽기가 취소된 경우에 호출 되는 메소드

            }
        };
    }

    @Override
    public void onClick(View v){

        switch (v.getId()){
            case R.id.join_btn:
                id = edit_id.getText().toString();
                name = edit_name.getText().toString();
                pwd = edit_pwd.getText().toString();

                getFirebaseId();
                postFirebaseDatabase(true);
                break;
        }
    }
}

