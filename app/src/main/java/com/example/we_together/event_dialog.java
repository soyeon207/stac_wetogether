package com.example.we_together;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class event_dialog extends Dialog {

    private EditText event_edit;
    private Button event_btn;
    String date;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    public event_dialog(@NonNull Context context,String date) {
        super(context);
        this.date = date;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_dialog);

        event_edit = findViewById(R.id.event_edit);
        event_btn = findViewById(R.id.event_btn);

        event_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getContext();

                SharedPreferences pref=context.getSharedPreferences("SAVE", context.MODE_PRIVATE);
                databaseReference.child("room").child(pref.getString("invitecode","")).child("event").child(date).push().setValue(event_edit.getText().toString());

                event_edit.setText("");
                dismiss();
            }
        });
    }
}
