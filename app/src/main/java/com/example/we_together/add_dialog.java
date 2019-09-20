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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class add_dialog extends Dialog {

    private ImageView exit_btn;
    private Button add_btn;
    private View.OnClickListener exit_listner;
    private Spinner spinner,p_spinner;
    private CheckBox ch1,ch2,ch3,ch4,ch5,ch6,ch7;
    private EditText d_time,d_do;

    SharedPreferences preferences;

    FirebaseDatabase mdatabase = FirebaseDatabase.getInstance();
    DatabaseReference mdatabaseRef = mdatabase.getReference();

    ArrayList<String> list= new ArrayList<>();
    ArrayAdapter<String> adapter;

    ArrayList<String> list_p = new ArrayList<>();
    ArrayAdapter<String> adapter_p;
    SharedPreferences s;

    String p_value="",p_value2="",value;

    public add_dialog(@NonNull Context context, View.OnClickListener exit_listner) {
        super(context);

         s = context.getSharedPreferences("SAVE",Context.MODE_PRIVATE);

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
                p_value2 = list_p.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                p_value = list.get(i);
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

        d_time = findViewById(R.id.d_time);
        d_do = findViewById(R.id.d_do);
        exit_btn = findViewById(R.id.add_close);
        add_btn = findViewById(R.id.add_btn);
        spinner = findViewById(R.id.spinner);
        p_spinner = findViewById(R.id.p_spinner);

        ch1 = findViewById(R.id.ch1);
        ch2 = findViewById(R.id.ch2);
        ch3 = findViewById(R.id.ch3);
        ch4 = findViewById(R.id.ch4);
        ch5 = findViewById(R.id.ch5);
        ch6 = findViewById(R.id.ch6);
        ch7 = findViewById(R.id.ch7);

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(p_value.equals(""))
                    Toast.makeText(getContext(),"사람을 설정해주세요",Toast.LENGTH_LONG).show();

//                else if(p_value2.equals(""))
//                    Toast.makeText(getContext(),"위치를 설정해주세요",Toast.LENGTH_LONG).show();

                else if(d_do==null)
                    Toast.makeText(getContext(),"시간을 설정해주세요",Toast.LENGTH_LONG).show();

                else if(d_time==null)
                    Toast.makeText(getContext(),"할일을 설정해주세요",Toast.LENGTH_LONG).show();

                else {
                    value = p_value+" " +d_do.getText()+" "+d_time.getText();
                    check(ch1,"mon",value); check(ch2,"tue",value); check(ch3,"wed",value); check(ch4,"thu",value);
                    check(ch5,"fri",value); check(ch6,"sat",value); check(ch7,"sun",value);

                    Toast.makeText(getContext(),"값이 정상적으로 입력되었습니다",Toast.LENGTH_LONG).show();
                }




            }
        });

        exit_btn.setOnClickListener(exit_listner);
    }

    public void check(CheckBox ch,String day,String val){
        if(ch.isChecked()){

            mdatabaseRef.child("room").child(s.getString("invitecode","")).child("day").child(day).push().setValue(val);



        }
    }

}
