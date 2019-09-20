package com.example.we_together;

import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.flags.impl.SharedPreferencesFactory;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Menu_share extends Fragment implements View.OnClickListener{

    private TextView day1,day2,day3,day4,day5,day6,day7;
    private View rootView;
    private TextView save_text;
    private ListView listView;

    List<String> Array = new ArrayList<String>();

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

    public void list(String date){
        FirebaseDatabase mdatabase = FirebaseDatabase.getInstance();
        DatabaseReference mdatabaseRef = mdatabase.getReference();

        Context context = getActivity();
        SharedPreferences sp = context.getSharedPreferences( "SAVE", Context.MODE_PRIVATE);

        ArrayList<String> midList = new ArrayList<String>();
        listView = rootView.findViewById(R.id.listview);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line,midList);
        listView.setAdapter(adapter);

        mdatabaseRef.child("room").child(sp.getString("invitecode","")).child("day").child(date).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapter.clear();
                for(DataSnapshot message:dataSnapshot.getChildren()){

                    String value = message.getValue().toString();
                    String[] words = value.split("\\s");



                    Array.add(value);
                    adapter.add(value);
                }
                adapter.notifyDataSetChanged();
                listView.setSelection(adapter.getCount()-1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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
                list("mon");
                change_color(day1);
                break;

            case R.id.day2:
                list("tue");
                change_color(day2);
                break;

            case R.id.day3:
                list("wed");
                change_color(day3);
                break;

            case R.id.day4:
                list("thu");
                change_color(day4);
                break;

            case R.id.day5:
                list("fri");
                change_color(day5);
                break;

            case R.id.day6:
                list("sat");
                change_color(day6);
                break;

            case R.id.day7:
                list("sun");
                change_color(day7);
                break;
        }
    }
}
