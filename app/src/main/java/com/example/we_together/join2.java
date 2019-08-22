package com.example.we_together;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class join2 extends AppCompatActivity implements View.OnClickListener{

    private EditText edit_email,edit_pwd,edit_name;
    private Button join_btn;
    private FirebaseAuth firebaseAuth;
    private String email,pwd,name;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join2);

        firebaseAuth = FirebaseAuth.getInstance(); // firebase의 인증과 연결

        edit_email = findViewById(R.id.email);
        edit_pwd = findViewById(R.id.pwd);
        edit_name = findViewById(R.id.name);
        join_btn = findViewById(R.id.join);
        progressBar = findViewById(R.id.pb);

        join_btn.setOnClickListener(this);
    }

    public void emptyChk(EditText e) { // EditText가 빈 칸일 경우 포커스와 에러 메시지를 알려주는 함수
        e.requestFocus();
        e.setError("빈칸을 입력해주세요");

    }

    public void registerUser() { // firebase에 사용자를 등록하기 위한 함수
        email = edit_email.getText().toString().trim();
        pwd = edit_pwd.getText().toString().trim();
        name = edit_name.getText().toString().trim();

        if(email.isEmpty()) emptyChk(edit_email); // 이메일이 입력되지 않은 경우
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) { // 이메일 형식이 옳지 않은 경우
            edit_email.requestFocus();
            edit_email.setError("이메일 형식이 올바르지 않습니다");
        }
        else if(pwd.isEmpty()) emptyChk(edit_pwd); // 비밀번호가 입력되지 않은 경우
        else if(name.isEmpty()) emptyChk(edit_name); // 이름이 입력되지 않은 경우
        else if(pwd.length()<6) {
            edit_pwd.requestFocus();
            edit_pwd.setError("6글자 이상 등록해주세요");
        }
        else { // 모든 EditText가 정상적으로 입력 된 경우

            progressBar.setVisibility(View.VISIBLE);
            firebaseAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    try{
                        if(task.isSuccessful()){
                            finish();
                            progressBar.setVisibility(View.INVISIBLE);
                            startActivity(new Intent(getApplication(),Test.class));

                        }
                    }catch (Exception e){
                        Toast.makeText(join2.this,"이미 등록되어 있는 이메일입니다. ",Toast.LENGTH_SHORT).show();
                        edit_email.requestFocus();
                        edit_email.setError("이메일 형식이 ");
                        Log.e("a",e.getMessage());
                    }

                }
            });


        }


    }

    @Override
    public void onClick(View v) { // 버튼이 클리되었을 때
        switch (v.getId()){
            case R.id.join:
                registerUser();
                break;
        }
    }
}
