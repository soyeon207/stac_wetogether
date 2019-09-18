package com.example.we_together;


import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity implements View.OnClickListener{

    TextView join;
    Button login;
    EditText edit_email,edit_pwd;
    FirebaseAuth firebaseAuth;
    String email,pwd;
    ProgressBar progressBar;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        progressBar = findViewById(R.id.login_progress);
        progressBar.setVisibility(View.INVISIBLE);
        join = findViewById(R.id.join);
        login = findViewById(R.id.login);
        edit_email = findViewById(R.id.login_email);
        edit_pwd = findViewById(R.id.login_pwd);

        join.setOnClickListener(this);
        login.setOnClickListener(this);

        firebaseAuth = firebaseAuth.getInstance();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                progressBar.setVisibility(View.VISIBLE);
                email = edit_email.getText().toString().trim();
                pwd = edit_pwd.getText().toString().trim();

                if(pwd.isEmpty()) {
                   edit_pwd.requestFocus();
                   edit_pwd.setError("비밀번호를 입력해주세요");
                   progressBar.setVisibility(View.INVISIBLE);
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) { // 이메일 형식이 옳지 않은 경우
                    progressBar.setVisibility(View.INVISIBLE);
                    edit_email.requestFocus();
                    edit_email.setError("이메일 형식이 올바르지 않습니다");
                }

                else {





                firebaseAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(Login.this, Room.class);
                                startActivity(intent);
                                progressBar.setVisibility(View.INVISIBLE);
                                finish();
                            } else {
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(Login.this, "아이디나 비밀번호를 확인해주세요", Toast.LENGTH_LONG).show();
                            }
                    }
                });

                }
                break;
            case R.id.join:
                Intent intent = new Intent(Login.this,join2.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
