package com.example.we_together;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;


import java.util.ArrayList;

public class placeActivity extends AppCompatActivity /*implements CompoundButton.OnCheckedChangeListener*/{
    private CheckBox ch1;
    private CheckBox ch2;
    private CheckBox ch3;
    private CheckBox ch4;
    private CheckBox ch5;
    private CheckBox ch6;
    private CheckBox ch7;
    private CheckBox ch8;
    private CheckBox ch9;
    private CheckBox ch10;
    private Button btn;
    int cnt=0;
    LinearLayout linearLayout;
    public ArrayList<String> placeList = new ArrayList<>();
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);
        linearLayout = findViewById(R.id.linear1);
        ch1 = (CheckBox)findViewById(R.id.checkBox2);
        ch2 = (CheckBox)findViewById(R.id.checkBox3);
        ch3 = (CheckBox)findViewById(R.id.checkBox4);
        ch4 = (CheckBox)findViewById(R.id.checkBox5);
        ch5 = (CheckBox)findViewById(R.id.checkBox6);
        ch6 = (CheckBox)findViewById(R.id.checkBox7);
        ch7 = (CheckBox)findViewById(R.id.checkBox8);
        ch8 = (CheckBox)findViewById(R.id.checkBox9);
        ch9 = (CheckBox)findViewById(R.id.checkBox10);
        ch10 = (CheckBox)findViewById(R.id.checkBox11);
        btn = findViewById(R.id.addButton);
        context = this;
        /*ch1.setOnCheckedChangeListener(this);
        ch2.setOnCheckedChangeListener(this);
        ch3.setOnCheckedChangeListener(this);
        ch4.setOnCheckedChangeListener(this);
        ch5.setOnCheckedChangeListener(this);
        ch6.setOnCheckedChangeListener(this);
        ch7.setOnCheckedChangeListener(this);
        ch8.setOnCheckedChangeListener(this);
        ch9.setOnCheckedChangeListener(this);
        ch10.setOnCheckedChangeListener(this);*/


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(ch1.isChecked()){
                    if(!placeList.contains((String)ch1.getText())) {
                        placeList.add((String) ch1.getText());
                    }
                }
                if(ch2.isChecked()){
                    if(!placeList.contains((String)ch2.getText())) {
                        placeList.add((String) ch2.getText());
                    }
                }
                if(ch3.isChecked()){
                    if(!placeList.contains((String)ch3.getText())) {
                        placeList.add((String) ch3.getText());
                    }
                }
                if(ch4.isChecked()){
                    if(!placeList.contains((String)ch4.getText())) {
                        placeList.add((String) ch4.getText());
                    }
                }
                if(ch5.isChecked()){
                    if(!placeList.contains((String)ch5.getText())) {
                        placeList.add((String) ch5.getText());
                    }
                }
                if(ch6.isChecked()){
                    if(!placeList.contains((String)ch6.getText())) {
                        placeList.add((String) ch6.getText());
                    }
                }
                if(ch7.isChecked()){
                    if(!placeList.contains((String)ch7.getText())) {
                        placeList.add((String) ch7.getText());
                    }
                }
                if(ch8.isChecked()){
                    if(!placeList.contains((String)ch8.getText())) {
                        placeList.add((String) ch8.getText());
                    }
                }
                if(ch9.isChecked()){
                    if(!placeList.contains((String)ch9.getText())) {
                        placeList.add((String) ch9.getText());
                    }
                }
                if(ch10.isChecked()){
                    if(!placeList.contains((String)ch10.getText())) {
                        placeList.add((String) ch10.getText());
                    }
                }
                Button btn[] = new Button[placeList.size()];
                int i=0;

                for(String place : placeList){

                    if(cnt==0) {
                        //for(int i=0;i<btn.length;i++) {
                        //if(placeList.contains(place))
                        btn[i] = new Button(context);
                        btn[i].setText(place);
                        btn[i].setWidth(66);
                        btn[i].setTextSize(11);
                        btn[i].setId(i);
                        linearLayout.addView(btn[i]);

/*                        btn[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });*/
                        i++;
                    }else{
                        linearLayout.removeAllViewsInLayout();
                        btn[i] = new Button(context);
                        btn[i].setText(place);
                        btn[i].setWidth(66);
                        btn[i].setTextSize(11);
                        btn[i].setId(i);
                        linearLayout.addView(btn[i]);

/*                        btn[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });*/
                        i++;
                    }
                    //}
                }

            }
        });


    }
    /*@Override
    public void onCheckedChanged(CompoundButton button, boolean isChecked){

        if(ch1.isChecked()){
            placeList.add((String)ch1.getText());
        }
        if(ch2.isChecked()){
            placeList.add((String)ch2.getText());
        }
        if(ch3.isChecked()){
            placeList.add((String)ch3.getText());
        }
        if(ch4.isChecked()){
            placeList.add((String)ch4.getText());
        }
        if(ch5.isChecked()){
            placeList.add((String)ch5.getText());
        }
        if(ch6.isChecked()){
            placeList.add((String)ch6.getText());
        }
        if(ch7.isChecked()){
            placeList.add((String)ch7.getText());
        }
        if(ch8.isChecked()){
            placeList.add((String)ch8.getText());
        }
        if(ch9.isChecked()){
            placeList.add((String)ch9.getText());
        }
        if(ch10.isChecked()){
            placeList.add((String)ch10.getText());
        }
        for(String place : placeList){

            Button btn[] = new Button[placeList.size()];
            for(int i=0;i<btn.length;i++) {
                btn[i] = new Button(this);
                btn[i].setText(place);
                btn[i].setWidth(66);
                btn[i].setTextSize(11);
                btn[i].setId(i);
                linearLayout.addView(btn[i]);

                btn[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });

            }
        }

    }
*/



}