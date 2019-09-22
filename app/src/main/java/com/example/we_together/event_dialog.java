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
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

public class event_dialog extends Dialog {

    private EditText event_edit;
    private Button event_btn;
    String date;
    MaterialCalendarView mvie;
    Menu_calendar menu_calendar;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    public event_dialog(@NonNull Context context, String date, MaterialCalendarView mview) {
        super(context);
        this.mvie = mview;
        this.date = date;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_dialog);

        menu_calendar = new Menu_calendar();
        event_edit = findViewById(R.id.event_edit);
        event_btn = findViewById(R.id.event_btn);

        event_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getContext();

                SharedPreferences pref=context.getSharedPreferences("SAVE", context.MODE_PRIVATE);
                String invite = pref.getString("invitecode","");
                databaseReference.child("room").child(invite).child("event").child(date).push().setValue(event_edit.getText().toString());

                event_edit.setText("");
                menu_calendar.check(invite,mvie);
                dismiss();
            }
        });
    }
}
