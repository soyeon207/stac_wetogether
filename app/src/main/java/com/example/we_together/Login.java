package com.example.we_together;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    Button join,login;
    EditText edit_email,edit_pwd;
    FirebaseAuth firebaseAuth;
    String email,pwd;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
                email = edit_email.getText().toString().trim();
                pwd = edit_pwd.getText().toString().trim();

                Log.e("email",email);
                Log.e("pwd",pwd);
                firebaseAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(Login.this, Room.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(Login.this, "아이디나 비밀번호를 확인해주세요", Toast.LENGTH_LONG).show();
                            }

                    }
                });

                break;
            case R.id.join:
                Intent intent = new Intent(Login.this,join2.class);
                startActivity(intent);
                break;
        }
    }
}
