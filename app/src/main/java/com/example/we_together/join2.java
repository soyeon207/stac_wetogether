package com.example.we_together;

import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class join2 extends AppCompatActivity implements View.OnClickListener{

    private EditText edit_email,edit_pwd,edit_name;
    private Button join_btn;
    private FirebaseAuth firebaseAuth;
    private String email,pwd,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join2);

        firebaseAuth = FirebaseAuth.getInstance(); // firebase의 인증과 연결

        edit_email = findViewById(R.id.email);
        edit_pwd = findViewById(R.id.pwd);
        edit_name = findViewById(R.id.name);
        join_btn = findViewById(R.id.join);

        join_btn.setOnClickListener(this);
    }

    public void emptyChk(EditText e) {
        e.requestFocus();
        e.setError("빈칸을 입력해주세요");

    }

    public void registerUser() {
        email = edit_email.getText().toString().trim();
        pwd = edit_pwd.getText().toString().trim();
        name = edit_name.getText().toString().trim();

        if(email.isEmpty()) emptyChk(edit_email);
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edit_email.requestFocus();
            edit_email.setError("이메일 형식이 올바르지 않습니다");
        }
        else if(pwd.isEmpty()) emptyChk(edit_pwd);
        else if(name.isEmpty()) emptyChk(edit_name);
        else {

        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.join:
                Log.e("a","a");
                registerUser();
                break;
        }
    }
}
