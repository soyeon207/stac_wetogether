package com.example.we_together;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class Menu_home extends Fragment {
    LinearLayout linearLayout;
    public ArrayList<String> placeList = new ArrayList<>();
    Context context;
    int cnt=0;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_menu_home,container,false);


        linearLayout = v.findViewById(R.id.linear1);
        context = this.getContext();


        ArrayList<String> list = getStringArrayPref(context,"pp");

        if (list != null) {
            placeList.clear();
            for (String value : list) {
                Log.d("ㅇㅇㅇㅇ","Get json : " + value);

                placeList.add(value);
            }
        }

        linearLayout.removeAllViewsInLayout();
        Button btn[] = new Button[placeList.size()+1];
        btn[0] = new Button(context);
        btn[0].setText("모두");
        btn[0].setWidth(66);
        btn[0].setTextSize(11);
        btn[0].setId(0);
        linearLayout.addView(btn[0]);
        btn[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("버튼","모두 버튼 누름 : " + 0);
            }
        });

        int i=1;
        if(i<=placeList.size()) {

            for(String place : placeList){


                btn[i] = new Button(context);
                btn[i].setText(place);
                btn[i].setWidth(66);
                btn[i].setTextSize(11);
                btn[i].setId(i);
                linearLayout.addView(btn[i]);


                btn[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Button btn2= (Button)view;
                        Log.d("버튼튼","버튼 누름 : "+btn2);
                    }
                });
                i++;
            }
            cnt++;
        }
        //else{
/*
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
*/


        //}


/*
        btn[i].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("버튼튼","버튼 누름 : "+i );
            }
        });
*/




        return v;
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
}
