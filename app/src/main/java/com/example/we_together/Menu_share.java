package com.example.we_together;

import android.graphics.Color;
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
import android.widget.TextView;

public class Menu_share extends Fragment implements View.OnClickListener{

    TextView day1,day2,day3,day4,day5,day6,day7;
    View rootView;
    TextView save_text;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.activity_menu_share, container, false);
        day1 = rootView.findViewById(R.id.day1);
        day2 = rootView.findViewById(R.id.day2);
        day3 = rootView.findViewById(R.id.day3);
        day4 = rootView.findViewById(R.id.day4);
        day5 = rootView.findViewById(R.id.day5);
        day6 = rootView.findViewById(R.id.day6);
        day7 = rootView.findViewById(R.id.day7);

        findView(day1); findView(day2); findView(day3); findView(day4);
        findView(day5); findView(day6); findView(day7);

        save_text=day1;

        return rootView;
    }

    public void findView(TextView t){
        t.setOnClickListener(this);
    }


    public void change_color(TextView a){
        save_text.setTextColor(Color.parseColor("#9b9b9b"));
        save_text.setTextSize(23);
        save_text = a;
        a.setTextSize(28);
        a.setTextColor(Color.parseColor("#2f4c9b"));
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.day1:
                change_color(day1);
                break;

            case R.id.day2:
                change_color(day2);
                break;

            case R.id.day3:
                change_color(day3);
                break;

            case R.id.day4:
                change_color(day4);
                break;

            case R.id.day5:
                change_color(day5);
                break;

            case R.id.day6:
                change_color(day6);
                break;

            case R.id.day7:
                change_color(day7);
                break;
        }
    }
}
