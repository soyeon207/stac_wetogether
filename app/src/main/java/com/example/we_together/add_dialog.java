package com.example.we_together;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class add_dialog extends Dialog {

    private ImageView exit_btn;
    private View.OnClickListener exit_listner;
    private Spinner spinner,p_spinner;

    SharedPreferences preferences;

    FirebaseDatabase mdatabase = FirebaseDatabase.getInstance();
    DatabaseReference mdatabaseRef = mdatabase.getReference();

    ArrayList<String> list= new ArrayList<>();
    ArrayAdapter<String> adapter;

    ArrayList<String> list_p = new ArrayList<>();
    ArrayAdapter<String> adapter_p;

    public add_dialog(@NonNull Context context, View.OnClickListener exit_listner) {
        super(context);

        SharedPreferences s = context.getSharedPreferences("SAVE",Context.MODE_PRIVATE);

        mdatabaseRef.child("room").child(s.getString("invitecode","")).child(("place")).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot message:dataSnapshot.getChildren()){
                    list_p.add(message.getKey());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        mdatabaseRef.child("room").child(s.getString("invitecode","")).child("user").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot message:dataSnapshot.getChildren()){
                    list.add(message.getValue().toString());

                    Log.e("value",message.getValue().toString());
                }

                List();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        this.exit_listner = exit_listner;
    }

    public void List(){ // 사람 리스트를 불러온다

        adapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item,list);
        adapter_p = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item,list_p);

        spinner = findViewById(R.id.spinner);
        p_spinner = findViewById(R.id.p_spinner);

        spinner.setAdapter(adapter);
        p_spinner.setAdapter(adapter_p);

        p_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("spinner",list.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;
        getWindow().setAttributes(layoutParams);

        setContentView(R.layout.activity_add_dialog);

        exit_btn = findViewById(R.id.add_close);
        spinner = findViewById(R.id.spinner);
        p_spinner = findViewById(R.id.p_spinner);

        exit_btn.setOnClickListener(exit_listner);
    }
}
