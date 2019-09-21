package com.example.we_together;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class Menu_calendar extends Fragment {

    private MaterialCalendarView materialCalendarView;
    private TextView text_cal;
    private ListView listView;

    private ImageView cal_img;
    private ArrayList<String> event_arr = new ArrayList<String>();
    private SharedPreferences pref;
    private View rootView;
    private String day,month;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private List<String> Array = new ArrayList<String>();
    private event_dialog event_dialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.activity_menu_calendar, container, false);

        materialCalendarView = rootView.findViewById(R.id.cal);
        text_cal = rootView.findViewById(R.id.text_cal);
        cal_img = rootView.findViewById(R.id.cal_img);

        cal_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                event_dialog = new event_dialog(getContext(),(month+day));
                event_dialog.show();
            }
        });

        Context context = getContext();
        pref=context.getSharedPreferences("SAVE", context.MODE_PRIVATE);
        calendar_dot();

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {


                text_cal.setText(String.valueOf((date.getMonth()+1)+"월 "+date.getDay()+"일 "+"가족 이벤트"));

                month = String.valueOf((date.getMonth()+1));
                day = String.valueOf(date.getDay());


///////////////////////////////////////////////////////////////////
                ArrayList<String> midList = new ArrayList<String>();
                listView = rootView.findViewById(R.id.list_cal);

                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line,midList);
                listView.setAdapter(adapter);

                String date2 = month+day;
                databaseReference.child("room").child(pref.getString("invitecode","")).child("event").child(date2).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        adapter.clear();
                        for(DataSnapshot message:dataSnapshot.getChildren()){
                            String value = message.getValue().toString();
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
        });

        return rootView;
    }

    public void calendar_dot() {
        databaseReference.child("room").child(pref.getString("invitecode","")).child("event").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot messageData : dataSnapshot.getChildren()) {
                    String event_data = messageData.getKey().toString();
                    event_arr.add(event_data);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

        new ApiSimulator().executeOnExecutor(Executors.newSingleThreadExecutor());
    }

    private class ApiSimulator extends AsyncTask<Void, Void, List<CalendarDay>> {

        @Override
        protected List<CalendarDay> doInBackground(@NonNull Void... voids) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            final ArrayList<CalendarDay> dates = new ArrayList<>();

            for(int ed=0;ed<event_arr.size();ed++) {

                final CalendarDay day = CalendarDay.from(2019,Integer.parseInt(event_arr.get(ed).substring(0,1))-1,Integer.parseInt(event_arr.get(ed).substring(1)));
                dates.add(day);
            }

            return dates;
        }

        @Override
        protected void onPostExecute(@NonNull List<CalendarDay> calendarDays) {
            super.onPostExecute(calendarDays);

            materialCalendarView.addDecorator(new EventDecorator(Color.parseColor("#2f4c9b"), calendarDays));
        }
    }
}
