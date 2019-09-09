package com.example.we_together;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView header_faimly_txt,header_name_txt,header_invitecode_txt;
    FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //////////////activity_bottom_nav//////////////////////////////
        BottomNavigationView bottom_nav = findViewById(R.id.bottom_nav);
        BottomNavigationHelper.disableShiftMode(bottom_nav);
        bottom_nav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //////////////activity_bottom_nav//////////////////////////////

        firebaseAuth = firebaseAuth.getInstance();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        //nav_header_main setText 바꾸기
        View nav_header_view = navigationView.getHeaderView(0);

        header_faimly_txt = ((View) nav_header_view).findViewById(R.id.header_family);
        header_invitecode_txt = ((View) nav_header_view).findViewById(R.id.header_invitecode);
        header_name_txt = ((View) nav_header_view).findViewById(R.id.header_name);


        SharedPreferences preferences = getSharedPreferences("SAVE",MODE_PRIVATE);
        header_invitecode_txt.setText("초대코드 : "+preferences.getString("invitecode",""));
        header_faimly_txt.setText(preferences.getString("room",""));
        header_name_txt.setText(preferences.getString("name",""));

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.drawer_calendar) {
            // Handle the camera action
        } else if (id == R.id.drawer_invitecode) {

        } else if (id == R.id.drawer_community) {

        } else if (id == R.id.drawer_setting) {

        } else if (id == R.id.drawer_logout) {
            SharedPreferences pref2 = getSharedPreferences("SAVE", MODE_PRIVATE);
            SharedPreferences.Editor editor2 = pref2.edit();
            editor2.clear();
            editor2.commit(); // SharedPreferences 의 데이터 모두 삭제

            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this,Login.class));
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.bottom_home:
                    return true;
                case R.id.bottom_share:
                    return true;
                case R.id.bottom_cal:
                    return true;

                case R.id.bottom_com:
                    return true;
            }
            return false;
        }
    };

}
