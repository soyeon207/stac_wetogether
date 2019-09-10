package com.example.we_together;



import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class placeActivity extends AppCompatActivity /*implements CompoundButton.OnCheckedChangeListener*/{
    private CheckBox ch1,ch2,ch3,ch4,ch5,ch6,ch7,ch8,ch9,ch10;
    private Button btn,goStart;
    int cnt;
    LinearLayout linearLayout;
    public ArrayList<String> placeList = new ArrayList<>();
    Context context;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();


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
        cnt=0;
        goStart = findViewById(R.id.goStart);
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
                if(cnt==0) {
                    int i=0;
                    for(String place : placeList){


                        //for(int i=0;i<btn.length;i++) {
                        //if(placeList.contains(place))
                        btn[i] = new Button(context);
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
                        i++;
                    }
                    cnt=1;
                }
                else{
                    int i=0;
                    linearLayout.removeAllViewsInLayout();
                    for(String place : placeList){



                        btn[i] = new Button(context);
                        btn[i].setText(place);
                        btn[i].setWidth(66);
                        btn[i].setTextSize(11);
                        btn[i].setId(i);
                        linearLayout.addView(btn[i]);


                        i++;
                    }



                }

/*                for(String place : placeList){

                   if(cnt==0) {
                       //for(int i=0;i<btn.length;i++) {
                       //if(placeList.contains(place))
                       btn[i] = new Button(context);
                       btn[i].setText(place);
                       btn[i].setWidth(66);
                       btn[i].setTextSize(11);
                       btn[i].setId(i);
                       linearLayout.addView(btn[i]);


*//*                        btn[i].setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {

                           }
                       });*//*
                       i++;
                   }else{
                       linearLayout.removeAllViewsInLayout();
                       btn[i] = new Button(context);
                       btn[i].setText(place);
                       btn[i].setWidth(66);
                       btn[i].setTextSize(11);
                       btn[i].setId(i);
                       linearLayout.addView(btn[i]);

*//*                        btn[i].setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {

                           }
                       });*//*
                       i++;
                   }
                   //}
               }
               cnt=1;*/

            }
        });


        goStart.setOnClickListener(new View.OnClickListener() {
            SharedPreferences pref=getSharedPreferences("SAVE", MODE_PRIVATE);
            String ccode = pref.getString("invitecode","");

            @Override
            public void onClick(View v) {
                setStringArrayPref(context, "pp" , placeList);

                ArrayList<String> list = getStringArrayPref(context,"pp");
                if (list != null) {
                    for (String value : list) {
                        Log.d("ㅇㅇㅇㅇ","Get json : " + value);
                        databaseReference.child("room").child(ccode).child("place").push().setValue(value);
                    }
                }

                startActivity(new Intent(placeActivity.this, MainActivity.class));
                finish();
            }
        });
    }


    private void setStringArrayPref(Context context, String key, ArrayList<String> values){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        JSONArray a = new JSONArray();
        for (int i = 0; i < values.size(); i++) {
            a.put(values.get(i));
        }
        if (!values.isEmpty()) {
            editor.putString(key, a.toString());
        } else {
            editor.putString(key, null);
        }
        editor.apply();
    }
    private ArrayList<String> getStringArrayPref(Context context, String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String json = prefs.getString(key, null);
        ArrayList<String> urls = new ArrayList<String>();
        if (json != null) {
            try {
                JSONArray a = new JSONArray(json);
                for (int i = 0; i < a.length(); i++) {
                    String url = a.optString(i);
                    urls.add(url);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return urls;
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

