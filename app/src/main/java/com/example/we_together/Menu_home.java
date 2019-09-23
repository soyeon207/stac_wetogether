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
        SimpleDateFormat s2 = new SimpleDateFormat("dd");
        String a1 = s1.format(date);
        String a2 = s2.format(date);

        String caldate = a1+a2; //이 부분 원래 String.valueOf(date.getDay()) 이거 였는데 16이 프린트 안되고 2가 프린트 됨 ㅠ
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

        switch(weekDay) {
            case "Monday":Day = "mon"; break;
            case "Tuesday":Day ="tue";break;
            case "Wednesday":Day = "wed";break;
            case "Thursday":Day = "thu";break;
            case "Friday":Day = "fri";break;
            case "Saturday":Day = "sat"; break;
            case "Sunday": Day = "sun"; break;
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
        ArrayList<String> list = getStringArrayPref(context,"pp");

        if (list != null) {
            placeList.clear();
            for (String value : list) {
                Log.d("ㅇㅇㅇㅇ","Get json : " + value);

                placeList.add(value);


                mReference = mDatabase.getReference("room");
                mReference.child(ccode).child("day").child(Day).child(Day).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //adapter.clear();
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


            }
        }

        linearLayout.removeAllViewsInLayout();
        Button btn[] = new Button[placeList.size()+1];
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
                Log.d("버튼","모두 버튼 누름 : " + 0);
            }
        });

        int i=1;
        if(i<=placeList.size()) {

            for(final String place : placeList){


                btn[i] = new Button(context);
                btn[i].setText(place);
                btn[i].setWidth(66);
                btn[i].setTextSize(11);
                btn[i].setId(i);
                btn[i].setBackgroundResource(R.drawable.cal_button);
                linearLayout.addView(btn[i]);


                btn[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Button btn2= (Button)view;
                        Log.d("버튼튼","버튼 누름 : "+btn2);

                        SharedPreferences pref=context.getSharedPreferences("SAVE", context.MODE_PRIVATE);
                        String ccode = pref.getString("invitecode","");


                        mReference = mDatabase.getReference("room");
                        mReference.child(ccode).child("place").child(place).child(Day).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                adapter.clear();
                                Array.clear();
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
                listView.setSelection(adapter2.getCount() - 1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



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
