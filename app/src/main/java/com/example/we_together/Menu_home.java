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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Menu_home extends Fragment {
    LinearLayout linearLayout;
    public ArrayList<String> placeList = new ArrayList<>();
    Context context;
    int cnt=0;

    String Day = "";
    private ListView listView;
    private ArrayAdapter<String> adapter;
    List<Object> Array = new ArrayList<>();
    String caldate;
    private ListView listView2;
    private ArrayAdapter<String> adapter2;
    List<Object> Array2 = new ArrayList<>();


    SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.getDefault());
    Calendar calendar = Calendar.getInstance();
    String weekDay = dayFormat.format(calendar.getTime());

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private ChildEventListener mChild;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_menu_home,container,false);


        listView = v.findViewById(R.id.listviewmsg);
        TextView tv = v.findViewById(R.id.today_tv);

        listView2 =v.findViewById(R.id.lv2);

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        date.setDate(date.getDate());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
        String getTime = sdf.format(date);
        tv.setText(getTime);

        SimpleDateFormat s1 = new SimpleDateFormat("M");
        SimpleDateFormat s2 = new SimpleDateFormat("d");
        String a1 = s1.format(date);
        String a2 = s2.format(date);

        caldate = a1+a2; //이 부분 원래 String.valueOf(date.getDay()) 이거 였는데 16이 프린트 안되고 2가 프린트 됨 ㅠ
        Log.v("check",caldate);

        //
        initDatabase();


        linearLayout = v.findViewById(R.id.linear1);
        context = this.getContext();
        SharedPreferences pref=context.getSharedPreferences("SAVE", context.MODE_PRIVATE);
        String ccode = pref.getString("invitecode","");



        adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line,new ArrayList<String>());
        listView.setAdapter(adapter);

        adapter2 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line,new ArrayList<String>());
        listView2.setAdapter(adapter2);

        Log.e("weekDay",weekDay);

        switch(weekDay) {
            case "Monday": case "월요일":Day = "mon"; break;
            case "Tuesday":case "화요일":Day ="tue";break;
            case "Wednesday":case "수요일":Day = "wed";break;
            case "Thursday":case "목요일":Day = "thu";break;
            case "Friday":case "금요일":Day = "fri";break;
            case "Saturday":case "토요일":Day = "sat"; break;
            case "Sunday": case "일요일":Day = "sun"; break;

            default:Day = weekDay;
        }



       /* mReference = mDatabase.getReference("room");
        mReference.child(ccode).child(Day).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapter.clear();
                for (DataSnapshot messageData : dataSnapshot.getChildren()) {
                    String msg2 = messageData.getValue().toString();
                    Array.add(msg2);
                    adapter.add(msg2);
                }
                adapter.notifyDataSetChanged();
                listView.setSelection(adapter.getCount() - 1);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
*/


        mReference = mDatabase.getReference("room");
        mReference.child(ccode).child("place").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //adapter.clear();

                placeList.clear();

                for (DataSnapshot messageData : dataSnapshot.getChildren()) {
                    String msg2 = messageData.getKey();
                    placeList.add(msg2);
                    Log.e("msg2",msg2);
                }

                fun();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





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

        mReference = mDatabase.getReference("room");
        mReference.child(ccode).child("event").child(caldate).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapter2.clear();
                for (DataSnapshot messageData : dataSnapshot.getChildren()) {
                    String msg2 = messageData.getValue().toString();
                    Array2.add(msg2);
                    adapter2.add(msg2);
                }
                adapter2.notifyDataSetChanged();
                listView2.setSelection(adapter2.getCount() - 1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        return v;
    }


    private void fun(){
        linearLayout.removeAllViewsInLayout();
        final Button btn[] = new Button[placeList.size()+1];
        btn[0] = new Button(context);
        btn[0].setText("모두");
        btn[0].setWidth(66);
        btn[0].setTextSize(11);
        btn[0].setId(0);

        btn[0].setBackgroundResource(R.drawable.cal_button);

        linearLayout.addView(btn[0]);
        btn[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn[0].setBackgroundResource(R.drawable.radius_button);
                for(int j=0;j<placeList.size()+1;j++){
                    if(j==0){
                        btn[0].setBackgroundResource(R.drawable.radius_button);
                    }
                    else{
                        btn[j].setBackgroundResource(R.drawable.cal_button);
                    }
                }
                SharedPreferences pref=context.getSharedPreferences("SAVE", context.MODE_PRIVATE);
                String ccode = pref.getString("invitecode","");

                mReference = mDatabase.getReference("room");
                mReference.child(ccode).child("day").child(Day).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        adapter.clear();
                        for (DataSnapshot messageData : dataSnapshot.getChildren()) {
                            String msg2 = messageData.getValue().toString();
                            String[] values = msg2.split("\\s");
                            String v2 = values[0]+"  /  "+values[3]+" "+values[2];
                            Array.add(v2);
                            adapter.add(v2);
                        }
                        adapter.notifyDataSetChanged();
                        listView.setSelection(adapter.getCount() - 1);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });

        int i=1;
        Log.e("size",String.valueOf(placeList.size()));
        if(i<=placeList.size()) {

            for(final String place : placeList){


                btn[i] = new Button(context);
                btn[i].setText(place);
                btn[i].setWidth(66);
                btn[i].setTextSize(11);
                btn[i].setId(i);
                btn[i].setBackgroundResource(R.drawable.cal_button);
                linearLayout.addView(btn[i]);


                final int finalI = i;
                btn[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        btn[finalI].setBackgroundResource(R.drawable.radius_button);
                        for(int j=0;j<placeList.size()+1;j++){
                            if(j==finalI){
                                btn[finalI].setBackgroundResource(R.drawable.radius_button);
                            }
                            else{
                                btn[j].setBackgroundResource(R.drawable.cal_button);
                            }
                        }

                        Button btn2= (Button)view;
                        Log.d("버튼튼","버튼 누름 : "+btn2);

                        SharedPreferences pref=context.getSharedPreferences("SAVE", context.MODE_PRIVATE);
                        String ccode2 = pref.getString("invitecode","");

                        Log.e("e",ccode2+" "+Day);
                        mReference = mDatabase.getReference("room");
                        mReference.child(ccode2).child("day").child(Day).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                adapter.clear();
                                Array.clear();
                                for (DataSnapshot messageData : dataSnapshot.getChildren()) {
                                    String msg2 = messageData.getValue().toString();


                                    String[] values = msg2.split("\\s");

                                    Log.e("a",values[1]);
                                    if(values[1].equals(place)){
                                        String v2 = values[0]+"  /  "+values[3]+" "+values[2];
                                        Array.add(v2);
                                        adapter.add(v2);
                                    }
                                }
                                adapter.notifyDataSetChanged();
                                listView.setSelection(adapter.getCount() - 1);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                    }
                });
                i++;
            }
            cnt++;
        }
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





    private void initDatabase() {

        mDatabase = FirebaseDatabase.getInstance();

        mReference = mDatabase.getReference("log");
        mReference.child("log").setValue("check");

        mChild = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mReference.addChildEventListener(mChild);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mReference.removeEventListener(mChild);
    }

}