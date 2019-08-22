package com.example.we_together;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class join2 extends AppCompatActivity implements View.OnClickListener{

    private EditText edit_email,edit_pwd,edit_name;
    private Button join_btn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join2);

        mAuth = FirebaseAuth.getInstance();

        edit_email = findViewById(R.id.email);
        edit_pwd = findViewById(R.id.pwd);
        edit_name = findViewById(R.id.name);
        join_btn = findViewById(R.id.join);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.join:

                break;
        }
    }
}
